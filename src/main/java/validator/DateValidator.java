package validator;

import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import modele.Computer;

public class DateValidator {

	private static Logger logger = LoggerFactory.getLogger(DateValidator.class);
	private static DateValidator instance = null;

	private DateValidator() {

	}

	public final static DateValidator getInstance() {
		if (DateValidator.instance == null) {
			instance = new DateValidator();
		}
		return instance;
	}
	public boolean dateIsValid(Computer computer) {
		LocalDate introduced = computer.getIntroduced(), discontinued = computer.getDiscontinued();
		boolean isValid = false;
		if(introduced == null && discontinued != null) {
			isValid = false;
		}
		else {
			 if(introduced.isAfter(discontinued)) {
				 logger.error("date de début trop grand par rapport à date de fin");
				 isValid = false;
			 }
			 else 
				 isValid = true;
		}
		return isValid;
	}

}