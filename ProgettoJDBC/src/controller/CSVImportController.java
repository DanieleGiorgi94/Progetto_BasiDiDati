package controller;

import java.io.*;
import java.math.BigDecimal;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.Part;

import dao.ContornoDao;
import dao.FilamentoDao;
import dao.ScheletroDao;
import dao.StellaDao;
import exception.FileFormatException;
import patternConnessione.DMsingleton;

public class CSVImportController {

	static CSVImportController instance;

	private CSVImportController(){}

	public static CSVImportController getInstance(){
		if(instance == null){
			instance = new CSVImportController();
		}
		return instance;
	}


	public  String getFileName(Part p){

		String GUIDwithext = Paths.get(p.getSubmittedFileName()).getFileName().toString();

		String GUID = GUIDwithext.substring(0, GUIDwithext.length () );
		return GUIDwithext;
	}

	public void processFile( Part filePart, String type, InputStream fileContent) throws IOException,FileNotFoundException, FileFormatException, ClassNotFoundException, SQLException{
		
		Connection conn = DMsingleton.getConnection();


		if(!filePart.getSubmittedFileName().endsWith(".csv")){
			throw new FileFormatException();
		}
		BufferedReader reader = new BufferedReader(new InputStreamReader(fileContent));
		String line;
		boolean startReading = false;

		try {
			while ((line = reader.readLine()) != null){
				if(startReading){
					switch(type){
					case "1":
						String file1 =  getFileName(filePart);
						if(!"contorni_filamenti_Herschel.csv".equals(file1)){
							throw new FileFormatException();
						}
						String[] outlineFilamentsHerschel = line.split(",");
						if (findDuplicateOutlines(outlineFilamentsHerschel[1], outlineFilamentsHerschel[2], outlineFilamentsHerschel[0], conn)) {
							ContornoDao.updateOutlines(outlineFilamentsHerschel, conn);
						}
						else {               			 
							ContornoDao.insertHerschel(outlineFilamentsHerschel, conn);
						}
						break;
					case "2":
						String file2 =  getFileName(filePart);
						if(!"contorni_filamenti_Spitzer.csv".equals(file2)){
							throw new FileFormatException();
						}
						String[] outlineFilamentsSpitzer = line.split(",");
						if (findDuplicateOutlines(outlineFilamentsSpitzer[1], outlineFilamentsSpitzer[2], outlineFilamentsSpitzer[0], conn)) {
							ContornoDao.updateOutlines(outlineFilamentsSpitzer, conn);
						}
						else {               			 
							ContornoDao.insertSpitzer(outlineFilamentsSpitzer, conn);
						}
						break;
					case "3":
						String file3 =  getFileName(filePart);
						if(!"filamenti_Herschel.csv".equals(file3)){
							throw new FileFormatException();
						}
						String[] filamentsHerschel = line.split(",");
						if (findDuplicateFilaments(filamentsHerschel[0], conn)){
							FilamentoDao.updateFilaments(filamentsHerschel, conn);
						}else {
							FilamentoDao.insertHerschel(filamentsHerschel, conn);
						}
						break;
					case "4":
						String file4 =  getFileName(filePart);
						if(!"filamenti_Spitzer.csv".equals(file4)){
							throw new FileFormatException();
						}
						String[] filamentsSpitzer = line.split(",");
						FilamentoDao.insertSpitzer(filamentsSpitzer, conn);
						break;
					case "5":
						String file5 =  getFileName(filePart);
						if(!"scheletro_filamenti_Herschel.csv".equals(file5)){
							throw new FileFormatException();
						}
						String[] skeleton_Herschel = line.split(",");
						
						if(findDuplicateSkeleton(skeleton_Herschel, conn)) {
							ScheletroDao.updateSkeleton(skeleton_Herschel, conn);
						}
						else {
							ScheletroDao.insertHeschel(skeleton_Herschel, conn);
						}
						break;
					case "6":
						String file6 =  getFileName(filePart);
						if(!"scheletro_filamenti_Spitzer.csv".equals(file6)){
							throw new FileFormatException();
						}
						String[] skeleton_Spitzer = line.split(",");
						
						if(findDuplicateSkeleton(skeleton_Spitzer, conn)) {
							ScheletroDao.updateSkeleton(skeleton_Spitzer, conn);
						}
						else {
							ScheletroDao.insertSpitzer(skeleton_Spitzer, conn);
						}
						break;
						
					case "7":
						String file7 =  getFileName(filePart);
						if(!"stelle_Herschel.csv".equals(file7)){
							throw new FileFormatException();
						}
						String[] stars_Herschel = line.split(",");
						if(findDuplicateStars(stars_Herschel[0], conn)){
							StellaDao.updateStar(stars_Herschel, conn);
						}
						else {
							StellaDao.insertHerschel(stars_Herschel, conn);
						}
						break;
					}
				} 

				startReading = true;
			}

		}catch(NullPointerException e){
			System.out.println("Il file è stato inserito correttamente!");
		}
	}
	

