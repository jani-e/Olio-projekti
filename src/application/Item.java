package application;

public class Item {

private String name; 	//luodaan oliomuuttujat
private double amount;
private String transType;

public Item(String name, double amount, String transType) { 	//parametrillinen konstruktori
	this.name=name;
	this.amount=amount;
	this.transType=transType;
}


	public String getName() {	//haetaan ja palautetaan nimi julkisella metodilla
	
		return this.name;
	}

	public double getAmount() {		//haetaan ja palautetaan summa julkisella metodilla
		
		return this.amount;
	}


	public String getTranstype() {		//haetaan ja palautetaan trantype (meno vai pano) julkisella metodilla
		
		return this.transType;
	}

}
