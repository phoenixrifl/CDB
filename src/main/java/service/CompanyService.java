package main.java.service;

import java.util.ArrayList;

import main.java.dto.CompanyDTO;
import main.java.dto.Mappeur;
import main.java.modele.Company;
import main.java.persistence.CompanyDAO;


public class CompanyService {

	private CompanyDAO companyDAO;
	private Mappeur mappeur;
	
	private static CompanyService instance = null;
	
	private CompanyService() {
		this.companyDAO = CompanyDAO.getInstance();
		this.mappeur = Mappeur.getInstance();
	}
	
	public final static CompanyService getInstance() {
		if(CompanyService.instance == null) {
			instance = new CompanyService();
		}
		return instance;
	}


	public CompanyDTO find(long id) {
		Company company = this.companyDAO.find(id);
		return this.mappeur.ModelToDTOCompany(company);
	}
	
	public ArrayList<CompanyDTO> findAll(int limits, int offset) {
		ArrayList<Company> companies = this.companyDAO.findAll(limits, offset);
		return this.mappeur.ModelToDTO_(companies);
	}
	
	public ArrayList<CompanyDTO> findAll() {
		ArrayList<Company> companies = this.companyDAO.findAll();
		return this.mappeur.ModelToDTO_(companies);
	} 
}
