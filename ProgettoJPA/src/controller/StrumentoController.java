package controller;

import java.util.List;
import java.util.Vector;

import dao.StrumentoDao;
import model.Banda;
import model.Satellite;

public class StrumentoController {
	
	public List<Banda> getBande(){
		return StrumentoDao.findBande();
	}
	
	public List<Satellite> getSatelliti(){
		return StrumentoDao.findSatelliti();
	}
	
	public void inserisciStrumento(String[] bande, String satellite, String nomeStrumento){
		Vector<Float> vBanda = new Vector<Float>(1);
				
		if(bande != null ){
			for(int i=0; i<bande.length; i++)
				vBanda.add(Float.parseFloat(bande[i]));
		}else{
			vBanda = null;
		}
		
		StrumentoDao.insertStrumento(vBanda, satellite, nomeStrumento);
	}
}