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
			Application.init("phonebook");
		} catch (SQLException e) {
			System.err.println(e.getMessage());
			System.exit(1);
		}
		Main.init();
		home();
	}
	
	public static void edit(int id) {
		Contact c = Contact.find(id);
		ApplicationView.edit(c);
	}
	
	public static void update(int id) {
		Contact c = Contact.find(id);
		ApplicationView.updateContact(c);
	}
	
	public static void update(Contact c) {
		c.update();
		ApplicationView.edit(c);
	}

	public static void addContact() {
		ApplicationView.addContact();
	}
	
	public static void delete(int id) {
		Contact.delete(id);
		list();
	}

	public static void create(String name, String surname, String number) {
		Contact newContact = new Contact(name, surname, number);
		if (newContact.save()) {
			// TODO redirect to contact info
			edit(newContact.getId());
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