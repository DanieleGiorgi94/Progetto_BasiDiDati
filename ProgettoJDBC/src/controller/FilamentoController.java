package controller;


import java.sql.SQLException;
import java.util.List;
import dao.FilamentoDao;
import model.Filamento;

public class FilamentoController {

	public Filamento getFilamenti(Integer id, String nome) {
		return FilamentoDao.getFilamenti(id,nome);
	}
	

	public Filamento getFilamentiPerId(Integer id) {
		return FilamentoDao.getFilamentiPerId(id);
	}
	

	public Filamento getFilamentiPerNome(String nome) {
		return FilamentoDao.getFilamentiPerNome(nome);
	}


	public List<Filamento> getAllFilamenti() {
		return FilamentoDao.getAllFilamenti();
	}
	
	public List<Filamento> ricercaperellitticitaebrillanza(float index1,float index2,float brillanza) {
		
		return FilamentoDao.ricercaperellitticitaebrillanza(index1,index2,brillanza);
	}


	public Long getCountAllFilamenti() throws SQLException {
		
		return FilamentoDao.getCountAllFilamenti();
	}
}
