package socket;

import java.io.IOException;
import java.net.ServerSocket;

public class KKMultiServer {

	public static void main(String args[]){
		
		try(ServerSocket ss = new ServerSocket(7001);){
			new KKMultiServerThread(ss.accept()).start();
		} catch (IOException e) {
			System.err.println("Could not listen on port ");
            System.exit(-1);
        }
	}
}
