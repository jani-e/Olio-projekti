package application;

import java.io.File;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;

public class ScanController {

	private Stage stage;
	private Scene scene;
	private Parent root;
	private Backend backend = Main.backend;
	private Alert informationAlert;

	public ScanController() {
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}

	public void initialize() {
		this.informationAlert = new Alert(AlertType.INFORMATION);
	}

	// Scan
	public void scanFile(ActionEvent event) { //asettaa tiedoston latausikkunan ja v‰litt‰‰ tiedoston eteenp‰in
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

	// Menu
	public void switchToScan(ActionEvent event) { //N‰kym‰n vaihto painikkeen mukaisesti
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
