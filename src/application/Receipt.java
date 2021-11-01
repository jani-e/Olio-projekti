package application;

import java.time.LocalDate;
import java.util.ArrayList;

public class Receipt {
	private ArrayList<String> receiptItems;
	private ArrayList<Double> receiptAmount;
	private LocalDate receiptDate;

	public Receipt(int year, int month, int day) {
		receiptItems = new ArrayList<>();
		receiptAmount = new ArrayList<>();
		receiptDate = LocalDate.of(year, month, day);
	}

	public void addItem(String name, double amount) {
		receiptItems.add(name);
		receiptAmount.add(amount);
	}
}
