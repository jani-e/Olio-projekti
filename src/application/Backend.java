package application;

import java.util.ArrayList;

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
		return history;
	}
	
	public void readPicture() {
		
	}
	
	public void addCustomItem() {
		
	}
}
