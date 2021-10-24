package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Controller {

	public Controller() {
		// TODO Auto-generated constructor stub
	}
	//Scan
	public void scan() {
		System.out.println("skannaa...");
	}
	//Add
	public void add() {
		System.out.println("lis‰‰...");
	}
	
	//History
	
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
