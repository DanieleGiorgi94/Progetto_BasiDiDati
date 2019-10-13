package view;

import java.util.ArrayList;

import controller.SegmentoController;

public class SegmentoBean {
	
	private Integer idsegmento;
	private Integer nProg;
	private String tipo;
	private String latitudine;
	private String longitudine;
	
	SegmentoController sc = new SegmentoController();
	
	public Integer getIdsegmento() {
		return idsegmento;
	}
	public void setIdsegmento(Integer idsegmento) {
		this.idsegmento = idsegmento;
	}
	public Integer getnProg() {
		return nProg;
	}
	public void setnProg(Integer nProg) {
		this.nProg = nProg;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getLatitudine() {
		return latitudine;
	}
	public void setLatitudine(String latitudine) {
		this.latitudine = latitudine;
	}
	public String getLongitudine() {
		return longitudine;
	}
	public void setLongitudine(String longitudine) {
		this.longitudine = longitudine;
	}
	
	public ArrayList<Double> getMinVertex(Integer idsegmento) {
		return sc.getMinVertex(idsegmento);
	}
	
	public ArrayList<Double> getMaxVertex(Integer idsegmento){
		return sc.getMaxVertex(idsegmento);
	}
	

}