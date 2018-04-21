import java.util.concurrent.CountDownLatch;


public class MultiThread implements Runnable{

	private int start;
	private int end;
	private CountDownLatch threadsSignal;  
	
	public MultiThread(int start,int end,CountDownLatch threadsSignal){
		this.start = start;
		this.end = end;
		this.threadsSignal = threadsSignal;
	}
	
	
	@Override
	public void run() {
		for(int i=start;i<=end;i++){
			System.out.println("time is "+i);
		}
		threadsSignal.countDown();
	}
	
	public static void main(String args[]){
		CountDownLatch threadSignal = new CountDownLatch(10);
		for(int i=0;i<10;i++){
			MultiThread t = new MultiThread(i,i+1,threadSignal);
			Thread thread = new Thread(t);
			thread.start();
		}
		try {
			threadSignal.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("finished! yeah!!");
	}
}
