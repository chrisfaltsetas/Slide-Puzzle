package gui;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import app_main.SlidePuzzleApp;
import general.ImageCropper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;

public class PuzzleController {

	@FXML
	Button tester;
	@FXML
	Button menu;
	@FXML
	Button scramble;
		
	Pane[] imagePieces;
	
	
	@FXML
	GridPane grid;
	
	
	SlidePuzzleApp puzzleApp;
	int puzzleRows;
	int puzzleColumns;
	int puzzlePieces;
	Image originalImage;
	

	
	@FXML
	protected void goToMenu(ActionEvent event) {
		puzzleApp.showMenu();
	}
	
	
	@FXML
	protected void test(ActionEvent event) {
	    grid.getChildren().remove(imagePieces[5]);
		grid.add(imagePieces[5], 2, 2);
	}
		
	
	public void initializePuzzle(File imgFile, int rows, int columns) {
		setImage(imgFile);
        setSize(rows, columns);
        setGrid();
        createPieces();
	}
	
	
	public void createPieces() {
		ImageCropper cropper = new ImageCropper(originalImage, puzzleRows, puzzleColumns);
		cropper.crop();
		ImageView[] pieces = cropper.getImagePieces();
		imagePieces = new Pane[puzzlePieces];

		int counter = 0;
		for (int i = 0; i < puzzleRows; i++) {
			for (int j = 0; j < puzzleColumns; j++) {
				if (counter < pieces.length - 1) {
					imagePieces[counter] = new Pane(pieces[counter]);
				} else {
					imagePieces[counter] = new Pane();
				}
				grid.add(imagePieces[counter], j, i );
				counter++;
			}
		}
	}
	
	
	public void setImage(File imgFile) {
		try (FileInputStream imgIS = new FileInputStream(imgFile)) {
			Image img = new Image(imgIS, grid.getWidth(), grid.getHeight(), false, true);
			this.originalImage = img;
		} catch (IOException e) {
			Alert alert = new Alert(AlertType.WARNING);
	        alert.initOwner(puzzleApp.getPrimaryStage());
	        alert.setTitle("Error");
	        alert.setHeaderText("Game Loading Error");
	        alert.setContentText("There was an error loading the game.");
	        alert.showAndWait();
		}
	}

	
	public void setSize(int rows, int columns) {
		this.puzzleRows = rows;
		this.puzzleColumns = columns;
		this.puzzlePieces = puzzleRows * puzzleColumns;
	}
	

	public void setGrid() {
		double colSize =  grid.getWidth() / puzzleColumns;
		double rowSize = grid.getHeight() / puzzleRows;
		
		resetGrid();		
		
		for (int i = 0; i < puzzleColumns; i++) {
			ColumnConstraints col = new ColumnConstraints();
			col.setPrefWidth(colSize);
			grid.getColumnConstraints().add(col);
		}
		for (int i = 0; i < puzzleRows; i++) {
			RowConstraints row = new RowConstraints();
			row.setPrefHeight(rowSize);
			grid.getRowConstraints().add(row);
		}
	}
	
	
	public void resetGrid() {
		grid.getRowConstraints().clear();
		grid.getColumnConstraints().clear();		
	}
	
	
	public void setPuzzleApp(SlidePuzzleApp puzzleApp) {
		this.puzzleApp = puzzleApp;
	}
}
