package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Backend {

	private ReceiptMaker receiptMaker;
	private DatabaseReader databaseReader;
	private ArrayList<String> history;

	public Backend() {
		this.receiptMaker = new ReceiptMaker();
		this.databaseReader = new DatabaseReader();
		File file = new File("test.db");  // Testing if the database file is empty
		if (file.length() == 0) {
			this.databaseReader.setupDatabase(); // setting up database if the file is empty
			System.out.println("setting up database");
		}
		else {
			System.out.println("Database ok");
		}
		// this.databaseReader.setupDatabase();
		this.history = new ArrayList<>();

	}

	// haetaan historia tietokannasta
	public ArrayList<String> getHistoryList() {
		ArrayList<Item> items = databaseReader.getItems();
		ArrayList<String> temp = new ArrayList<>();
		for (int i = 0; i < items.size(); i++) {
			String name = items.get(i).getName();
			Double amount = items.get(i).getAmount();
			String transType = items.get(i).getTranstype();
			temp.add(name + " " + amount + " " + transType);
		}
		this.history = temp;
		return this.history;
	}

	// luetaan kuva tiedostosta
	public void readPicture(File file) {
		ArrayList<Item> kuitti = this.receiptMaker.getReceipt(file);
		for (Item item : kuitti) {
			this.databaseReader.insertItem(item);
		}
	}
	// ei-skannatun kulun lisääminen
	public void addCustomItem(Item item) {
		this.databaseReader.insertItem(item);
	}
}
