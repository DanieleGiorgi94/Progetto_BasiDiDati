package view;


import java.util.List;
import controller.ContornoController;
import model.Contorno;
import model.Filamento;

public class ContornoBean {
	private float longitudine;
	private float latitudine;
	private Integer filamento_id;
	ContornoController cc = new ContornoController();
	private List<Filamento> ListFilCircle;
	private List<Filamento> ListFilSquare;
	public static String typegeo;
	private List<Contorno> list;
	private List<Integer> ListRectangle;
	
	
	
	public List<Filamento> getListFilCircle() {
		return ListFilCircle;
	}

	public void setListFilCircle(List<Filamento> listFilCircle) {
		ListFilCircle = listFilCircle;
	}

	public List<Filamento> getListFilSquare() {
		return ListFilSquare;
	}

	public void setListFilSquare(List<Filamento> listFilSquare) {
		ListFilSquare = listFilSquare;
	}


	public List<Integer> getListRectangle() {
		return ListRectangle;
	}

	public void setListRectangle(List<Integer> listRectangle) {
		ListRectangle = listRectangle;
	}

	public List<Contorno> getList() {
		return list;
	}

	public void setList(List<Contorno> list) {
		this.list = list;
	}

	public static String getTypegeo() {
		return typegeo;
	}

	public static void setTypegeo(String typegeo) {
		ContornoBean.typegeo = typegeo;
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
	public ContornoController getCc() {
		return cc;
	}
	public void setCc(ContornoController cc) {
		this.cc = cc;
	}
	public Integer getFilamento_id() {
		return filamento_id;
	}
	public void setFilamento_id(Integer filamento_id) {
		this.filamento_id = filamento_id;
	}
	
	//REQUISITO 5: RECUPERO INFORMAZIONI DERIVATE DI UN FILAMENTO -> CALCOLO CENTROIDE
	public float[] getCentroide() {
		return cc.getCentroide(filamento_id);
	}
	
	public List<Contorno> getContorniperId() {
		return cc.getContorniperId(filamento_id);
	}
	//REQUISITO 5: RECUPERO INFORMAZIONI DERIVATE DI UN FILAMENTO -> CALCOLO ESTENSIONE 
	public double[] getExtension() {
		return cc.getEstensione(filamento_id);
	}
	
	public List<Contorno> getAllContorni() {
		this.setList(cc.getAllContorni());
		return this.getList();
	}
	
	//REQUISITO 8: RICERCA DI UN OGGETTO ALL'INTERNO DI UNA REGIONE -> CALCOLO STRUTTURE ESTESE ALL'INTERNO DI UNA REGIONE CIRCOLARE  
	public List<Filamento> getObjcircle(float raggio,float lat,float longit) throws NumberFormatException {
		this.setListFilCircle(cc.getObjcircle(lat, longit, raggio));
		return this.getListFilCircle();
	}
	
	//REQUISITO 8: RICERCA DI UN OGGETTO ALL'INTERNO DI UNA REGIONE -> CALCOLO STRUTTURE ESTESE ALL'INTERNO DI UNA REGIONE QUADRATA
	public List<Filamento> getObjsquare(float lato,float lat,float longit) throws NumberFormatException {
		this.setListFilSquare(cc.getObjSquare(lat, longit, lato));
		return this.getListFilSquare();
	}
	//REQUISITO 10; FRAZIONE DI STELLE IN FORMAZIONE ALL'INTERNO DI UNA REGIONE -> CALCOLO CONTORNI E QUINDI STRUTTURE ESTESE ALL'INTERNO DI UNA REGIONE RETTANGOLARE
	public List<Integer> getObjRectangle(float latoA,float latoB, float lat,float longit) throws NumberFormatException {
		this.setListRectangle(cc.getObjRectangle(lat, longit, latoA,latoB));
		return this.getListRectangle();
	}
}
