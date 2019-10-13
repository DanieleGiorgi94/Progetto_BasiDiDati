package model;

import java.util.Collection;

import javax.persistence.*;

@NamedQuery(name="Satelliti",query="SELECT s from Satellite s",lockMode=LockModeType.OPTIMISTIC)
@Entity
public class Satellite {
	@Id //identify the key
	private String name;
	
	@OneToMany(mappedBy="satellite")
	private Collection<Strumento> tools;
	
	@ManyToMany
	private Collection<Agenzia> spacialAgencies;
	
	private String firstObservation;
	private String lastObservation;
	private String duration;

	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Collection<Strumento> getTools() {
		return tools;
	}
	public void setTools(Collection<Strumento> tools) {
		this.tools = tools;
	}
	public Collection<Agenzia> getSpacialAgencies() {
		return spacialAgencies;
	}
	public void setSpacialAgencies(Collection<Agenzia> spacialAgencies) {
		this.spacialAgencies = spacialAgencies;
	}
	public String getFirstObservation() {
		return firstObservation;
	}
	public void setFirstObservation(String firstObservation) {
		this.firstObservation = firstObservation;
	}
	public String getLastObservation() {
		return lastObservation;
	}
	public void setLastObservation(String lastObservation) {
		this.lastObservation = lastObservation;
	}
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}

}
