package application;

public class Backend {
	
	private ReceiptMaker receiptMaker;
	private DatabaseReader databaseReader;
	public Backend() {
		receiptMaker = new ReceiptMaker();
		databaseReader = new DatabaseReader();
		System.out.println("hello world!!1111");
	}
	
	//CHANGE THESE METHODS TO WHATEVER IS NEEDED WHEN YOU KNOW!
	public void getHistoryList() {
		
	}
	
	public void readPicture() {
		
	}
	
	public void addCustomItem() {
		
	}
}
