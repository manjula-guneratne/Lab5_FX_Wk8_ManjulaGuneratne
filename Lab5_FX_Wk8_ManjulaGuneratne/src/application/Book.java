package application;

import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class Book extends HBox{
	
	public Book() {
		
		// Connect the image on file
		Image bookImage = new Image("file:C:/Users/Owner/OneDrive/Desktop/College/COMP 228 - Java/Projects/Lab5_FX_Wk8_ManjulaGuneratne/src/application/Image/breath.jpg");
		
		// To display the image
		ImageView bookView = new ImageView(bookImage);
		
		// Decrease the image size and to preserve ratio
		bookView.setFitWidth(150);
		bookView.setPreserveRatio(true);
		
		// Image center align and add to HBox
		this.setAlignment(Pos.CENTER);
		this.getChildren().add(bookView);
	}
	
}
