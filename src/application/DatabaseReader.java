package application;

import java.sql.ResultSet;
import java.util.ArrayList;

public class DatabaseReader {
	private SQLiteJDBC jdbc;

	public DatabaseReader() {
		jdbc = new SQLiteJDBC();
	}

	public void setupDatabase() {	//create the tables shown below
		String komento = "DROP TABLE IF EXISTS CATEGORY;" + "CREATE TABLE CATEGORY "
				+ "(CATEGORYID INTEGER PRIMARY KEY AUTOINCREMENT," + " NAME TEXT	NOT NULL)";
		jdbc.createTable(komento);

		komento = "DROP TABLE IF EXISTS RECEIPT;" + "CREATE TABLE RECEIPT "
				+ "(RECEIPTID INTEGER PRIMARY KEY AUTOINCREMENT," + "RECEIPT_DATE DATE NOT NULL," + "RECEIPT_TIME)";
		jdbc.createTable(komento);

		komento = "CREATE TABLE ITEM " + "(ITEMID INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ " NAME TEXT		NOT NULL," + " AMOUNT	DOUBLE    NOT NULL, " + " TRANS_TYPE	TEXT     NOT NULL, "
				+ " CATEGORYID	INT , " + " RECEIPTID		INT , " + " FOREIGN KEY(CategoryID) "
				+ " REFERENCES Category(CategoryID), " + " FOREIGN KEY(ReceiptID) " + "REFERENCES Receipt(ReceiptID))";
		jdbc.createTable(komento);
//"DROP TABLE IF EXISTS ITEM;" + 	//miksi tää on heitetty tänne? Eikö pitäisi olla ennen item taulua?
	}

	public void insertItem(Item item) {		//The following fields are added to the Item class
		String name = item.getName();
		double amount = item.getAmount();
		String transtype = item.getTranstype();
		String lisays = "INSERT INTO ITEM (NAME,AMOUNT,TRANS_TYPE) " + "VALUES ('" + name + "', " + amount + ", '"
				+ transtype + "')";
		jdbc.sqlQuery(lisays);
	}

	public ArrayList<Item> getItems() {
		ArrayList<Item> items = new ArrayList<>();
		items = jdbc.selectQuery("ITEM", "*");
		

		return items;
	}

}
