package rmi.client;

import java.rmi.Naming;
import java.util.List;

import rmi.model.PersonEntity;
import rmi.server.PersonService;

public class Program {
	public static void main(String args[]) {
		try {
			PersonService personService = (PersonService) Naming
					.lookup("rmi://127.0.0.1:6600/PersonService");
			List<PersonEntity> personList = personService.GetList();
			for (PersonEntity person : personList) {
				System.out.println("ID:" + person.getId() + " Age:"
						+ person.getAge() + " Name:" + person.getName());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
