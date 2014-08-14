package log660.model;

import java.util.Date;

/**
 * Created by olivier on 29/05/14.
 */

public class Personne {

    private Integer idPersonne;
    private String nom;
    private Date anniversaire;
    private String lieuNaissance;
    private String biographie;

    public Integer getIdPersonne() {
        return idPersonne;
    }

    public void setIdPersonne(Integer idPersonne) {
        this.idPersonne = idPersonne;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Date getAnniversaire() {
        return anniversaire;
    }

    public void setAnniversaire(Date anniversaire) {
        this.anniversaire = anniversaire;
    }

    public String getLieuNaissance() {
        return lieuNaissance;
    }

    public void setLieuNaissance(String lieuNaissance) {
        this.lieuNaissance = lieuNaissance;
    }

    public String getBiographie() {
        return biographie;
    }

    public void setBiographie(String biographie) {
        this.biographie = biographie;
    }
}