package model;


public class Scheletro {
	
	private Filamento filamento; 
	
	public Scheletro(Filamento filamento, Punto_scheletro puntoscheletro, double flusso) {
		super();
		this.filamento = filamento;
		this.puntoscheletro = puntoscheletro;
		this.flusso = flusso;
	}
	
	
	public Scheletro(Filamento f) {
		this.filamento = f;
	}


	public Filamento getFilamento() {
		return filamento;
	}

	public void setFilamento(Filamento filamento) {
		this.filamento = filamento;
	}
	
	private Punto_scheletro puntoscheletro;
	
	public Punto_scheletro getPuntoscheletro() {
		return puntoscheletro;
	}
	public void setPuntoscheletro(Punto_scheletro puntoscheletro) {
		this.puntoscheletro = puntoscheletro;
	}
	
	private double flusso;
	
	public double getFlusso() {
		return flusso;
	}
	public void setFlusso(double flusso) {
		this.flusso = flusso;
	}
}

