package log660.model;

/**
 * Created by Francois on 2014-06-01.
 */

public class Role {
	
	private Integer idRole;
	private Film film;
	private Personne acteur;
	private String personnage;
	
	public Integer getIdRole() {
        return idRole;
    }
    
    public void setIdRole(Integer idRole) {
        this.idRole = idRole;
    }
    
    public Film getFilm() {
        return film;
    }
    
    public void setFilm(Film film) {
        this.film = film;
    }
    
    public Personne getActeur() {
        return acteur;
    }
    
    public void setActeur(Personne acteur) {
        this.acteur = acteur;
    }
    
    public String getPersonnage() {
        return personnage;
    }
    
    public void setPersonnage(String personnage) {
        this.personnage = personnage;
    }
}