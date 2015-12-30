package br.com.framework.annotation;

import java.lang.annotation.Annotation;
import java.util.List;

/**
 * 
 * Defines the methods for all the annotations readers.
 * 
 * @author viniciusmunhoz
 *
 * @param <T>
 * 		The class to be read by the annotation reader.
 */
public interface AnnotationReader<T> {
	/**
	 * 
	 * @return
	 * 		The object that is been read.
	 */
	public T getObject();

	/**
	 * 
	 * @param t
	 * 		The object to be read.
	 */
	public void setObject(T t);

	/**
	 * Read all the annotations of the object.
	 * 
	 * @return
	 * 		A collection with all the annotations
	 */
	public List<Annotation> read();

	/**
	 * 
	 * @return
	 * 		The number of annotations in the class.
	 */
	public int countAnnotations();

	/**
	 * 
	 * @return
	 * 		True if the class has an annotation on her declaration
	 */
	public boolean hasClassAnnotation();

	/**
	 * 
	 * @return
	 * 		True if the class has at least one constructor with an annotation
	 */
	public boolean hasConstructorAnnotation();

	/**
	 * 
	 * @return
	 * 		True if the class has a method with an annotation
	 */
	public boolean hasMethodAnnotation();
}
