package model;


public class Punto_contorno {
	

	 private float Longitudine;
	 private float Latitudine;

	
	public Punto_contorno(float latitudine, float longitudine) {
		super();
		Longitudine = longitudine;
		Latitudine = latitudine;
	}
	public Punto_contorno() {
		// TODO Auto-generated constructor stub
	}
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
