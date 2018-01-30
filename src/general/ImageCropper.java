package general;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ImageCropper {

	final Image originalImage;
	final double imgHeight;
	final double imgWidth;
	
	int rows;
	int columns;
	int pieces;
	ImageView[] imagePieces;
	
	
	//Constructors
	public ImageCropper(Image originalImage) {
		this.originalImage = originalImage;
		imgHeight = originalImage.getHeight();
		imgWidth = originalImage.getWidth();
		setRows(1);
		setColumns(1);
		setPieces();
	}
	
	public ImageCropper(Image originalImage, int rows, int columns) {
		this.originalImage = originalImage;
		imgHeight = originalImage.getHeight();
		imgWidth = originalImage.getWidth();
		setRows(rows);
		setColumns(columns);
		setPieces();
	}
	
	//crop method
	public void crop() {
		imagePieces = new ImageView[pieces];
		if (pieces == 1) {
			imagePieces[0].setImage(originalImage);			
		} else {
			int counter = 0;
			double pieceHeight = imgHeight / rows;
			double pieceWidth = imgWidth / columns;
			for (int i = 1; i <= rows; i++) {
				for (int j = 1; j <= columns; j++) {
					imagePieces[counter] = new ImageView();
					imagePieces[counter].setImage(originalImage);
					Rectangle2D viewportRect = new Rectangle2D(pieceWidth * (j - 1), pieceHeight * (i - 1), pieceWidth, pieceHeight);
					imagePieces[counter].setViewport(viewportRect);
					counter++;
				}
			}			
		}
	}
	
	
	//Getters and Setters
	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		if (rows <= 0) {
			rows = 1;
		}
		this.rows = rows;
	}

	public int getColumns() {
		return columns;
	}

	public void setColumns(int columns) {
		if (columns <= 0) {
			columns = 1;
		}
		this.columns = columns;
	}

	public int getPieces() {
		return pieces;
	}

	private void setPieces() {
		pieces = rows * columns;
	}

	public Image getOriginalImage() {
		return originalImage;
	}

	public ImageView[] getImagePieces() {
		return imagePieces;
	}
	
}
