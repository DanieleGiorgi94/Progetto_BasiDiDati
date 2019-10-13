package view;


import java.util.List;
import java.util.Vector;
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
	
	
	public float[] getCentroide() throws Exception {
		return cc.getCentroide(filamento_id);
	}
	
	public List<Contorno> getContorniperId() throws Exception {
		return cc.getContorniperId(filamento_id);
	}
	
	public double[] getExtension() {
		return cc.getEstensione(filamento_id);
	}
	
	public List<Contorno> getAllContorni() {
		this.setList(cc.getAllContorni());
		return this.getList();
	}

	public List<Filamento> getObjcircle(float raggio,float lat,float longit) throws NumberFormatException {
		this.setListFilCircle(cc.getObjcircle(lat, longit, raggio));
		return this.getListFilCircle();
	}

	public List<Filamento> getObjsquare(float lato,float lat,float longit) throws NumberFormatException {
		this.setListFilSquare(cc.getObjSquare(lat, longit, lato));
		return this.getListFilSquare();
	}

	public List<Integer> getObjRectangle(float latoA,float latoB, float lat,float longit) throws NumberFormatException {
		this.setListRectangle(cc.getObjRectangle(lat, longit, latoA,latoB));
		return this.getListRectangle();
	}

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

	public void setListRectangle(List<Integer> list) {
		ListRectangle = list;
	}
}
