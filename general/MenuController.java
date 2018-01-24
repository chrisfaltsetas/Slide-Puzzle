package general;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;

public class MenuController {

	//Image Preview
	@FXML
	ImageView preview;
	@FXML
	GridPane grid3x3;
	@FXML
	GridPane grid4x4;
	
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
	MenuItem selectSize3x3;
	@FXML
	MenuItem selectSize4x4;
	
	@FXML
	Button play;

	
	SlidePuzzleApp puzzleApp;
	boolean imageMode;
	int puzzleSize;
	
	
	@FXML
	protected void loadImage(ActionEvent event) {
		FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Picture files (*.jpg, *.png, *.bmp)", "*.jpg", "*.png", "*.bmp");
        fileChooser.getExtensionFilters().add(extFilter);
        
        File imgFile = fileChooser.showOpenDialog(puzzleApp.getPrimaryStage());
        if (imgFile != null) {
        	Image img = new Image(imgFile.toURI().toString(), 300, 300, false, true);
        	preview.setImage(img);
        	modeSelector.setText(imgFile.getName());
        }
	}
	
	@FXML
	protected void loadNumbers(ActionEvent event) throws FileNotFoundException {
		if (puzzleSize == 3) {
			FileInputStream fileIS = new FileInputStream("resources/images/numbers3x3.jpg");
			Image img = new Image(fileIS, 300, 300, false, true);
			preview.setImage(img);
		} else if (puzzleSize == 4) {
			FileInputStream fileIS = new FileInputStream("resources/images/numbers4x4.jpg");
			Image img = new Image(fileIS, 300, 300, false, true);
			preview.setImage(img);
		}
		modeSelector.setText(numbers.getText());
	}
	
	@FXML
	protected void selectSize(ActionEvent event) {
		if (event.getSource().equals(selectSize3x3)) {
			sizeSelector.setText("3x3");
			puzzleSize = 3;			
			if (modeSelector.getText().equals(numbers.getText())) {
				numbers.fire();
			}
			grid4x4.setGridLinesVisible(false);
			grid3x3.setGridLinesVisible(true);
		} else if (event.getSource().equals(selectSize4x4)) {
			sizeSelector.setText("4x4");
			puzzleSize = 4;
			if (modeSelector.getText().equals(numbers.getText())) {
				numbers.fire();
			}
			grid3x3.setGridLinesVisible(false);
			grid4x4.setGridLinesVisible(true);
		}
			
	}
	
	@FXML
	protected void playGame(ActionEvent event) {
		if (preview != null) {
			puzzleApp.showPuzzle();
			
		}
	}
	
	
	public void setPuzzleApp(SlidePuzzleApp sPApp) {
		this.puzzleApp = sPApp;
	}
}
