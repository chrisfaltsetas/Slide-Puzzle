package gui;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import app_main.SlidePuzzleApp;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

public class MenuController {

	//Image Preview
	@FXML
	ImageView preview;
	
	//Select Mode Options
	@FXML
	MenuButton modeSelector;
	@FXML
	MenuItem numbers;
	@FXML
	MenuItem load;
	
	//Select Size Options
	@FXML
	MenuButton sizeSelector;
	@FXML
	MenuItem size3x3;
	@FXML
	MenuItem size4x4;
	
	@FXML
	Button play;

	
	SlidePuzzleApp puzzleApp;
	int puzzleRows;
	int puzzleColumns;
	File imgFile;
	
	
	@FXML
	protected void loadImage(ActionEvent event) {
		FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Picture files (*.jpg, *.png, *.bmp)", "*.jpg", "*.png", "*.bmp");
        fileChooser.getExtensionFilters().add(extFilter);
        
        imgFile = fileChooser.showOpenDialog(puzzleApp.getPrimaryStage());
        if (imgFile != null) {
	        try (FileInputStream imgIS = new FileInputStream(imgFile)) {
		        if (imgFile != null) {
		        	Image img = new Image(imgIS, preview.getFitWidth(), preview.getFitHeight(), false, true);
		        	preview.setImage(img);
		        	modeSelector.setText(imgFile.getName());
		        }
	        } catch (IOException e) {
	        	Alert alert = new Alert(AlertType.WARNING);
		        alert.initOwner(puzzleApp.getPrimaryStage());
		        alert.setTitle("Error");
		        alert.setHeaderText("Image Loading Error");
		        alert.setContentText("There was an error loading your image.");
		        alert.showAndWait();
	        }
        }
	}
	
	@FXML
	protected void loadNumbers(ActionEvent event) {
		if (puzzleRows >= 3 && puzzleColumns >= 3) {
			imgFile = new File("resources/images/numbers/numbers" + puzzleRows + "x" + puzzleColumns +".jpg");
			try (FileInputStream imgIS = new FileInputStream(imgFile)) {				
				Image img = new Image(imgFile.toURI().toString(), preview.getFitWidth(), preview.getFitHeight(), false, true);
				preview.setImage(img);				
			} catch (IOException e) {
				Alert alert = new Alert(AlertType.WARNING);
		        alert.initOwner(puzzleApp.getPrimaryStage());
		        alert.setTitle("Error");
		        alert.setHeaderText("Numbers Loading Error");
		        alert.setContentText("There was an error loading the numbers.");
		        alert.showAndWait();
			}
		}
		modeSelector.setText(numbers.getText());
	}
	
	@FXML
	protected void selectSize(ActionEvent event) {
		if (event.getSource().equals(size3x3)) {
			sizeSelector.setText(size3x3.getText());
			setPuzzleRows(3);
			setPuzzleColumns(3);
			if (modeSelector.getText().equals(numbers.getText())) {
				numbers.fire();
			}
		} else if (event.getSource().equals(size4x4)) {
			sizeSelector.setText(size4x4.getText());
			setPuzzleRows(4);
			setPuzzleColumns(4);
			if (modeSelector.getText().equals(numbers.getText())) {
				numbers.fire();
			}
		}
	}
	
	@FXML
	protected void playGame(ActionEvent event) {
		boolean isModeSelected = !modeSelector.getText().equals("Select Mode...");
		boolean isSizeSelected = !sizeSelector.getText().equals("Select Size...");
		if (isModeSelected && isSizeSelected) {
			puzzleApp.showPuzzle(imgFile, puzzleRows, puzzleColumns);			
		}
	}

	public void setPuzzleRows(int puzzleRows) {
		if (puzzleRows > 0)
			this.puzzleRows = puzzleRows;
		else
			this.puzzleRows = 0;
	}

	public void setPuzzleColumns(int puzzleColumns) {
		if (puzzleColumns > 0)
			this.puzzleColumns = puzzleColumns;
		else
			this.puzzleColumns = 0;
	}	
	
	public void setPuzzleApp(SlidePuzzleApp sPApp) {
		this.puzzleApp = sPApp;
	}
}
