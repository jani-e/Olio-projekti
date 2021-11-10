package application;

import java.io.File;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.FileChooser;
import javafx.stage.Window;

public class ScanController extends MenuController {


	private Backend backend = Main.backend;
	private Alert informationAlert;

	public ScanController() {
	}

	public void initialize() {
		this.informationAlert = new Alert(AlertType.INFORMATION); //information window pop up
	}

	// Scan
	public void scanFile(ActionEvent event) {
		try {
			Window window = ((Node) (event.getSource())).getScene().getWindow(); //window for file selecting
			FileChooser fileChooser = new FileChooser();
			FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.tiff",
					"*.png", "*.bmp", "*.gif"); //allowed file types
			fileChooser.getExtensionFilters().add(extFilter); //add filter to filechooser
			File file = fileChooser.showOpenDialog(window); //set selected file
			event.consume(); //stop eventlistener
			this.backend.readPicture(file); //send file to backend
			this.informationAlert.setHeaderText("File sent!"); //extra information pop up after file is sent
			this.informationAlert.setContentText("File: " + file.getName()); //show file info
			this.informationAlert.showAndWait(); //wait for user to close
		} catch (NullPointerException e) {
			System.out.println("File not found"); //error if file select is cancelled
		}
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
