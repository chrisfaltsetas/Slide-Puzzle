package gui;

import app_main.SlidePuzzleApp;
import general.ImageCropper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public class PuzzleController {

	@FXML
	Button tester;
	@FXML
	Button menu;
	@FXML
	Button scramble;
		
	ImageView[] imagePieces;
	
	
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
		ImageView tempIV = new ImageView();
		tempIV.setImage(originalImage);
		Rectangle2D viewportRect = new Rectangle2D(0, 0, 100, 100);
		//tempIV.setViewport(((ImageView) grid.getChildren().get(2)).getViewport());
		tempIV.setViewport(viewportRect);
		//tempIV = (ImageView) grid.getChildren().get(2);
		grid.add(tempIV, 2, 2);
	}
		
	
	public void initializePuzzle(Image img, int rows, int columns, GridPane grid) {
		setImage(img);
        setSize(rows, columns);
        setGrid(grid);
        createPieces();
	}
	
	
	//Change?
	public void createPieces() {
		ImageCropper cropper = new ImageCropper(originalImage, puzzleRows, puzzleColumns);
		cropper.crop();
		imagePieces = cropper.getImagePieces();
		
		//change -> set layout of imageviews instead of adding them to the grid 
		int counter = 0;
		for (int i = 0; i < puzzleRows; i++) {
			for (int j = 0; j < puzzleColumns; j++) {
				grid.add(imagePieces[counter], j, i );
				counter++;
			}
		}	
	}
	
	public void setImage(Image img) {
		this.originalImage = img;
	}
	
	public void setPuzzleApp(SlidePuzzleApp puzzleApp) {
		this.puzzleApp = puzzleApp;
	}

	public void setSize(int rows, int columns) {
		this.puzzleRows = rows;
		this.puzzleColumns = columns;
		this.puzzlePieces = puzzleRows * puzzleColumns;
	}

	public void setGrid(GridPane grid) {
		this.grid.getColumnConstraints().setAll(grid.getColumnConstraints());
		this.grid.getRowConstraints().setAll(grid.getRowConstraints());
		this.grid.setGridLinesVisible(true);
	}

}
