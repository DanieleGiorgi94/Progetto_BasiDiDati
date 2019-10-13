package controller;

import java.sql.SQLException;
import java.util.List;

import dao.SatelliteDao;
import exception.DataObbligatoriaException;
import exception.IllegalLastObsException;
import exception.SatelliteEsistenteException;
import model.Agenzia;
import model.Satellite;


public class SatelliteController {

	public void insertSatellite(String nomeSatellite, String primaOp, String agenzia) throws SatelliteEsistenteException, SQLException{		
		SatelliteDao.insertSatellite(nomeSatellite, primaOp, agenzia);
	}
	
	public void insertAgenzia(String idSatellite, String agenzia) throws SQLException{
		SatelliteDao.insertAgenzia(idSatellite, agenzia);
	}
	
	public List<Satellite> getSatelliti() throws SQLException{
		return SatelliteDao.getSatelliti();
	}
	
	public Satellite getSatelliteDaVisualizzare(String idSatellite) throws SQLException{
		return SatelliteDao.getSatelliteDaVisualizzare(idSatellite);
	}
	
	public void insertLastObs(String idSatellite, String lastObs) throws DataObbligatoriaException, IllegalLastObsException, SQLException{
		SatelliteDao.insertLastObs(idSatellite, lastObs);
	}
	
	public List<Agenzia> findAgenzie() throws SQLException{
		return SatelliteDao.findAgenzie();
	}
}
