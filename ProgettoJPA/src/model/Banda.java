package model;

//import java.io.Serializable;
import java.util.Collection;

import javax.persistence.*;

@NamedQuery(name="Bande",query="SELECT b from Banda b",lockMode=LockModeType.OPTIMISTIC)
@Entity
public class Banda {
	//chiave è risoluzione
	@Id
	private float risoluzione;
	

	@ManyToMany(mappedBy = "banda")
	private Collection<Strumento> tools;
	
	@OneToMany(mappedBy="banda")
	private Collection<BandaTipiStella> bandstypestars;
	
	public Collection<BandaTipiStella> getBandstypestars() {
		return bandstypestars;
	}

	public void setBandstypestars(Collection<BandaTipiStella> bandstypestars) {
		this.bandstypestars = bandstypestars;
	}

	public float getRisoluzione() {
		return risoluzione;
	}

	public void setRisoluzione(float risoluzione) {
		this.risoluzione = risoluzione;
	}

	public Collection<Strumento> getTools() {
		return tools;
	}

	public void setTools(Collection<Strumento> tools) {
		this.tools = tools;
	}
}