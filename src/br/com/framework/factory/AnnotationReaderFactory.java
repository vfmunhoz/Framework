package br.com.framework.factory;

import br.com.framework.annotation.AnnotationReader;
import br.com.framework.annotation.ClassAnnotationReader;

/**
 * This class is responsible for create all the AnnotationReader objects.
 * 
 * @author viniciusmunhoz
 *
 * @param <T>
 * 		The class to be read
 */
public class AnnotationReaderFactory<T> {

	private T t;
	private Class<T> c;

	/**
	 * This is the only constructor.
	 * @param t
	 * 		The object that will be read.
	 * @param c
	 * 		The class that can create the object to be read
	 */
	public AnnotationReaderFactory(T t, Class<T> c) {
		this.t = t;
		this.c = c;
	}

	/**
	 * This method return creates the reader.
	 * 
	 * @return
	 * 		An annotation reader ready to be used.
	 */
	public AnnotationReader<T> getAnnotationReader() {
		AppLoggerFactory.getLogger(AnnotationReaderFactory.class).info("Creating a new AnnotationReader");
		return new ClassAnnotationReader<T>(this.t, this.c);
	}
}
