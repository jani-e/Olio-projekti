package application;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
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
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;

public class Controller {

	private Stage stage;
	private Scene scene;
	private Parent root;
	private Backend backend = Main.backend;
	private ObservableList<String> lista = FXCollections.observableArrayList(backend.getHistoryList());
	private Alert errorAlert;
	private Alert informationAlert;

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

	public void setStage(Stage stage) {
		this.stage = stage;
	}

	public void initialize() {
		// backend = Main.backend;
		// System.out.println(backend.getHistoryList());
		// lista = FXCollections.observableArrayList(backend.getHistoryList());
		// historyList = new ListView<String>(lista);
		// System.out.println(backend);
		// System.out.println(this);
		this.errorAlert = new Alert(AlertType.ERROR);
		this.informationAlert = new Alert(AlertType.INFORMATION);
		loadHistory();
	}

	// Scan
	public void scanFile(ActionEvent event) {
		try {
			Window window = ((Node) (event.getSource())).getScene().getWindow();
			FileChooser fileChooser = new FileChooser();
			FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.tiff",
					"*.png", "*.bmp", "*.gif");
			fileChooser.getExtensionFilters().add(extFilter);
			File file = fileChooser.showOpenDialog(window);
			event.consume();
			this.backend.readPicture(file);
			this.informationAlert.setHeaderText("File sent!");
			this.informationAlert.setContentText("File: " + file.getName());
			this.informationAlert.showAndWait();
		} catch (NullPointerException e) {
			System.out.println("File not found");
		}
	}

	// Add
	public void addItem() {
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
			this.backend.addCustomItem(new Item(name, amount, transType)); //todo: add date
			this.informationAlert.setHeaderText("Item added!");
			this.informationAlert.setContentText("Date: " + date + "\nName: " + name + "\nAmount: " + amount);
			this.informationAlert.showAndWait();
		} else {
			errorPrompt(validDate, validName, validAmount);
			System.out.println("checks failed");
		}
	}

	public void errorPrompt(Boolean validDate, Boolean validName, Boolean validAmount) {
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

	// History
	public void loadHistory() {
		// System.out.println(lista);
		this.historyList.setItems(lista);
		this.historyList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
	}

	public void filter() {
		LocalDate start = null;
		LocalDate end = null;
		start = startDate.getValue();
		end = endDate.getValue();

		System.out.println(start + " " + end);
	}

	// Menu
	public void switchToScan(ActionEvent event) {
		try {
			switchScene(event, 1);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void switchToAdd(ActionEvent event) {
		try {
			switchScene(event, 2);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void switchToHistory(ActionEvent event) {
		try {
			switchScene(event, 3);
		} catch (IOException e) {
			e.printStackTrace();
		}
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
		FXMLLoader loader = new FXMLLoader(getClass().getResource(scenePath));
		this.root = loader.load();

		this.stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		this.scene = new Scene(root);
		this.stage.setScene(scene);

		Controller controller = loader.getController();
		controller.setStage(stage);
		this.stage.show();
	}

}
