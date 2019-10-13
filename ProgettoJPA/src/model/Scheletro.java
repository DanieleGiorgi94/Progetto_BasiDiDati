package model;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.*;

import model.*;

@Entity  
@IdClass(ScheletroId.class)
@NamedQuery(name="Scheletro",query="SELECT s from Scheletro s",lockMode=LockModeType.OPTIMISTIC)
public class Scheletro {
	
	@Id
	@ManyToOne
	private Filamento filamento; 
	
	public Filamento getFilamento() {
		return filamento;
	}
	public void setFilamento(Filamento filamento) {
		this.filamento = filamento;
	}
	
	@Id
	@ManyToOne
	private Punto_scheletro puntoscheletro;
	
	public Punto_scheletro getPuntoscheletro() {
		return puntoscheletro;
	}
	public void setPuntoscheletro(Punto_scheletro puntoscheletro) {
		this.puntoscheletro = puntoscheletro;
	}
	
	@Id
	private double flusso;
	
	public double getFlusso() {
		return flusso;
	}
	public void setFlusso(double flusso) {
		this.flusso = flusso;
	}
}

	@Embeddable 
	class ScheletroId implements Serializable{
		private Filamento filamento;
		private Punto_scheletro puntoscheletro;
		private double flusso;
	}
