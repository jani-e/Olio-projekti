package application;


import java.sql.*;

public class SQLiteJDBC {
	  public SQLiteJDBC() {
	      Connection c = null;
	      
	      
	      try {
	         Class.forName("org.sqlite.JDBC");
	         c = DriverManager.getConnection("jdbc:sqlite:test.db");
	      } catch ( Exception e ) {
	         System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	         System.exit(0);
	      }
	      System.out.println("Opened database successfully");
	      
	   }
	
	  public void createTable(String komento) {
	      Connection c = null;
	      Statement stmt = null;
	      
	      try {
	         Class.forName("org.sqlite.JDBC");
	         c = DriverManager.getConnection("jdbc:sqlite:test.db");
	         System.out.println("Opened database successfully");

	         stmt = c.createStatement();
	         String sql = komento;
							
	         stmt.executeUpdate(sql);
	         stmt.close();
	         c.close();
	      } catch ( Exception e ) {
	         System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	         System.exit(0);
	      }
	      System.out.println("Table created successfully");
	   }
	  	
	      	      
	  	public void insertQuery(String kysely) {
	  		Connection c = null;
	  	      Statement stmt = null;
	  	      
	  	      try {
	  	         Class.forName("org.sqlite.JDBC");
	  	         c = DriverManager.getConnection("jdbc:sqlite:test.db");
	  	         c.setAutoCommit(false);
	  	         System.out.println("Opened database successfully");

	  	         stmt = c.createStatement();
	  	         String sql = kysely; 							
	  	         stmt.executeUpdate(sql);

	  	         stmt.close();
	  	         c.commit();
	  	         c.close();
	  	      } catch ( Exception e ) {
	  	         System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	  	         System.exit(0);
	  	      }
	  	      System.out.println("Records created successfully");
	  	}
	  	
	  	public void selectQuery(String table, String query) {
	  		if(table.contains(";") || query.contains(";")) {
	  			return;
	  		}
	  		//String table = "; DROP TABLE ITEM;";
	  		Connection c = null;
	  		   Statement stmt = null;
	  		   try {
	  		      Class.forName("org.sqlite.JDBC");
	  		      c = DriverManager.getConnection("jdbc:sqlite:test.db");
	  		      c.setAutoCommit(false);
	  		      System.out.println("Opened database successfully");

	  		      stmt = c.createStatement();
	  		      ResultSet rs = stmt.executeQuery( "SELECT " +query+ "FROM "+table+";" );
	  		      
	  		      while ( rs.next() ) {
	  		    int itemid = rs.getInt("itemid");
	  			 String name = rs.getString("name");	
	  	         double amount = rs.getDouble("amount");
	  			 String trans_type = rs.getString("trans_type");
	  	         int categoryid  = rs.getInt("categoryid");
	  			 int receiptid  = rs.getInt("receiptid");      
	  	         
	  	         System.out.println( "ITEMID = " + itemid );
	  			 System.out.println( "NAME = " + name );
	  	         System.out.println( "AMOUNT = " + amount );
	  	         System.out.println( "TRANS_TYPE = " + trans_type );		
	  	         System.out.println( "CATEGORYID = " + categoryid );
	  	         System.out.println( "RECEIPTID = " + receiptid );
	  	         System.out.println();
	  	      }
	  		      rs.close();
	  		      stmt.close();
	  		      c.close();
	  		   } catch ( Exception e ) {
	  		      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	  		      System.exit(0);
	  		   }
	  		   System.out.println("Operation done successfully");
	  	}
	  }
