package application;


import java.sql.*;

public class SQLiteJDBC {
	  public static void main( String args[] ) {
		  Connection c = null;
	      Statement stmt = null;
	      
	      try {
	          Class.forName("org.sqlite.JDBC");
	          c = DriverManager.getConnection("jdbc:sqlite:test.db");
	     
	       System.out.println("Opened database successfully");
	    

	         stmt = c.createStatement();
	         String sql = "CREATE TABLE ITEM " +						
	                        "(ITEMID INT PRIMARY KEY     NOT NULL," + 
							" NAME TEXT		NOT NULL,"+
	                        " AMOUNT	DOUBLE    NOT NULL, " + 
	                        " TRANS_TYPE	TEXT     NOT NULL, " + 
							" CATEGORYID	INT 	NOT NULL, " + 
	                        " RECEIPTID		INT 	NOT NULL, " +		
							" FOREIGN KEY(CategoryID) "	+		
							" REFERENCES Category(CategoryID), " +
							" FOREIGN KEY(ReceiptID) "	+
							"REFERENCES Receipt(ReceiptID)";

							
	         stmt.executeUpdate(sql);
	         stmt.close();
	         c.close();

	      System.out.println("Table created successfully");
	      
	      stmt = c.createStatement();
	         String sqlla = "INSERT INTO ITEM (ITEMID,NAME,AMOUNT,TRANS_TYPE,CATEGORYID,RECEIPTID) " +	
	                        "VALUES (123, 'milk', 2, 'expense', 30, 40);"; 							
	         stmt.executeUpdate(sqlla);

	         stmt.close();
	         c.commit();
	         c.close();

	      System.out.println("Records created successfully");
	      
	      stmt = c.createStatement();
	      ResultSet rs = stmt.executeQuery( "SELECT * FROM ITEM;" );
	      
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
	
	   System.out.println("Operation done successfully.");
	   
	  } catch ( Exception e ) {
          System.err.println( e.getClass().getName() + ": " + e.getMessage() );
          System.exit(0);
       }
	  
	  }
}
	   
	   
	

    