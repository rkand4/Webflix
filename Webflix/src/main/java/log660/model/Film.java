package log660.model;

import java.util.Set;
import java.util.HashSet;

/**
 * Created by Alex on 2014-05-28.
 */

public class Film {

    private Integer idFilm;
    private Personne realisateur;
    private String titre;
    private Integer anneeSortie;
    private String langueOriginal;
    private Integer duree;
    private String resume;
    private String poster;
    private Set<Genre> genres;
    private Set<Personne> scenaristes;
    private Set<Pays> pays;
    private Set<Role> roles;

    public Integer getIdFilm() {
        return idFilm;
    }

    public void setIdFilm(Integer idFilm) {
        this.idFilm = idFilm;
    }

    public Personne getRealisateur() {
        return realisateur;
    }

    public void setRealisateur(Personne realisateur) {
        this.realisateur = realisateur;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public Integer getAnneeSortie() {
        return anneeSortie;
    }

    public void setAnneeSortie(Integer anneeSortie) {
        this.anneeSortie = anneeSortie;
    }

    public String getLangueOriginal() {
        return langueOriginal;
    }

    public void setLangueOriginal(String langueOriginal) {
        this.langueOriginal = langueOriginal;
    }

    public Integer getDuree() {
        return duree;
    }

    public void setDuree(Integer duree) {
        this.duree = duree;
    }

    public String getResume() {
        return resume;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public Set<Genre> getGenres() {
        return genres;
    }

    public void setGenres(Set genres) {
        this.genres = genres;
    }
    
    public Set<Personne> getScenaristes() {
        return scenaristes;
    }

    public void setScenaristes(Set<Personne> scenaristes) {
        this.scenaristes = scenaristes;
    }

    public Set<Pays> getPays() { return pays; }

    public void setPays(Set<Pays> pays) { this.pays = pays; }

    public Set<Role> getRoles() { return roles; }

    public void setRoles(Set<Role> roles) { this.roles = roles; }
}