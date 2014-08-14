package log660.model;

import java.util.Set;

/**
 * Created by Olivier on 29/05/14.
 */

public class Copie {
	
    private Integer idCopie;
    private Film film;
    private String codeCopie;
    private Set<Location> locations;

    public Integer getIdCopie() {
        return idCopie;
    }

    public void setIdCopie(Integer idCopie) {
        this.idCopie = idCopie;
    }

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    public String getCodeCopie() {
        return codeCopie;
    }

    public void setCodeCopie(String codeCopie) {
        this.codeCopie = codeCopie;
    }

    public Set<Location> getLocations() {
        return locations;
    }

    public void setLocations(Set<Location> locations) {
        this.locations = locations;
    }
}