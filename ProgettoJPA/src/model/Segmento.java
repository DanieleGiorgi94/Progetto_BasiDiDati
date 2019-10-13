package model;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import javax.persistence.*;

import model.*;

@Entity 
@IdClass(SegmentoId.class)
@NamedQuery(name="Segmento",query="SELECT s from Segmento s",lockMode=LockModeType.OPTIMISTIC)
public class Segmento {
	
	@ManyToOne @Id 
	private Punto_scheletro puntoscheletro;
	
	
	public Punto_scheletro getPuntoscheletro() {
		return puntoscheletro;
	}
	public void setPuntoscheletro(Punto_scheletro puntoscheletro) {
		this.puntoscheletro = puntoscheletro;
	}
	@Id private Integer idSegmento;
	@Id private Integer numeroProgressivo;
	@Id private String tipo;
	
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
class SegmentoId implements Serializable{
	private Integer idSegmento;
	private Integer numeroProgressivo;
	private Punto_scheletro puntoscheletro;
	private String tipo;
}