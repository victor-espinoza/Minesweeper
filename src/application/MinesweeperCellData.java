/*
 * Victor Espinoza 
 * Created December 2022 - February 2023
 * Project: Minesweeper
 *
 * File Name: MinesweeperCellData.java
 * 
 * Description: This class is used to keep track of each Minesweeper Cell's data when the user exits out 
 * of the program. By keeping track of all of this information, I am able to preserve the state of the 
 * Minesweeper board and thus continue playing the same game from the previous session once the program 
 * is restarted. 
 * 
 */

package application;
import java.io.Serializable;
import java.util.Objects;

public class MinesweeperCellData implements Serializable {
    private static final long serialVersionUID = 6934733779798068447L; //unique UID
	public static final int MINE_VAL = -1; //default mine value is -1
	private int xInd; //x index of Minesweeper Cell
	private int yInd; //y index of Minesweeper Cell
	private int cellVal; //value of Minesweeper Cell (-1 = mine; 0 = empty; <0 = number of surrounding mines)
	private boolean minePresent; //flag to keep track of whether there is a mine present in the cell
	private boolean cellHidden; //flag to keep track of whether the cell is hidden or not
	private String cellComment; //keeps track of the cell's comment (if applicable)
	
	
	//Constructor method for the MinesweeperCellData
	public MinesweeperCellData(int x, int y, boolean mineStatus, String comment) {
		xInd = x; //assign the x index
		yInd = y; //assign the y index
		minePresent = mineStatus; //assign the minePresent flag
		cellHidden = true; //each new cell is hidden by default
		cellVal = (minePresent) ? MINE_VAL: 0; //assign the default cell value
		cellComment = comment; //assign the desired cell comment	
	} //close MinesweeperCellData(...) constructor

	
	//Changes the data given new input values
	public void setCellData(int x, int y, int newVal, boolean mineStatus, boolean hiddenStatus, String comment) {
		xInd = x; //assign a new x index
		yInd = y; //assign a new y index
		cellVal = newVal; //assign the new cell value
		minePresent = mineStatus; //assign the new minePresent flag
		cellHidden = hiddenStatus; //assign the new cellHidden flag
		cellComment = comment; //assign the new cell comment
	} //close setCellData(...)
	
	
	//Return the current x index
	public int getXInd() {
		return xInd;
	} //close getXInd()	
	
	
	//Assign a new x index
	public void setXInd(int x) {
		xInd = x;
	} //close setXInd(...)
	
	
	//Return the current y index
	public int getYInd() {
		return yInd;
	} //close getYInd()
	
	
	//Assign a new y index
	public void setYInd(int y) {
		yInd = y;
	} //close setYInd(...)

	
	//Return the current cell value
	public int getCellVal() {
		return cellVal;
	} //close getCellVal()
	
		
	//Assign a new cell Value
	public void setCellVal(int newVal) {
		cellVal = newVal;
	} //close setCellVal(...)
	
	
	//Return the current minePresent value
	public boolean mineIsPresent() {
		return minePresent;
	} //close mineIsPresent()
	

	//Assign a new minePresent value
	public void setMineFlag(boolean newVal) {
		minePresent = newVal;
	} //close setMineFlag(...)
		
	
	//Return the current cellHidden value
	public boolean getHiddenStatus() {
		return cellHidden;
	} //close getHiddenStatus()
	

	//Assign a new cellHidden value
	public void setHiddenStatus(boolean newVal) {
		cellHidden = newVal;
	} //close setHiddenStatus(...)
		
	
	//Return the current cellComment
	public String getCellComment() {
		return cellComment;
	} //close getCellComment()
	

	//Assign a new cellComment value
	public void setCellComment(String newText) {
		cellComment = newText;
	} //close setCellComment(...)
	

	//Override the equals method to allow MinesweeperCellData objects to be compared to each other
	@Override
    public boolean equals(Object object) {
        boolean itemsEqual = false; //objects are not equal by default
        //make sure the object is an instance of MinesweeperCellData
        if (object instanceof MinesweeperCellData){
        	//compare all of the member variables to each other to determine their equality
        	itemsEqual = (this.xInd == ((MinesweeperCellData) object).xInd) && 
        	 (this.yInd == ((MinesweeperCellData) object).yInd) && 
        	 (this.minePresent == ((MinesweeperCellData) object).minePresent) &&
        	 (this.cellHidden == ((MinesweeperCellData) object).cellHidden) && 
        	 (this.cellVal == ((MinesweeperCellData) object).cellVal) &&
        	 (this.cellComment == ((MinesweeperCellData) object).cellComment);
        } //end if
        return itemsEqual;
    } //close equals(...)
    
	
	//It is good practice to override the hashCode() method as well
    @Override
    public int hashCode() {
        return Objects.hash(xInd, yInd, minePresent, cellHidden, cellVal, cellComment);
    } //close hashCode()
	
} //close Class MinesweeperCellData
