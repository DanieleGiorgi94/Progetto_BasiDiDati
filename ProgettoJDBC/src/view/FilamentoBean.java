package view;

import controller.FilamentoController;
import model.Filamento;

import java.sql.SQLException;
import java.util.List;



public class FilamentoBean {
	private Integer id;
	private String nome;
	private float DensitaMedia;
	private float TemperaturaMedia;
	private float ellitticita;
	private float Contrasto;
	private float FlussoTotale;
	private float Index1;
	private float Index2;
	private float brillanza;
	private static int nFilamenti;
	private static int numeroSegmenti;
	
	
	public float getBrillanza() {
		return brillanza;
	}

	public void setBrillanza(float brillanza) {
		this.brillanza = brillanza;
	}

	private FilamentoController fc= new FilamentoController();
	

	private List<Filamento> list;
	
	private static int c = 0;
	
	
	public static int getC() {
		return c;
	}

	public static void setC(int c) {
		FilamentoBean.c = c;
	}

	public static void incC() {
		c++;
		System.out.println(c);
	}
	
	public static void decC() {
		c--;
	}

	public List<Filamento> getList() {
		return list;
	}
	
	public List<Filamento> getAllFilamenti() {
		this.setList(fc.getAllFilamenti());
		return this.getList();
	}
	
	public Filamento getFilamenti() {
		return fc.getFilamenti(id,nome);
	}
	
	public Filamento getFilamentiPerId() {
		return fc.getFilamentiPerId(id);
	}
	
	public Filamento getFilamentiPerNome() {
		return fc.getFilamentiPerNome(nome);
	}
	
	public void setList(List<Filamento> Filamenti) {
		this.list = Filamenti;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public float getDensitaMedia() {
		return DensitaMedia;
	}
	public void setDensitaMedia(float densitaMedia) {
		DensitaMedia = densitaMedia;
	}
	public float getTemperaturaMedia() {
		return TemperaturaMedia;
	}
	public void setTemperaturaMedia(float temperaturaMedia) {
		TemperaturaMedia = temperaturaMedia;
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
	public float getFlussoTotale() {
		return FlussoTotale;
	}
	public void setFlussoTotale(float flussoTotale) {
		FlussoTotale = flussoTotale;
	}
	
	
	public double getCatalogoFilamenti() {
		double size = Double.valueOf(this.ricercaperellitticitaebrillanza().size()) / Double.valueOf(this.getAllFilamenti().size());
		size = Math.floor(size * 1000) / 1000;
		return size;
	}

	public float getIndex1() {
		return Index1;
	}

	public void setIndex1(float index1) {
		Index1 = index1;
	}

	public float getIndex2() {
		return Index2;
	}

	public void setIndex2(float index2) {
		Index2 = index2;
	}
	
	public List<Filamento> ricercaperellitticitaebrillanza() {
		return fc.ricercaperellitticitaebrillanza(Index1,Index2,brillanza);
	
}

	public static int getnFilamenti() {
		return nFilamenti;
	}

	public static void setnFilamenti(int nFilamenti) {
		FilamentoBean.nFilamenti = nFilamenti;
	}
	
	public double getCatalogoFilamenti(List<Filamento> filBrillanza, Long CountAllFilamenti) {
		double size = Double.valueOf(filBrillanza.size())/ Double.valueOf(CountAllFilamenti);
		size = Math.floor(size * 1000) / 1000;
		return size;
	}
	
	public Long getCountAllFilamenti() throws SQLException {
		return fc.getCountAllFilamenti();
	}

	public static int getNumeroSegmenti() {
		return numeroSegmenti;
	}

	public static void setNumeroSegmenti(int numeroSegmenti) {
		FilamentoBean.numeroSegmenti = numeroSegmenti;
	}
	
}
