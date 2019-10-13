package model;



public class Segmento {
	
	private Punto_scheletro puntoscheletro;
	

	


	public Segmento(Punto_scheletro puntoscheletro, Integer idSegmento, Integer numeroProgressivo, String tipo) {
		super();
		this.puntoscheletro = puntoscheletro;
		this.idSegmento = idSegmento;
		this.numeroProgressivo = numeroProgressivo;
		this.tipo = tipo;
	}




	public Segmento(Punto_scheletro ps, int idSegmento, int nProg) {
		this.puntoscheletro = ps;
		this.idSegmento = idSegmento;
		this.numeroProgressivo = nProg;
	}




	public Punto_scheletro getPuntoscheletro() {
		return puntoscheletro;
	}
	public void setPuntoscheletro(Punto_scheletro puntoscheletro) {
		this.puntoscheletro = puntoscheletro;
	}
	
	private Integer idSegmento;
	private Integer numeroProgressivo;
	
	private String tipo;
	
	public Integer getIdSegmento() {
		return idSegmento;
	}
	public void setIdSegmento(Integer idSegmento) {
		this.idSegmento = idSegmento;
	}
	
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public Integer getNumeroProgressivo() {
		return numeroProgressivo;
	}
	public void setNumeroProgressivo(Integer numeroProgressivo) {
		this.numeroProgressivo = numeroProgressivo;
	}
	
}
