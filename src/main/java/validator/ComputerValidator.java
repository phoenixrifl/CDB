package validator;

import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dto.ComputerDTO;

public class ComputerValidator {

	private static Logger logger = LoggerFactory.getLogger(ComputerValidator.class);
	private static ComputerValidator instance = null;

	private ComputerValidator() {

	}

	public final static ComputerValidator getInstance() {
		if (ComputerValidator.instance == null) {
			instance = new ComputerValidator();
		}
		return instance;
	}

	public boolean isAComputerValid(ComputerDTO computerdto) {
		if (isAValidName(computerdto.getName()) == false) {
			logger.error("Computer name invalid : " + computerdto.getName());
			return false;
		} else {
			return true;
		}
	}

	public boolean isAValidName(String name) {
		if (name.equals(null) || name.equals(""))
			return false;
		else
			return true;
	}

	public boolean dateIsValid(ComputerDTO computerdto) {
		LocalDate introduced = LocalDate.parse(computerdto.getIntroduced()),
				discontinued = LocalDate.parse(computerdto.getDiscontinued());
		if (introduced == null && discontinued != null) {
			logger.error("introduced is null and discontinued is not null");
			return false;
		} else {
			if (introduced.isAfter(discontinued)) {
				logger.error("introduced > discontinued");
				return false;
			}
		}
		return true;
	}

}