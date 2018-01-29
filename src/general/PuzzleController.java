package general;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;

public class PuzzleController {

	@FXML
	Button restart;
	@FXML
	Button menu;
	
	@FXML
	ImageView imgview;
	
	
	@FXML
	GridPane grid;
	
	@FXML
	Rectangle r1;
	
	SlidePuzzleApp puzzleApp;
	int puzzleRows;
	int puzzleColumns;
	Image img;

	
	@FXML
	protected void goToMenu(ActionEvent event) {
		puzzleApp.showMenu();
	}
	
	@FXML
	protected void testImage(ActionEvent event) {
		PixelReader pPeader = img.getPixelReader();
		WritableImage newImage = new WritableImage(pPeader, 0, 0, 100, 100);
		imgview.setImage(newImage);
	}
	
	public void setImage(Image img) {
		this.img = img;
	}
	
	public void setPuzzleApp(SlidePuzzleApp puzzleApp) {
		this.puzzleApp = puzzleApp;
	}

	public void setSize(int rows, int columns) {
		this.puzzleRows = rows;
		this.puzzleColumns = columns;
	}

}
