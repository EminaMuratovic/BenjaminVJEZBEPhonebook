package View;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import Controller.ApplicationController;
import Model.Contact;

public class ApplicationView extends Main {

	public static void edit(final Contact c) {
		JPanel content = new JPanel();
		Font dataFont = new Font("SansSerif", Font.BOLD, 30);

		content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
		JLabel name = new JLabel("Name: ");
		JLabel lname = new JLabel(c.getName());
		lname.setFont(dataFont);
		JLabel surname = new JLabel("Surname: ");
		JLabel lsurname = new JLabel(c.getSurname());
		lsurname.setFont(dataFont);
		JLabel number = new JLabel("Number: ");
		JLabel lnumber = new JLabel(c.getNumber());
		lnumber.setFont(dataFont);

		content.add(name);
		content.add(lname);
		content.add(surname);
		content.add(lsurname);
		content.add(number);
		content.add(lnumber);

		JButton back = new JButton("Back");
		JButton edit = new JButton("Edit");
		JButton delete = new JButton("Delete");

		back.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ApplicationController.home();

			}
		});

		edit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ApplicationController.update(c.getId());

			}
		});
		
		delete.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int response = JOptionPane.showConfirmDialog(null, "Are you sure?", "Delete?", JOptionPane.YES_NO_OPTION);
				if(response == JOptionPane.YES_OPTION)
					ApplicationController.delete(c.getId());
				else
					return;
				
			}
		});

		content.add(back);
		content.add(edit);
		content.add(delete);

		replaceContent(content);
	}

	public static void home() {
		JPanel content = new JPanel();

		JLabel greeting = new JLabel("Welcome to BitBook");
		Font greetingFont = new Font("SansSerif", Font.BOLD, 30);
		greeting.setFont(greetingFont);
		content.add(greeting);

		JButton showContacts = new JButton("Show Contacts");
		showContacts.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ApplicationController.list();
			}

		});

		JButton addContact = new JButton("Add Contact");
		addContact.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ApplicationController.addContact();

			}
		});

		content.add(addContact);
		content.add(showContacts);
		Main.replaceContent(content);
	}

	public static void addContact() {
		JPanel content = new JPanel();
		content.setLayout(new FlowLayout());
		JLabel name = new JLabel("Name: ");
		final JTextField nameText = new JTextField(25);
		JLabel surname = new JLabel("Surname: ");
		final JTextField surnameText = new JTextField(25);
		JLabel number = new JLabel("Number: ");
		final JTextField numberText = new JTextField(25);
		JButton save = new JButton("SAVE");
		save.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				/*
				 * get the data from the input and send it to the create method
				 */
				String cName = nameText.getText();
				String cSurname = surnameText.getText();
				String cNumber = numberText.getText();
				ApplicationController.create(cName, cSurname, cNumber);

			}
		});
		JButton back = new JButton("BACK");
		back.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ApplicationController.home();

			}
		});
		JPanel buttons = new JPanel();
		buttons.add(save);
		buttons.add(back);

		content.add(name);
		content.add(nameText);
		content.add(surname);
		content.add(surnameText);
		content.add(number);
		content.add(numberText);
		content.add(buttons);
		Main.replaceContent(content);

	}

	/*
	 * creates a button for each contact in the list sets the label and name for
	 * the button connects an action listener and adds the button to the content
	 * panel
	 */
	public static void list(Contact[] all) {
		int buttonHeight = 50;
		JPanel content = new JPanel();
		content.setPreferredSize(new Dimension(
				ApplicationView.windowWidth - 10, all.length
						* (buttonHeight + 20)));
		JButton add = new JButton("Add Contact");
		JButton back = new JButton("Back");
		add.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ApplicationController.addContact();

			}
		});
		back.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ApplicationController.home();

			}
		});
		content.add(add);
		content.add(back);

		if (all.length < 1) {
			JLabel message = new JLabel("The list is empty!");
			Font messageFont = new Font("SansSerif", Font.BOLD, 30);
			message.setFont(messageFont);
			content.add(message);
		}
		// TODO add ADD Contact button

		for (int i = 0; i < all.length; i++) {
			JButton current = new JButton(all[i].getDisplyName());
			current.setName(Integer.toString(all[i].getId()));
			current.setPreferredSize(new Dimension(
					ApplicationView.windowWidth - 75, buttonHeight));
			current.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					JButton clicked = (JButton) (e.getSource());
					int id = Integer.parseInt(clicked.getName());
					ApplicationController.edit(id);
				}
			});
			content.add(current);

		}
		JScrollPane sp = new JScrollPane(content);
		sp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

		replaceContent(sp);
	}

	public static void updateContact(final Contact c) {
		JPanel content = new JPanel();
		content.setLayout(new FlowLayout());
		JLabel name = new JLabel("Name: ");
		final JTextField nameText = new JTextField(c.getName(), 25);
		JLabel surname = new JLabel("Surname: ");
		final JTextField surnameText = new JTextField(c.getSurname(), 25);
		JLabel number = new JLabel("Number: ");
		final JTextField numberText = new JTextField(c.getNumber(), 25);
		JButton save = new JButton("Update");
		save.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				/*
				 * get the data from the input and send it to the create method
				 */
				String cName = nameText.getText();
				String cSurname = surnameText.getText();
				String cNumber = numberText.getText();
				c.setName(cName);
				c.setSurname(cSurname);
				c.setNumber(cNumber);
				ApplicationController.update(c);

			}
		});
		JButton back = new JButton("BACK");
		back.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ApplicationController.edit(c.getId());

			}
		});
		JPanel buttons = new JPanel();
		buttons.add(save);
		buttons.add(back);

		content.add(name);
		content.add(nameText);
		content.add(surname);
		content.add(surnameText);
		content.add(number);
		content.add(numberText);
		content.add(buttons);
		Main.replaceContent(content);

	}

}