package application;

import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class Book extends HBox{
	
	public Book() {
		
		Image bookImage = new Image("file:C:/Users/Owner/OneDrive/Desktop/College/COMP 228 - Java/Projects/Lab5_FX_Wk8_ManjulaGuneratne/src/application/Image/breath.jpg");
		
		ImageView bookView = new ImageView(bookImage);
		
		bookView.setFitWidth(150);
		bookView.setPreserveRatio(true);
		
		this.setAlignment(Pos.CENTER);
		this.getChildren().add(bookView);
	}
	
}
