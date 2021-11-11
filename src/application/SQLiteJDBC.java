package application;

import java.sql.*;
import java.util.ArrayList;

public class SQLiteJDBC {
	public SQLiteJDBC() {	//connect to database
		Connection c = null;

		try {
			Class.forName("org.sqlite.JDBC");	//load or register the JDBC driver for the database. Class class provides forName() method to dynamically load the driver class.
			c = DriverManager.getConnection("jdbc:sqlite:test.db"); //open a database connection
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("Opened database successfully");

	}

	public void createTable(String command) {	//creating a table
		Connection c = null;
		Statement stmt = null;

		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:test.db");	
			System.out.println("Opened database successfully");

			stmt = c.createStatement();
			String sql = command;	//the command is called in the class DatabaseReader 

			stmt.executeUpdate(sql);
			stmt.close();
			c.close();	//Close database connection
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("Table created successfully");
	}

	public void sqlQuery(String addData) { 	//adding data to the table from databasereader
		Connection c = null;
		Statement stmt = null;

		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:test.db");
			c.setAutoCommit(false);
			System.out.println("Opened database successfully");

			stmt = c.createStatement();
			String sql = addData;
			stmt.executeUpdate(sql);

			stmt.close();
			c.commit();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("Records created successfully");
	}

	public ArrayList<Item> selectQuery(String table, String query) {
		if (table.contains(";") || query.contains(";")) {
			return null;	//do nothing if there is a ";" so that no one drops the table
		}						
		// String table = "; DROP TABLE ITEM;";
		Connection c = null;
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList<Item> items = new ArrayList<>();	//tilap‰inen arraylist, sis‰lt‰‰ item olioita
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:test.db");
			c.setAutoCommit(false);
			System.out.println("Opened database successfully");

			stmt = c.createStatement();
			rs = stmt.executeQuery("SELECT " + query + "FROM " + table + ";");	//rs= hakutulokset

			
			while (rs.next()) { 	//rs sis‰lt‰‰ kaiken mit‰ lˆyt‰‰ komennolla
				String name = rs.getString("NAME");
				double amount = rs.getDouble("AMOUNT");
				String transType = rs.getString("TRANS_TYPE");
				Item item = new Item(name, amount, transType);	//tekee uuden itemin
				items.add(item);								//lis‰‰ itemit arraylistaan
			}
			
			rs.close();
			stmt.close();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("Operation done successfully");
		
		return items;		//palauttaa items arraylistan
	}
}
