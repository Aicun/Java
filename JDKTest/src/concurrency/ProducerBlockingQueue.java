package concurrency;

import java.util.concurrent.BlockingQueue;

public class ProducerBlockingQueue implements Runnable {

	private BlockingQueue<String> drop;
	
	public ProducerBlockingQueue(BlockingQueue<String> drop) {
		this.drop = drop;
	}
	
	@Override
	public void run() {
		String importantInfo[] = { "Mares eat oats", "Does eat oats",
				"Little lambs eat ivy", "A kid will eat ivy too" };
		try {
			for (int i = 0; i < importantInfo.length; i++) {
					drop.put(importantInfo[i]);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

}
