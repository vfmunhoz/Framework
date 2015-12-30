package br.com.framework.factory;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import br.com.framework.annotation.AnnotationReader;
import br.com.framework.annotation.ClassAnnotationReader;
import br.com.framework.model.MyThreadForTests;

public class AnnotationReaderFactoryTest {

	@Test
	public void testGetAnnotationReader() {
		MyThreadForTests classAnnotation = new MyThreadForTests();
		AnnotationReaderFactory<MyThreadForTests> readerFactory = new AnnotationReaderFactory<MyThreadForTests>(classAnnotation, MyThreadForTests.class);

		assertNotNull(readerFactory);

		AnnotationReader<MyThreadForTests> annotationReader = readerFactory.getAnnotationReader();

		assertNotNull(annotationReader);
		assertTrue(annotationReader instanceof ClassAnnotationReader<?>);
	}
}
