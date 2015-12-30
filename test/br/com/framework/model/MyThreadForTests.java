package br.com.framework.model;

import br.com.framework.annotation.Parallel;
import br.com.framework.annotation.Thread;
import br.com.framework.annotation.ThreadConstructor;
import br.com.framework.parallel.ExecuteParallel;

@Thread
public class MyThreadForTests implements ExecuteParallel {

	private int id;

	public MyThreadForTests() {
	}

	@ThreadConstructor
	public MyThreadForTests(int id) {
		this.id = id;
	}

	@Override
	@Parallel
	public void parallel() {
		for(int i = 0; i < 200; i++) {
			System.out.println("Valor = " + i + " id: " + this.id);
			try {
				java.lang.Thread.sleep(50);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
