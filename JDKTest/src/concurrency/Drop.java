package concurrency;

public class Drop {
	private String message;
	private boolean isEmpty = true;

	public synchronized void put(String message) {
		while (!isEmpty) {
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		isEmpty = false;
		System.out.println("Producer is putting a drop......");
		this.message = message;
		notifyAll();
	}

	public synchronized String get() {
		while(isEmpty){
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		isEmpty = true;
		System.out.println("Consumer is getting a drop " + message);
		notifyAll();
		return message;
	}
}
