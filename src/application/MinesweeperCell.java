/*
 * Victor Espinoza 
 * Created December 2022 - February 2023
 * Project: Minesweeper
 *
 * File Name: MinesweeperCell.java
 * 
 * Description: This class encapsulates all of the necessary variables that make up a MinesweeperCell. A 
 * rectangle is used to represent a border around the cell and hide if from view unless the user wants to 
 * reveal said cell. Every MinesweeperCell has it's own applicable MinesweeperCellData. Label's are also 
 * used to display the cell's value and any comments to the user. 
 * 
 */

package application;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

public class MinesweeperCell extends StackPane{
	private int CELL_SIZE = 23; //default cell size
	public static final int MINE_VAL = -1; //default mine value
	private MinesweeperCellData cellData; //hold all of the cell's data
	private Rectangle cellBorder = new Rectangle(CELL_SIZE, CELL_SIZE); //used to conceal the cell's value
	private Label cellText = new Label(); //displays the cell's vale to the user
	private Label cellComment = new Label(); //displays the cell's comment to the user


	//Constructor method for the MinesweeperCell
	public MinesweeperCell(int x, int y, boolean mineStatus) {
		//assign the cell's data to the MinesweeperCell
		cellData = new MinesweeperCellData(x, y, mineStatus, ""); //the comment field is empty by default
		cellBorder.setFill(Color.BLACK); //fill the border in black
		cellBorder.setStroke(Color.BLACK); //set the outline to black
		cellText.setFont(Font.font("Arial", 18)); //set the font size for the cell text
		
		//Assign the appropriate cell text value (if there is a mine then change it to a mine, otherwise
		//the value will be empty by default).
		cellText.setText(cellData.mineIsPresent() ? "\u25CE" : ""); 
		cellText.setVisible(false); //conceal the cell's text until the user wants to make it visible
		cellComment.setFont(Font.font("Arial", 17)); //set the font size for the cell comment
		cellComment.setText(""); //make the comment empty by default
		cellComment.setVisible(false); //conceal the comment until the user wants to see it
		
		//set minimum size of each cell
		setMinHeight(CELL_SIZE);
		setMinWidth(CELL_SIZE);
		getChildren().addAll(cellBorder, cellText, cellComment); //add everything to the container (StackPane)	
	} //close MinesweeperCell(...) constructor 
	
	
	//Return the cell's data
	public MinesweeperCellData getCellData() {
		return cellData;
	} //close getCellData()
	
	
	//Update a cell's data given new input values
	public void updateCellData(int x, int y, int newVal, boolean mineStatus, boolean hiddenStatus, 
	 String comment) {
		cellData.setCellData(x, y, newVal, mineStatus, hiddenStatus, comment);
	} //close updateCellData(...)
	
	
	//Return the cell's x index
	public int getCellXInd() {
		return cellData.getXInd();
	} //close getCellXInd()
	
	
	//Return the cell's y index
	public int getCellYInd() {
		return cellData.getYInd();
	}//close getCellYInd()
	
	
	//Return the cell's cellValue
	public int getCellVal() {
		return cellData.getCellVal();
	} //close getCellValue
	
	
	//Assign a new cellValue
	public void setCellVal(int newVal) {
		cellData.setCellVal(newVal);
	} //close setCellVal(...)
	
	
	//Return the cell's mine status
	public boolean hasMine() {
		return cellData.mineIsPresent();
	}//close hasMine()
	
	
	//Assign a new mine status
	public void setMineStatus(boolean newStatus) {
		cellData.setMineFlag(newStatus);
	} //close setMineStatus(...)
	
	
	//Return the cell's hidden status
	public boolean isHidden() {
		return cellData.getHiddenStatus();
	} //close isHidden()
	
	
	//Assign a new hidden status
	public void setHiddenFlag(boolean newVal) {
		cellData.setHiddenStatus(newVal);
	} //close setHiddenFlag(...)
	
	
	//Reset the cell's hidden status
	public void resetHiddenFlag() {
		cellData.setHiddenStatus(false);
	} //close resetHiddenFlag()
	
	
	//Return the cell's cellText label
	public Label getCellTextLabel() {
		return cellText;
	} //close getCellTextLabel()
	
	
	//Return the cell's cellComment label
	public Label getCellCommentLabel() {
		return cellComment;
	}//close getCellCommentLabel()
	
	
	//Update a cell which has an incorrect mine comment placed on it once the game ends
	public void updateIncorrectCell() {
		//make sure the comment is incorrectly placed by the user
		if(cellComment.getText().equals("\u2691") && !cellData.mineIsPresent()) {
			cellText.setText("\u25CE"); //change the text to a mine
			cellBorder.setFill(null); //erase the cell border of the incorrect cell
			cellText.setTextFill(Color.DARKSLATEGRAY.darker()); //change color to gray
			cellText.setVisible(true); //make the cellText visible
			//change to comment to an X (indicates incorrect mine comment placement)
			cellComment.setText("X");
			cellComment.setTextFill(Color.RED); //change the comment color to red
			cellComment.setVisible(true); //make the red X comment visible	
		}//end if
	} //close updateIncorrectCell()
	
	
	//Return the cell's text (in string form)
	public String getCellText() {
		return cellText.getText();
	} //close getCellText()
	
	
	//Return the cell's comment (in string form)
	public String getCellComment() {
		return cellComment.getText();
	} //close getCellComment()
	
	
	//Add a comment to the cell
	public void addComment(String text) {
		//only add comments to cells that are currently hidden
		if (cellData.getHiddenStatus()) {
			cellComment.setText(text); //update the cellComment's text 
			cellData.setCellComment(text); //update the cell's data as well
			cellComment.setVisible(true); //make the cellComment visible
		}//end if
	}//close addComment(...)
	
	
	//Change the background color the of the cellBorder
	public void updateBackgroundColor(Color bkColor) {
		cellBorder.setFill(bkColor);
	} //close updateBackgroundColor(...)
	
	
	//Erase the background color for the cellBorder
	public void disableBackgroundColor() {
		cellBorder.setFill(null);
	} //close disableBackgroundColor()
	
	
	//Change the text color for the cellComment Label
	public void setCommentsTextColor(Color textColor) {
		cellComment.setTextFill(textColor); 
	} //close setCommentsTextColor(...)
	 
	
	//Assign a new text value to the cellText Label
	public void updateCellText(String text) {
		cellText.setText(text);
	} //close updateCellText
	
	
	//Change the text color for the cellText Label
	public void setCellTextColor(Color textColor) {
		cellText.setTextFill(textColor);
	} //close setCellTextColor(...)
	
	
	//Return the cellBorder Rectangle
	public Rectangle getCellRect() {
		return cellBorder;
	} //close getCellRect()
	
	
	//Display the mine to the user
	public void makeMineVisible() {
		//make sure the cell is currently hidden
		if (cellData.getHiddenStatus()) {
			cellData.setHiddenStatus(false); //update the data's cellHidden flag
			cellText.setVisible(true); //make the cell text visible
		}//end if
		cellComment.setOpacity(0.8); //change opacity of the cell comment
	} //close makeMineVisible()
	
	
	//make the current cell visible (doesn't care about data's cellHidden value)
	public void makeCellVisible() {
		cellData.setHiddenStatus(false); //update the data's cellHidden flag
		cellComment.setVisible(false); //conceal the cellComment
		cellText.setVisible(true); //reveal the cellText
		cellBorder.setFill(null); //erase the cellBorder
	} //close makeCellVisible()
	
	
	//Reveal the cell's contents to the user
	public int revealCell() {
		//Make sure that the cell is currently hidden (avoids redundancy for cells that are already revealed)
		if (cellData.getHiddenStatus()) {
			cellData.setHiddenStatus(false); //change the hidden status to false
			cellComment.setVisible(false); //conceal any comments that were previously on the cell
			cellText.setVisible(true); //make the cell's text visible to the user
			cellBorder.setFill(null); //erase the cell border so that the user can see the cell's value
		} //end if
		return cellData.getCellVal(); //return the cell's value
	} //close revealCell()
	
	
	//Reset the cell to it's default values
	public void resetCellToDefault() {
		cellData.setHiddenStatus(true);	//make the cell hidden
		cellBorder.setFill(Color.BLACK); //refill the cell's border with black
		cellBorder.setStroke(Color.BLACK); //change the outline back to black.
		cellText.setVisible(false); //conceal the cell's text
		cellText.setText(cellData.mineIsPresent() ? "\u25CE" : (cellData.getCellVal() == 0 ? "" :
		 String.valueOf(cellData.getCellVal()))); //change the cell's text back to it's default value
		cellComment.setText(""); //reset the comment text
		cellData.setCellComment(""); //reset the cell's comment data
		cellComment.setVisible(false); //conceal any comments from the user
	} //close resetCellToDefault()
	
	
	//Zero-out the Minesweeper cell
	public void zeroOutCell() {
		cellData.setMineFlag(false); //change the minePresent flag to false
		cellData.setHiddenStatus(true); //change the cellHidden flag to true
		cellData.setCellVal(0); //change the cellValue to 0
		cellData.setCellComment(""); //change the cellComment to ""
		cellBorder.setFill(Color.BLACK); //fill the border in with black
		cellBorder.setStroke(Color.BLACK); //change the outline color to black
		cellText.setText(""); //erase the previous cellText
		cellText.setVisible(false); //conceal the cellText
		cellComment.setText(""); //erase the previous cellComent
		cellComment.setVisible(false); //conceal the cellComment
	} //close zeroOutCell()


} //close Class MinesweeperCell