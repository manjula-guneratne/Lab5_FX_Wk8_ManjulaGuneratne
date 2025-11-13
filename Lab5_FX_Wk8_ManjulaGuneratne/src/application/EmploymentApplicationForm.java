package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

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
	
	// Multiple row Textarea
	private TextArea multiInput = new TextArea();
	
	public EmploymentApplicationForm() {
		// Grid for mapping form
		GridPane pane = new GridPane();
		pane.setAlignment(Pos.CENTER);
		pane.setPadding(new Insets(15,5,15,5));
		pane.setHgap(5.5);
		pane.setVgap(5.5);
		
		//Placing nodes in the pane
		pane.add(new Label("First name *"), 0, 0);
		TextField fName = new TextField();
		pane.add(fName, 0, 1);
		
		pane.add(new Label("Last name *"), 1, 0);
		TextField lName = new TextField();
		pane.add(lName, 1, 1);
		
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
		TextField salaryAmount = new TextField();
		pane.add(salaryAmount, 0, 10);
		
		pane.add(new Label("When can you start?"), 1, 9);
		TextField startDate = new TextField();
		pane.add(startDate, 1, 10);	
		
		pane.add(new Label("Phone *"), 0, 11);
		TextField phoneNo = new TextField();
		pane.add(phoneNo, 0, 12);
		
		pane.add(new Label("Fax"), 1, 11);
		TextField faxNo = new TextField();
		pane.add(faxNo, 1, 12);	
		
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
		// Assigning values to Radio buttons
		yesButton.setUserData("Yes");
		noButton.setUserData("No");
		notsureButton.setUserData("Not sure");

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
		
		// TextArea set to have 10 rows
		multiInput.setPrefRowCount(8);
		multiInput.setWrapText(true);
		
		// Add TextArea to GridPane, spanning 2 columns for full width
		pane.add(multiInput, 0, 18, 2, 1);  // columnIndex=0, rowIndex=18, colspan=2, rowspan=1

		// Optional: make it grow horizontally if the window is resized
		GridPane.setHgrow(multiInput, Priority.ALWAYS);
		GridPane.setVgrow(multiInput, Priority.ALWAYS);		
		
		//Database connection
		Database db = new Database();
		
		Button btnSubmit = new Button("Submit");		
		btnSubmit.setMaxWidth(Double.MAX_VALUE); // fill available width
		pane.add(btnSubmit, 0, 19, 2, 1); // span 2 columns
		GridPane.setHgrow(btnSubmit, Priority.ALWAYS); // allow horizontal growth
		
		//When submit is clicked
		btnSubmit.setOnAction(e -> {
			// getting the radio button value
			String relocateValue = group.getSelectedToggle() != null
					? group.getSelectedToggle().getUserData().toString()
					: "Not specified";
		    db.saveApplication(fName, lName, emailField, portfolioField, positionField,
		                       salaryAmount, startDate, phoneNo, faxNo, relocateValue, companyField, multiInput);
		});


		Button btnRead = new Button("Read");
		btnRead.setMaxWidth(Double.MAX_VALUE); // fill available width
		pane.add(btnRead, 0, 20, 2, 1); // span 2 columns
		GridPane.setHgrow(btnRead, Priority.ALWAYS); // allow horizontal growth
		
		//When read is clicked
		btnRead.setOnAction(e -> {
			db.readApplication();
		});
		
		getChildren().add(pane);		
	}
	
	public class Database{
		private String url="jdbc:mysql://localhost:3306/week9_LabWork_ManjulaGuneratne";
		private String username ="root";
		private String password="admin";		
		
		public void readApplication() {
			
			String qry = "Select * from Application";
			
			try {
				// Loads the DB driver
				Class.forName("com.mysql.cj.jdbc.Driver");
				
				Connection conn = DriverManager.getConnection(url,username,password);
				System.out.println("Connection Established successfully");
				
				// Create a statement
				Statement st = conn.createStatement();
				
				// Execute the query
				ResultSet rs = st.executeQuery(qry);
				
				// Process the results
				StringBuilder sb = new StringBuilder();
		        while (rs.next()) {
		            sb.append("First Name: ").append(rs.getString("firstname")).append("\n");
		            sb.append("Last Name: ").append(rs.getString("lastname")).append("\n");
		            sb.append("Email: ").append(rs.getString("email")).append("\n");
		            sb.append("Portfolio: ").append(rs.getString("portfolio")).append("\n");
		            sb.append("Position: ").append(rs.getString("position")).append("\n");
		            sb.append("Salary: ").append(rs.getString("salary")).append("\n");
		            sb.append("Start Date: ").append(rs.getString("startDate")).append("\n");
		            sb.append("Phone: ").append(rs.getString("phoneNo")).append("\n");
		            sb.append("Fax: ").append(rs.getString("faxNo")).append("\n");
		            sb.append("Relocate: ").append(rs.getString("relocate")).append("\n");
		            sb.append("Last Company: ").append(rs.getString("lastCompanyworked")).append("\n");
		            sb.append("Reference Question: ").append(rs.getString("referenceQ")).append("\n");
		            sb.append("------------------------------------\n");
		        }
		        
		        // Now set all that text into your TextArea
		        multiInput.setText(sb.toString());
			
				// Close the statement and connection
				st.close();
				conn.close();
				System.out.println("Connection closed...");
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
		public void saveApplication(
			    TextField fName, TextField lName, TextField emailField, TextField portfolioField,
			    TextField positionField, TextField salaryAmount, TextField startDate, 
			    TextField phoneNo, TextField faxNo, String relocateValue, TextField companyField, TextArea multiInput
			) {

			
			try {
				// Loads the DB driver
				Class.forName("com.mysql.cj.jdbc.Driver");
				
				Connection conn = DriverManager.getConnection(url,username,password);
				System.out.println("Connection Established successfully");
				
				// Create a statement
				Statement st = conn.createStatement();				

				String qry = "INSERT INTO Application (firstname, lastname, email, portfolio, position, salary, startDate, phoneNo, faxNo, relocate, lastCompanyworked, referenceQ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
				PreparedStatement ps = conn.prepareStatement(qry);
				ps.setString(1, fName.getText());
				ps.setString(2, lName.getText());
				ps.setString(3, emailField.getText());
				ps.setString(4, portfolioField.getText());
				ps.setString(5, positionField.getText());
				ps.setString(6, salaryAmount.getText());
				ps.setString(7, startDate.getText());
				ps.setString(8, phoneNo.getText());
				ps.setString(9, faxNo.getText());
				ps.setString(10, relocateValue);
				ps.setString(11, companyField.getText());
				ps.setString(12, multiInput.getText());
				ps.executeUpdate();
				
				// Execute the query
//				int rows = st.executeUpdate(qry);
//				
//				 System.out.println(rows + " row(s) inserted.");
			
				// Close the statement and connection
				st.close();
				conn.close();
				System.out.println("Connection closed...");
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}

}
