
package model;

import java.io.Serializable;

import javax.persistence.*;
import model.Banda; 
import model.Stella;
import model.TipiStella;

@Entity
@IdClass(WavelenghtId.class)
public class BandaTipiStella {
		
		@Id
	    @ManyToOne
	    private TipiStella typeStars;
		
	    @Id
	    @ManyToOne
	    private Banda banda;
	    
	    @Id
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

	class WavelenghtId implements Serializable{
		private TipiStella typeStars;
		private Banda banda;
		private Integer wavelenght;
	}
