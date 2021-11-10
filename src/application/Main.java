package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;


public class Main extends Application {
	public static Backend backend;
	@Override
	public void start(Stage stage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("ScanScene.fxml")); //load fxml
			Parent root = loader.load(); //set loaded fxml to root
			Scene scene = new Scene(root); //set scene with root
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm()); //set custom css file to scene
			stage.setTitle("Rahanseuranta"); //set title to scene
			stage.setScene(scene); //set scene to stage
			stage.show(); //show stage
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		backend = new Backend();
		launch(args);
	}
	
	
	
}