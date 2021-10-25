package application;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.stage.Stage;

public class Controller {
	
	private final ObservableList<String> lista = FXCollections.observableArrayList();
	
	private BackEnd backend = new BackEnd();
	
	@FXML
	private ListView<String> historyList = new ListView<String>(lista);
	
	public Controller() {
	}
	
	public void initialize() {
        loadHistory();
    } 

	//Scan
	public void scan() {
		System.out.println("skannaa...");
	}
	//Add
	public void add() {
		System.out.println("lis��...");
	}
	
	//History
    private void loadHistory() {
        lista.add("testi rivi 1");
        lista.add("testi rivi 2");
        lista.add("test3");
        historyList.setItems(lista);
        //historyList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE); //voi poistaa?
    }
	//Menu
	//vaihtaa scenen nappulan mukaisesti
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
		case 1: scenePath = "ScanScene.fxml"; break;
		case 2: scenePath = "AddScene.fxml"; break;
		case 3: scenePath = "HistoryScene.fxml"; break;
		default: scenePath = "ScanScene.fxml"; break;
		}
		Parent viewParent = FXMLLoader.load(getClass().getResource(scenePath));
		Scene viewScene = new Scene(viewParent);
		
		//gets Stage information
		Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
		
		window.setScene(viewScene);
		window.show();
	}

}
