package general;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class SlidePuzzleApp extends Application {

	private Stage primaryStage;
	private BorderPane root;

	@Override
	public void start(Stage primaryStage) throws Exception {
		this.primaryStage = primaryStage;
        this.primaryStage.setTitle("SlidePuzzle");
        
		showMenu();
	}
	
	public void showMenu() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(SlidePuzzleApp.class.getResource("Menu.fxml"));
			root = (BorderPane) loader.load();
			
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
		    primaryStage.show();
		    
		    MenuController controller = loader.getController();
		    controller.setPuzzleApp(this);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void showPuzzle() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(SlidePuzzleApp.class.getResource("Puzzle.fxml"));
			root = (BorderPane) loader.load();
			
        	Scene scene = new Scene(root);
        	primaryStage.setScene(scene);
            primaryStage.show();
            
            PuzzleController controller = loader.getController();
            controller.setSPApp(this);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}

	public Stage getPrimaryStage() {
		return primaryStage;
	}
}
