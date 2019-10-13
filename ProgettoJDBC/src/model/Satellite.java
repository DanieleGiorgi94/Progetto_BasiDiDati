package model;

import java.util.Collection;

public class Satellite {
	
	public Satellite(String name, String firstObservation, String lastObservation, String duration) {
		super();
		this.name = name;
		this.firstObservation = firstObservation;
		this.lastObservation = lastObservation;
		this.duration = duration;
		
	}
	public Satellite() {
		// TODO Auto-generated constructor stub
	}
	
	public Satellite(Agenzia agency) {
		spacialAgencies.add(agency);
	}

	public Satellite(String nome) {
		this.name = nome;
	}

	private String name;
	private String firstObservation;
	private String lastObservation;
	private String duration;
	private Collection<Agenzia> spacialAgencies;
	
	private Collection<Strumento> tools;

	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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

	
}
