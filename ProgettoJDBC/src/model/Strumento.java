package model;

import java.util.ArrayList;
import java.util.Vector;


public class Strumento {
	//chiave nome e satellite
	private String toolId;
	
	private Vector<Banda> banda;
	
	private Satellite satellite;
	
	public Strumento(String nomeStrumento, Satellite nomeSat) {
		this.toolId = nomeStrumento;

	}

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

	public Vector<Banda> getBanda() {
		return banda;
	}

	public void setBanda(Vector<Banda> vBanda) {
		this.banda = vBanda;
	}

	
	
}

