package rmi.remotingservice;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import rmi.server.PersonService;
import rmi.server.PersonServiceImpl;

public class Program {
	public static void main(String args[]) {

		try {
			PersonService personService = new PersonServiceImpl();
			LocateRegistry.createRegistry(6600);
			// ×¢²áÍ¨Ñ¶Â·¾¶
			Naming.rebind("rmi://127.0.0.1:6600/PersonService", personService);
			System.out.println("Service Start!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