	private boolean findDuplicateSkeleton(String skeleLine[], Connection conn) throws SQLException {
		
		PreparedStatement stmt = null;
		boolean found = false;

		if (conn == null || conn.isClosed()) 
			conn = DMsingleton.getConnection();


		stmt=conn.prepareStatement("SELECT puntoscheletro_longitudine, puntoscheletro_latitudine, filamento_id "+
				"FROM Scheletro "+
				"WHERE puntoscheletro_longitudine = ? and puntoscheletro_latitudine = ? and filamento_id = ? ");
		

		stmt.setDouble(1, Double.parseDouble(skeleLine[3]));
		stmt.setDouble(2, Double.parseDouble(skeleLine[4]));
		stmt.setInt(3, Integer.parseInt(skeleLine[0]));

		stmt= conn.prepareStatement("SELECT puntoscheletro_longitudine, puntoscheletro_latitudine, idsegmento, numeroprogressivo, tipo "+
				"FROM Segmento "+
				"WHERE puntoscheletro_longitudine = ? and puntoscheletro_latitudine = ?  and idsegmento = ? and numeroprogressivo = ? and tipo = ? ");
		
		stmt.setDouble(1, Double.parseDouble(skeleLine[3]));
		stmt.setDouble(2, Double.parseDouble(skeleLine[4]));
		stmt.setInt(3, Integer.parseInt(skeleLine[1]));
		stmt.setInt(4, Integer.parseInt(skeleLine[5]));
		stmt.setString(5,  skeleLine[2]);
				
				
		ResultSet rs = stmt.executeQuery();

		found = rs.next();

		rs.close();
		stmt.close();

		return found;

	}
	

	private boolean findDuplicateStars(String id, Connection conn) throws SQLException {

		PreparedStatement stmt = null;
		boolean found = false;

		if (conn.isClosed() || conn == null)  
			conn = DMsingleton.getConnection();


		stmt=conn.prepareStatement("SELECT * "+
				"FROM Stella "+
				"WHERE stellaid = ? ");

		stmt.setInt(1, Integer.parseInt(id));


		ResultSet rs = stmt.executeQuery();

		found = rs.next();

		rs.close();
		stmt.close();

		return found;

	}
	

	private boolean findDuplicateFilaments(String id, Connection conn) throws SQLException{

		PreparedStatement stmt = null;
		boolean found = false;

		if (conn.isClosed() || conn == null)
			conn = DMsingleton.getConnection();


		stmt=conn.prepareStatement("SELECT id, nome, flussototale, densitamedia, temperaturamedia, ellitticita, contrasto "+
				"FROM filamento "+
				"WHERE id = ? ");

		stmt.setInt(1, Integer.parseInt(id));

		ResultSet rs = stmt.executeQuery();


		found = rs.next();

		rs.close();
		stmt.close();

		return found;

	}


	private boolean findDuplicateOutlines(String longitudine, String latitudine, String idFil, Connection conn) throws SQLException{

		PreparedStatement stmt = null;
		boolean found = false;

		if (conn == null || conn.isClosed()) 
			conn = DMsingleton.getConnection();


		stmt=conn.prepareStatement("SELECT * "+
				"FROM Contorno "+
				"WHERE puntocontorno_longitudine = ? and puntocontorno_latitudine = ? and filamento_id = ? ");

		stmt.setFloat(1, Float.parseFloat(longitudine));
		stmt.setFloat(2, Float.parseFloat(latitudine));
		stmt.setInt(3, Integer.parseInt(idFil));
	


		ResultSet rs = stmt.executeQuery();

		found = rs.next();

		rs.close();
		stmt.close();

		return found;


	}
}