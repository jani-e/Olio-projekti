package application;

import java.time.LocalDate;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class AddController extends MenuController {

	private Backend backend = Main.backend;
	private Alert errorAlert;
	private Alert informationAlert;

	@FXML
	private DatePicker valueDate;
	@FXML
	private TextField valueName;
	@FXML
	private TextField valueAmount;

	public AddController() {
	}

	public void initialize() { //error and information window pop ups
		this.errorAlert = new Alert(AlertType.ERROR);
		this.informationAlert = new Alert(AlertType.INFORMATION);
	}

	public void addItem() { //sets user inputs to values
		LocalDate date = valueDate.getValue();
		String name = valueName.getText();
		String tempAmount = valueAmount.getText();
		Double amount = null;

		Boolean validDate = false; //validation
		Boolean validName = false;
		Boolean validAmount = false;

		if (date == null) {
			validDate = false;
		} else {
			validDate = true;
		}

		if (name.equals("")) {
			validName = false;
		} else {
			validName = true;
		}

		if (tempAmount.contains(",")) { //turn commas into dots
			String tempArray[] = tempAmount.split(",");
			tempAmount = tempArray[0] + "." + tempArray[1];
		}

		if (tempAmount.equals("")) {
			validAmount = false;
		} else {
			try { //if legit double value, continue
				Double.parseDouble(tempAmount);
				amount = Double.valueOf(tempAmount);
				validAmount = true;
			} catch (NumberFormatException e) {
				validAmount = false;
			}
		}

		if (validDate && validName && validAmount) { //if all checks are true, continue
			String transType = "";
			if (amount < 0) { //transaction type based on value
				transType = "Expense";
			} else {
				transType = "Income";
			}
			this.backend.addCustomItem(new Item(name, amount, transType)); //add item to backend
			this.informationAlert.setHeaderText("Item added!"); //info pop up
			this.informationAlert.setContentText("Date: " + date + "\nName: " + name + "\nAmount: " + amount);
			this.informationAlert.showAndWait();
		} else {
			errorPrompt(validDate, validName, validAmount); //calls method to show user input errors
			System.out.println("checks failed");
		}
	}

	public void errorPrompt(Boolean validDate, Boolean validName, Boolean validAmount) { //shows to user what went wrong
		String errorMessage = "";
		String errorDate = "Date is missing!\n";
		String errorName = "Name is missing!\n";
		String errorAmount = "Invalid amount input!";

		if (!validDate) {
			errorMessage += errorDate;
		}
		if (!validName) {
			errorMessage += errorName;
		}
		if (!validAmount) {
			errorMessage += errorAmount;
		}

		this.errorAlert.setHeaderText("Something went wrong:");
		this.errorAlert.setContentText(errorMessage);
		this.errorAlert.showAndWait();
	}

	// Menu
		public void switchToScan(ActionEvent event) {
			super.switchToScan(event);
		}

		public void switchToAdd(ActionEvent event) {
			super.switchToAdd(event);
		}

		public void switchToHistory(ActionEvent event) {
			super.switchToHistory(event);
		}

	}
