package model;



public class Punto_scheletro{
	

	 private double Longitudine;
	 private double Latitudine;
	
	
	public Punto_scheletro(double lat, double longit) {
		this.Latitudine = lat;
		this.Longitudine = longit;
	}
	public Punto_scheletro() {
		// TODO Auto-generated constructor stub
	}
	
	public double getLongitudine() {
		return Longitudine;
	}
	public void setLongitudine(float longitudine) {
		Longitudine = longitudine;
	}
	public double getLatitudine() {
		return Latitudine;
	}
	public void setLatitudine(float latitudine) {
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


