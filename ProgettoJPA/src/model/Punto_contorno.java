package model;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.*;

import model.Contorno;
@Entity 
@IdClass(PuntoContornoId.class)
@NamedQuery(name="Punto_contorno",query="SELECT p from Punto_contorno p",lockMode=LockModeType.OPTIMISTIC)
public class Punto_contorno {
	
	@OneToMany(mappedBy = "puntocontorno")
	private Collection<Contorno> contorno;

	public Collection<Contorno> getContorno() {
		return contorno;
	}
	public void setContorno(Collection<Contorno> contorno) {
		this.contorno = contorno;
	}
	
	@Id private float Longitudine;
	@Id private float Latitudine;
	
	
	
	public float getLongitudine() {
		return Longitudine;
	}
	public void setLongitudine(float longitudine) {
		Longitudine = longitudine;
	}
	public float getLatitudine() {
		return Latitudine;
	}
	public void setLatitudine(float latitudine) {
		Latitudine = latitudine;
	}
	
	public float getMedia(Float avg) {
		return avg;
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
	class PuntoContornoId implements Serializable{
		private float Longitudine;
		private float Latitudine;
		
	}
