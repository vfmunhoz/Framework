package br.com.framework.annotation;

import java.lang.annotation.Annotation;
import java.util.List;

/**
 * This class is responsible for reading the annotations of an object.
 * 
 * @author viniciusmunhoz
 */
public abstract class AbstractAnnotationReader<T> implements AnnotationReader<T> {

	private T t;
	private Class<T> c;

	protected AbstractAnnotationReader(T t, Class<T> c) {
		this.t = t;
		this.setC(c);
	}

	@Override
	public T getObject() {
		return t;
	}

	@Override
	public void setObject(T t) {
		this.t = t;
	}

	public abstract List<Annotation> read();

	public abstract int countAnnotations();

	public abstract boolean hasClassAnnotation();

	public abstract boolean hasConstructorAnnotation();

	public abstract boolean hasMethodAnnotation();

	public void setC(Class<T> c) {
		this.c = c;
	}

	public Class<T> getC() {
		return c;
	}
}
