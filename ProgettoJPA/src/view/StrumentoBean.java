package view;

import java.util.List;
import java.util.Vector;

import controller.StrumentoController;
import exception.BandaObbligatoriaException;
import exception.StrumentoObbligatorioException;
import model.Banda;
import model.Satellite;

public class StrumentoBean {

	StrumentoController sc = new StrumentoController();
	
	
	public List<Banda> getBande(){
		return sc.getBande();
	}
	
	public List<Satellite> getSatelliti(){
		return sc.getSatelliti();
	}
	
	//REQUISITO 3: INSERIRE DATI STRUMENTO CON RELATIVE BANDE 
	public void inserisciStrumento(String[] bande,String satellite, String nomeStrumento) throws BandaObbligatoriaException, StrumentoObbligatorioException{
		if(bande == null ){
			throw new BandaObbligatoriaException();
		}
		if("".equals(nomeStrumento)){
			throw new StrumentoObbligatorioException();
		}
		sc.inserisciStrumento(bande, satellite, nomeStrumento);
	}
}