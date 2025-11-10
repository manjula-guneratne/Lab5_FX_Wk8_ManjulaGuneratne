package application;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class SubmitHandlerClass implements EventHandler<ActionEvent>{
	
	@Override
	public void handle(ActionEvent arg0) {
		
		System.out.println("Submit button was clicked.");
		
	}
}
