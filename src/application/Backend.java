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
		//this.databaseReader.setupDatabase();
		this.history = new ArrayList<>();
		
	}
	
	//CHANGE THESE METHODS TO WHATEVER IS NEEDED WHEN YOU KNOW!
	public ArrayList getHistoryList() {
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
	
	public void readPicture(File file) {
		try {
			Scanner reader = new Scanner(file);
		    while (reader.hasNextLine()) {
		    	String data = reader.nextLine();
		    	System.out.println(data);
		    }
		} catch (FileNotFoundException e) {
			System.out.println(e);
		}
	}
	
	public void addCustomItem() {
		
	}

	public void addCustomItem(Item item) {
		this.databaseReader.insertItem(item);
	}
}
