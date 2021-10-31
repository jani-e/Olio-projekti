package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
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
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;

public class Controller {

	private ObservableList<String> lista = FXCollections.observableArrayList(); //poistetaan backend työn jälkeen
	
	private Backend backend = new Backend();
	//private ObservableList<String> lista = FXCollections.observableArrayList(backend.getHistoryList());
	
	@FXML
	private ListView<String> historyList = new ListView<String>(lista);
	
	public Controller() {
	}
	
	public void initialize() {
        loadHistory();
    }

	//Scan
	public void scan(ActionEvent event) {
		Window window = ((Node) (event.getSource())).getScene().getWindow();
	    FileChooser fileChooser = new FileChooser();
	    File file = fileChooser.showOpenDialog(window);
	    event.consume();
	    lueTiedosto(file); //testimetodi
	    //backend.readPicture(file); //tiedosto siirtyy backendiin
	}
	
	public void lueTiedosto(File file) { //testi, kirjoittaa tiedoston konsoliin
		try {
			Scanner reader = new Scanner(file);
		    while (reader.hasNextLine()) {
		    	String data = reader.nextLine();
		    	System.out.println(data);
		    }
		} catch (FileNotFoundException e) {
			System.out.println(e);
		}
	}
	//Add
	public void add() {
		System.out.println("lisaa...");
	}
	
	//History
    private void loadHistory() { //testi2
        //lista.add("testi rivi 1"); //esko lisaa backendiin arraylist, jossa on tietokannan tulot ja menot
        lista.add("testi rivi 2");
        historyList.setItems(lista);
        historyList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
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
