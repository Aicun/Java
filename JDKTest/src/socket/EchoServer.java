package socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
	public static void main(String args[]){
		int portNumber = 7001;
		try (
			ServerSocket serverSocket = new ServerSocket(portNumber);
			Socket connSocket = serverSocket.accept();
			PrintWriter pw = new PrintWriter(connSocket.getOutputStream(),true);
			BufferedReader br = new BufferedReader(new InputStreamReader(connSocket.getInputStream()));
		){
			String inputLine;
			while((inputLine = br.readLine())!=null){
				System.out.println("message from client is "+ inputLine);
				pw.println(inputLine);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Exception caught when trying to listen on port "
	                + portNumber + " or listening for a connection");
	            System.out.println(e.getMessage());
		}
	}
}
