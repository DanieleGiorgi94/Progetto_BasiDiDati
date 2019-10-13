package model;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.*;
import model.Filamento;

@Entity  
 public class Stella {
	//identificazione esterna tra id e mappa
	@Id private Integer stellaId;
	
	@ManyToOne 
	private TipiStella typeStars;
	private String nome;
	private float latitudine;
	private float longitudine;
	private float flusso;
	
	@Transient
	private double distanza;
	
	public Integer getStellaId() {
		return stellaId;
	}
	public void setStellaId(Integer stellaId) {
		this.stellaId = stellaId;
	}
	
	public TipiStella getTypeStars() {
		return typeStars;
	}
	public void setTypeStars(TipiStella typeStars) {
		this.typeStars = typeStars;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public float getLongitudine() {
		return longitudine;
	}
	public void setLongitudine(float longitudine) {
		this.longitudine = longitudine;
	}
	public float getLatitudine() {
		return latitudine;
	}
	public void setLatitudine(float latitudine) {
		this.latitudine = latitudine;
	}
	public float getFlusso() {
		return flusso;
	}
	public void setFlusso(float flusso) {
		this.flusso = flusso;
	}
	public double getDistanza() {
		return distanza;
	}
	public void setDistanza(double distanza) {
		
		this.distanza = distanza;
	}
}
