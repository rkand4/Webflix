package log660.dao;

import log660.HibernateUtil;
import log660.model.*;
import log660.utils.BCrypt;
import log660.utils.WebFlixValidationException;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.*;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Alex on 2014-06-02.
 */
public class SystemDAO {

    public Client getClientFromCredentials(String courriel, String motPasse){
        Session session = HibernateUtil.getSessionFactory().openSession();

        Query query = session.createQuery("from Client c join fetch c.forfait where c.courriel = :courriel");

        query.setParameter("courriel", courriel);
        query.setMaxResults(1);

        List results = query.list();

        session.close();

        Client user = null;
        if(!results.isEmpty()){
            user = (Client) results.get(0);

            //Validate Password
            if(!BCrypt.checkpw(motPasse, user.getMotPasse()))
                user = null;

        }



        return user;
    }

    public Film getFilm(Integer idFilm){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery("from Film f join fetch f.genres join fetch f.realisateur join fetch f.scenaristes  where f.idFilm = :idFilm ");
        query.setParameter("idFilm", idFilm);
        query.setMaxResults(1);

        List results = query.list();

        session.close();

        Film film = null;
        if(!results.isEmpty()){
            film = (Film) results.get(0);
        }



        return film;
    }

    public List<Genre> getGenresComboBox(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery("from Genre");

        List results = query.list();
        session.close();
        return results;
    }

    public List<Pays> getPaysComboBox(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery("from Pays");

        List results = query.list();
        session.close();
        return results;
    }

    public List<String> getLanguesComboBox(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery("select distinct f.langueOriginal from Film f order by f.langueOriginal");

        List results = query.list();
        session.close();
        return results;
    }

    public List<Copie> getAvailableCopies(Integer idFilm){
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Copie> liste = getAvailableCopies(session, idFilm);
        session.close();
        return liste;
    }


    public List<Copie> getAvailableCopies(Session session, Integer idFilm){
        Query query = session.createQuery("select distinct c from Copie c join c.film f left join c.locations l where f.idFilm = :idFilm and (l = null or l.dateRetour != null)");
        query.setParameter("idFilm", idFilm);

        List results = query.list();
        return results;
    }

    public List<Location> getNonReturnedLocations(int idClient){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery("from Location l join l.client c where l.dateRetour = null and c.idUtilisateur = :idClient");
        query.setParameter("idClient", idClient);

        List results = query.list();
        session.close();
        return results;
    }

    public Copie rentCopie(int idFilm, Client client) throws WebFlixValidationException{
        Session session = null;
        Transaction tx = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            List<Copie> copies = getAvailableCopies(session, idFilm);

            if (copies.size() > 0) {
                Calendar cal = Calendar.getInstance();

                Date now = cal.getTime();

                Date then = null;

                if (client.getForfait().getDureeMax() != null) {
                    cal.add(Calendar.DAY_OF_MONTH, client.getForfait().getDureeMax());
                    then = cal.getTime();
                }

                Copie c = copies.get(0);

                Location location = new Location();
                location.setCopie(c);
                location.setClient(client);
                location.setDateLouee(now);
                location.setDateRetourPrevu(then);

                session.save(location);
                tx.commit();

                return c;
            }
        } catch(Exception e) {
            System.err.println(e);
        } finally{
            if(tx != null){
                if(!tx.wasCommitted()) {
                    System.err.println("NOT COMMITED!");
                    tx.rollback();
                }
            }
            if(session != null) {
                session.flush();
                session.close();
            }
        }

        return null;
    }

    public List<Film> showNewMovies()
    {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Criteria search = session.createCriteria(Film.class);
        search.addOrder(Order.desc("idFilm"));
        search.setMaxResults(3);
        List<Film> films = search.list();
        session.close();
        return films;
    }

    public List<Film> search(String actorName,String country,String directorName,String filmTitle, String language,String yearEnd, String yearStart, String genres[]){
        Session session = HibernateUtil.getSessionFactory().openSession();

        Criteria search = session.createCriteria(Film.class);
        if (!"".equals(filmTitle)) {
            search.add(Restrictions.ilike("titre", filmTitle, MatchMode.ANYWHERE));
        }
        if (!"".equals(yearStart)) {
            try {
                int year = Integer.parseInt(yearStart);
                search.add(Restrictions.ge("anneeSortie", year));
            }
            catch(NumberFormatException ex){
            }
        }
        if (!"".equals(yearEnd)) {
            try {
                int year = Integer.parseInt(yearEnd);
                search.add(Restrictions.le("anneeSortie", year));
            }
            catch(NumberFormatException ex){
            }
        }
        if (!"".equals(language)) {
            search.add(Restrictions.ilike("langueOriginal", language));
        }
        if (!"".equals(country)) {
            search.createCriteria("pays").add(Restrictions.like("nom", country));
        }
        if (genres != null && genres.length > 0) {

            Conjunction genreConjunction = Restrictions.conjunction();
            int i = 0;

            for (String name : genres){
                DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Film.class).setProjection(Projections.id());
                String alias = "Genre".concat(Integer.toString(i));
                detachedCriteria.createAlias("genres", alias);
                detachedCriteria.add(Restrictions.ilike(alias.concat(".titre"), name.trim()));
                genreConjunction.add(Property.forName("id").in(detachedCriteria));
                i++;
            }

            search.add(genreConjunction);
        }
        if (!"".equals(directorName)) {
            search.createCriteria("realisateur").add(Restrictions.ilike("nom", directorName, MatchMode.ANYWHERE));
        }
        if (!"".equals(actorName)) {
            String[] actors = actorName.split(",");

            Conjunction conjunction = Restrictions.conjunction();

            for(String name : actors){
                DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Film.class).setProjection(Projections.id());
                detachedCriteria.createCriteria("roles").createCriteria("acteur")
                        .add(Restrictions.ilike("nom", name.trim(),MatchMode.ANYWHERE));
                conjunction.add(Property.forName("id").in(detachedCriteria));
            }

            search.add(conjunction);
        }

        List list = search.list();
        session.close();
        return list;
    }
}
