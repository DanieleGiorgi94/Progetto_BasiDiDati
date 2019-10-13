package controller;

import java.util.ArrayList;
import java.util.List;

import dao.SegmentoDao;
import model.Segmento;

public class SegmentoController {

	public ArrayList<Double> getMinVertex(Integer idsegmento) {
		return SegmentoDao.getMinVertex(idsegmento);
	}

	public ArrayList<Double> getMaxVertex(Integer idsegmento) {
		// TODO Auto-generated method stub
		return SegmentoDao.getMaxVertex(idsegmento);
	}

}