package Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import Model.Application;
import Model.Contact;

public class ModelTest {

	private static void testFind() {
		Contact c = Contact.find(1);
		// TODO add equals method to Contact class and rewrite this part of the
		// Test
		if (!c.getName().equals("Bob") || !c.getSurname().equals("Bobs")
				|| !c.getNumber().equals("12345")) {
			System.err.println("Contact not equal");
		}
		c = Contact.find(4);
		if (c != null)
			System.err.println("Found non existing contact");
	}

	private static void testAll() {
		Contact[] all = Contact.all();
		if (all.length != 3) {
			System.err.println("Length does not match.");
			return;
		}
		if (!all[0].getName().equals("Bob")
				|| !all[0].getSurname().equals("Bobs")) {
			System.err.println("Contact not equal");
		}

		if (!all[1].getName().equals("Benjo")
				|| !all[1].getSurname().equals("Talic")) {
			System.err.println("Contact not equal");
		}

		if (!all[2].getName().equals("Hiko")
				|| !all[2].getSurname().equals("Lino")) {
			System.err.println("Contact not equal");
		}
	}

	public static void main(String[] args) {
		try {
			Application.init("phonebookTest");
		} catch (SQLException e) {
			System.err.println("Test failed: no connection");
			System.exit(1);
		}
		
		new Contact(1, "Bob", "Bobs", "12345").save();
		new Contact(2, "Benjo", "Talic", "4567").save();
		new Contact(3, "Hiko", "Lino", "6789").save();

		System.out.println("Testing find: ");
		testFind();
		System.out.println("Testing all: ");
		testAll();
		
		String[] tableNames = {"contacts"};
		System.out.println("Done testing"); 
	}

}
