package controller;

import java.util.List;
import java.util.Vector;

import dao.ContornoDao;
import model.Contorno;
import model.Filamento;

public class ContornoController {
	
	public double[] getEstensione(Integer id) {
        // TODO Auto-generated method stub
        return ContornoDao.getEstensione(id);
    }

	public List<Filamento> getObjcircle(float lat, float longit, float raggio) {
		// TODO Auto-generated method stub
		return ContornoDao.getObjCircle(lat,longit,raggio);
	}
	
	public List<Filamento> getObjSquare(float lat, float longit, float lato) {
		// TODO Auto-generated method stub
		return ContornoDao.getObjSquare(lat,longit,lato);
	}

	public List<Contorno> getContorniperId(Integer filamento_id) {
		return ContornoDao.getContorniperId(filamento_id);
	}

	public List<Contorno> getAllContorni() {
		// TODO Auto-generated method stub
		return ContornoDao.getAllContorni();
	}

	public List<Integer> getObjRectangle(float lat, float longit, float latoA,float latoB) {
		// TODO Auto-generated method stub
		return ContornoDao.getObjRectangle(lat,longit,latoA,latoB);
	}

	public float[] getCentroide(Integer filamento_id) {
		// TODO Auto-generated method stub
		return ContornoDao.getCentroide(filamento_id);
	}
}
