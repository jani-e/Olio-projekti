package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;


public class Main extends Application {
	public static Backend backend;
	@Override
	public void start(Stage stage) { //luodaan ensimm‰inen n‰kym‰ (stage)
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("ScanScene.fxml"));
			Parent root = loader.load();
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			stage.setTitle("Rahanseuranta");
			stage.setScene(scene);
			ScanController controller = loader.getController(); //haetaan controller, joka vied‰‰n n‰kym‰‰n mukaan
			controller.setStage(stage);
			stage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		backend = new Backend();
		launch(args);
	}
	
	
	
}