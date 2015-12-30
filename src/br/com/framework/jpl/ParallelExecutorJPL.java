package br.com.framework.jpl;

import br.com.framework.parallel.ExecuteParallel;
import edu.rit.pj.LongForLoop;
import edu.rit.pj.ParallelRegion;
import edu.rit.pj.ParallelTeam;
import edu.rit.util.Random;

public class ParallelExecutorJPL {

	private int id;
	private int numberThreads = 1;

	private ExecuteParallel execute;

	public ParallelExecutorJPL(int id, ExecuteParallel execute) {
		this.id = id;
		this.execute = execute;
	}

	public ParallelExecutorJPL(int id, ExecuteParallel execute, int numberThreads) {
		this.id = id;
		this.execute = execute;

		if(numberThreads > 0) {
			this.numberThreads = numberThreads;
		}
	}

	public void execute() throws Exception {
		new ParallelTeam(this.numberThreads).execute(new ParallelRegion() {
			
			@Override
			public void run() throws Exception {
				System.out.println(getThreadIndex());
				long a = 0;
				long b = 15;
				execute(a, b, new LongForLoop() {
					Random prng_thread = Random.getInstance (System.currentTimeMillis());
					
					@Override
					public void run(long arg0, long arg1) throws Exception {
						prng_thread.setSeed (System.currentTimeMillis());
						prng_thread.skip (2 * arg0);

						execute.parallel();
					}
				});
			}
		});
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}
}
