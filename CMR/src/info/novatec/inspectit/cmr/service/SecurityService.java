package info.novatec.inspectit.cmr.service;


import javax.annotation.PostConstruct;

import info.novatec.inspectit.spring.logger.Log;

import org.slf4j.Logger;
import org.springframework.stereotype.Service;


/**
 * Provides general security operations for client<->cmr interaction.
 * 
 * @author Andreas Herzog
 * @author Clemens Geibel
 */
@Service
public class SecurityService implements ISecurityService {
	
	/** The logger of this class. */
	@Log
	Logger log;

	/**
	 * If this works, we rock.
	 */
	private String message = "You rock!";

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getMessage() {
		return message;
	}

	/**
	 * Is executed after dependency injection is done in order to perform any initialization.
	 */
	@PostConstruct
	public void postConstruct() {
		if (log.isInfoEnabled()) {
			log.info("|-Security Service active...");
		}
	}

}
