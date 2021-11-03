package application;


public class DatabaseReader {
	private SQLiteJDBC jdbc;
	public DatabaseReader() {
		jdbc = new SQLiteJDBC();
	}
	
	public void setupDatabase() {
		String komento= "DROP TABLE IF EXISTS ITEM;"
         		+ "CREATE TABLE ITEM " +						
                "(ITEMID INT PRIMARY KEY     NOT NULL AUTO_INCREMENT," + 
				" NAME TEXT		NOT NULL,"+
                " AMOUNT	DOUBLE    NOT NULL, " + 
                " TRANS_TYPE	TEXT     NOT NULL, " + 
				" CATEGORYID	INT , " + 
                " RECEIPTID		INT , " +		
				" FOREIGN KEY(CategoryID) "	+		
				" REFERENCES Category(CategoryID), " +
				" FOREIGN KEY(ReceiptID) "	+
				"REFERENCES Receipt(ReceiptID))";
	jdbc.createTable(komento);
	komento="DROP TABLE IF EXISTS CATEGORY;"	
     		+ "CREATE TABLE CATEGORY " +						
            "(CATEGORYID INT PRIMARY KEY NOT NULL AUTO_INCREMENT," + 
			" NAME TEXT		NOT NULL,)";
		jdbc.createTable(komento);
		komento= "DROP TABLE IF EXISTS RECEIPT;"	
         		+ "CREATE TABLE RECEIPT " +						
                "(RECEIPTID INT PRIMARY KEY     NOT NULL AUTO_INCREMENT," +
				"RECEIPT_DATE DATE NOT NULL," +
				"RECEIPT_TIME,)";
		jdbc.createTable(komento);
	}
	
	public void insertItem(Item item) {
		String name = item.getName();
		int amount = item.getAmount();
		String kysely = "INSERT INTO ITEM (ITEMID,NAME,AMOUNT,TRANS_TYPE,CATEGORYID,RECEIPTID) " +	
                  "VALUES (123, 'milk', 2, 'expense', 30, 40)";
		jdbc.insertQuery(kysely);
	}
}
