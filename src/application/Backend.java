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
		receiptMaker = new ReceiptMaker();
		databaseReader = new DatabaseReader();
		history = new ArrayList<>();
		
	}
	
	//CHANGE THESE METHODS TO WHATEVER IS NEEDED WHEN YOU KNOW!
	public ArrayList getHistoryList() {
		// lista tietokannasta
		//stuff from database
		return history;
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

	public void addCustomItem(LocalDate date, String name, Double amount) {
		// TODO Auto-generated method stub
		//stuff to database
	}
}
