package model;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@IdClass(ContornoId.class)
@NamedQuery(name="Contorno",query="SELECT c from Contorno c",lockMode=LockModeType.OPTIMISTIC)
public class Contorno {
	
	@Id
	@ManyToOne
	private Punto_contorno puntocontorno;
	
	public Punto_contorno getPuntocontorno() {
		return puntocontorno;
	}
	public void setPuntocontorno(Punto_contorno puntocontorno) {
		this.puntocontorno = puntocontorno;
	}
	@Id
	@ManyToOne
	private Filamento filamento;

	public Filamento getFilamento() {
		return filamento;
	}
	public void setFilamento(Filamento filamento) {
		this.filamento = filamento;
	}
	
}

class ContornoId implements Serializable{
	
	private Filamento filamento;
	private Punto_contorno puntocontorno;
}


