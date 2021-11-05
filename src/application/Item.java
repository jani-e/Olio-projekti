package application;

public class Item {

private String name;
private double amount;
private String transType;

public Item(String name, double amount, String transType) {
	this.name=name;
	this.amount=amount;
	this.transType=transType;
}


	public String getName() {
	
		return this.name;
	}

	public double getAmount() {
		
		return this.amount;
	}


	public String getTranstype() {
		
		return this.transType;
	}

}
