package application;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class ReadHandlerClass implements EventHandler<ActionEvent>{

	@Override
	public void handle(ActionEvent arg0) {
		
		System.out.println("Read button was clicked.");
		
	}
	
	

}
