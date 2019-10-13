package model;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.LockModeType;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;

@Entity
@NamedQuery(name="Agenzie",query="SELECT a from Agenzia a",lockMode=LockModeType.OPTIMISTIC)
public class Agenzia {
	@Id //identify the key
	private String name;
	
	@ManyToMany(mappedBy="spacialAgencies")
	private Collection<Satellite> satellites;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Collection<Satellite> getSatellites() {
		return satellites;
	}

	public void setSatellites(Collection<Satellite> satellites) {
		this.satellites = satellites;
	}
}