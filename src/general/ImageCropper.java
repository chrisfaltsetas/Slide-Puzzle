package general;

import javafx.scene.image.Image;

public class ImageCropper {

	Image img;
	
	public ImageCropper(Image img) {
		this.img = img;
	}
	
	public Image[] crop(int pieces) {
		Image[] imagePieces = new Image[pieces];
		return imagePieces;
	}
	
}
