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
		Parent viewParent = FXMLLoader.load(getClass().getResource("ScanScene.fxml"));
		Scene viewScene = new Scene(viewParent);
		
		//gets Stage information
		Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
		
		window.setScene(viewScene);
		window.show();
	}
	public void switchToAdd(ActionEvent event) throws IOException {
		Parent viewParent = FXMLLoader.load(getClass().getResource("AddScene.fxml"));
		Scene viewScene = new Scene(viewParent);
		
		//gets Stage information
		Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
		
		window.setScene(viewScene);
		window.show();
	}
	public void switchToHistory(ActionEvent event) throws IOException {
		Parent viewParent = FXMLLoader.load(getClass().getResource("HistoryScene.fxml"));
		Scene viewScene = new Scene(viewParent);
		
		//gets Stage information
		Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
		
		window.setScene(viewScene);
		window.show();
	}

}
