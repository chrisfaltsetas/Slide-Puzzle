package app_main;

import java.io.File;
import java.io.IOException;

import gui.MenuController;
import gui.PuzzleController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
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
			loader.setLocation(SlidePuzzleApp.class.getResource("../gui/Menu.fxml"));
			root = (BorderPane) loader.load();
			
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
		    primaryStage.show();
		    
		    MenuController controller = loader.getController();
		    controller.setPuzzleApp(this);
		} catch (IOException e) {
			Alert alert = new Alert(AlertType.WARNING);
	        alert.initOwner(getPrimaryStage());
	        alert.setTitle("Error");
	        alert.setHeaderText("Scene Loading Error");
	        alert.setContentText("There was an error loading the Menu scene.");
	        alert.showAndWait();
		}
	}
	
	public void showPuzzle(File imgFile, int rows, int columns) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(SlidePuzzleApp.class.getResource("../gui/Puzzle.fxml"));
			root = (BorderPane) loader.load();
			
        	Scene scene = new Scene(root);
        	primaryStage.setScene(scene);
            primaryStage.show();
            
            PuzzleController controller = loader.getController();
            controller.setPuzzleApp(this);            
            controller.initializePuzzle(imgFile, rows, columns);
		} catch (IOException e) {
			Alert alert = new Alert(AlertType.WARNING);
	        alert.initOwner(getPrimaryStage());
	        alert.setTitle("Error");
	        alert.setHeaderText("Scene Loading Error");
	        alert.setContentText("There was an error loading the Puzzle scene.");
	        alert.showAndWait();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}

	public Stage getPrimaryStage() {
		return primaryStage;
	}
}
