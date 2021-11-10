package application;

import java.time.LocalDate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;

public class HistoryController extends MenuController {

	private Backend backend = Main.backend;
	private ObservableList<String> obsList;


	@FXML //references to FXML
	private ListView<String> historyList;
	@FXML
	private DatePicker startDate;
	@FXML
	private DatePicker endDate;

	public HistoryController() { //JavaFX requires empty constructor
	}

	public void initialize() { //Code executed after FXML loading
		obsList = FXCollections.observableArrayList(backend.getHistoryList()); //get arrayList from backend and set it to obsList
		historyList.getItems().addAll(obsList); //get current items from historyList + add fetched arrayList/obsList
	}

	public void filter() { //delete?
		LocalDate start = null;
		LocalDate end = null;
		start = startDate.getValue();
		end = endDate.getValue();

		System.out.println(start + " " + end);
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
