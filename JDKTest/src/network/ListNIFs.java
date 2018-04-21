package network;

import java.io.PrintStream;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Enumeration;

public class ListNIFs {
	
	private static PrintStream out = System.out;
	
	public static void main(String args[]) throws SocketException {
		Enumeration<NetworkInterface> nets = NetworkInterface
				.getNetworkInterfaces();

		for (NetworkInterface netIf : Collections.list(nets)) {
			//System.out.printf("Display name: %s\n", netIf.getDisplayName());
			//System.out.printf("Name: %s\n", netIf.getName());
			displaySubInterfaces(netIf);
			System.out.printf("\n");
		}
	}

	static void displaySubInterfaces(NetworkInterface netint)
			throws SocketException {
		 out.printf("Display name: %s\n", netint.getDisplayName());
	        out.printf("Name: %s\n", netint.getName());
	        Enumeration<InetAddress> inetAddresses = netint.getInetAddresses();
	        
	        for (InetAddress inetAddress : Collections.list(inetAddresses)) {
	            out.printf("InetAddress: %s\n", inetAddress);
	        }
	       
	        out.printf("Up? %s\n", netint.isUp());
	        out.printf("Loopback? %s\n", netint.isLoopback());
	        out.printf("PointToPoint? %s\n", netint.isPointToPoint());
	        out.printf("Supports multicast? %s\n", netint.supportsMulticast());
	        out.printf("Virtual? %s\n", netint.isVirtual());
	        out.printf("Hardware address: %s\n",
	                    Arrays.toString(netint.getHardwareAddress()));
	        out.printf("MTU: %s\n", netint.getMTU());
	        out.printf("\n");
	}
}
