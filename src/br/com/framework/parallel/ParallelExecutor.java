package br.com.framework.parallel;

import br.com.framework.annotation.AnnotationReader;
import br.com.framework.factory.AnnotationReaderFactory;
import br.com.framework.factory.AppLoggerFactory;
import br.com.framework.jpl.ParallelExecutorJPL;

public class ParallelExecutor<T> {

	private T t;
	private Class<T> c;

	public ParallelExecutor(T t, Class<T> c) {
		this.t = t;
		this.c = c;
	}

	public void executeParallel() throws InvalidObjectException, ExecutionException {
		AppLoggerFactory.getLogger(ParallelExecutor.class).debug("Starting the parallel execution...");
		AnnotationReader<T> annotationReader = new AnnotationReaderFactory<T>(this.t, this.c).getAnnotationReader();

		if(!(this.t instanceof ExecuteParallel && annotationReader.hasClassAnnotation() && annotationReader.hasMethodAnnotation())) {
			AppLoggerFactory.getLogger(ParallelExecutor.class).debug("Testing if the class is ready to execute...");
			throw new InvalidObjectException("Invalid Object type or invalid annotations");
		}

		AppLoggerFactory.getLogger(ParallelExecutor.class).debug("Creating parallel executor with: 2 threads");
		ParallelExecutorJPL executor = new ParallelExecutorJPL(1, (ExecuteParallel) this.t, 2);

		try {
			AppLoggerFactory.getLogger(ParallelExecutor.class).debug("Executing...");
			executor.execute();
		} catch (Exception e) {
			throw new ExecutionException("Error trying to execute.", e);
		}

		AppLoggerFactory.getLogger(ParallelExecutor.class).info("Parallel method executed successful");
	}
}
