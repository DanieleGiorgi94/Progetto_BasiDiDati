package model;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.*;

import model.Scheletro;
import model.Segmento;


@Entity 
@IdClass(PuntoScheletroId.class)
@NamedQuery(name="Punto_scheletro",query="SELECT p from Punto_scheletro p",lockMode=LockModeType.OPTIMISTIC)
public class Punto_scheletro{
	
	@OneToMany(mappedBy = "puntoscheletro")
	private Collection<Scheletro> scheletro;
	
	@OneToMany(mappedBy = "puntoscheletro",
	        cascade = CascadeType.ALL, 
	        orphanRemoval = true)
	
	private Collection<Segmento> segmenti;
	
	public Collection<Scheletro> getScheletro() {
		return scheletro;
	}
	public void setScheletro(Collection<Scheletro> scheletro) {
		this.scheletro = scheletro;
	}
	public Collection<Segmento> getSegmenti() {
		return segmenti;
	}
	public void setSegmenti(Collection<Segmento> segmenti) {
		this.segmenti = segmenti;
	}

	@Id private double Longitudine;
	@Id private double Latitudine;
	
	
	
	public double getLongitudine() {
		return Longitudine;
	}
	public void setLongitudine(double longitudine) {
		Longitudine = longitudine;
	}
	public double getLatitudine() {
		return Latitudine;
	}
	public void setLatitudine(double latitudine) {
		Latitudine = latitudine;
	}
	
	public int mediaLongitudini(String[] Longitudine) {
		int somma = 0;
		for (int i=0; i<Longitudine.length; i++) {
			 somma = somma + (Integer.parseInt(Longitudine[i])); 
		}
		
		int mediaLongitudini = somma / Longitudine.length;
		return mediaLongitudini;
	}
		
	public int mediaLatitudini(String[] Latitudine) {
		int somma = 0;
		for (int i=0; i<Latitudine.length; i++) {
			 somma = somma + (Integer.parseInt(Latitudine[i])); 
		}
		
		int mediaLatitudini = somma / Latitudine.length;
		return mediaLatitudini;
	}
	
	public double getDistance(double lat1, double longit1, double lat2, double longit2) {
		return (Math.hypot( longit1-longit2,lat1-lat2) );
		
	}
}

	@Embeddable
	class PuntoScheletroId implements Serializable{
		private double Longitudine;
		private double Latitudine;
		
	}
