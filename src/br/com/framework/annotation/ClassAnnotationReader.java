package br.com.framework.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import br.com.framework.factory.AppLoggerFactory;
import br.com.framework.logger.AppLogger;

public class ClassAnnotationReader<T> extends AbstractAnnotationReader<T> {

	private boolean hasCountedAnnotations;
	private int annotationsNumber;

	private List<Annotation> annotations;
	private AppLogger appLogger;

	public ClassAnnotationReader(T t, Class<T> c) {
		super(t, c);

		this.appLogger = AppLoggerFactory.getLogger(ClassAnnotationReader.class);
	}

	@Override
	public List<Annotation> read() {
		this.appLogger.info("Trying to list all the annotations in the class.");
		if(this.annotations == null) {
			this.annotations = new ArrayList<Annotation>();

			this.appLogger.debug("Reading all the class annotations");
			Annotation[] annotations = super.getC().getAnnotations();
			for(Annotation annotation : annotations) {
				if(annotation instanceof Thread) {
					this.annotations.add(annotation);
				}
			}

			this.appLogger.debug("Reading all the constructor annotations");
			Constructor<?>[] constructors = super.getC().getConstructors();
			for(Constructor<?> c : constructors) {
				if(c.isAnnotationPresent(ThreadConstructor.class)) {
					this.annotations.add(c.getAnnotation(ThreadConstructor.class));
				}
			}

			this.appLogger.debug("Reading all the method annotations");
			Method[] methods = super.getC().getDeclaredMethods();
			for(Method m : methods) {
				if(m.isAnnotationPresent(Parallel.class)) {
					this.annotations.add(m.getAnnotation(Parallel.class));
				}
			}
		}

		return this.annotations;
	}

	@Override
	public int countAnnotations() {
		this.appLogger.info("Trying to count all the annotations in the class.");
		if(!this.hasCountedAnnotations) {
			if(this.hasClassAnnotation()) {
				this.annotationsNumber++;
			}

			Constructor<?>[] constructors = super.getC().getConstructors();

			for(Constructor<?> c : constructors) {
				if(c.isAnnotationPresent(ThreadConstructor.class)) {
					this.annotationsNumber++;
				}
			}

			Method[] methods = super.getC().getDeclaredMethods();

			for(Method m : methods) {
				if(m.isAnnotationPresent(Parallel.class)) {
					this.annotationsNumber++;
				}
			}
		}

		return this.annotationsNumber;
	}

	@Override
	public boolean hasClassAnnotation() {
		this.appLogger.info("Testing if the annotation Thread is present in this class");
		return super.getC().getAnnotation(Thread.class) != null;
	}

	@Override
	public boolean hasConstructorAnnotation() {
		this.appLogger.info("Looking for constructor annotations");
		Constructor<?>[] constructors = super.getC().getConstructors();

		for(Constructor<?> c : constructors) {
			if(c.isAnnotationPresent(ThreadConstructor.class)) {
				this.appLogger.debug("Constructor annotation has been founded!");
				return true;
			}
		}

		return false;
	}

	@Override
	public boolean hasMethodAnnotation() {
		this.appLogger.info("Looking for method annotations");
		Method[] methods = super.getC().getDeclaredMethods();

		for(Method m : methods) {
			if(m.isAnnotationPresent(Parallel.class)) {
				this.appLogger.debug("Method annotation has been founded!");
				return true;
			}
		}

		return false;
	}

}
