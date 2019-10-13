package model;

import java.util.ArrayList;
import java.util.Collection;

public class Agenzia {
	
	private Collection<Satellite> satellites;

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Agenzia(String nome) {
		this.name = nome;
	}

	public Agenzia(Collection<Satellite> satellites, String name) {
		super();
		this.satellites = satellites;
		this.name = name;
	}

	public Collection<Satellite> getSatellites() {
		return satellites;
	}

	public void setSatellites(Collection<Satellite> satellites) {
		this.satellites = satellites;
	}
	
}