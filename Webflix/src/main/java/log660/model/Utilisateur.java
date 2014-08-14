package log660.model;

import java.util.Date;

/**
 * Created by Olivier on 29/05/14.
 */

public abstract class Utilisateur {

    protected Integer idUtilisateur;
    protected Adresse adresse;
    protected String nom;
    protected String telephone;
    protected String courriel;
    protected String motPasse;
    protected Date anniversaire;

    public Integer getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(Integer idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    public Adresse getAdresse() {
        return adresse;
    }

    public void setAdresse(Adresse adresse) {
        this.adresse = adresse;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getCourriel() {
        return courriel;
    }

    public void setCourriel(String courriel) {
        this.courriel = courriel;
    }

    public String getMotPasse() {
        return motPasse;
    }

    public void setMotPasse(String motPasse) {
        this.motPasse = motPasse;
    }

    public Date getAnniversaire() {
        return anniversaire;
    }

    public void setAnniversaire(Date anniversaire) {
        this.anniversaire = anniversaire;
    }
}