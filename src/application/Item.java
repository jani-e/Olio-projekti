package application;

public class Item {

private String name; 	//creating object variables
private double amount;
private String transType;

public Item(String name, double amount, String transType) { 	//parametric constructor
	this.name=name;
	this.amount=amount;
	this.transType=transType;
}


	public String getName() {	//getting and returning the name using the public method
	
		return this.name;
	}

	public double getAmount() {		//getting and returning the amount using the public method
		
		return this.amount;
	}


	public String getTranstype() {		//getting and returning the transtype (income or expense) using the public method
		
		return this.transType;
	}

}
