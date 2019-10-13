package controller;

import java.util.ArrayList;
import java.util.List;

import dao.ScheletroDao;
import model.Scheletro;
import model.Stella;
import model.Filamento;

public class ScheletroController {
	
	public Integer numeroSegmenti(Integer id) {
		
		return ScheletroDao.numeroSegmenti(id);
	}
	
	public List<Integer> filamentipersegmenti(Integer Index1,Integer Index2) {
		return ScheletroDao.filamentipersegmenti(Index1,Index2);
	}

	public ArrayList<Stella> DistanzaSpina(Integer filamento_id, ArrayList<Stella> StelleFilamento) {
		// TODO Auto-generated method stub
		return ScheletroDao.DistanzaSpina(filamento_id,StelleFilamento);
	}
	
	public double getMinDistance(double lat, double longit,Integer filamento_id) {
		// TODO Auto-generated method stub
		return ScheletroDao.getMinDistance(lat, longit,filamento_id);
	}

	public Integer IdFilamentoVerticiSegmento(double lat, double longit) {
		// TODO Auto-generated method stub
		return ScheletroDao.IdFilamentoVerticiSegmento(lat, longit);
	}
}
