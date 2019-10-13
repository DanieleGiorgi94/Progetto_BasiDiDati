package view;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import controller.ScheletroController;
import model.Filamento;
import model.Stella;

public class ScheletroBean {
	
	private double flusso;
	private float longitudine;
	private float latitudine;
	private static Integer filamento_id;
	private static Integer Index1;
	private static Integer Index2;
	private static int nScheletri;
	
	public Integer getIndex1() {
		return Index1;
	}
	public static void setIndex1(Integer index1) {
		ScheletroBean.Index1 = index1;
	}
	
	
	public static Integer getIndex2() {
		return Index2;
	}
	public static void setIndex2(Integer index2) {
		ScheletroBean.Index2 = index2;
	}
	
	Filamento filamento = new Filamento();
	
	ScheletroController sc = new ScheletroController();
	
	
	public double getFlusso() {
		return flusso;
	}
	public void setFlusso(double flusso) {
		this.flusso = flusso;
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
	public static Integer getFilamento_id() {
		return filamento_id;
	}
	public static void setFilamento_id(Integer filamento_id) {
		ScheletroBean.filamento_id = filamento_id;
	}
	
	public Integer numeroSegmenti() throws SQLException {
		return sc.numeroSegmenti(filamento_id);
	}
	
	public List<Integer> filamentipersegmenti(){
		return sc.filamentipersegmenti(Index1,Index2);
		
	}
	
	public ArrayList<Stella> DistanzaSpina(ArrayList<Stella> stelleFilamento) throws Exception {
		stelleFilamento = StellaBean.getAllStars();
		return sc.DistanzaSpina(filamento_id,stelleFilamento);
	}
	

	public Integer IdFilamentoVerticiSegmento(double lat,double longit) {
		return sc.IdFilamentoVerticiSegmento(lat,longit);
	}
	
	public double getMinDistance(double lat,double longit) throws Exception {
		
		Integer filamento_id = this.IdFilamentoVerticiSegmento(lat,longit);
		return sc.getMinDistance(lat, longit,filamento_id);
	}
	
	public static int getnScheletri() {
		return nScheletri;
	}
	public static void setnScheletri(int nScheletri) {
		ScheletroBean.nScheletri = nScheletri;
	}
}
