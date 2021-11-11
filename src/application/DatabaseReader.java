package application;

import java.sql.ResultSet;
import java.util.ArrayList;

public class DatabaseReader {
	private SQLiteJDBC jdbc;

	public DatabaseReader() {
		jdbc = new SQLiteJDBC();	//reference to sqlite class
	}

	public void setupDatabase() {	//create the tables shown below
		String command = "DROP TABLE IF EXISTS CATEGORY;" + "CREATE TABLE CATEGORY "	//this table is not in use, it's reserved for further development
				+ "(CATEGORYID INTEGER PRIMARY KEY AUTOINCREMENT," + " NAME TEXT	NOT NULL)";
		jdbc.createTable(command);

		command = "DROP TABLE IF EXISTS RECEIPT;" + "CREATE TABLE RECEIPT "	//this table is not in use, it's reserved for further development
				+ "(RECEIPTID INTEGER PRIMARY KEY AUTOINCREMENT," + "RECEIPT_DATE DATE NOT NULL," + "RECEIPT_TIME)";
		jdbc.createTable(command);

		command = "CREATE TABLE ITEM " + "(ITEMID INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ " NAME TEXT		NOT NULL," + " AMOUNT	DOUBLE    NOT NULL, " + " TRANS_TYPE	TEXT     NOT NULL, "
				+ " CATEGORYID	INT , " + " RECEIPTID		INT , " + " FOREIGN KEY(CategoryID) "
				+ " REFERENCES Category(CategoryID), " + " FOREIGN KEY(ReceiptID) " + "REFERENCES Receipt(ReceiptID))";
		jdbc.createTable(command);

	}

	public void insertItem(Item item) {		//The following fields are added to the Item class
		String name = item.getName();
		double amount = item.getAmount();
		String transtype = item.getTranstype();
		String addData = "INSERT INTO ITEM (NAME,AMOUNT,TRANS_TYPE) " + "VALUES ('" + name + "', " + amount + ", '"
				+ transtype + "')";
		jdbc.sqlQuery(addData);
	}

	public ArrayList<Item> getItems() {
		ArrayList<Item> resultItems = new ArrayList<>();	//uusi items arraylist
		resultItems = jdbc.selectQuery("ITEM", "*");	//queryn nimi on item, 2. parametri on *
		//items arraylistaan astetaan mitä löydetty metodilla

		return resultItems;
	}

}
