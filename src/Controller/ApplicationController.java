package Controller;

import java.sql.SQLException;

import javax.swing.JOptionPane;

import Model.*;
import View.*;

public class ApplicationController {

	public static void home() {
		// prikaz home GUI-a
		ApplicationView.home();
	}

	public static void main(String[] args) {
		try {
			Application.init();
		} catch (SQLException e) {
			System.err.println(e.getMessage());
			System.exit(1);
		}
		Main.init();
		home();
	}

	public static void addContact() {
		ApplicationView.addContact();
	}

	public static void create(String name, String surname, String number) {
		Contact newContact = new Contact(name, surname, number);
		if (newContact.save()) {
			// TODO redirect to contact info
			JOptionPane.showMessageDialog(null,
					"You have saved " + newContact.getName(), "Contact added",
					JOptionPane.INFORMATION_MESSAGE);
			home();
		} else {
			JOptionPane.showMessageDialog(null, "There has been a mistake!",
					"Error saving contact", JOptionPane.WARNING_MESSAGE);
		}
	}

	public static void list() {
		Contact[] all = Contact.all();
		ApplicationView.list(all);
	}

}