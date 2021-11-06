package application;

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
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

public class HistoryController {

	private Stage stage;
	private Scene scene;
	private Parent root;
	private Backend backend = Main.backend;
	private ObservableList<String> lista;;

	@FXML
	private ListView<String> historyList;
	@FXML
	private DatePicker startDate;
	@FXML
	private DatePicker endDate;

	public HistoryController() {
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}

	public void initialize() {
		lista = FXCollections.observableArrayList(backend.getHistoryList());
		historyList.getItems().addAll(lista);
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
			FXMLLoader loader = new FXMLLoader(getClass().getResource("ScanScene.fxml"));
			this.root = loader.load();

			this.stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			this.scene = new Scene(root);
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
			this.stage.setScene(scene);

			HistoryController controller = loader.getController();
			controller.setStage(stage);
			this.stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
