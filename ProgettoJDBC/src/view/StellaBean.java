package view;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Vector;

import controller.StellaController;
import model.Stella;

public class StellaBean {
	private double Distanza;
	private Integer stellaId;
	private float flusso;
	private float longitudine;
	private float latitudine;
	private String nome;
	private static Integer filamento_id;
	private String tipostella;
	private static int c = 0;
	private static int nStelleFlusso;
	private static int nStelle;
	private ArrayList<Stella> AllStars = new ArrayList<Stella>();	
	static StellaController sc = new StellaController();
	private List<Stella> ListRectangle;
	private ArrayList<Stella> stelleFilRect = new ArrayList<Stella>();
	
	
	public List<Stella> getListRectangle() {
		return ListRectangle;
	}
	public void setListRectangle(List<Stella> listRectangle) {
		ListRectangle = listRectangle;
	}
	
	public double getDistanza() {
		return Distanza;
	}
	public void setDistanza(double distanza) {
		this.Distanza = distanza;
	}
	
	public ArrayList<Stella> getStelleFilRect() {
		return stelleFilRect;
	}
	public void setStelleFilRect(ArrayList<Stella> stelleFilRect) {
		this.stelleFilRect = stelleFilRect;
	}
	
	public Integer getStellaid() {
		return stellaId;
	}
	public void setStellaid(Integer stellaid) {
		this.stellaId = stellaid;
	}
	public float getFlusso() {
		return flusso;
	}
	public void setFlusso(float flusso) {
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
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public static Integer getFilamento_id() {
		return filamento_id;
	}
	public static void setFilamento_id(Integer filamento_id) {
		StellaBean.filamento_id = filamento_id;
	}
	
	public void setid_filamento(Integer filamento_id) {
		StellaBean.filamento_id = filamento_id;
	}
	
	public Integer getid_filamento() {
		return filamento_id;
	}
	
	public String getTipostella() {
		return tipostella;
	}
	public void setTipostella(String tipostella) {
		this.tipostella = tipostella;
	}
	
	public static int getC() {
		return c;
	}
	public static void setC(int c) {
		StellaBean.c = c;
	}	
	
	public static void incC() {
		c++;
	}
	
	public static void decC() {
		System.out.println(c);
		c--;
	}
	
	public static int getnStelle() {
		return nStelle;
	}
	public static void setnStelle(int nStelle) {
		StellaBean.nStelle = nStelle;
	}
	
	// REQUISITO 9: RICERCA DI UN OGGETTO DI TUTTE LE STELLE ALL'INTERNO DI UNA REGIONE 
	public static ArrayList<Stella> getAllStars() throws Exception {
		return sc.getAllStars(filamento_id);
	}
	
	// REQUISITO 12:DISTANZA STELLA SPINA -> ORDINAMENTO IN BASE A DISTANZA
	public ArrayList<Stella> DistanzaSpinaOrderedBYDistanza(ArrayList<Stella> stelleFilamento) {
		Collections.sort(stelleFilamento, new Comparator<Stella>() {
	        @Override
	        public int compare(Stella stella1, Stella stella2)
	        {

	           return Double.valueOf(stella1.getDistanza()).compareTo(Double.valueOf(stella2.getDistanza()));
	        }
	    });
		
		return stelleFilamento;
	}
	
	// REQUISITO 12:DISTANZA STELLA SPINA -> ORDINAMENTO IN BASE A FLUSSO
	public ArrayList<Stella> DistanzaSpinaOrderedBYFlusso(ArrayList<Stella> stelleFilamento) {
		Collections.sort(stelleFilamento, new Comparator<Stella>() {
	        @Override
	        public int compare(Stella stella1, Stella stella2)
	        {

	           return Double.valueOf(stella1.getFlusso()).compareTo(Double.valueOf(stella2.getFlusso()));
	        }
	    });
		return stelleFilamento;
	}
	
	// PERCENTUALI STELLE ALL'INTERNO DI UN FILAMENTO; REQUISITO 9: RICERCA DI UN OGGETTO DI TUTTE LE STELLE ALL'INTERNO DI UNA STRUTTURA ESTESA
	public ArrayList<Double> PercentualeStelleFilamento(ArrayList<Stella> allStars) throws Exception, NullPointerException { 
		allStars = StellaBean.getAllStars();
		ArrayList<Double> Percentuali = new ArrayList<Double>();
		Integer i;
		Integer count_protostellar=0;
		Integer count_prestellar=0;
		Integer count_unbound=0;
		double Percentuale_Protostellar;
		double Percentuale_Prestellar;
		double Percentuale_Unbound;
		for (i=0;i<allStars.size(); i++) {
			if (allStars.get(i).getTypeStars().getName().equals("PROTOSTELLAR")) {
				count_protostellar ++;
			}
			if (allStars.get(i).getTypeStars().getName().equals("PRESTELLAR")) {
				count_prestellar++;
			}
			if (allStars.get(i).getTypeStars().getName().equals("UNBOUND")) {
				count_unbound++;
			}
		}
		Percentuale_Protostellar = 100.0*count_protostellar/allStars.size();
		Percentuale_Prestellar = 100.0*count_prestellar/allStars.size();
		Percentuale_Unbound = 100.0*count_unbound/allStars.size();
		Percentuali.add(0, Percentuale_Protostellar); // percentuale stelle di tipo protostellar
		Percentuali.add(1,Percentuale_Prestellar); // percentuae di stelle di tipo prestellar 
		Percentuali.add(2,Percentuale_Unbound); // percentuale di stelle di tipo unbound
		Percentuali.add(3,(double) allStars.size());
		return Percentuali;
	}
	
	//REQUISITO 10: FRAZIONE DI STELLE IN FORMAZIONE ALL'INTERNO DI UNA REGIONE -> STELLE ALL'INTERNO DELLA REGIONE DEL RETTANGOLO 
	public List<Stella> getObjRectangle(float latoA, float latoB, float lat,float longit) throws NumberFormatException, SQLException { 
		this.setListRectangle(sc.getObjRectangle(lat, longit, latoA,latoB));
		return this.getListRectangle();
	}
	
	//REQUISITO 10: FRAZIONE DI STELLE IN FORMAZIONE ALL'INTERNO DELLA REGIONE -> STELLE ALL'INTERNO DEL RETTANGOLO E DEI FILAMENTI
	public ArrayList<Stella> getAllStarsRectangle(float latoA,float latoB, float lat, float longit) throws Exception {  
		this.setStelleFilRect(sc.getAllStarsRectangle(lat,longit,latoA,latoB));
		return this.getStelleFilRect();
	}
	
	// MI RESTITUISCE LE PERCENTUALI (TOTALE E IN BASE ALLE VARIE CATEGORIE) DI TUTTE LE STELLE INTERNE ALLA REGIONE RETTANGOLO E AI FILAMENTI (SEMPRE NELLA REGIONE)
	public ArrayList<Double> PercentualeStelleRectangleFilamento(List<Stella> StarsRect, ArrayList<Stella> StarsRectFil) {
		
		ArrayList<Double> Percentuali = new ArrayList<Double>(); 
		double PercentualeRectTot = 0; 
		double PercentualeRect_Protostellar = 0; 
		double PercentualeRect_Prestellar = 0; 
		double PercentualeRect_Unbound = 0; 
		double PercentualeNotRectTot = 0;  
		Integer count_rect_protostellar=0;
		Integer count_rect_prestellar=0;
		Integer count_rect_unbound=0;

		for (Integer i=0;i<StarsRectFil.size(); i++) {
			if (StarsRectFil.get(i).getTypeStars().getName().equals("PROTOSTELLAR")) {
				count_rect_protostellar ++;
			}
			if (StarsRectFil.get(i).getTypeStars().getName().equals("PRESTELLAR")) {
				count_rect_prestellar++;
			}
			if (StarsRectFil.get(i).getTypeStars().getName().equals("UNBOUND")) {
				count_rect_unbound++;
			}
		}
		
		PercentualeRectTot = 100.0*(StarsRectFil.size()/StarsRect.size());
		PercentualeRect_Protostellar = 100.0*count_rect_protostellar/StarsRectFil.size();
		PercentualeRect_Prestellar = 100.0*count_rect_prestellar/StarsRectFil.size();
		PercentualeRect_Unbound = 100.0*count_rect_unbound/StarsRectFil.size();
		PercentualeNotRectTot = 100.0*(StarsRect.size() - StarsRectFil.size())/StarsRect.size();
		Percentuali.add(0, PercentualeRectTot); 
		Percentuali.add(1,PercentualeRect_Protostellar); 
		Percentuali.add(2,PercentualeRect_Prestellar); 
		Percentuali.add(3,PercentualeRect_Unbound); 
		Percentuali.add(4,PercentualeNotRectTot); 
	
		return Percentuali;
		
	}
	public static int getnStelleFlusso() {
		return nStelleFlusso;
	}
	public static void setnStelleFlusso(int nStelleFlusso) {
		StellaBean.nStelleFlusso = nStelleFlusso;
	}
	
}

