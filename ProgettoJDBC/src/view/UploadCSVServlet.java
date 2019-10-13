package view;

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
import java.sql.SQLException;
import java.util.List;
import controller.CSVImportController;
import exception.FileException;
import exception.FileFormatException;

@WebServlet(name ="UploadCSVServlet")
@MultipartConfig(
	    maxFileSize= 1024 * 1024 * 100,     // 100Mb max
	    fileSizeThreshold= 1024 * 1024 * 50, //52 Mb before buffering to disk
	    maxRequestSize= 1024 * 1024 * 100      // 100 Mb of meta data
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
			type = request.getParameter("optradio");
		    Part filePart = request.getPart("dataFile"); 
		    String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // MSIE fix.
		    InputStream fileContent = filePart.getInputStream();

			if (filePart == null || fileName == null) {
				throw new FileException();
			}

			controller = CSVImportController.getInstance();
			controller.processFile(filePart,type, fileContent);
			request.setAttribute("fileup", "TRUE");
		}catch (FileException e) {
			e.printStackTrace();
				request.setAttribute("fileup", "FALSE");
		} catch (FileFormatException fe) {
			fe.printStackTrace();
			request.setAttribute("fileup", "FALSE");
		} catch (IOException ie) {
			ie.printStackTrace();
			request.setAttribute("fileup", "FALSE");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			requestDispatcher.forward(request, response);
		}

	}

	private String generateRandomName(String fileName) {
		// TODO RANDOM NAME
		return fileName;
	}
}