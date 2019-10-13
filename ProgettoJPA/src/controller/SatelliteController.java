package controller;

import java.util.List;

import dao.SatelliteDao;
import exception.DataObbligatoriaException;
import exception.IllegalLastObsException;
import exception.SatelliteEsistenteException;
import model.Agenzia;
import model.Satellite;


public class SatelliteController {

	public void insertSatellite(String nomeSatellite, String primaOp, String agenzia) throws SatelliteEsistenteException{		
		SatelliteDao.insertSatellite(nomeSatellite, primaOp, agenzia);
	}
	
	public void insertAgenzia(String idSatellite, String agenzia){
		SatelliteDao.insertAgenzia(idSatellite, agenzia);
	}
	
	public List<Satellite> getSatelliti(){
		return SatelliteDao.getSatelliti();
	}
	
	public Satellite getSatelliteDaVisualizzare(String idSatellite){
		return SatelliteDao.getSatelliteDaVisualizzare(idSatellite);
	}
	
	public void insertLastObs(String idSatellite, String lastObs) throws DataObbligatoriaException, IllegalLastObsException{
		SatelliteDao.insertLastObs(idSatellite, lastObs);
	}
	
	public List<Agenzia> findAgenzie(){
		return SatelliteDao.findAgenzie();
	}
}
