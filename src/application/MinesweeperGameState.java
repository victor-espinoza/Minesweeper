/*
 * Victor Espinoza 
 * Created December 2022 - February 2023
 * Project: Minesweeper
 *
 * File Name: MinesweeperGameState.java
 * 
 * Description: This class is used to keep track of the overall game state when the user exits out of the 
 * program. This includes the elapsed time, the window position and size, the current game difficulty 
 * selected, if the game is finished, the current size of the game and the total number of mines, if a game 
 * was generated or not, and of course the cellData for each cell on the minesweeper board. By keeping 
 * track of all of this information, I am able to preserve the game state of the current game being played
 * (if applicable) and thus continue playing from where the previous session left off once the program 
 * is restarted. 
 * 
 */

package application;
import java.io.Serializable;

public class MinesweeperGameState implements Serializable{
	private static final long serialVersionUID = -7337564063518368538L; //unique UID
	private boolean gameFinished; //keeps track on whether the game was finished or not
	private boolean isFullScreen; //keeps track of whether the window was in full screen mode or not
	private boolean gameGenerated; //keeps track of whether a game was generated
	private String gameDifficulty; //keeps track of the difficulty being played
	private int gameRows, gameCols, gameMines; //keeps track of the rows, cols, and mines in the game
	private MinesweeperCellData[][] gameData; //keeps track of the MinesweeperCellData for each cell
	private long elapsedGameTime; //keeps track of the elapsed time
	private double windowHeight; //keeps track of the window height
	private double windowWidth; //keeps track of the window width
	private double windowXPos; //keeps track of the window's x position
	private double windowYPos; //keeps track of the window's y position
	
	
	//Constructor method for the MinesweeperGameState
	public MinesweeperGameState(boolean gameOver, String difficulty, int rows, int cols, int mines, 
	 MinesweeperCellData[][] cellStates, long elapsedTime, double height, double width, double xPos, 
	 double yPos, boolean screenFlag, boolean gameCreated) {
		gameFinished = gameOver; //Assign gameFinished flag
		gameDifficulty = difficulty; //Assign gameDifficulty value
		gameGenerated = gameCreated; //Assign gameGenerated flag
		gameRows = rows; //Assign gameRows value
		gameCols = cols; //Assign gameCols value
		gameMines = mines; //Assign gameMines value
		gameData = cellStates; //Assign gameData value
		elapsedGameTime = elapsedTime; //Assign elapsedGameTime value
		windowHeight = height; //Assign windowHeight value
		windowWidth = width; //Assign windowWidth value
		windowXPos = xPos; //Assign windowXPos value
		windowYPos = yPos; //Assign windowYPos value
		isFullScreen = screenFlag; //Assign isFullScreen flag	
	} //close MinesweeperGameState(...) constructor
	

	//Return the gameFinished value
	public boolean isFinished() {
		return gameFinished;
	} //close isFinished()
	
	
	//Assign a new gameFinished value
	public void setGameStatus(boolean newStatus) {
		gameFinished = newStatus;
	} //close setGameStatus(...)
	
	
	//Return the gameDifficulty value
	public String getGameDifficulty() {
		return gameDifficulty;
	} //close getGameDifficulty()
	
	
	//Assign a new gameDifficulty value
	public void setGameDifficulty(String newDifficulty) {
		gameDifficulty = newDifficulty;
	} //close setGameDifficulty(...)
	
	
	//Return the number of gameRows
	public int getGameRows() {
		return gameRows;
	} //close getGameRows()
	
	
	//Assign a new number of gameRows
	public void setGameRows(int newRows) {
		gameRows = newRows;
	} //close setGameRows(...)
	
	
	//Return the number of gameCols
	public int getGameCols() {
		return gameCols;
	} //close getGameCols()
	
	
	//Assign a new number of gameCols
	public void setGameCols(int newCols) {
		gameCols = newCols;
	} //close setGameCols(...)
	
	
	//Return the number of gameMines
	public int getGameMines() {
		return gameMines;
	} //close getGameMines()
	
	
	//Assign a new number of gameMines
	public void setGameMines(int newMines) {
		gameMines = newMines;
	} //close setGameMines(...)

	
	//Return all of the gameData for each cell on the minesweeper board
	public MinesweeperCellData[][] getGameData() {
		return gameData;
	} //close getGameData()
	
	
	//Assign new gameData for each cell on the minesweeper board
	public void setGameData(MinesweeperCellData[][] newGrid) {
		gameData = newGrid;
	} //close setGameData(...)
	
	
	//Return the total elapsed time
	public long getElapsedTime() {
		return elapsedGameTime;
	} //close getGameTime()
	
	
	//Assign a new total elapsed time
	public void setElapsedTime(long newTime) {
		elapsedGameTime = newTime;
	} //close setGameTime(...)
	
	
	//Return the window height
	public double getWindowHeight() {
		return windowHeight;
	} //close getWindowHeight()
	
	
	//Assign a new window height value
	public void setWindowHeight(double newHeight) {
		windowHeight = newHeight;
	} //close setWindowHeight(...)
	
	
	//Return the window width
	public double getWindowWidth() {
		return windowWidth;
	} //close getWindowWidth()
	
	
	//Assign a new window width value
	public void setWindowWidth(double newWidth) {
		windowWidth = newWidth;
	} //close setWindowWidth(...)
	
	
	//Return the window's x position
	public double getWindowXPos() {
		return windowXPos;
	} //close getWindowXPos()
	
	
	//Assign a new windowXPos value
	public void setWindowXPos(double newX) {
		windowXPos = newX;
	} //close setWindowXPos(...)
	
	
	//Return the window's y position
	public double getWindowYPos() {
		return windowYPos;
	} //close getWindowYPos()
	
	
	//Assign a new windowYPos value
	public void setWindowYPos(double newY) {
		windowHeight = newY;
	} //close setWindowYPos(...)
	
	
	//Return the isFullScreen value
	public boolean getFullScreenStatus() {
		return isFullScreen;
	} //close getFullScreenStatus()
	
	
	//Assign a new isFullScreen value
	public void setFullScreenStatus(boolean newStatus) {
		isFullScreen = newStatus;
	}//close setFullScreenStatus(...)
	
	
	//Return the gameGenerated value
	public boolean getGameGeneratedStatus() {
		return gameGenerated;
	} //close getGameGeneratedStatus()

	
	//Assign a new gameGenerated value
	public void setGameGeneratedStatus(boolean newStatus) {
		gameGenerated = newStatus;
	} //close setGameGeneratedStatus(...)

} //close class MinesweeperGameState
