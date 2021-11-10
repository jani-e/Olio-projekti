package application;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MenuController {

	private Stage stage;
	private Scene scene;
	private Parent root;

	public MenuController() {
	}

	public void initialize() {
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

			this.stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
