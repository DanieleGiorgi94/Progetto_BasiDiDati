package controller;

import java.util.ArrayList;
import java.util.List;

import dao.StellaDao;
import model.Stella;

public class StellaController {

	public ArrayList<Stella> getAllStars(Integer filamento_id) {
		return StellaDao.getAllStars(filamento_id);
		
	}

	public List<Stella> getObjRectangle(float lat, float longit, float latoA, float latoB) { // stelle all'interno del rettangolo
		// TODO Auto-generated method stub
		return StellaDao.getObjRectangle(lat,longit,latoA,latoB);
	}

	public ArrayList<Stella> getAllStarsRectangle(float latitudine, float longitudine, float latoA, float latoB) throws InterruptedException {
		// TODO Auto-generated method stub
		return StellaDao.getAllStarsRectangle(latitudine,longitudine,latoA,latoB);
	}

	/*public Integer  getAllStars2() throws InterruptedException {
		// TODO Auto-generated method stub
		return StellaDao.getAllStars2();
	}
*/
	/*public ArrayList<Stella> insertDistanze(ArrayList<Stella> stelleFilamento) {
		// TODO Auto-generated method stub
		return StellaDao.insertDistanze(stelleFilamento);
	}*/

}