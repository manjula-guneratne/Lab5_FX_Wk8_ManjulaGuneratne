package application;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;


public class EmploymentApplicationForm extends StackPane{
	
	public EmploymentApplicationForm() {
		// Grid for mapping form
		GridPane pane = new GridPane();
		pane.setAlignment(Pos.CENTER);
		pane.setPadding(new Insets(15,5,15,5));
		pane.setHgap(5.5);
		pane.setVgap(5.5);
		
		//Placing nodes in the pane
		pane.add(new Label("First name *"), 0, 0);
		pane.add(new TextField(), 0, 1);
		
		pane.add(new Label("Last name *"), 1, 0);
		pane.add(new TextField(), 1, 1);
		
		pane.add(new Label("Email *"), 0, 3);
		TextField emailField = new TextField();
		pane.add(emailField, 0, 4);
		// Expands 2 rows
		GridPane.setColumnSpan(emailField, 2);
		GridPane.setHgrow(emailField, Priority.ALWAYS);
		
		pane.add(new Label("Portfolio website *"), 0, 5);
		TextField portfolioField = new TextField();
		pane.add(portfolioField, 0, 6);
		// Adds text to Textfield
		portfolioField.setText("http://");
		GridPane.setColumnSpan(portfolioField, 2);
		GridPane.setHgrow(portfolioField, Priority.ALWAYS);
		
		pane.add(new Label("Position you are applying for*"), 0, 7);
		TextField positionField = new TextField();
		pane.add(positionField, 0, 8);
		GridPane.setColumnSpan(positionField, 2);
		GridPane.setHgrow(positionField, Priority.ALWAYS);
		
		pane.add(new Label("Salary requirements"), 0, 9);
		pane.add(new TextField(), 0, 10);
		
		pane.add(new Label("When can you start?"), 1, 9);
		pane.add(new TextField(), 1, 10);	
		
		pane.add(new Label("Phone *"), 0, 11);
		pane.add(new TextField(), 0, 12);
		
		pane.add(new Label("Fax"), 1, 11);
		pane.add(new TextField(), 1, 12);	
		
		pane.add(new Label("Are you willing to relocate?"), 0, 13);
		//RadioButtons
		VBox paneForRadioButtons = new VBox(10);
		RadioButton yesButton = new RadioButton("Yes");
		RadioButton noButton = new RadioButton("No");
		RadioButton notsureButton = new RadioButton("Not sure");		
		//Toggle group
		ToggleGroup group = new ToggleGroup();
		yesButton.setToggleGroup(group);
		noButton.setToggleGroup(group);
		notsureButton.setToggleGroup(group);		
		//Adding buttons to the VBox
		paneForRadioButtons.getChildren().addAll(yesButton,noButton,notsureButton);
		//Add VBox to your GridPane
		pane.add(paneForRadioButtons, 0, 14, 2, 1);
		
		
		pane.add(new Label("Last company you worked for"), 0, 15);
		TextField companyField = new TextField();
		pane.add(companyField, 0, 16);
		GridPane.setColumnSpan(companyField, 2);
		GridPane.setHgrow(companyField, Priority.ALWAYS);		
		
		pane.add(new Label("Reference / Comments / Questions"), 0, 17);
		// Multiple row Textarea
		TextArea multiInput = new TextArea();
		// TextArea set to have 10 rows
		multiInput.setPrefRowCount(8);
		multiInput.setWrapText(true);
		
		// Add TextArea to GridPane, spanning 2 columns for full width
		pane.add(multiInput, 0, 18, 2, 1);  // columnIndex=0, rowIndex=18, colspan=2, rowspan=1

		// Optional: make it grow horizontally if the window is resized
		GridPane.setHgrow(multiInput, Priority.ALWAYS);
		GridPane.setVgrow(multiInput, Priority.ALWAYS);		
		
		Button btnSubmit = new Button("Submit");		
		btnSubmit.setMaxWidth(Double.MAX_VALUE); // fill available width
		pane.add(btnSubmit, 0, 19, 2, 1); // span 2 columns
		GridPane.setHgrow(btnSubmit, Priority.ALWAYS); // allow horizontal growth
		// Submit button Event handler
		SubmitHandlerClass handler1 = new SubmitHandlerClass();
		btnSubmit.setOnAction(handler1);

		Button btnRead = new Button("Read");
		btnRead.setMaxWidth(Double.MAX_VALUE); // fill available width
		pane.add(btnRead, 0, 20, 2, 1); // span 2 columns
		GridPane.setHgrow(btnRead, Priority.ALWAYS); // allow horizontal growth
		// Read button Event handler
		ReadHandlerClass handler2 = new ReadHandlerClass();
		btnRead.setOnAction(handler2);
		
		
		getChildren().add(pane);
		
		
	}

}
