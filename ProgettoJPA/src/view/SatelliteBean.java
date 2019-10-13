package view;

import java.util.List;

import controller.SatelliteController;
import dao.SatelliteDao;
import exception.AgenziaObbligatoriaException;
import exception.DataObbligatoriaException;
import exception.IllegalLastObsException;
import exception.InfoSatelliteException;
import exception.SatelliteEsistenteException;
import model.Agenzia;
import model.Satellite;

public class SatelliteBean {

	private String primaOp;
	private String nomeSat;
	private String agenzia;
	private String termineOp = "Ancora attivo" ;
	
	private static String visualSat;
	
	private SatelliteController satControl = new SatelliteController();
	
	public String getVisualSat() {
		return visualSat;
	}
	public void setVisualSat(String visualSat) {
		SatelliteBean.visualSat = visualSat;
	}
	
	public String getPrimaOp() {
		return primaOp;
	}
	public void setPrimaOp(String primaOp) {
		this.primaOp = primaOp;
	}
	public String getNomeSat() {
		return nomeSat;
	}
	public void setNomeSat(String nomeSat) {
		this.nomeSat = nomeSat;
	}
	public String getAgenzia() {
		return agenzia;
	}
	public void setAgenzia(String agenzia) {
		this.agenzia = agenzia;
	}
	public String getTermineOp() {
		return termineOp;
	}
	public void setTermineOp(String termineOp) {
		this.termineOp = termineOp;
	}
	
	/*METODI*/
	
	public void insertSatellite() throws SatelliteEsistenteException, InfoSatelliteException{
		if(this.getNomeSat() == null || this.getPrimaOp() == null){
			throw new InfoSatelliteException();
		}
		satControl.insertSatellite(this.getNomeSat(), this.getPrimaOp(), this.getAgenzia());
	}
	
	public List<Satellite> getSatelliti(){
		if(satControl.getSatelliti() != null){
			return satControl.getSatelliti();
		}
		return null;
	}
	
	public Satellite getSatelliteDaVisualizzare(String idSatellite){
		return satControl.getSatelliteDaVisualizzare(idSatellite);
	}
	
	public void insertLastObs(String idSat, String lastObs) throws DataObbligatoriaException, IllegalLastObsException{
		if(lastObs == null || "".equals(lastObs)){
			throw new DataObbligatoriaException();
		}
		satControl.insertLastObs(idSat, lastObs);
	}
	
	public void insertAgenzia(String idSat, String agenzia) throws AgenziaObbligatoriaException{
		if(agenzia == null || "".equals(agenzia)){
			throw new AgenziaObbligatoriaException();
		}
		satControl.insertAgenzia(idSat, agenzia);
	}
	
	public List<Agenzia> getAgenzie(){
		return satControl.findAgenzie();
	}
}
