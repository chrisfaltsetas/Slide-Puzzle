package general;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

public class PuzzleController {

	@FXML
	Button b1;
	@FXML
	ImageView imgview;
	
	SlidePuzzleApp sPApp;
	
	@FXML
	protected void moveImage(ActionEvent event) {
		//imgview.setImage(img);
		imgview.setLayoutX(400);
		imgview.setLayoutY(400);
	}
	
	public void setSPApp(SlidePuzzleApp sPApp) {
		this.sPApp = sPApp;
	}

}
