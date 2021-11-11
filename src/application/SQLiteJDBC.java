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

	public void createTable(String komento) {	//creating a table
		Connection c = null;
		Statement stmt = null;

		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:test.db");	
			System.out.println("Opened database successfully");

			stmt = c.createStatement();
			String sql = komento;	//the command is called in the class DatabaseReader 

			stmt.executeUpdate(sql);
			stmt.close();
			c.close();	//Close database connection
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("Table created successfully");
	}

	public void sqlQuery(String lisays) { 	//adding data to the table
		Connection c = null;
		Statement stmt = null;

		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:test.db");
			c.setAutoCommit(false);
			System.out.println("Opened database successfully");

			stmt = c.createStatement();
			String sql = lisays;
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
		ArrayList<Item> items = new ArrayList<>();
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:test.db");
			c.setAutoCommit(false);
			System.out.println("Opened database successfully");

			stmt = c.createStatement();
			rs = stmt.executeQuery("SELECT " + query + "FROM " + table + ";");

			
			while (rs.next()) {
				String name = rs.getString("NAME");
				double amount = rs.getDouble("AMOUNT");
				String transType = rs.getString("TRANS_TYPE");
				Item item = new Item(name, amount, transType);
				items.add(item);
			}
			
			rs.close();
			stmt.close();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("Operation done successfully");
		
		return items;
	}
}
