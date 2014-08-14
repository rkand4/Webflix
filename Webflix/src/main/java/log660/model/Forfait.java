package log660.model;

/**
 * Created by Francois on 2014-06-01.
 */

public class Forfait {
	
	private Integer idForfait;
	private Integer cout;
	private Integer locationMax;
	private Integer dureeMax;
	
	public Integer getIdForfait() {
        return idForfait;
    }
    
    public void setIdForfait(Integer idForfait) {
        this.idForfait = idForfait;
    }
    
    public Integer getCout() {
        return cout;
    }
    
    public void setCout(Integer cout) {
        this.cout = cout;
    }
    
    public Integer getLocationMax() {
        return locationMax;
    }
    
    public void setLocationMax(Integer locationMax) {
        this.locationMax = locationMax;
    }
    
    public Integer getDureeMax() {
        return dureeMax;
    }
    
    public void setDureeMax(Integer dureeMax) {
        this.dureeMax = dureeMax;
    }
}