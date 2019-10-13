package model;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import javax.persistence.*;

import model.Contorno;


@Entity 
@NamedQuery(name="Filamento",query="SELECT f from Filamento f",lockMode=LockModeType.OPTIMISTIC)
public class Filamento { // identify the key 
	
	@Id // identify the key
	private Integer id;
	
	@ManyToOne
	private Strumento strumento;
	
	@OneToMany(mappedBy = "filamento")
	private Collection<Scheletro> scheletro;
	
	@OneToMany(mappedBy = "filamento")
	private Collection<Contorno> contorno;
	
	
	public Collection<Contorno> getContorno() {
		return contorno;
	}
	public void setContorno(Collection<Contorno> contorno) {
		this.contorno = contorno;
	}
	private String nome;
	private double DensitaMedia;
	private float TemperaturaMedia;
	private double FlussoTotale;
	private float ellitticita;
	private float Contrasto;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Strumento getStrumento() {
		return strumento;
	}
	public void setStrumento(Strumento strumento) {
		this.strumento = strumento;
	}
	public Collection<Scheletro> getScheletro() {
		return scheletro;
	}
	public void setScheletro(Collection<Scheletro> scheletro) {
		this.scheletro = scheletro;
	}
	
	public void setPerimetro(Collection<Contorno> contorno) {
		this.contorno = contorno;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public double getDensitaMedia() {
		return DensitaMedia;
	}
	public void setDensitaMedia(double densitaMedia) {
		DensitaMedia = densitaMedia;
	}
	public float getTemperaturaMedia() {
		return TemperaturaMedia;
	}
	public void setTemperaturaMedia(float temperaturaMedia) {
		TemperaturaMedia = temperaturaMedia;
	}
	public double getFlussoTotale() {
		return FlussoTotale;
	}
	public void setFlussoTotale(double flussoTotale) {
		FlussoTotale = flussoTotale;
	}
	public float getEllitticita() {
		return ellitticita;
	}
	public void setEllitticita(float ellitticita) {
		this.ellitticita = ellitticita;
	}
	public float getContrasto() {
		return Contrasto;
	}
	public void setContrasto(float contrasto) {
		Contrasto = contrasto;
	}
	
}

		

	
