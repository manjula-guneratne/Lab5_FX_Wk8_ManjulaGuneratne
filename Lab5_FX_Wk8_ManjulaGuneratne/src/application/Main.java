package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

 
public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		// Create a border pane
		BorderPane pane = new BorderPane();
		// Place nodes in the pane
		pane.setTop(new Book());		
		pane.setRight(new CustomPane("Right"));
		pane.setBottom(new CustomPane("2025/10/29"));
		pane.setLeft(new CustomPane("Left"));
		pane.setCenter(new EmploymentApplicationForm());	
			
		// Create a scene and place it in the stages
		Scene scene = new Scene(pane);
		primaryStage.setTitle("ShowBorderPane"); // Set the stage title
		primaryStage.setScene(scene); // Place the scene in the stage
		primaryStage.show(); // Display the stage
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
