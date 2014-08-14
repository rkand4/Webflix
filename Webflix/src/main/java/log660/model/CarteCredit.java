package log660.model;

import java.util.Date;

/**
 * Created by Olivier on 29/05/14.
 */

public class CarteCredit {

    private Integer idCarte;
    private String typeCarte;
    private String numero;
    private Date expiration;
    private String cvv;

    public Integer getIdCarte() {
        return idCarte;
    }

    public void setIdCarte(Integer idCarte) {
        this.idCarte = idCarte;
    }

    public String getTypeCarte() {
        return typeCarte;
    }

    public void setTypeCarte(String typeCarte) {
        this.typeCarte = typeCarte;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Date getExpiration() {
        return expiration;
    }

    public void setExpiration(Date expiration) {
        this.expiration = expiration;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }
}