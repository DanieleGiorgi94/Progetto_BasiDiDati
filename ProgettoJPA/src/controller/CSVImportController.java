package controller;

import java.io.*;
import java.nio.file.Paths;

import javax.persistence.PersistenceException;
import javax.servlet.http.Part;

import dao.ContornoDao;
import dao.FilamentoDao;
import dao.ScheletroDao;
import dao.StellaDao;
import exception.FileException;
import exception.FileFormatException;

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
	
	public void processFile( Part filePart, String type, InputStream fileContent) throws IOException,PersistenceException,FileNotFoundException, FileFormatException{
		
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
                		ContornoDao.insertHerschel(outlineFilamentsHerschel);
                		break;
                	case "2":
                		String file2 =  getFileName(filePart);
                		if(!"contorni_filamenti_Spitzer.csv".equals(file2)){
                			throw new FileFormatException();
                		}
                		System.out.println("marco");
                		 String[] outlineFilamentsSpitzer = line.split(";");
                		 ContornoDao.insertSpitzer(outlineFilamentsSpitzer);
                		break;
                	case "3":
                		String file3 =  getFileName(filePart);
                		if(!"filamenti_Herschel.csv".equals(file3)){
                			throw new FileFormatException();
                		}
                		String[] filamentsHerschel = line.split(",");
                		 FilamentoDao.insertHerschel(filamentsHerschel);
                		break;
                	case "4":
                		String file4 =  getFileName(filePart);
                		if(!"filamenti_Spitzer.csv".equals(file4)){
                			throw new FileFormatException();
                		}
                		String[] filamentsSpitzer = line.split(";");
                		 FilamentoDao.insertSpitzer(filamentsSpitzer);
                		break;
                	case "5":
                		String file5 =  getFileName(filePart);
                		if(!"scheletro_filamenti_Herschel.csv".equals(file5)){
                			throw new FileFormatException();
                		}
                		String[] skeleton_Herschel = line.split(",");
                		 ScheletroDao.insertHeschel(skeleton_Herschel);
                		 break;
                	case "6":
                		String file6 =  getFileName(filePart);
                    	if(!"scheletro_filamenti_Spitzer.csv".equals(file6)){
                    		throw new FileFormatException();
                    	}
                    	String[] skeleton_Spitzer = line.split(";");
                    	 ScheletroDao.insertSpitzer(skeleton_Spitzer);
                    	 break;
                    	 
                	case "7":
                		String file7 =  getFileName(filePart);
                    	if(!"stelle_Herschel.csv".equals(file7)){
                    		throw new FileFormatException();
                    	}
                    	String[] stars_Herschel = line.split(";");
                    	 StellaDao.insertHerschel(stars_Herschel);
                    	 break;
                }
            	} 
            	  
         startReading = true;
         }
       
	}catch(NullPointerException e){
		System.out.println("Il file è stato inserito correttamente!");
	}
	}
}

