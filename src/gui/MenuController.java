package gui;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import app_main.SlidePuzzleApp;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.FileChooser;

public class MenuController {

	//Image Preview
	@FXML
	ImageView preview;
	@FXML
	GridPane grid;
	
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
	Image img;
	
	
	@FXML
	protected void loadImage(ActionEvent event) {
		FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Picture files (*.jpg, *.png, *.bmp)", "*.jpg", "*.png", "*.bmp");
        fileChooser.getExtensionFilters().add(extFilter);
        
        File imgFile = fileChooser.showOpenDialog(puzzleApp.getPrimaryStage());
        if (imgFile != null) {
        	img = new Image(imgFile.toURI().toString(), preview.getFitWidth(), preview.getFitHeight(), false, true);
        	preview.setImage(img);
        	modeSelector.setText(imgFile.getName());
        }
        
        if (!sizeSelector.getText().equals("Select Size...")) {
        	grid.setGridLinesVisible(true);
        }
	}
	
	@FXML
	protected void loadNumbers(ActionEvent event) throws FileNotFoundException {
		if (puzzleRows >= 3 && puzzleColumns >= 3) {
			FileInputStream fileIS = new FileInputStream("resources/images/numbers" + puzzleRows + "x" + puzzleColumns +".jpg");
			img = new Image(fileIS, 300, 300, false, true);
			preview.setImage(img);
		}
		modeSelector.setText(numbers.getText());
		
		if (!sizeSelector.getText().equals("Select Size...")) {
        	grid.setGridLinesVisible(true);
        }
	}
	
	@FXML
	protected void selectSize(ActionEvent event) {
		if (event.getSource().equals(size3x3)) {
			sizeSelector.setText(size3x3.getText());
			setPuzzleRows(3);
			setPuzzleColumns(3);
			setGrid();
			if (modeSelector.getText().equals(numbers.getText())) {
				numbers.fire();
			}
		} else if (event.getSource().equals(size4x4)) {
			sizeSelector.setText(size4x4.getText());
			setPuzzleRows(4);
			setPuzzleColumns(4);
			setGrid();
			if (modeSelector.getText().equals(numbers.getText())) {
				numbers.fire();
			}
		}
		if (!modeSelector.getText().equals("Select Mode...")) {
			grid.setGridLinesVisible(true);
		}
	}
	
	@FXML
	protected void playGame(ActionEvent event) {
		if (preview != null) {
			puzzleApp.showPuzzle(img, puzzleRows, puzzleColumns);			
		}
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
		grid.setGridLinesVisible(false);
		grid.getRowConstraints().clear();
		grid.getColumnConstraints().clear();		
	}	

	public void setPuzzleRows(int puzzleRows) {
		this.puzzleRows = puzzleRows;
	}

	public void setPuzzleColumns(int puzzleColumns) {
		this.puzzleColumns = puzzleColumns;
	}	
	
	public void setPuzzleApp(SlidePuzzleApp sPApp) {
		this.puzzleApp = sPApp;
	}
}
