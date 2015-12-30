package br.com.framework.factory;

import org.apache.log4j.Logger;

import br.com.framework.logger.AppLogger;

/**
 * This class creates all the loggers.
 * 
 * @author viniciusmunhoz
 */
public class AppLoggerFactory {

	/**
	 * Creates the logger according to the specified name.
	 * @param name
	 * 		The logger name.
	 * @return
	 * 		The logger object ready to use.
	 */
	public static AppLogger getLogger(String name) {
		return new AppLogger(Logger.getLogger(name));
	}

	/**
	 * Creates the logger according to the specified class.
	 * @param c
	 * 		The class that will use the logger.
	 * @return
	 * 		The logger object ready to use
	 */
	public static AppLogger getLogger(Class<?> c) {
		return new AppLogger(Logger.getLogger(c));
	}
}
