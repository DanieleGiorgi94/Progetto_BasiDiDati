package model;



public class Filamento { // identify the key 
	
	private Integer id;
	private String nome;
	private double DensitaMedia;
	private float TemperaturaMedia;
	private double FlussoTotale;
	private float ellitticita;
	private float Contrasto;
	private Strumento strumento;
	
	
	public Filamento(Integer id, String nome, double densitaMedia, float temperaturaMedia, double flussoTotale,
			float ellitticita, float contrasto) {
		super();
		this.id = id;
		this.nome = nome;
		DensitaMedia = densitaMedia;
		TemperaturaMedia = temperaturaMedia;
		FlussoTotale = flussoTotale;
		this.ellitticita = ellitticita;
		Contrasto = contrasto;
	}

	public Filamento() {
		// TODO Auto-generated constructor stub
	}
	
	public Filamento(Integer filamento_id) {
		this.id = filamento_id;
	}


	public Filamento(int idFilamento, String nome) {
		this.id = idFilamento;
		this.nome = nome;
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


	public Strumento getStrumento() {
		return strumento;
	}


	public void setStrumento(Strumento strumento) {
		this.strumento = strumento;
	}
	
}

		

	
