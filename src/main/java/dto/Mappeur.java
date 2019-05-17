package dto;

import java.time.LocalDate;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import exception.DateFormatException;
import modele.Company;
import modele.Computer;

public class Mappeur {

	private static Mappeur instance = null;
	private static Logger logger = LoggerFactory.getLogger(Mappeur.class);

	private Mappeur() {

	}

	public final static Mappeur getInstance() {
		if (Mappeur.instance == null) {
			instance = new Mappeur();
		}
		return instance;
	}

	public ComputerDTO ModelToDTO(Computer computer) {
		ComputerDTO computerDTO = new ComputerDTO();
		if (computer != null) {
			computerDTO.setId(String.valueOf(computer.getId_()));
			computerDTO.setName(computer.getName());
			if(String.valueOf(computer.getIntroduced()).equals("null"))
					computerDTO.setIntroduced("");
			else
				computerDTO.setIntroduced(String.valueOf(computer.getIntroduced()));
			if(String.valueOf(computer.getDiscontinued()).equals("null"))
				computerDTO.setDiscontinued("");
			else
				computerDTO.setDiscontinued(String.valueOf(computer.getDiscontinued()));
			if (computer.getCompany() != null) {
				computerDTO.setCompany_id(String.valueOf(computer.getCompany().getId_()));
				if (computer.getCompany().getName() != null)
					computerDTO.setCompany_name(computer.getCompany().getName());
			}
		}
		return computerDTO;
	}

	public Computer DTOToModel(ComputerDTO computerDto) {
		LocalDate convIntro = null;
		LocalDate convDisco = null;
		Computer computer = null;
		try {
		
			if (!computerDto.getIntroduced().equals("null")) {
				convIntro = LocalDate.parse(computerDto.getIntroduced());
			}

			if (!computerDto.getDiscontinued().equals("null")) {
				convDisco = LocalDate.parse(computerDto.getDiscontinued());
			}
			computer = new Computer(Integer.parseInt(computerDto.getId()), computerDto.getName(), convIntro, convDisco, Integer.parseInt(computerDto.getCompany_id()), computerDto.getCompany_name());
			
		} catch (Exception e) {
			logger.error("erreur format date", new DateFormatException());
			throw e;
		}

		return computer;
	}

	public ArrayList<Computer> DTOToModel(ArrayList<ComputerDTO> computerDto) {
		ArrayList<Computer> tmp = new ArrayList<Computer>();
		for (ComputerDTO c : computerDto) {
			tmp.add(DTOToModel(c));
		}
		return tmp;
	}

	public ArrayList<ComputerDTO> ModelToDTO(ArrayList<Computer> computer) {
		ArrayList<ComputerDTO> tmp = new ArrayList<ComputerDTO>();
		for (Computer c : computer) {
			tmp.add(ModelToDTO(c));
		}
		return tmp;
	}

	public CompanyDTO ModelToDTOCompany(Company company) {
		return new CompanyDTO(String.valueOf(company.getId_()), company.getName());
	}

	public Company DTOTOModelCompany(CompanyDTO companyDto) {
		return new Company(Integer.parseInt(companyDto.getId()), companyDto.getName());
	}

	public ArrayList<Company> DTOToModel_(ArrayList<CompanyDTO> companyDto) {
		ArrayList<Company> tmp = new ArrayList<Company>();
		for (CompanyDTO c : companyDto) {
			tmp.add(DTOTOModelCompany(c));
		}
		return tmp;
	}

	public ArrayList<CompanyDTO> ModelToDTO_(ArrayList<Company> company) {
		ArrayList<CompanyDTO> tmp = new ArrayList<CompanyDTO>();
		for (Company c : company) {
			tmp.add(ModelToDTOCompany(c));
		}
		return tmp;
	}
}