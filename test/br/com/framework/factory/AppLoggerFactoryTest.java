package br.com.framework.factory;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import br.com.framework.factory.AppLoggerFactory;
import br.com.framework.model.MyThreadForTests;

public class AppLoggerFactoryTest {

	@Test
	public void testGetLogger() {
		assertNotNull(AppLoggerFactory.getLogger(MyThreadForTests.class));
		assertNotNull(AppLoggerFactory.getLogger("Meu Log"));
	}
}
