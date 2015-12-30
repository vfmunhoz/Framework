package br.com.framework.parallel;

import org.junit.Test;

import br.com.framework.model.MyThreadForTests;


public class ParallelExecutorTest {

	@Test
	public void executeTest() {
		MyThreadForTests myThreadForTests = new MyThreadForTests(0);
		//MyThreadForTests myThreadForTests1 = new MyThreadForTests(1);

		ParallelExecutor<MyThreadForTests> executor = new ParallelExecutor<MyThreadForTests>(myThreadForTests, MyThreadForTests.class);
		try {
			executor.executeParallel();
		} catch (InvalidObjectException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
