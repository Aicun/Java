package socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class EchoClient {

	public static void main(String args[]) {
		try (Socket socket = new Socket("127.0.0.1", 7001);
				PrintWriter pw = new PrintWriter(socket.getOutputStream(),true);
				BufferedReader br = new BufferedReader(new InputStreamReader(
						socket.getInputStream()));) {
			pw.println("hello Server");
			System.out.println("message echoed from server is "+br.readLine());
		} catch (UnknownHostException e) {
			System.err.println("Don't know about host ");
			System.exit(1);
		} catch (IOException e) {
			System.err.println("Couldn't get I/O for the connection to");
			System.exit(1);
		}
	}

}
