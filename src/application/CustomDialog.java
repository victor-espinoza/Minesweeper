/*
 * Victor Espinoza 
 * Created December 2022 - February 2023
 * Project: Minesweeper
 *
 * File Name: CustomDialog.java
 * 
 * Description: This class allows me to add in my own custom dialog when I need to have more 
 * functionality than the default alerts are able to provide. This includes the ability to show more 
 * complex dialog content and adding buttons that stack vertically on top of each other. This is not 
 * something that is achievable using a standard alert type, which is why I decided to create my own 
 * class to handle this functionality.
 * 
 */

package application;
import java.util.Optional;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

public class CustomDialog {

	 private String[] OPTIONS; //dialog options that the user can choose from
	 private Button[] btns; //buttons used in the dialog
	 private Label contentLabel; //body of the dialog
	 private final Stage stage; //dialog window
	 private int minBtnWidth; //minimum width of the buttons in the dialog	
	 private String selectedOption = null ; //option the user selected
	
	 
	 //Default constructor method
	 public CustomDialog() {
	     this(null, 0, 0, 0, false, null, null, null);
	 } //close CustomDialog()
	
	 
	 //Constructor method for the CustomDialog
	 public CustomDialog(Window parent, int width, int height, int btnWidth, boolean verticalDialog, 
	  String dialogTitle, String content, String[] btnOptions) {
		 minBtnWidth = btnWidth; //assign the minimum button width
		 var customDialog = new VBox(); //contains all of the content within the dialog
	     customDialog.setStyle("-fx-padding: 12px; -fx-spacing: 5px;");
	     var btnContainer = (verticalDialog) ? new VBox() : new HBox(); //contains the buttons used
	     btnContainer.setStyle("-fx-padding: 5px; -fx-spacing: 5px;");
	     contentLabel = new Label(content); //assign the body of the dialog
	     contentLabel.setWrapText(true); //allow text to wrap if it overflows
	     customDialog.getChildren().add(contentLabel); //add content to the dialog
	     
	     //assign the selected options
	     OPTIONS = btnOptions; 
	     btns = new Button[OPTIONS.length];	     
	     int index = 0;
	     //create a button for each of the options given
	     for (String choice : OPTIONS)
	    	 btns[index++] = createButton(choice);
	     //set the final button in the list as the default button
	     btns[btns.length-1].setDefaultButton(true);
	     //request focus on the default button (must be done later because
	     //the window isn't ready to handle focus requests on initialization)
	     Platform.runLater(()->btns[btns.length-1].requestFocus());     
	     
	     //add buttons to button container
	     for (Button btn : btns)
	    	 btnContainer.getChildren().add(btn);
	     //add button container to the dialog
	     customDialog.getChildren().add(btnContainer);
	     
	     //create a new scene/stage for the customDialog
	     var scene = new Scene(customDialog);
	     stage = new Stage();
	     stage.setTitle(dialogTitle); //Set the dialog title
	     stage.initOwner(parent); //establish the parent window of the custom dialog
	     stage.initModality(Modality.WINDOW_MODAL); //force focus on custom dialog
	     stage.setWidth(width); //set the desired width
	     stage.setHeight(height); //set the desired height
	     
	     //Set stage coordinates so that it appears in the middle of the parent window
	     stage.setX(parent.getX() + parent.getWidth() / 2 - stage.getWidth() / 2);
	     stage.setY(parent.getY() + parent.getHeight() / 2 - stage.getHeight() / 2);
	     stage.setResizable(false); //give the dialog a fixed size
	     stage.setScene(scene); 
	     stage.setOnCloseRequest(event -> {
	    	 //fire the event of the default button when the user closes the window
	    	 btns[btns.length-1].fire();
	     }); //end setOnCloseRequest(...)     
	 } //close CustomDialog(...) constructor
	
	 
	 //Create the desired button based on the given input text.
	 private Button createButton(String text) {
	     var button = new Button(text); //create a new button
	     button.setMinWidth(minBtnWidth); //set the button's minimum width
	     button.setAlignment(Pos.CENTER_LEFT); //Align the button
	     //Update the selectedOption string when a button is pressed
	     button.setOnAction(e -> {
	         selectedOption = text ;
	         stage.close(); //close the stage
	     }); //end setOnCloseRequest(...)
	     return button; //return the newly created button
	 } //close createButton(...)
	
	 
	 //Show the custom dialog to the user
	 public Optional<String> showDialog() {
	     selectedOption = null ; //no option is selected by default
	     stage.showAndWait(); //wait until a button is pressed
	     return Optional.ofNullable(selectedOption); //return the selected option
	 } //close showDialog()
	 
	 
	 //Return the width of the custom dialog
	 public double getWidth() {
		 return stage.getWidth();
	 } //close getWidth()
	 
	 
	 //Return the height of the custom dialog
	 public double getHeight() {
		 return stage.getHeight();
	 } //close getHeight()
	 
	 
	 //Change the x position of the custom dialog
	 public void setX(double x) {
		 stage.setX(x);
	 } //close setX(...)
	 
	 
	 //Change the y position of the custom dialog
	 public void setY(double y) {
		 stage.setY(y);
	 } //close setY(...)
	 
} //close Class CustomDialog