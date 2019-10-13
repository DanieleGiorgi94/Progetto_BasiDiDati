package view;

import javax.persistence.PersistenceException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.*;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.oreilly.servlet.MultipartRequest;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.List;
import controller.CSVImportController;
import exception.FileException;
import exception.FileFormatException;

@WebServlet(name ="UploadCSVServlet")
@MultipartConfig(
	    maxFileSize= 1024 * 1024 * 100,     
	    fileSizeThreshold= 1024 * 1024 * 50, 
	    maxRequestSize= 1024 * 1024 * 100      
	)

public class UploadCSVServlet extends HttpServlet {

	private Part filePart;
	private String description;
	private String fileName;
	private String type;
	private CSVImportController controller;
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/Importazione di un file.jsp");
		try {
			String type = request.getParameter("optradio"); 
		    Part filePart = request.getPart("dataFile"); 
		    String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); 
		    InputStream fileContent = filePart.getInputStream();

			if (filePart == null || fileName == null) {
				throw new FileException();
			}

			controller = CSVImportController.getInstance();
			controller.processFile(filePart,type, fileContent);
			request.setAttribute("fileup", "TRUE");
		}catch (FileException e) {
				request.setAttribute("fileup", "FALSE");
		} catch (FileFormatException fe) {
			request.setAttribute("fileup", "FALSE");
		} catch (IOException ie) {
			request.setAttribute("fileup", "FALSE");
		} catch (PersistenceException pe) {
			request.setAttribute("fileup", "FALSE");
		} finally {
			requestDispatcher.forward(request, response);
		}

	}

	private String generateRandomName(String fileName) {
		// TODO RANDOM NAME
		return fileName;
	}
}
