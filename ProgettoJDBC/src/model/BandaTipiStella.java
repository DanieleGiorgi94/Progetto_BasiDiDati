
package model;

import model.Banda; 
import model.TipiStella;


public class BandaTipiStella {
	
	    private TipiStella typeStars;
		
	    private Banda banda;
	    
	
	    private Integer wavelenght;
	    
	    public TipiStella getTypestars() {
			return typeStars;
		}

		public void setTypestars(TipiStella typeStars) {
			this.typeStars = typeStars;
		}

		public Banda getBanda() {
			return banda;
		}

		public void setBanda(Banda banda) {
			this.banda = banda;
		}

		public Integer getWavelenght() {
			return wavelenght;
		}

		public void setWavelength(Integer wavelenght) {
			this.wavelenght = wavelenght;
		}
}


