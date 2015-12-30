package br.com.framework.annotation;

import static org.junit.Assert.*;

import java.lang.annotation.Annotation;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import br.com.framework.factory.AnnotationReaderFactory;
import br.com.framework.model.MyThreadForTests;

public class ClassAnnotationReaderTest {

	private AnnotationReader<MyThreadForTests> annotationReader;

	@Before
	public void setUp() throws Exception {
		MyThreadForTests classAnnotation = new MyThreadForTests();
		AnnotationReaderFactory<MyThreadForTests> readerFactory = new AnnotationReaderFactory<MyThreadForTests>(classAnnotation, MyThreadForTests.class);
		AnnotationReader<MyThreadForTests> annotationReader = readerFactory.getAnnotationReader();

		this.annotationReader = annotationReader;
	}

	@Test
	public void testHasClassAnnotation() {
		assertTrue(this.annotationReader.hasClassAnnotation());
	}

	@Test
	public void testHasConstructorAnnotation() {
		assertTrue(this.annotationReader.hasConstructorAnnotation());
	}

	@Test
	public void testHasMethodAnnotation() {
		assertTrue(this.annotationReader.hasMethodAnnotation());
	}

	@Test
	public void testRead() {
		List<Annotation> annotations = this.annotationReader.read();

		assertNotNull(annotations);
		assertEquals(3, annotations.size());
	}

	@Test
	public void testCountAnnotations() {
		assertEquals(3, this.annotationReader.countAnnotations());
	}
}
