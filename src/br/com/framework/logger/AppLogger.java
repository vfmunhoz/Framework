package br.com.framework.logger;

import org.apache.log4j.Logger;

/**
 * This class encapsulates the log4j logger object.
 * 
 * @author viniciusmunhoz
 *
 */
public class AppLogger {

	private Logger logger;

	/**
	 * 
	 * @param logger
	 * 		The logger object from the log4j library.
	 */
	public AppLogger(Logger logger) {
		this.logger = logger;
	}

	/**
	 * This method is used to log as a debug
	 * @param message
	 * 		To be logged
	 */
	public void debug(String message) {
		this.logger.debug(message);
	}

	/**
	 * This method is used to log as a info
	 * @param message
	 * 		To be logged
	 */
	public void info(String message) {
		this.logger.info(message);
	}

	/**
	 * This method is used to log as a warn
	 * @param message
	 * 		To be logged
	 */
	public void warn(String message) {
		this.logger.warn(message);
	}

	/**
	 * This method is used to log as a error
	 * @param message
	 * 		To be logged
	 */
	public void error(String message) {
		this.logger.error(message);
	}

	/**
	 * This method is used to log as a error
	 * @param message
	 * 		To be logged
	 * @param e
	 * 		The exception that made this log be executed
	 */
	public void error(String message, Exception e) {
		this.logger.error(message, e);
	}

	/**
	 * This method is used to log a fatal error
	 * @param message
	 * 		To be logged
	 */
	public void fatal(String message) {
		this.logger.fatal(message);
	}

	/**
	 * This method is used to log a fatal error
	 * @param message
	 * 		To be logged
	 * @param e
	 * 		The exception that made this log be executed
	 */
	public void fatal(String message, Exception e) {
		this.logger.fatal(message, e);
	}
}
