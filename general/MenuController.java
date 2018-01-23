package general;

import java.io.File;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

public class MenuController {

	@FXML
	Button load;
	@FXML
	ImageView preview;
	@FXML
	MenuButton sizeSelector;
	@FXML
	MenuItem selectSize3x3;
	@FXML
	MenuItem selectSize4x4;
	@FXML
	Button play;
	
	SlidePuzzleApp puzzleApp;
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
        	load.setText(imgFile.getName());
        }
	}
	
	@FXML
	protected void selectSize(ActionEvent event) {
		if (event.getSource().equals(selectSize3x3)) {
			sizeSelector.setText("3x3");
			puzzleSize = 3;
		} else if (event.getSource().equals(selectSize4x4)) {
			sizeSelector.setText("4x4");
			puzzleSize = 4;
		}
			
	}
	
	@FXML
	protected void playGame(ActionEvent event) {
		if (preview != null) {
			
		}
	}
	
	
	public void setPuzzleApp(SlidePuzzleApp sPApp) {
		this.puzzleApp = sPApp;
	}
}
