package log660.model;

import java.util.Date;

/**
 * Created by Olivier on 29/05/14.
 */

public class Location {

    private Integer idLocation;
    private Client client;
    private Copie copie;
    private Date dateLouee;
    private Date dateRetourPrevu;
    private Date dateRetour;

    public Integer getIdLocation() {
        return idLocation;
    }

    public void setIdLocation(Integer idLocation) {
        this.idLocation = idLocation;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Copie getCopie() {
        return copie;
    }

    public void setCopie(Copie copie) {
        this.copie = copie;
    }

    public Date getDateLouee() {
        return dateLouee;
    }

    public void setDateLouee(Date dateLouee) {
        this.dateLouee = dateLouee;
    }

    public Date getDateRetourPrevu() {
        return dateRetourPrevu;
    }

    public void setDateRetourPrevu(Date dateRetourPrevu) {
        this.dateRetourPrevu = dateRetourPrevu;
    }

    public Date getDateRetour() {
        return dateRetour;
    }

    public void setDateRetour(Date dateRetour) {
        this.dateRetour = dateRetour;
    }
}