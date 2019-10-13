package model;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.*;


@Entity  
@IdClass(ToolId.class)
public class Strumento {
	//chiave nome e satellite
	@Id private String toolId;
	
	@ManyToOne @Id
	private Satellite satellite;
	
		
	@ManyToMany
	private Collection<Banda> banda;

	public Collection<Banda> getBanda() {
		return banda;
	}

	public void setBanda(Collection<Banda> banda) {
		this.banda = banda;
	}
	@OneToMany(mappedBy = "strumento")
	private Collection<Filamento> Filamenti;
	
	public String getToolId() {
		return toolId;
	}

	public void setToolId(String toolId) {
		this.toolId = toolId;
	}

	public Satellite getSatellite() {
		return satellite;
	}

	public void setSatellite(Satellite satellite) {
		this.satellite = satellite;
	}
	
	public Collection<Filamento> getFilamenti() {
		return Filamenti;
	}
	public void setFilamenti(Collection<Filamento> Filamenti) {
		this.Filamenti = Filamenti;
	}
}

@Embeddable
class ToolId implements Serializable{
	private String toolId;
	private Satellite satellite; //vincolo di integrità con satelllite
}