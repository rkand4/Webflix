package log660.model;

/**
 * Created by Francois on 2014-06-01.
 */

public class Client extends Utilisateur{

	private CarteCredit carteCredit;
	private Forfait forfait;

    public CarteCredit getCarteCredit() {
        return carteCredit;
    }
    
    public void setCarteCredit(CarteCredit carteCredit) {
        this.carteCredit = carteCredit;
    }
    
    public Forfait getForfait() {
        return forfait;
    }
    
    public void setForfait(Forfait forfait) {
        this.forfait = forfait;
    }
}