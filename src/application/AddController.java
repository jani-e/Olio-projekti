package application;

import java.io.IOException;
import java.time.LocalDate;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddController {

	private Stage stage;
	private Scene scene;
	private Parent root;
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

	public void setStage(Stage stage) {
		this.stage = stage;
	}

	public void initialize() { //varoitus sekä ilmoitusikkunat
		this.errorAlert = new Alert(AlertType.ERROR);
		this.informationAlert = new Alert(AlertType.INFORMATION);
	}

	public void addItem() { //käy läpi käyttäjän syötteet
		LocalDate date = valueDate.getValue();
		String name = valueName.getText();
		String tempAmount = valueAmount.getText();
		Double amount = null;

		Boolean validDate = false;
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

		if (tempAmount.contains(",")) {
			String tempArray[] = tempAmount.split(",");
			tempAmount = tempArray[0] + "." + tempArray[1];
		}

		if (tempAmount.equals("")) {
			validAmount = false;
		} else {
			try {
				Double.parseDouble(tempAmount);
				amount = Double.valueOf(tempAmount);
				validAmount = true;
			} catch (NumberFormatException e) {
				validAmount = false;
			}
		}

		if (validDate && validName && validAmount) {
			System.out.println("checks ok: ");
			System.out.println(date + " " + name + " " + amount);
			String transType = "";
			if (amount < 0) {
				transType = "Expense";
			} else {
				transType = "Income";
			}
			this.backend.addCustomItem(new Item(name, amount, transType)); //todo: add date vai poisto?
			this.informationAlert.setHeaderText("Item added!");
			this.informationAlert.setContentText("Date: " + date + "\nName: " + name + "\nAmount: " + amount);
			this.informationAlert.showAndWait();
		} else {
			errorPrompt(validDate, validName, validAmount);
			System.out.println("checks failed");
		}
	}

	public void errorPrompt(Boolean validDate, Boolean validName, Boolean validAmount) { //näyttää käyttäjälle missä tiedoissa oli virheitä
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
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("ScanScene.fxml"));
			this.root = loader.load();

			this.stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			this.scene = new Scene(root);
			this.scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			this.stage.setScene(scene);

			ScanController controller = loader.getController();
			controller.setStage(stage);
			this.stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void switchToAdd(ActionEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("AddScene.fxml"));
			this.root = loader.load();

			this.stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			this.scene = new Scene(root);
			this.scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			this.stage.setScene(scene);

			AddController controller = loader.getController();
			controller.setStage(stage);
			this.stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void switchToHistory(ActionEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("HistoryScene.fxml"));
			this.root = loader.load();

			this.stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			this.scene = new Scene(root);
			this.scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			this.stage.setScene(scene);

			HistoryController controller = loader.getController();
			controller.setStage(stage);
			this.stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
