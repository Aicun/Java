package concurrency;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Safelock {

	static class Friend {
		private final String name;
		private Lock lock = new ReentrantLock();

		public Friend(String name) {
			this.name = name;
		}

		public String getName() {
			return name;
		}

		public boolean impendingBow(Friend myFriend) {
			boolean mylock = false;
			boolean yourlock = false;
			try {
				mylock = lock.tryLock();
				yourlock = myFriend.lock.tryLock();
			} finally {
				if (!(mylock && yourlock)) {
					if (mylock) {
						lock.unlock();
					}
					if (yourlock) {
						myFriend.lock.unlock();
					}
				}
			}
			return mylock && yourlock;
		}

		public void bow(Friend myFriend) {
			if (impendingBow(myFriend)) {
				try {
					System.out.format("%s: %s has" + " bowed to me!%n",
							this.name, myFriend.getName());
					myFriend.bowBack(this);
				} finally {
					this.lock.unlock();
					myFriend.lock.unlock();
				}
			} else {
				System.out.format("%s: %s started"
						+ " to bow to me, but saw that"
						+ " I was already bowing to" + " him.%n", this.name,
						myFriend.getName());
			}
		}

		public void bowBack(Friend myFriend) {
			System.out.format("%s: %s has" + " bowed back to me!%n", this.name,
					myFriend.getName());
		}
	}

	static class BowLoop implements Runnable {

		private Friend friendA;
		private Friend friendB;

		public BowLoop(Friend friendA, Friend friendB) {
			this.friendA = friendA;
			this.friendB = friendB;
		}

		public void run() {
			Random random = new Random();
			for (;;) {
				try {
					Thread.sleep(random.nextInt(10));
				} catch (InterruptedException e) {
				}
				friendA.bow(friendB);
			}
		}
	}

	public static void main(String[] args) {
		final Friend Aicun = new Friend("Aicun");
		final Friend Danpeng = new Friend("Danpeng");
		new Thread(new BowLoop(Aicun, Danpeng)).start();
		new Thread(new BowLoop(Danpeng, Aicun)).start();
	}
}
