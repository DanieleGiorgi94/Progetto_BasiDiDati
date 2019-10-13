package model;

  
 public class Stella {
	
	private Integer stellaId;
	
	private Filamento filamento;
	
	private TipiStella typeStars;
	
	private String nome;
	private float latitudine;
	private float longitudine;
	private float flusso;
	private double distanza;
	
	
	
	public double getDistanza() {
		return distanza;
	}



	public void setDistanza(double distanza) {
		this.distanza = distanza;
	}



	public Stella(Integer stellaId, TipiStella typeStars, String nome, float latitudine,
			float longitudine, float flusso) {
		super();
		this.stellaId = stellaId;
		this.typeStars = typeStars;
		this.nome = nome;
		this.latitudine = latitudine;
		this.longitudine = longitudine;
		this.flusso = flusso;
	}



	public Integer getStellaId() {
		return stellaId;
	}
	public void setStellaId(Integer stellaId) {
		this.stellaId = stellaId;
	}
	public Filamento getFilamento() {
		return filamento;
	}
	public void setFilamento(Filamento filamento) {
		this.filamento = filamento;
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
		
}