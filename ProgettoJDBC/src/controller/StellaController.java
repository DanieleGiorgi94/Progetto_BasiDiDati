package controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.StellaDao;
import model.Stella;

public class StellaController {

	public ArrayList<Stella> getAllStars(Integer filamento_id) throws Exception {
		return StellaDao.getAllStars(filamento_id);
		
	}

	public List<Stella> getObjRectangle(float lat, float longit, float latoA, float latoB) throws SQLException { // stelle all'interno del rettangolo
		// TODO Auto-generated method stub
		return StellaDao.getObjRectangle(lat,longit,latoA,latoB);
	}

	public ArrayList<Stella> getAllStarsRectangle(float latitudine, float longitudine, float latoA, float latoB) throws Exception {
		// TODO Auto-generated method stub
		return StellaDao.getAllStarsRectangle(latitudine,longitudine,latoA,latoB);
	}

}