package concurrency;

public class Consumer implements Runnable {

	private Drop drop;
	
	public Consumer(Drop drop) {
		this.drop = drop;
	}
	
	@Override
	public void run() {
		while(true){
			drop.get();
		}
	}

}
