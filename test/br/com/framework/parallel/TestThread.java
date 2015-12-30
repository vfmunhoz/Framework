package br.com.framework.parallel;

import br.com.framework.model.MyThreadForTests;

public class TestThread {

	public static void main(String[] args) {
		MyThreadForTests myThreadForTests = new MyThreadForTests(0);

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
