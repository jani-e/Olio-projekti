package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;

public class Controller {

	private Backend backend = Main.backend;
	private ObservableList<String> lista = FXCollections.observableArrayList(backend.getHistoryList());

	@FXML
	private ListView<String> historyList = new ListView<String>(lista);
	@FXML
	private DatePicker valueDate;
	@FXML
	private TextField valueName;
	@FXML
	private TextField valueAmount;
	@FXML
	private DatePicker startDate;
	@FXML
	private DatePicker endDate;

	public Controller() {
	}

	public void initialize() {
		//backend = Main.backend;
		//System.out.println(backend.getHistoryList());
		//lista = FXCollections.observableArrayList(backend.getHistoryList());
		//historyList = new ListView<String>(lista);
		System.out.println(backend);
		System.out.println(this);
		loadHistory();
	}

	// Scan
	public void scan(ActionEvent event) {
		Window window = ((Node) (event.getSource())).getScene().getWindow();
		FileChooser fileChooser = new FileChooser();
		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.tiff", "*.png", "*.bmp", "*.gif");
		fileChooser.getExtensionFilters().add(extFilter);
		File file = fileChooser.showOpenDialog(window);
		event.consume();
		backend.readPicture(file); //backend receives file
	}

	// Add
	public void add() {
		Alert errorAlert = new Alert(AlertType.ERROR);
		LocalDate date = LocalDate.of(2000, 1, 1);
		String name = "";
		Double amount = 0.0;
		
		Boolean validDate = false;
		Boolean validName = false;
		Boolean validAmount = false;
		
		if (valueDate.getValue() == null) {
			validDate = false;
			errorAlert.setHeaderText("Date is missing!");
			errorAlert.setContentText("Please select a date.");
			errorAlert.showAndWait();
		} else {
			date = valueDate.getValue(); //returns year-month-day ex. 2021-11-01
			validDate = true;
		}
		
		if (valueName.getText().equals("")) {
			validName = false;
			errorAlert.setHeaderText("Name is empty!");
			errorAlert.setContentText("Please add a name.");
			errorAlert.showAndWait();
		} else {
			name = valueName.getText();
			validName = true;
		}
			
		if (valueAmount.getText().equals("")) {//todo: allow numbers , . only 
			validAmount = false;
			errorAlert.setHeaderText("Amount error!");
			errorAlert.setContentText("Valid input: 0.00");
			errorAlert.showAndWait();
		} else {
			amount = Double.valueOf(valueAmount.getText());
			validAmount = true;
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
			backend.addCustomItem(new Item(name, amount, transType));
			//backend.addCustomItem(date, name, amount);
		} else {
			System.out.println("checks failed");
		}
	}

	// History
	public void loadHistory() {
		System.out.println(lista);
		historyList.setItems(lista);
		historyList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
	}
	public void filter() {
		LocalDate start = startDate.getValue(); //LocalDate.of(2000, 1, 1);
		LocalDate end = endDate.getValue(); //LocalDate.of(2000, 1, 1);
		
		System.out.println(start + " " + end);
	}

	// Menu
	public void switchToScan(ActionEvent event) throws IOException {
		switchScene(event, 1);
	}

	public void switchToAdd(ActionEvent event) throws IOException {
		switchScene(event, 2);
	}

	public void switchToHistory(ActionEvent event) throws IOException {
		switchScene(event, 3);
	}

	public void switchScene(ActionEvent event, int sceneNumber) throws IOException {
		String scenePath = "";
		switch (sceneNumber) {
		case 1:
			scenePath = "ScanScene.fxml";
			break;
		case 2:
			scenePath = "AddScene.fxml";
			break;
		case 3:
			scenePath = "HistoryScene.fxml";
			break;
		default:
			scenePath = "ScanScene.fxml";
			break;
		}
		Parent viewParent = FXMLLoader.load(getClass().getResource(scenePath));
		Scene viewScene = new Scene(viewParent);
		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		window.setScene(viewScene);
		window.show();
	}

}
