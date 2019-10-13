package model;


public class Contorno {
	

	
	private Punto_contorno puntocontorno;
	

	public Punto_contorno getPuntocontorno() {
		return puntocontorno;
	}
	public Contorno(Punto_contorno puntocontorno, Filamento filamento) {
		super();
		this.puntocontorno = puntocontorno;
		this.filamento = filamento;
	}

	public void setPuntocontorno(Punto_contorno puntocontorno) {
		this.puntocontorno = puntocontorno;
	}

	private Filamento filamento;

	public Filamento getFilamento() {
		return filamento;
	}
	public void setFilamento(Filamento filamento) {
		this.filamento = filamento;
	}
	
}



