/*
 * Victor Espinoza 
 * Created December 2022 - February 2023
 * Project: Minesweeper
 *
 * File Name: Main.java
 * 
 * Description: This class is where all of the magic happens. Buckle up, because you're in for a bumpy ride...
 *  
 */

package application;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import javafx.animation.Animation;
import javafx.animation.Timeline;
import javafx.animation.KeyFrame;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.ObjectExpression;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Duration;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import java.text.DecimalFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Main extends Application {	
	private static final double EASY_WND_HEIGHT = 400; //window height for easy difficulty
	private static final double EASY_WND_WIDTH = 330; //window width for easy difficulty
	private static final double EASY_GRID_HEIGHT = 600; //grid height for easy difficulty
	private static final double EASY_GRID_WIDTH = 600; //grid width for easy difficulty
	private static final double MEDIUM_WND_HEIGHT = 640; //window height for medium difficulty
	private static final double MEDIUM_WND_WIDTH = 580; //window width for medium difficulty
	private static final double MEDIUM_GRID_HEIGHT = 700; //grid height for medium difficulty
	private static final double MEDIUM_GRID_WIDTH = 700; //grid width for medium difficulty
	private static final double HARD_WND_HEIGHT = 640; //window height for hard difficulty
	private static final double HARD_WND_WIDTH = 1100; //window width for hard difficulty
	private static final double HARD_GRID_HEIGHT = 700; //window height for hard difficulty 
	private static final double HARD_GRID_WIDTH = 1350; //window width for hard difficulty
	private static final double WIDTH_SCALE = 1.6; //determines how wide the gameInfo text should be
    private static final double DEFAULT_FONT_SIZE = 32; //default font size
    private static final double WIDTH_RATIO_LIMIT = 2.0; //limit used when resizing custom games
	private static final double MAX_SCREEN_WIDTH = Screen.getPrimary().getVisualBounds().getWidth(); //1366
	private static final double MAX_SCREEN_HEIGHT = Screen.getPrimary().getVisualBounds().getHeight(); //728
	
	private static final int MAX_CELL_WIDTH = 76; //maximum cell size for each minesweeper cell
	private static final int ROW_LIMIT = 22; //maximum number of rows allowed on screen at once
	private static final int EASY_ROWS = 9; //number of rows used for the easy difficulty
	private static final int EASY_COLS = 9; //number of columns used for the easy difficulty
	private static final int EASY_MINES = 10; //number of mines used for the easy difficulty
	private static final int MEDIUM_ROWS = 16; //number of rows used for the medium difficulty
	private static final int MEDIUM_COLS = 16; //number of columns used for the medium difficulty
	private static final int MEDIUM_MINES = 40; //number of mines used for the medium difficulty
	private static final int HARD_ROWS = 16; //number of rows used for the hard difficulty
	private static final int HARD_COLS = 30; //number of columns used for the hard difficulty
	private static final int HARD_MINES = 99; //number of mines used for the hard difficulty
	private static final int TEXT_FIELD_SIZE = 50; //default text size for the game information 
	private static final int FLAG_VAL = 0; //value used to represent a flag comment
	private static final int QUESTION_VAL = -2; //value used to represent a question comment
	private static final int MINE_VAL = -1; //value used to represent a mine
	private static final int TEXT_SIZE_OFFSET = 25; //value used to proportionately scale game info to grid size
	private static final int NEW_GAME_DIALOG_WIDTH = 270; //window width for the new game dialog screen
	private static final int NEW_GAME_DIALOG_HEIGHT = 250; //window height for the new game dialog screen
	private static final int NEW_GAME_BTN_WIDTH = 220; //button width for buttons used in the new game dialog
	private static final int DIFF_DIALOG_WIDTH = 340; //window width for the change difficulty dialog confirmation
	private static final int DIFF_DIALOG_HEIGHT = 220; //window height for the change difficulty dialog confirmation
	private static final int DIFF_BTN_WIDTH = 290; //button width for buttons in the change difficulty dialog
	private static final int GAME_OVER_DIALOG_WIDTH = 307; //window width for the game over dialog
	private static final int GAME_OVER_DIALOG_HEIGHT = 237; //window height for the game over dialog
	private static final int CUSTOM_GAME_OVER_DIALOG_HEIGHT = 220; //window height for the custom dialog
	private static final int STATISTICS_DIALOG_HEIGHT = 175; //window height for the statistics window
	private static final int GAME_OVER_BTN_WIDTH = 70; //button width for the game over dialog
	private static final int CUSTOM_SIZE_LOWER_BOUND = 9; //minimum rows available for a custom game
	private static final int CUSTOM_ROWS_UPPER_BOUND = 24; //maximum rows available for a custom game
	private static final int CUSTOM_COLS_UPPER_BOUND = 30; //maximum columns available for a custom game
	private static final int CUSTOM_MINES_LOWER_BOUND = 10; //minimum columns available for a custom game
	private static final int LIST_CELL_HEIGHT = 30; //list height for statistics window
	private static final int LIST_CELL_WIDTH = 110;	//list width for statistics window
	private static final int BEST_TIMES_CELL_WIDTH = 150; //width for the best times section of the statistics	
	private static final int TEXT_OFFSET = 6; //offset for the text inside of the MinesweeperCell
	private static final int TOTAL_GAME_INFO_NODES = 6; //number of nodes in game info
	private static final int MAX_EXCLUDED_CELLS = 9; //number of maximum excluded cells
	private static final int SCREEN_WIDTH_OFFSET = (int) (MAX_SCREEN_WIDTH/CUSTOM_COLS_UPPER_BOUND) -  
	 CUSTOM_SIZE_LOWER_BOUND; //used to determine the proportional screen width when playing custom games
	private static final int SCREEN_HEIGHT_OFFSET = (int) (MAX_SCREEN_HEIGHT/CUSTOM_ROWS_UPPER_BOUND) -  
	 CUSTOM_SIZE_LOWER_BOUND; //used to determine the proportional screen width when playing custom games
	
	
	//default font size of the game info text
    private static final Font defaultFont = Font.font("Arial", FontWeight.BOLD, DEFAULT_FONT_SIZE);
	private static final Label difficultyLabel = new Label("Difficulty:"); //label used for difficulty text
	private static final DecimalFormat decimalFormatter = new DecimalFormat("0.00"); //formats to 2 decimal places
	
	private double wndHeight; //current window height
	private double wndWidth; //current window width	
	private double wndWidthRatio; //maintains the width/height ratio (used to resize the grid proportionally)
    private double oldWidth; //the old window width (before maximizing screen)
    private double oldHeight; //the old window height (before maximizing screen)
	
	private int customRows; //selected number of rows for a custom game
	private int customCols; //selected number of columns for a custom game
	private int customMines; //selected number of mines for a custom game
    private int gameRows; //number of rows used in the current game
    private int gameCols;//number of columns used in the current game
	private int totalMines; //number of mines used in the current game
	private int remainingMines; //number of remaining mines on the game board (totalMines - flag comments)
	
	private boolean customOptionSelected; //flag used to determine when a custom game is selected
	private boolean gameGenerated; //flag used to check if a game is generated or not
	private boolean mineRevealed; //flag used to check if a mine was revealed
	private boolean gameOver; //flag used to check if a game is finished
	private boolean resetCurrentGame; //flag used to check if the user wants to reset the current game
	private boolean endCurrentGame; //flag used to check if the user wants to end the current game
	private boolean screenMaximized; //flag used to see if the screen is currently maximized
	private boolean closeParentWindow; //flag used to check if the parent window should also be closed
	private boolean changeDifficulty; //flag used to check if the user wants to change difficulty immediately
	private boolean changeDifficultyWhenGameDone; //flag used to change the difficulty after the game ends
	
	private long startTime; //stores the start time for the game
	private long elapsedTime; //stores the elapsed time for the game
	private long resumeGameTime; //used when resuming an unfinished game
	
    private String gameDifficulty; //the current game difficulty
    private String tempDifficulty; //keeps track of the game difficulty the user wants to switch to
	private String maximizedGameDifficulty; //the game difficulty while the screen is maximized
	
	private Label elapsedTimeLabel; //label used for the elapsed time
	private Label remainingMinesLabel; //label used for the remaining mines
	private Label mineIcon; //label used to display the mine icon
	private Label clockIcon; //label used to display the clock icon
	
	private CustomDialog newGameDialog; //custom dialog for a new game
	private CustomDialog diffChangeDialog; //custom dialog for changing the difficulty
	private CustomDialog gameOverDialog; //custom dialog for a finished game
	private ObjectExpression<Font> cellTextExpression; //expression used for the cell text size

	private Timeline timeline; //keeps track of the elapsed time
	private HBox fillerNode; //filler HBox used for padding between the game information
	private static Stage mainWindow; //the main window that displays the program
	private BorderPane root; //container for the menu, game grid, and game info
	private GridPane grid; //container for all of the MinesweeperCells (the game grid)
	private HBox gameInfo; //container for the elapsed time and remaining mines information
	private MenuBar menubar; //displays all of the available options for the program
	
	private GameStatistics easyStats; //Serialized easy difficulty statistics data
	private GameStatistics mediumStats; //Serialized medium difficulty statistics data
	private GameStatistics hardStats; //Serialized hard difficulty statistics data
	private MinesweeperGameState gameState; //Serialized game state of previous session
	
	private MinesweeperCell[][] minesweeperBoard; //holds all of the MinesweeperCell objects (the gameboard)

	
	//Creates the file menu, game grid, and the game info used to display the ongoing progress of the game
	private Parent createContent() {
		root.setStyle("-fx-background-color: DARKGRAY"); //make window background dark gray
	
		//add menu items
        Menu FileMenu = new Menu("Game"); //main file menu for the program
        //New Game option
        MenuItem newGameMenuItem=new MenuItem("New Game"); 
        newGameMenuItem.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
            	//check to see if a game is currently generated or not
            	if(!gameGenerated)
            		createNewGame(); //create a new game
            	else  //ask the user what they want to do with the current game
            		displayNewGameDialogBox(gameDifficulty.equals("CUSTOM")); 
            } //close handle(...)
        }); //close setOnAction(...)
        //Add shortcut accelerator to the menu item
        newGameMenuItem.setAccelerator(new KeyCodeCombination(KeyCode.N, KeyCombination.CONTROL_DOWN));
        MenuItem statisticsMenuItem=new MenuItem("View Statistics");  
        statisticsMenuItem.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
            	displayStatisticsDialogBox(); //open a dialog showing the current statistics
            }//close handle(...)
        }); //close setOnAction(...)
        //Add shortcut accelerator to the menu item
        statisticsMenuItem.setAccelerator(new KeyCodeCombination(KeyCode.S, KeyCombination.CONTROL_DOWN));
        //Change Difficulty option
        MenuItem difficultyMenuItem=new MenuItem("Change Difficulty");
        //View Statistics option
        difficultyMenuItem.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
            	displayDifficultyDialogBox(); //open a dialog and let the user choose a new difficulty
            }//close handle(...)
        }); //close setOnAction(...)
        //Add shortcut accelerator to the menu item
        difficultyMenuItem.setAccelerator(new KeyCodeCombination(KeyCode.D, KeyCombination.CONTROL_DOWN));
        //Quit option
        MenuItem quitMenuItem=new MenuItem("Quit");    
        quitMenuItem.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
            	closeProgram(); //serialize statistics/game-state and exit the program    
            }//close handle(...)
        }); //close setOnAction(...)
        //Add shortcut accelerator to the menu item
        quitMenuItem.setAccelerator(new KeyCodeCombination(KeyCode.X, KeyCombination.CONTROL_DOWN));
        //Add all of the menu items to the menu
        FileMenu.getItems().addAll(newGameMenuItem, statisticsMenuItem, difficultyMenuItem, quitMenuItem);  
        menubar.getMenus().addAll(FileMenu); //add the menu to the menu bar
		createGameInfo(); //create all of the game information
		adjustWindowBounds(); //adjust the window bounds to fit the game that is currently being played
        createGrid(); //create the game grid that stores all of the MinesweeperCell objects
                      
		//add everything to the root BorderPane and position it accordingly
        root.setTop(menubar); //position the menu bar in the top of the window
        root.setCenter(grid); //position the grid in the center of the window
        BorderPane.setMargin(grid, new Insets(2, 5, 2, 5)); //add padding around the grid container
        BorderPane.setMargin(gameInfo, new Insets(0, 0, 1, 0)); //add padding around the gameInfo container
        root.setBottom(gameInfo); //position the game info on the bottom of the window
        	
		return root; //return the BorderPane
	}//close createContent()
	
	
	//Assigns the game bounds based on which game difficulty is selected
	public void adjustGridBounds() {
		switch (gameDifficulty) {
			case "EASY":
				gameRows = EASY_ROWS; //9 rows
				gameCols = EASY_COLS; //9 columns 
				totalMines = EASY_MINES; //10 mines
				break;
			case "MEDIUM":
				gameRows = MEDIUM_ROWS; //16 rows
				gameCols = MEDIUM_COLS; //16 columns
				totalMines = MEDIUM_MINES; //40 mines
				break;
			case "HARD":
				gameRows = HARD_ROWS; //16 rows
				gameCols = HARD_COLS; //30 columns
				totalMines = HARD_MINES; //99 mines
				break;
			case "CUSTOM":
				gameRows = customRows; //9-24 rows
				gameCols = customCols; //9-30 columns 
				totalMines = customMines; //10-667 mines (depends on grid size)
				break;
			default:
				gameRows = EASY_ROWS; //9 rows
				gameCols = EASY_COLS; //9 columns
				totalMines = EASY_MINES; //10 mines
		} //end switch (gameDifficulty)
	} //close adjustGameBounds()
	
	
	/* This method creates the GridPane that stores all of the MinesweeperCells of the game. The overall
	 * grid size changes according to the difficulty that the user has selected.
	 */
	public void createGrid() {
		grid.setAlignment(Pos.CENTER); //center the gridpane
        grid.setHgap(3); //horizontal gap between grid elements
        grid.setVgap(3); //vertical gap between grid elements
        grid.setPadding(new Insets(2, 5, 2, 5)); //padding between grid elements
        //adjust the maximum width and height of the grid according to the selected difficulty
		switch (gameDifficulty) {
			case "EASY":
				grid.setMaxHeight(EASY_GRID_HEIGHT); //600
				grid.setMaxWidth(EASY_GRID_WIDTH); //600
				break;
			case "MEDIUM":
				grid.setMaxHeight(MEDIUM_GRID_HEIGHT); //700
				grid.setMaxWidth(MEDIUM_GRID_WIDTH); //700
				break;
			case "HARD":
				grid.setMaxHeight(HARD_GRID_HEIGHT); //700
				grid.setMaxWidth(HARD_GRID_WIDTH); //1350
				break;
			case "CUSTOM":
				grid.setMaxHeight((MAX_CELL_WIDTH - customCols)  * customRows); //varies based on rows
				grid.setMaxWidth((MAX_CELL_WIDTH - customCols) * customCols); //varies based on columns
				break;
			default :
				grid.setMaxHeight(EASY_GRID_HEIGHT); //600
				grid.setMaxWidth(EASY_GRID_HEIGHT);	//600	
		}//end switch (gameDifficulty)
		
		grid.getChildren().clear(); //empty out any previous nodes from the grid
		boolean textExpressionDeclared = false;	//flag used to initialize expression only once
		//retrieve the serialized MinesweeperCellData from the previous session
		MinesweeperCellData[][] previousGameData = gameState.getGameData();
		boolean isEmpty; //flag that checks if a serialized cell had a comment or not
		
		/* This flag is used to ensure that this is the first game of the session (avoids redundancy 
		 * when creating a grid after the first game since the CellData no longer needs to be
		 * copied over).
		 */
		boolean firstGameOfSession = gameGenerated && !endCurrentGame && !changeDifficulty && 
		 !changeDifficultyWhenGameDone && !resetCurrentGame;
		int cellCommentColor; //stores the value of the desired comment color
		//Iterate through the grid and create every MinesweeperCell for the game
		for (int i = 0; i < gameRows; i++) {
			for (int j = 0; j < gameCols; j++) { 
				MinesweeperCell cell = new MinesweeperCell(i, j, false); //create a new cell
				//Transfer over the MinesweeperCellData if it is the first game of the session
				if (firstGameOfSession) {
					//extract data located in the provided index
					MinesweeperCellData data = previousGameData[i][j];
					if(data!= null) { //update cell data with previous session's data
						cell.updateCellData(data.getXInd(), data.getYInd(), data.getCellVal(),
						 data.mineIsPresent(), data.getHiddenStatus(), data.getCellComment());
						if(data.getCellVal() > 0) {
							//update the cell's Label and Label color with the correct values
							cell.updateCellText(String.valueOf(data.getCellVal()));
							cell.setCellTextColor(getColorVal(Math.toIntExact(data.getCellVal())));
						} //end if
						//add in any applicable comments to cells that are currently hidden
						isEmpty = data.getCellComment() == null || data.getCellComment().isBlank();
						if (!isEmpty && data.getHiddenStatus()) { 
							cell.addComment(data.getCellComment()); //add comment to cell
							//change the comment to the correct color
							cellCommentColor = data.getCellComment().equals("\u2691") ? FLAG_VAL : 
							 QUESTION_VAL; //determines what color the comment should be
							cell.setCommentsTextColor(getColorVal(cellCommentColor)); //change color
							//make sure to update the remainingMines value to reflect any mine comments
							remainingMines -= data.getCellComment().equals("\u2691") ? 1 : 0;
						} //end if 		
					} //end if
				} //end if
				
				//only initialize the expression once since every cell in the grid is the same size.
				if (!textExpressionDeclared) {
					/* Create an object binding so that the font is always slightly smaller than the cell's 
					 * height. This allows the height of the text displayed inside of each cell to scale 
					 * dynamically as the window is resized.
					 */
					cellTextExpression = Bindings.createObjectBinding(
					 () -> Font.font(cell.getHeight() - TEXT_OFFSET),  cell.heightProperty());
					textExpressionDeclared = true;  //prevents expression from being re-assigned more than once.
				} //end if
				//bind height to scale appropriately to window size
				cell.prefHeightProperty().bind(grid.heightProperty().divide(gameRows)); 
				//bind width the scale appropriately to window size
				cell.prefWidthProperty().bind(grid.widthProperty().divide(gameCols));
				//bind border rectangle width to the width of the cell
				cell.getCellRect().widthProperty().bind(cell.widthProperty()); 
				//bind border rectangle height to the height of the cell
				cell.getCellRect().heightProperty().bind(cell.heightProperty()); 
				//bind cell value text to cell size
				cell.getCellTextLabel().fontProperty().bind(cellTextExpression); 
				//bind comment text to cell size
				cell.getCellCommentLabel().fontProperty().bind(cellTextExpression); 
				minesweeperBoard[i][j] = cell; //assign cell to minesweeperGrid
				grid.add(cell, j, i); //add cell to grid pane				
			}//end inner for
		}//end outer for
		
		//Add in the mine text to any applicable cells with mines in them (if it's the first game)
		if (firstGameOfSession) {
			remainingMinesLabel.setText(String.valueOf(remainingMines)); //update remaining mines label
			Platform.runLater(new Runnable() {
				@Override
				public void run() {	
					//iterate through the minesweeper grid
					for (int i = 0; i < gameRows; i++) {
						for (int j = 0; j < gameCols; j++) {
							if (minesweeperBoard[i][j].hasMine()) {
								//update the cell value text to represent a mine
								minesweeperBoard[i][j].updateCellText("\u25CE"); 
								//update the color of the mine text as well
								minesweeperBoard[i][j].setCellTextColor(getColorVal(MINE_VAL));
							} //end if
							//reveal any cells that were revealed in the previous session
							if(!minesweeperBoard[i][j].getCellData().getHiddenStatus())
								minesweeperBoard[i][j].makeCellVisible(); //make cell visible				
						} //end inner for
					} //end outer for
				} //close run
			}); //close Platform.runLater(...)
		} //end if 
	
		//add a MouseEvent handler to handle clicks for each node of the GridPane
		addGridEvent();	
	} //close createGrid()
	
	
	//return the associated color based on the input value
	public Color getColorVal(int val) {
		Color valColor; //holds the designated color value
		switch (val) {
			case -2: //? comment
				valColor = Color.rgb(255, 229, 180); //cream
				break;
			case -1: //mine value
				valColor = Color.DARKSLATEGRAY.darker(); 
				break;
			case 0: //flag (mine comment)
				valColor = Color.MAROON; 
				break;				
			case 1: //1 surrounding mine
				valColor = Color.BLUE;
				break;
			case 2: //2 surrounding mines
				valColor = Color.GREEN;
				break;
			case 3: //3 surrounding mines
				valColor = Color.RED;
				break;
			case 4: //4 surrounding mines
				valColor = Color.PURPLE;
				break;
			case 5: //5 surrounding mines
				valColor = Color.ORANGE;
				break;
			case 6: //6 surrounding mines
				valColor = Color.TURQUOISE;
				break;
			case 7: //7 surrounding mines
				valColor = Color.BLACK;
				break;
			case 8: //8 surrounding mines
				valColor = Color.DARKGRAY;
				break;
			default: //default case
				valColor = Color.PINK;
		}//end switch (val)
		return valColor;	
	}//close getColorVal(...)
		
	
	//create the game info that displays the ongoing progress of the game
	public void createGameInfo() {
		gameInfo.setSpacing(10); //space between nodes
		gameInfo.setStyle("-fx-alignment: CENTER;"); //align content to the center	
		
		//used for the game info text that is displayed to the user
		DoubleProperty fontSize = new SimpleDoubleProperty(); 
		//scale the text size accordingly
        fontSize.bind(root.heightProperty().divide(TEXT_SIZE_OFFSET));
        
    	clockIcon = new Label("\u231A"); //clock icon
    	clockIcon.setFont(defaultFont); //set to the default font
    	//bind width of the clockIcon to the height of the game info HBox
    	clockIcon.prefWidthProperty().bind(gameInfo.heightProperty());  
    	//Style the clockIcon (black text and center alignment)
    	clockIcon.styleProperty().bind(Bindings.concat("-fx-font-size: ", fontSize.asString(), ";", 
    	 "-fx-background-color: LIGHTSTEELBLUE;", "-fx-border-color: LAVENDER;", "-fx-text-fill: BLUE;", 
    	 "-fx-alignment: CENTER;")); 

        elapsedTimeLabel = new Label(String.valueOf(resumeGameTime)); //elapsed time text
        elapsedTimeLabel.setFont(defaultFont); //set to the default font
        //bind the width of the elapsed time label to the height of the game info HBox
        elapsedTimeLabel.prefWidthProperty().bind(gameInfo.heightProperty().multiply(WIDTH_SCALE)); 
        //Style the elapsed time label (black and center alignment)
        elapsedTimeLabel.styleProperty().bind(Bindings.concat("-fx-font-size: ", fontSize.asString(), ";", 
         "-fx-background-color: LIGHTSTEELBLUE;", "-fx-border-color: LAVENDER;", "-fx-text-fill: BLACK;", 
         "-fx-alignment: CENTER;")); 

        startTime = 0; //reset the start time
        //create a Timeline that updates every 500 milliseconds
        timeline = new Timeline(new KeyFrame(Duration.millis(500), e -> {
        	//only update if the Timeline is already started
        	if (startTime != 0) {
        		//convert elapsed time to seconds
        		elapsedTime = ((System.nanoTime() - startTime) / 1000000000) + resumeGameTime; 
        		elapsedTimeLabel.setText(Long.toString(elapsedTime)); //update label
        	}//end if
        })); //close Timeline(...)
        timeline.setCycleCount(Animation.INDEFINITE); // loop forever
        
        fillerNode = new HBox(); //filler node used to separate the elapsed time from the remaining mines
        //bind width of the filler node to the amount of nodes in gameInfo (6)
    	fillerNode.prefWidthProperty().bind(grid.widthProperty().divide(TOTAL_GAME_INFO_NODES));   
    	fillerNode.setVisible(false); //make the filler node invisible
       
    	//Label showing how many mines are remaining on the board (each mine comment = 1 less mine)
        remainingMinesLabel = new Label(String.valueOf(remainingMines));
        remainingMinesLabel.setFont(defaultFont); //set to the default font
        //bind the remaining mines label to the height of the game info HBox
        remainingMinesLabel.prefWidthProperty().bind(gameInfo.heightProperty().multiply(WIDTH_SCALE));  
        //style label (black text and center alignment)
        remainingMinesLabel.styleProperty().bind(Bindings.concat("-fx-font-size: ", fontSize.asString(), 
         ";", "-fx-background-color: LIGHTSTEELBLUE;", "-fx-border-color: LAVENDER;", "-fx-text-fill: BLACK;", 
         "-fx-alignment: CENTER;")); 

    	mineIcon = new Label("\u25CE"); //mine icon
    	mineIcon.setFont(defaultFont); //set to the default font
    	//bind width of the mineIcon to the height of the game info HBox
    	mineIcon.prefWidthProperty().bind(gameInfo.heightProperty());   
    	//Style the mineIcon (black text and center alignment)
    	mineIcon.styleProperty().bind(Bindings.concat("-fx-font-size: ", fontSize.asString(), ";", 
    	 "-fx-background-color: LIGHTSTEELBLUE;", "-fx-border-color: LAVENDER;", "-fx-text-fill: MAROON;", 
    	 "-fx-alignment: CENTER;")); 

    	//add everything to the gameInfo HBox
        gameInfo.getChildren().addAll(clockIcon, elapsedTimeLabel, fillerNode, remainingMinesLabel, 
         mineIcon);		
	} //close createGameInfo()
	
	
	/* Populate the Minesweeper board (since it is empty by default until the user clicks on a cell. Once
	 * a cell is clicked, then the appropriate number of mines (totalMines) are placed in random locations
	 * on the grid. Note: the clicked cell and all of it's immediate neighbors are exempt from having
	 * mines placed in their respective locations on the grid.
	 */
	public void populateMinesweeperBoard(MinesweeperCell clickedCell, int totalMines) {
		Random ran = new Random(); //random variable 
		int randomXInd = 0; //x index for random point on grid
		int randomYInd = 0; //y index for random point on grid
		int mineCount = 0; //number of mines assigned so far
		//Exclude the clicked cell and it's immediate neighbors
		HashSet<Point> excludedIndexes = createExcludedCellList(clickedCell);
		//keep placing mines in random spots until the totalMines number is reached
		while (mineCount < totalMines) {
			//get random index
			randomXInd = ran.nextInt(gameRows); 
			randomYInd = ran.nextInt(gameCols);
			/* make sure the random index doesn't already have a mine. If it does, or if the
			 * random point lies within an excluded cell, then get a different index (and 
			 * repeat the process over again).
			 */
			MinesweeperCell cell = minesweeperBoard[randomXInd][randomYInd];
			if (cell.getCellVal() != MINE_VAL && !excludedIndexes.contains(
			 new Point(randomXInd, randomYInd))) {
				//update the cell value and mine status variables
				cell.setCellVal(MINE_VAL);
				cell.setMineStatus(true);
				Platform.runLater(new Runnable() {
					@Override
					public void run() {	
						cell.updateCellText("\u25CE"); //update cell value text
						cell.setCellTextColor(getColorVal(MINE_VAL)); //update color
					} //close run()
				}); //close Platform.runLater(...)
				mineCount++; //increment the mine count
			}//end if			
		}//end while
		
		/* Once all of the mines are assigned, I now need to iterate through the grid and assign
		 * the cell values for every cell that has a neighboring mine. Once this is finished, 
		 * then the Minesweeper game is ready to play.
		 */
		for (int i = 0; i < gameRows; i++) {
			for (int j = 0; j < gameCols; j++) {
				MinesweeperCell cell = minesweeperBoard[i][j]; //retrieve the cell at the given index
				/* Obtain a list of all neighboring cells (make sure to filter only the neighboring
				 * cells that are bombs). Assign the number of neighboring bombs as the current 
				 * cell's cell value.
				 */
				if (!cell.hasMine()) {
					long mines = getNeighbors(cell).stream().filter(c -> c.hasMine()).count();
					if (mines > 0) { //only add text to cells with adjacent mines
						cell.setCellVal(Math.toIntExact(mines)); //assign neighboring mine total
						Platform.runLater(new Runnable() {
							@Override
							public void run() {		
								//update the cell text and the color accordingly)
								cell.updateCellText(String.valueOf(mines)); 
								cell.setCellTextColor(getColorVal(Math.toIntExact(mines)));
							} //close run()
						}); //close Platform.runLater(...)
					}//end if
				}//end if 	
			}//end inner for
		}//end outer for
		
		//reveal the clicked cell to the user
		Platform.runLater(new Runnable() {
			@Override
			public void run() {	
				//reveal the clicked cell and (if it's empty) any adjacent non-mine cells to the user
				revealCells(clickedCell); 	
			} //close run()
		}); //close Platform.runLater(...)	
	}//close populateMinesweeperBoard(...)


	// A left click action reveals the cell (and any adjacent non-mine cells) to the user.
    private void leftClickAction(MinesweeperCell cell) {
    	/* Check to see if a game has been generated or not (also make sure that the
    	 * clicked cell doesn't have a flag comment. A flag comment means that
    	 * the user suspects that there is a mine on the selected cell and therefore
    	 * doesn't want to reveal the cell.
    	 */
        if (!gameGenerated && !cell.getCellComment().equals("\u2691")) {
        	populateMinesweeperBoard(cell, totalMines); //create a new game
        	gameGenerated = true; //let the program know that a game has been generated
        	startTime = System.nanoTime(); //get the start time
        	//start the timeline / start keeping track of the elapsed time
        	timeline.playFromStart(); 
        } else {
        	if (resetCurrentGame) {
        		//check to see if the user wants to reset the current game
            	startTime = System.nanoTime(); //get the start time
            	//start the timeline / start keeping track of the elapsed time
            	timeline.playFromStart();
            	resetCurrentGame = false; //update flag
        	} //end if
        	Platform.runLater(new Runnable() {
				@Override
				public void run() {	
					//don't reveal cell if it has a flag comment on it
					if (!cell.getCellComment().equals("\u2691")) {
						revealCells(cell); //reveal cells to user
						//check to see if a revealed cell contains a mine
						mineRevealed = (cell.getCellVal() == MINE_VAL);
						if (mineRevealed) {
							//a mine was revealed, end the game
							displayMines(); //display every mine on the field
							/* update the background color and the text color of
							 * the revealed mine that ended the game
							 */
							cell.updateBackgroundColor(Color.PALEVIOLETRED);
							cell.setCellTextColor(Color.CRIMSON.brighter());
							//update any incorrect flag comments that the user had
							updateIncorrectFlagCells();							
							gameOver = true; //end the game
						} else {
							gameOver = gameWon(); //update game status
							updateGameStatusAfterLeftClick(); //check to see if the game is over
						} //end else				
						if (gameOver)
							// Stop the timeline, adjust game statistics, and display the game over dialog
							updateFinishedGameVariables(); 
					} //end if
				} //close run()
			}); //close Platform.runLater(...)
        } //end else
    } //close leftClickAction

    
    /* A double click action is performed when the left click button is pressed twice. This action
     * is used as a shortcut to reveal all of the neighboring cells as long as the correct condition
     * is met. That conditions is that the clicked cell must have the correct number of mine comments
     * present in the neighboring cells. If it does, then this action reveals all of the neighboring
     * cells and all of their adjacent neighbors that aren't mines.
     */
    private void doubleClickAction(MinesweeperCell cell) {
        //generate a game if one hasn't been created yet.
    	if(!gameGenerated && !cell.getCellComment().equals("\u2691")) {
    		populateMinesweeperBoard(cell, totalMines); //generate a new game
        	gameGenerated = true; //let the program know that a game has been generated
        	startTime = System.nanoTime(); //get the start time
        	//start the timeline / start keeping track of the elapsed time
        	timeline.playFromStart();
    	} else {
    		int cellValue = cell.getCellVal(); //get the current cell's value
    		//make sure that the cell isn't hidden and only deal with cells that aren't empty
    		if (cellValue > 0 && !cell.isHidden()) {
    			//see how many flag comments are present in the selected cell's neighbors.
    			long flagsPresent = getNeighbors(cell).stream().filter(c -> 
    			 c.getCellComment().equals("\u2691")).count();	
    			//Make sure flag comments are equal to the cell value
    			if (Math.toIntExact(flagsPresent) == cell.getCellVal()) {
	    			Platform.runLater(new Runnable() { 
	    				@Override
	    				public void run() {	
	    					//reveal neighbor cells that don't have flag comments 
	    					List<MinesweeperCell> neighbors = getNeighbors(cell).stream().filter(c -> 
	    					 !c.getCellComment().equals("\u2691")).toList();
	    					/* Get a list of neighboring cells that contain mine values (this is due 
	    					 * to incorrect flag comments being placed on the board)
	    					 */
	    					List<MinesweeperCell> neighborMines = neighbors.stream().filter(c -> 
	    					 c.hasMine()).toList();
	    					//If there are no neighboring mines, then reveal all of the cells
	    					if (neighborMines.isEmpty()) {
		    					for (MinesweeperCell cell : neighbors)
		    						//reveal the cell and (if it's empty) any adjacent non-mine cells
		    						revealCells(cell); 
								gameOver = gameWon(); //update the game status
								updateGameStatusAfterLeftClick(); //check to see if the game is over
	    					} else {
	    						//there is a neighboring mine, end the game
	    						//reveal the first neighboring mine in the list
	    						neighborMines.get(0).revealCell();
	    						//update the text / background color for the game-losing mine
	    						neighborMines.get(0).updateBackgroundColor(Color.PALEVIOLETRED);
	    						neighborMines.get(0).setCellTextColor(Color.CRIMSON.brighter());
	    						//update any incorrect flag comments that the user had
								updateIncorrectFlagCells();
								displayMines(); //display all of mines in the game
								//let the program know that the game is over
								mineRevealed = true;
								gameOver = true;
	    					} //end else
	    					if (gameOver) {
	    						/*Stop the timeline, adjust the game statistics, and display the game 
	    						 * over dialog.
	    						*/
	    						updateFinishedGameVariables();
	    					} //end if
	    				} //close run()
	    			}); //close runLater(...)
    			} //end if
    		} //end if
    	}//end else		
    }//close doubleClickAction(...)

    
    /* A right click action creates a comment on any concealed cell. The click pattern goes from 
     * a flag comment to a question mark comment to no comment at all. This pattern repeats itself
     * for as many times as the user performs a right click action.
     */
    private void rightClickAction(MinesweeperCell cell) {
    	//make sure the cell is hidden
		if(cell.isHidden()) {
			Platform.runLater(new Runnable() {
				@Override
				public void run() {	
					//""- No Comments yet;  "\u2691" - Flag Comment;  "?" - Question Mark Comment
					String currText = cell.getCellComment(); //get the current comment
					//assign the desired comment based on what the current comment is
					String desiredText = currText.equals("") ? "\u2691" : 
					 (currText.equals("\u2691") ? "?" : (currText.equals("?") ? "" : "\u2691"));
					//update comment color if applicable
					if (!desiredText.isBlank()) {
						int desiredColor = desiredText.equals("\u2691") ? FLAG_VAL : QUESTION_VAL;
						//update remainingMines value if the comment is a flag
						remainingMines += desiredText.equals("\u2691") ? -1: 1;
						cell.setCommentsTextColor(getColorVal(desiredColor)); //update color
						//update remainingMinesLabel to reflect change in remainingMines value
						remainingMinesLabel.setText(String.valueOf(remainingMines));		
					}//end if
					cell.addComment(desiredText); //add the comment to the cell
				} //close run()
			});	//close Platform.runLater(...)	
		} //end if
    } //close rightClickAction(...)
    
    
    //check to see if the game is over
    public void updateGameStatusAfterLeftClick() {
    	if (gameOver) {
			/* If this point is reached, then it means that the clicked cell
			 * was the last hidden cell that didn't have a mine (the user 
			 * won the game). Display any remaining mines (that don't have 
			 * flag comments)
			 */
			displayUnflaggedMines();
			//the user won the game, so there are no more mines remaining
			remainingMines = 0;	
			//update the label to reflect this changed value
			remainingMinesLabel.setText(String.valueOf(remainingMines));	
		} //end if
    } //close updateGameStatusAfterLeftClick()
    
    
    // Stop the timeline, adjust game statistics, and display the game over dialog
    public void updateFinishedGameVariables() {
    	timeline.stop(); //stop keeping track of the elapsed time
		adjustGameStatistics(); //adjust the game statistics
		displayGameOverDialogBox(); //display the game over dialog
    } //close updateFinishedGameVariables()
    
    
    /* This dialog allows the user to select a different difficulty for the Minesweeper board.
     * If there is not a game in progress, then the change is automatically applied to the 
     * board. If there is an ongoing game, then the user is directed to another prompt where
     * they can decide to change the difficulty immediately, wait until the current game 
     * finishes, or cancel changing the difficulty altogether. The user can select between
     * easy (Beginner), medium (Intermediate), and hard (Advanced) difficulty for standard
     * games or they can even make their own custom game difficulty.
     */
    public void displayDifficultyDialogBox() {
    	String dialogTitle = "Change Difficulty"; //title for the dialog
		ButtonType okBtnType = new ButtonType("Ok", ButtonData.LEFT); //ok button
		Alert diffAlert = new Alert(AlertType.NONE, null, okBtnType, ButtonType.CANCEL);
		//make the okBtnType button the default button
		((Button)diffAlert.getDialogPane().lookupButton(okBtnType)).setDefaultButton(true);
		VBox difficultyDialogBox = new VBox(5); //container for the dialog box
		HBox difficultyOptionsContainer = new HBox(50);	//container for every difficulty option
		VBox standardDifficultyBtns = new VBox(5); //container for standard difficulty options
		GridPane radioBtnContainer = new GridPane(); //container for custom difficulty options
		radioBtnContainer.setHgap(5); //horizontal padding between elements
		radioBtnContainer.setVgap(5); //vertical padding between elements
		ToggleGroup group = new ToggleGroup(); //toggle for each of the standard option fields

		//Beginner button information
		RadioButton beginnerBtn = new RadioButton("Beginner\n10 Mines\n9 x 9 tile grid");
		beginnerBtn.setUserData("EASY"); //Easy difficulty
		beginnerBtn.setToggleGroup(group); //add button to toggle group
		//Intermediate button information
		RadioButton intermediateBtn = new RadioButton("Intermediate\n40 Mines\n16 x 16 tile grid");
		intermediateBtn.setUserData("MEDIUM"); //Medium difficulty
		intermediateBtn.setToggleGroup(group); //add button to toggle group
		//Advanced button information
		RadioButton advancedBtn = new RadioButton("Advanced\n99 Mines\n16 x 30 tile grid");
		advancedBtn.setUserData("HARD"); //Hard difficulty
		advancedBtn.setToggleGroup(group); //add button to toggle group
		//Custom button information
		RadioButton customSizeBtn = new RadioButton("Custom");
		customSizeBtn.setUserData("CUSTOM"); //Custom difficulty
		customSizeBtn.setToggleGroup(group); //add button to toggle group
		
		//add event handler for each button to return focus to Ok button of dialog pane. 
		group.getToggles().stream().forEach(btn -> ((RadioButton) btn).setOnMouseClicked(
		 new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
            	//give focus back to OK button
            	diffAlert.getDialogPane().lookupButton(okBtnType).requestFocus();
            } //close handle(...)
        })); //close getToggles().stream().forEach(...)
		
		//select the appropriate option based on the current game difficulty
		switch (gameDifficulty) {
			case "EASY":
				beginnerBtn.setSelected(true); //select beginner button
				break;
			case "MEDIUM":
				intermediateBtn.setSelected(true); //select intermediate button
				break;
			case "HARD":
				advancedBtn.setSelected(true); //select advanced button
				break;
			case "CUSTOM":
				customSizeBtn.setSelected(true); //select custom button
				break;
			default:
				beginnerBtn.setSelected(true); //select beginner button	
		} //end switch (gameDifficulty)
		
		//handle what happens when a button is selected
		group.selectedToggleProperty().addListener(new ChangeListener<Toggle>(){
			@Override
		    public void changed(ObservableValue<? extends Toggle> ov,
		     Toggle oldToggleVal, Toggle newToggleVal) {
		    	if (group.getSelectedToggle() != null) {
		    		//update the flag that checks if the custom button was selected
		    		customOptionSelected = 
		    		 (group.getSelectedToggle().getUserData().toString().equals("CUSTOM"));
		    		/* Temporarily assign the new difficulty to a variable, since the user
		    		 * hasn't confirmed whether they want to change the difficulty immediately.
		    		 */
		    		tempDifficulty = group.getSelectedToggle().getUserData().toString(); 
		    		//Move the focus back to the Ok button
		    		diffAlert.getDialogPane().lookupButton(okBtnType).requestFocus();
		    	} //end if
		    } //close changed(...)		
		}); //close addListener(...)
		
		Label customRowsLabel = new Label("Height (9-24): "); //label for custom rows
		Label customColsLabel = new Label("Width (9-30): "); //label for custom columns
		Label customMinesLabel = new Label("Mines (10-667): "); //label for custom mines	
		TextField customMinesInput = new TextField(); //text field for custom mines
		TextField customRowsInput = new TextField(); //text field for custom rows
		TextField customColsInput = new TextField(); //text field for custom columns
		
		//parse the user's inputs and make sure they are within their designated ranges
		configureTextFields(customSizeBtn, customMinesInput, customRowsInput, customColsInput);
		//Add all of the custom buttons to the GridPane 
		radioBtnContainer.add(customSizeBtn, 1, 1); 
		radioBtnContainer.add(customRowsLabel, 1, 2);
		radioBtnContainer.add(customRowsInput, 2, 2);
		radioBtnContainer.add(customColsLabel, 1, 3);
		radioBtnContainer.add(customColsInput, 2, 3);
		radioBtnContainer.add(customMinesLabel, 1, 4);
		radioBtnContainer.add(customMinesInput, 2, 4);
		//add all of the standard buttons to the VBox
		standardDifficultyBtns.getChildren().addAll(beginnerBtn, intermediateBtn, advancedBtn);
		//add standard buttons VBox and custom buttons GridPane to the container
		difficultyOptionsContainer.getChildren().addAll(standardDifficultyBtns, radioBtnContainer);
		//add difficulty label and the container of all of the difficulty options to the dialog box
		difficultyDialogBox.getChildren().addAll(difficultyLabel, difficultyOptionsContainer);
		diffAlert.setTitle(dialogTitle); //assign title to the dialog	
		diffAlert.getDialogPane().setContent(difficultyDialogBox); //set the content of the dialog
		//Set stage position so that it appears in the middle of the parent window
		Platform.runLater(new Runnable() {
		    @Override
		    public void run() {
		    	//get the midpoint for the x position
		        diffAlert.setX(mainWindow.getX() + mainWindow.getWidth() / 2 - diffAlert.getWidth() / 2);
		        //get the midpoint for the y position
		        diffAlert.setY(mainWindow.getY() + mainWindow.getHeight() / 2 - diffAlert.getHeight() / 2); 
		        //shift focus back to the ok button
		        ((Button) diffAlert.getDialogPane().lookupButton(okBtnType)).requestFocus();
		    } //close run()
		}); //close Platform.runLater(...)
		
		//handle the ok button being pressed
		diffAlert.getDialogPane().lookupButton(okBtnType).addEventFilter(ActionEvent.ACTION, event -> {
			//update flag that checks if a custom option was selected
			customOptionSelected = (group.getSelectedToggle().getUserData().toString().equals("CUSTOM"));
			//check to see if the desired difficulty is different from the current difficulty
			if (!gameDifficulty.equals(tempDifficulty) || customOptionSelected){
				//ask the user what they want to do if a game is already in progress
				if(gameGenerated)
					displayDifficultyDecisionDialogBox(tempDifficulty, gameDifficulty.equals("CUSTOM"), diffAlert);	
				else {
					//there is no game in progress, so change the difficulty immediately
					gameDifficulty = tempDifficulty; //assign the new difficulty
					closeParentWindow = true; //close the dialog window
					endCurrentGame = true; //end the current game
					changeDifficulty = true; //update the changeDifficulty flag
					oldWidth = mainWindow.getWidth();
					oldHeight = mainWindow.getHeight();
					createNewGame(); //create a new game in the desired difficulty
				}//end else
			} else if (gameDifficulty.equals(tempDifficulty))
				closeParentWindow = true; //close the current dialog window
			//only close the dialog window once the user has made a decision
	    	if(!closeParentWindow)
	    		 event.consume(); //user hasn't made a decision yet, so prevent the dialog from closing
	    }); //close addEventFilter(...)
		diffAlert.showAndWait(); //show the user the dialog and wait for a result		
    } //close displayDifficultyDialogBox()
    
    
    /* This method configures the custom text fields so that they have the desired functionality. This 
     * functionality includes parsing out input values that are not numbers and the ability to automatically 
     * update any input values if they fall outside of the given ranges. For rows that range is 9-24, for 
     * columns that range is 9-30, and for mines the range is 10-667. The maximum amount of mines allowed
     * varies according to the size of the custom game.
     */
    public void configureTextFields(RadioButton parentBtn, TextField mines, TextField rows, TextField cols ) {
		mines.setPrefWidth(TEXT_FIELD_SIZE); //set the preferred size
		mines.setMaxWidth(TEXT_FIELD_SIZE); //set the maximum size 
		mines.setText(String.valueOf(customMines)); //assign the text to the given input value
		//only allow the user to enter a value in this text field if the Custom option is selected
		mines.disableProperty().bind(parentBtn.selectedProperty().not());
		
		//make sure the input value is a number
		mines.textProperty().addListener(new ChangeListener<String>() {
		    @Override
		    public void changed(ObservableValue<? extends String> observable, String oldValue, 
		        String newValue) {
		        if (!newValue.matches("\\d*"))
		            mines.setText(newValue.replaceAll("[^\\d]", "")); //erase any non-numeric characters
		    } //close changed(...)
	    }); //close addListener(...)
		
		//parse the input value so that it is within the appropriate range
		mines.focusedProperty().addListener((observable, oldValue, newValue) -> {
			if (mines.isFocused() == false && !mines.getText().isBlank()) {	   
			   try {
				   //get the value of the mines text field
				   int textVal = Integer.parseInt(mines.getText());
				   //find the limit to how many mines are allowed for the desired game dimensions
				   int mineLimit = (customCols - 1) * (customRows - 1);
				   //if the value is less than the minimum number of mines, change it
			       if (textVal < CUSTOM_MINES_LOWER_BOUND)
			    	   mines.setText(String.valueOf(CUSTOM_MINES_LOWER_BOUND)); //set to lower bound
				   else if (textVal > mineLimit) //input exceeded mine limit, change it to mine limit
					   mines.setText(String.valueOf(mineLimit)); //set to mine limit
				   customMines = Integer.parseInt(mines.getText()); //assign new value to text field
		    	} catch (NumberFormatException e) {
		    		//find the limit to how many mines are allowed for the desired game dimensions
		    		int mineLimit = (customCols - 1) * (customRows - 1);
		    		//set text to the mine limit if there is an exception
		    		mines.setText(String.valueOf(mineLimit)); 
		    		customMines = mineLimit; //update desired value
		    	} //close catch(...)  
		   } //end if(...)
		}); //close addListener(...)
		
		rows.setPrefWidth(TEXT_FIELD_SIZE); //set the preferred size
		rows.setMaxWidth(TEXT_FIELD_SIZE); //set the maximum size
		rows.setText(String.valueOf(customRows)); //assign the text to the given input value
		//only allow the user to enter a value in this text field if the Custom option is selected
		rows.disableProperty().bind(parentBtn.selectedProperty().not());
		
		//make sure the input value is a number
		rows.textProperty().addListener(new ChangeListener<String>() {
		    @Override
		    public void changed(ObservableValue<? extends String> observable, String oldValue, 
		        String newValue) {
		        if (!newValue.matches("\\d*"))
		        	rows.setText(newValue.replaceAll("[^\\d]", "")); //erase any non-numeric characters
		    } //close changed(...)
	    }); //close addListener(...)
		
		//parse the input value so that it is within the appropriate range
		rows.focusedProperty().addListener((observable, oldValue, newValue) -> {
		   if (rows.isFocused() == false && !rows.getText().isBlank()) {
			   try {
				   int textVal = Integer.parseInt(rows.getText()); //get input value
				   //change value to lower bound if it is smaller
			       if (textVal < CUSTOM_SIZE_LOWER_BOUND)
			    	   rows.setText(String.valueOf(CUSTOM_SIZE_LOWER_BOUND));
				   else if (textVal > CUSTOM_ROWS_UPPER_BOUND) //change value to upper bound if bigger
					   rows.setText(String.valueOf(CUSTOM_ROWS_UPPER_BOUND));
				   customRows = Integer.parseInt(rows.getText()); //update value
		    	} catch (NumberFormatException e) {
		    		//set text to the maximum value if there is an exception
		    		rows.setText(String.valueOf(CUSTOM_ROWS_UPPER_BOUND));
		    		customRows = CUSTOM_ROWS_UPPER_BOUND; //update desired value
		    	} //close catch(...)
			   
			    //determine the mine limit given the new input value
			    int mineLimit = (customCols - 1) * (customRows - 1);
			    //if the value is less than the minimum number of mines, change it
			    if (customMines < CUSTOM_MINES_LOWER_BOUND) {
			    	//set text to lower bound
			    	mines.setText(String.valueOf(CUSTOM_MINES_LOWER_BOUND));
			    	customMines = CUSTOM_MINES_LOWER_BOUND; //update value
			    } else if (customMines > mineLimit) { //value exceeds mine limit, so change it
			    	//set value to the mine limit
					mines.setText(String.valueOf(mineLimit));
			    	customMines = mineLimit; //update value
			    } //end else 
		   } //end if
		}); //close addListener(...)

		cols.setPrefWidth(TEXT_FIELD_SIZE); //set the preferred size
		cols.setMaxWidth(TEXT_FIELD_SIZE); //set the maximum size
		cols.setText(String.valueOf(customCols)); //assign the text to the given input value
		//only allow the user to enter a value in this text field if the Custom option is selected
		cols.disableProperty().bind(parentBtn.selectedProperty().not());
		
		//make sure the input value is a number
		cols.textProperty().addListener(new ChangeListener<String>() {
		    @Override
		    public void changed(ObservableValue<? extends String> observable, String oldValue, 
		        String newValue) {
		        if (!newValue.matches("\\d*"))
		        	cols.setText(newValue.replaceAll("[^\\d]", "")); //erase any non-numeric characters
		    } //end if
	    }); //close addListener(...)
		
		//parse the input value so that it is within the appropriate range
		cols.focusedProperty().addListener((observable, oldValue, newValue) -> {
			if (cols.isFocused() == false && !cols.getText().isBlank()) {
				try {
				   int textVal = Integer.parseInt(cols.getText()); //get input value
				   //change value to lower bound if it is smaller
			       if (textVal < CUSTOM_SIZE_LOWER_BOUND)
			    	   cols.setText(String.valueOf(CUSTOM_SIZE_LOWER_BOUND));
				   else if (textVal > CUSTOM_COLS_UPPER_BOUND) //change value to upper bound if bigger
					   cols.setText(String.valueOf(CUSTOM_COLS_UPPER_BOUND));
				   customCols = Integer.parseInt(cols.getText()); //update value
		    	} catch (NumberFormatException e) {
		    		//set text to the maximum value if there is an exception
		    		cols.setText(String.valueOf(CUSTOM_COLS_UPPER_BOUND));
		    		customCols = CUSTOM_COLS_UPPER_BOUND; //update value
		    	} //close catch(...)
				
				//determine the mine limit given the new input value
			    int mineLimit = (customCols - 1) * (customRows - 1);
			    //if the value is less than the minimum number of mines, change it
			    if (customMines < CUSTOM_MINES_LOWER_BOUND) {
			    	//set to lower bound
			    	mines.setText(String.valueOf(CUSTOM_MINES_LOWER_BOUND));
			    	customMines = CUSTOM_MINES_LOWER_BOUND; //update value
			    } else if (customMines > mineLimit) {  //value exceeds mine limit, so change it
			    	//set text to the mine limit
					mines.setText(String.valueOf(mineLimit));
			    	customMines = mineLimit; //update value
			    } //end else if
		   } //end if
		}); //close addListener(...)
    } //close configureTextFields(...)
    

    /* This dialog confirms what the user wants to do when there is currently an ongoing game and the user
     * selected to change the difficulty. They can either decide to change the difficulty immediately (which
     * will result in a loss for the user's statistics if a standard difficulty is being played), or they can
     * change the difficulty once the current game is finished.
     */
    public void displayDifficultyDecisionDialogBox(String desiredDifficulty, Boolean customGame, Alert parent) {
    	String dialogTitle = "About Current Game Progress"; //title for the dialog
		String dialogContent = "This difficulty change won't apply to the current game in progress." + 
    	 " What do you want to do?\n\n"; //content prompt for the dialog
		/* The button text changes depending on if the ongoing game is a custom difficulty or not (since 
		 * statistics are not tracked for custom games)
		 */
		String[] btnOptions = new String[]{"Quit/Start a new game with the desired difficulty." + 
    	 (customGame ? "\n ": "\n*This counts as a loss in your statistics."), "Continue Playing\n*This change " +
		 "will be implemented in the next game."};
		//create the dialog
		diffChangeDialog = new CustomDialog(parent.getDialogPane().getScene().getWindow(), 
		 DIFF_DIALOG_WIDTH, DIFF_DIALOG_HEIGHT, DIFF_BTN_WIDTH, true, dialogTitle, dialogContent, btnOptions);
		
		//Set dialog position so that it appears in the middle of the parent window
		Platform.runLater(new Runnable() {
		    @Override
		    public void run() {
		    	//set the midpoint for the x position
		    	diffChangeDialog.setX(parent.getX() + parent.getWidth() / 2 - diffChangeDialog.getWidth() / 2);
		    	//set the midpoint for the y position
		    	diffChangeDialog.setY(parent.getY() + parent.getHeight() / 2 - diffChangeDialog.getHeight() / 2);
		    } //close run()
		}); //close Platform.runLater(...)
		
		//show the dialog and wait for a result
		Optional<String> alertResult = diffChangeDialog.showDialog();	
		if (alertResult.isPresent()) { 
			closeParentWindow = true; //a result is selected, so close the parent window
			if (alertResult.get() == btnOptions[0]) { //Quit/Start a new game
				endCurrentGame = true; 
				changeDifficulty = true;
				oldWidth = mainWindow.getWidth();
				oldHeight = mainWindow.getHeight();
				timeline.stop();
				adjustGameStatistics();
				//generate a new board if the difficulty was changed
				gameDifficulty = desiredDifficulty;
				createNewGame(); 
			} else if (alertResult.get() == btnOptions[1]) //Continue playing the ongoing game
				changeDifficultyWhenGameDone = true; //change the difficulty after the next game.
		} //end if	
    } //close displayDifficultyDecisionDialogBox(...)
    
    
    /* This dialog allows the user to reset their current statistics scores for any of the standard
     * difficulty levels. Before resetting the statistics, the user must confirm that they are positive
     * that they want to reset their statistics. Doing so erases all of their records for the selected
     * difficulty and this action cannot be reversed.
     */
    public void displayResetStatsDialogBox(String inputDifficulty, Alert parent) {
    	String dialogTitle = "Reset Statistics"; //dialog title
    	//dialog prompt
		String dialogContent = "Are you sure you want to reset your " + inputDifficulty + " statistics?"; 
		//create alert
		Alert resetConfirmationAlert = new Alert(AlertType.NONE, null, ButtonType.YES, ButtonType.NO);
		resetConfirmationAlert.setTitle(dialogTitle); //assign title
		resetConfirmationAlert.setContentText(dialogContent); //assign content
		
		//set dialog position so that it appears in the middle of the parent window
		Platform.runLater(new Runnable() {
		    @Override
		    public void run() {
		    	//set the midpoint for the x position
		        resetConfirmationAlert.setX(parent.getX() + parent.getWidth() / 2 - 
		         resetConfirmationAlert.getWidth() / 2);
		        //set the midpoint for the y position
		        resetConfirmationAlert.setY(parent.getY() + parent.getHeight() / 2 - 
		         resetConfirmationAlert.getHeight() / 2);
		    } //close run()
		}); //close Platform.runLater(...)
		//show dialog and wait for result
		Optional<ButtonType> alertResult = resetConfirmationAlert.showAndWait();
		if (alertResult.isPresent()) {
			if (alertResult.get() == ButtonType.YES) { //reset selected statistics
				closeParentWindow = true; //close the parent window
				//erase the desired game statistics
				switch (inputDifficulty) {
					case "Beginner": //erase easy statistics
						easyStats.resetGameStatistics();
						break;
					case "Intermediate": //erase medium statistics
						mediumStats.resetGameStatistics();
						break;
					case "Advanced": //erase hard statistics
						hardStats.resetGameStatistics();
						break;
					default:	
				}//end switch (inputDifficulty)
			}//end if
		}//end if
    } //close displayResetStatsDialogBox(...)
    
    
    /* This dialog appears whenever there is currently an ongoing game and the user selects the New Game option 
     * from the file menu. The user is prompted to decide what they want to do with the current game. They can
     * either start a new game (counts as a loss if playing a standard difficulty), reset the current game (also
     * counts as a loss if playing a standard difficulty), or continue playing the current game.
     */
    public void displayNewGameDialogBox(Boolean customGame) {
    	String dialogTitle = "New Game"; //dialog title
		String dialogContent = "What do you want to do with the game in progress?\n\n"; //dialog prompt 
		/* The button text changes depending on if the ongoing game is a custom difficulty or not (since 
		 * statistics are not tracked for custom games)
		 */
		String[] btnOptions = new String[]{"Quit/Start New Game" + 
		 (customGame ? "\n ": "\n*This counts as a loss in your statistics."), 
		 "Restart Current Game" + (customGame ? "\n ": "\n*This counts as a loss in your statistics."), 
		 "Continue Playing"};
		//create the dialog
		newGameDialog = new CustomDialog(mainWindow, NEW_GAME_DIALOG_WIDTH, NEW_GAME_DIALOG_HEIGHT, 
		 NEW_GAME_BTN_WIDTH, true, dialogTitle, dialogContent, btnOptions);
		//show the dialog and wait for a result
		Optional<String> alertResult = newGameDialog.showDialog();	
		if (alertResult.isPresent()) { 
			//only do something if the result isn't to continue playing the current game
			if (alertResult.get() != btnOptions[2]) {
				gameOver = true; //alert program that the game is over
				endCurrentGame = true; //end the current game
				timeline.stop(); //timeline no longer needs to update, so stop it
				if (!gameDifficulty.equals("CUSTOM"))
					adjustGameStatistics(); //adjust game statistics if applicable
				
				if (alertResult.get() == btnOptions[0]) //Quit/Start New Game Option
					createNewGame(); //create a new game
				else //Reset Current Game Option
					resetMinesweeperBoard(); //reset the current game
			}//end if
		} //end if	
    } //close displayNewGameDialogBox(...)
    
    
    /* This method generates a new Minesweeper grid based on the desired difficulty. It is important to note
     * that this new grid will be empty because the game isn't actually populated until the user clicks
     * somewhere on the grid.
     */
    public void createNewGame() {
    	//adjust the grid bounds if the game difficulty was changed
		if (changeDifficulty || customOptionSelected || changeDifficultyWhenGameDone) {
			//check to see if the difficulty needs to be changed
	    	if (changeDifficultyWhenGameDone)
	    		gameDifficulty = tempDifficulty; //assign the new difficulty
			adjustWindowBounds(); //adjust the window bounds to compensate for the new difficulty
			adjustGridBounds(); //adjust the grid bounds
			minesweeperBoard = new MinesweeperCell[gameRows][gameCols]; //create a new Minesweeper board
			createGrid(); //create the game grid that stores all of the MinesweeperCell objects
		} //end if
		//reset any important game variables and zero out all of the cells in the Minesweeper board
		zeroOutMinesweeperBoard();
    } //close createNewGame()
    
    
    //Adjust any game statistics once a standard difficulty (non-custom) game is finished.
    public void adjustGameStatistics() {
    	if (gameOver || endCurrentGame) { //make sure the game is over
    		//adjust appropriate game variables	
       		Format mdy = new SimpleDateFormat("MM/dd/yyyy"); //date format
       		String currDate = mdy.format(new Date()); //current date
    		String endTimeData =  Math.toIntExact(elapsedTime) + " \t\t\t" + currDate; //end time
    		int bestTimeIndex; //index where the current game should go (for the user's best game times)
    		switch (gameDifficulty) {
				case "EASY":
					easyStats.incrementGamesPlayed(); //add finished game to total games played
					if (!mineRevealed && !endCurrentGame) { //user won the game
						easyStats.incrementGamesWon(); //add the win to the statistics
						//get the best time index for the won game (only the top 5 times are recorded)
						bestTimeIndex = easyStats.getInputIndex(Math.toIntExact(elapsedTime));
						//add the game to the best times list (if applicable)
						easyStats.addBestTime(bestTimeIndex, endTimeData);				
					} //end if
					easyStats.calculateWinPercentage(); //update win percentage
					//update current streak
					easyStats.adjustCurrentStreak(!mineRevealed && !endCurrentGame);
					easyStats.adjustLongestWinningStreak(); //update longest losing streak
					easyStats.adjustLongestLosingStreak(); //update longest winning streak
					break;
				case "MEDIUM":
					mediumStats.incrementGamesPlayed(); //add finished game to total games played
					if (!mineRevealed && !endCurrentGame) { //user won the game
						mediumStats.incrementGamesWon(); //add the win to the statistics
						//get the best time index for the won game (only the top 5 times are recorded)
						bestTimeIndex = mediumStats.getInputIndex(Math.toIntExact(elapsedTime));
						//add the game to the best times list (if applicable)
						mediumStats.addBestTime(bestTimeIndex, endTimeData);				
					} //end if
					mediumStats.calculateWinPercentage(); //update win percentage
					//update current streak
					mediumStats.adjustCurrentStreak(!mineRevealed && !endCurrentGame);
					mediumStats.adjustLongestWinningStreak(); //update longest losing streak
					mediumStats.adjustLongestLosingStreak(); //update longest winning streak
					break;
				case "HARD":
					hardStats.incrementGamesPlayed(); //add finished game to total games played
					if (!mineRevealed && !endCurrentGame) { //user won the game
						hardStats.incrementGamesWon(); //add the win to the statistics
						//get the best time index for the won game (only the top 5 times are recorded)
						bestTimeIndex = hardStats.getInputIndex(Math.toIntExact(elapsedTime));
						//add the game to the best times list (if applicable)
						hardStats.addBestTime(bestTimeIndex, endTimeData);				
					} //end if
					hardStats.calculateWinPercentage(); //update win percentage
					//update current streak
					hardStats.adjustCurrentStreak(!mineRevealed && !endCurrentGame);
					hardStats.adjustLongestWinningStreak(); //update longest losing streak
					hardStats.adjustLongestLosingStreak(); //update longest winning streak
					break;
				default: //custom game statistics don't count, so do nothing
    		} //end switch (gameDifficulty)
    	} //end if
    } //close adjustGameStatistics()
    
    
    /* This dialog displays the statistics for all of the different standard difficulties (Easy, 
     * Medium, and Hard). This data includes the 5 fastest winning times that the user has achieved 
     * for the difficulty, the amount of games played, the win percentage, the longest winning 
     * streak, the longest losing streak, and the current streak that the user is on. The user can 
     * see all of this data for each of the standard difficulties. They can also choose to reset 
     * any of the statistic data independently from the other difficulties' data.
     */
    public void displayStatisticsDialogBox() {
       	String dialogTitle = "Minesweeper Statistics"; //title for the dialog
		ButtonType resetBtnType = new ButtonType("Reset", ButtonData.RIGHT); //custom button
		//create dialog
		Alert statisticsAlert = new Alert(AlertType.NONE, null, ButtonType.CLOSE, resetBtnType);
		//make the close button the default button
		((Button)statisticsAlert.getDialogPane().lookupButton(ButtonType.CLOSE)).setDefaultButton(true);
		statisticsAlert.getDialogPane().setMinHeight(STATISTICS_DIALOG_HEIGHT); //assign minimum height
		HBox dialogContainer = new HBox(25); //container for the dialog content
		VBox bestTimesContainer = new VBox(5); //container for the fastest user times
		bestTimesContainer.setPrefWidth(BEST_TIMES_CELL_WIDTH); //set the width for the fastest times
		VBox statisticsOptionsContainer = new VBox(5); //container for the rest of the statistics
		Label statisticsTextLabel = new Label(); //Label for the statistics text
		Label bestTimesTextLabel = new Label(); //Label for the best times text
		
		//ListView that contains the different difficulties that the user can select from
		ListView<String> statisticsOptionsList = new ListView<String>();
		//The text for all of the different difficulty options
		String[] listOptions = new String[]{"Beginner", "Intermediate", "Advanced"};
		//add all of the options to the list
		for (String option : listOptions)
			statisticsOptionsList.getItems().add(option);
		//set the preferred height of the list
		statisticsOptionsList.setPrefHeight(statisticsOptionsList.getItems().size() * LIST_CELL_HEIGHT);
		statisticsOptionsList.setPrefWidth(LIST_CELL_WIDTH); //set the preferred width of the list
		Label bestTimesLabel = new Label("Best Times:"); //assign label text
		
		//populate best times for the highlighted statisticsOption.
		statisticsOptionsList.getSelectionModel().selectedItemProperty().addListener(
		 new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {
				//determine which option was selected
				String selectedOption = statisticsOptionsList.getSelectionModel().getSelectedItem().toString();
				List<String> bestTimesList; //list that stores the fastest times
				String allBestTimes = ""; //empty by default
				String gameStatistics = ""; //empty by default
				switch (selectedOption){
					case "Beginner": //easy mode
						//retrieve times from the easy GameStatistics object
						bestTimesList = easyStats.getBestTimes();
						//iterate through list and add each time to the string variable
						for (String time: bestTimesList)
							allBestTimes += time + "\n";					
						//populate gamesStatistics String with data from the easy GameStatistics object
						gameStatistics += "Games Played: " + easyStats.getGamesPlayed();
						gameStatistics += "\nGames Won: " + easyStats.getGamesWon();
						gameStatistics += "\nWin Percentage: " + decimalFormatter.format(
						 easyStats.getWinPercentage()) + "%";
						gameStatistics += "\nLongest Winning Streak: " + easyStats.getLongestWinningStreak();
						gameStatistics += "\nLongest Losing Streak: " + easyStats.getLongestLosingStreak();
						gameStatistics += "\nCurrent Streak: " + easyStats.getCurrentStreak();			
						break;
					case "Intermediate": //medium mode
						//retrieve times from the medium GameStatistics object
						bestTimesList = mediumStats.getBestTimes();
						//iterate through list and add each time to the string variable
						for (String time: bestTimesList)
							allBestTimes += time + "\n";
						//populate gamesStatistics String with data from the medium GameStatistics object
						gameStatistics += "Games Played: " + mediumStats.getGamesPlayed();
						gameStatistics += "\nGames Won: " + mediumStats.getGamesWon();
						gameStatistics += "\nWin Percentage: " + decimalFormatter.format(
						 mediumStats.getWinPercentage()) + "%";
						gameStatistics += "\nLongest Winning Streak: " + mediumStats.getLongestWinningStreak();
						gameStatistics += "\nLongest Losing Streak: " + mediumStats.getLongestLosingStreak();
						gameStatistics += "\nCurrent Streak: " + mediumStats.getCurrentStreak();
						break;
					case "Advanced": //hard mode
						//retrieve times from the hard GameStatistics object
						bestTimesList = hardStats.getBestTimes();
						//iterate through list and add each time to the string variable
						for (String time: bestTimesList)
							allBestTimes += time + "\n";
						//populate gamesStatistics String with data from the hard GameStatistics object
						gameStatistics += "Games Played: " + hardStats.getGamesPlayed();
						gameStatistics += "\nGames Won: " + hardStats.getGamesWon();
						gameStatistics += "\nWin Percentage: " + decimalFormatter.format(
						 hardStats.getWinPercentage()) + "%";
						gameStatistics += "\nLongest Winning Streak: " + hardStats.getLongestWinningStreak();
						gameStatistics += "\nLongest Losing Streak: " + hardStats.getLongestLosingStreak();
						gameStatistics += "\nCurrent Streak: " + hardStats.getCurrentStreak();
						break;
					default: //no valid mode selected; do nothing
				} //end switch (selectedOption)
				bestTimesTextLabel.setText(allBestTimes); //add fastest times text to label
				statisticsTextLabel.setText(gameStatistics); //add game statistics text to label
			} //close changed(...)
		}); //close addListene(...)
		
		//preemptively select the appropriate option based on the current game difficulty
		switch (gameDifficulty) {
			case "EASY":
				statisticsOptionsList.getSelectionModel().select("Beginner"); //select easy difficulty
				break;
			case "MEDIUM":
				statisticsOptionsList.getSelectionModel().select("Intermediate"); //select medium difficulty
				break;
			case "HARD":
				statisticsOptionsList.getSelectionModel().select("Advanced"); //select hard difficulty
				break;
			default:
				statisticsOptionsList.getSelectionModel().select("Beginner"); //select easy difficulty by default	
		}//end switch (gameDifficulty)	
		
		//add list to options to it's container
		statisticsOptionsContainer.getChildren().addAll(statisticsOptionsList);		
		//add best times to it's container
		bestTimesContainer.getChildren().addAll(bestTimesLabel, bestTimesTextLabel);	
		//add the list container, the best times container, and the statistics data to the dialog container
		dialogContainer.getChildren().addAll(statisticsOptionsContainer, bestTimesContainer, statisticsTextLabel);
		statisticsAlert.setTitle(dialogTitle); //set the dialog title
		statisticsAlert.getDialogPane().setContent(dialogContainer); //set the dialog content
		
		//close the window whenever the user presses the enter key.
		statisticsOptionsList.setOnKeyPressed(event -> {
			if( event.getCode() == KeyCode.ENTER ) //perform the close button action
				((Button)statisticsAlert.getDialogPane().lookupButton(ButtonType.CLOSE)).fire(); 
		}); //close setOnKeyPressed(...)

		//Set window position so that it appears in the middle of the parent window
		Platform.runLater(new Runnable() {
		    @Override
		    public void run() {
		    	//set the x midpoint position for the window
		        statisticsAlert.setX(mainWindow.getX() + mainWindow.getWidth() / 2 - 
		         statisticsAlert.getWidth() / 2);
		        //set the y midpoint position for the window
		        statisticsAlert.setY(mainWindow.getY() + mainWindow.getHeight() / 2 - 
		         statisticsAlert.getHeight() / 2);
		        statisticsOptionsList.requestFocus(); //give focus to the options list
		    } //close run()
		}); //close Platform.runLater(...)
		
		//Prompt user to make sure they want to delete all of the current statistics data
		statisticsAlert.getDialogPane().lookupButton(resetBtnType).addEventFilter(ActionEvent.ACTION, 
		    event -> {
		        //verify that the user wants to reset their statistics.
		    	displayResetStatsDialogBox(
		    	 statisticsOptionsList.getSelectionModel().getSelectedItem().toString(), statisticsAlert);
		    	if (!closeParentWindow) {
		    		//user hasn't made a decision yet, so prevent the dialog from closing
		    		statisticsOptionsList.requestFocus(); //move focus to list
		    		event.consume(); //consume event to prevent window from closing
		    	} //end if
		    } //close event
		); //close addEventFilter(...)
		//show dialog and wait for a result
		Optional<ButtonType> alertResult = statisticsAlert.showAndWait();	
		//when a result occurs, reset the closeParentWindow flag since it is no longer needed.
		if (alertResult.isPresent()) {
			closeParentWindow = false; 
		} //end if
    } //close displayStatisticsDialogBox()
    
    
    /* This dialog is displayed whenever a game is finished. The user is notified as to whether they have won
     * or lost the game that just concluded. If they won the game, then their final time and the current date
     * are displayed on the window. For standard difficulty games, the user is also shown their current best
     * time, the number of games played, the number of games won, and the overall win percentage. For custom
     * games, the user is just shown whether they won or lost the concluded game. 
     */
    public void displayGameOverDialogBox(){	
    	//make sure the game is finished
    	if (gameOver) {
	    	String dialogTitle = mineRevealed ? "Game Lost" : "Game Won"; //dialog title
	    	String dialogContent; //dialog content
	    	//move text further down on custom games (since there isn't as much information to show)
	    	if (gameDifficulty.equals("CUSTOM")) 
	    		dialogContent = mineRevealed ? "\n\n\n\t\tSorry, you lost this game...\n\n\n" : 
	    		 "\n\n\n\t\tCongratulations, you won!\n\n\n";
	    	else
	    		dialogContent = mineRevealed ? "Sorry, you lost this game..." : "Congratulations, you won!";
    		Format mdy = new SimpleDateFormat("MM/dd/yyyy"); //date format
			String currDate = mdy.format(new Date()); //format the current date
			dialogContent += !gameDifficulty.equals("CUSTOM") ? (("\n\n\nTime: " + elapsedTime + 
			 (elapsedTime > 1 ? " seconds" : " second")) + ((!mineRevealed) ? ("\t\tDate: " + currDate) : 
			 "")) : ""; //Add final time to standard difficulty games / For wins add the current date.
			int bestTime; //fastest game the user won
			switch (gameDifficulty) {
				case "EASY": //easy difficulty
					bestTime = easyStats.getBestTime(); //get the best time
					//add best time to dialog
					dialogContent += (bestTime > 0) ? ("\n\nBest Time: " +  bestTime + " seconds") :  "\n";
					dialogContent += "\nGames Played: " + easyStats.getGamesPlayed(); //add games played
					dialogContent += "\nGames Won: " + easyStats.getGamesWon(); //add games won
					dialogContent += "\t\t\tPercentage: " + decimalFormatter.format(
					 easyStats.getWinPercentage()) + "%"; //add win percentage
					break;
				case "MEDIUM": //medium difficulty
					bestTime = mediumStats.getBestTime(); //get the best time
					//add best time to dialog
					dialogContent += (bestTime > 0) ? ("\n\nBest Time: " +  bestTime + " seconds") :  "\n";
					dialogContent += "\nGames Played: " + mediumStats.getGamesPlayed(); //add games played
					dialogContent += "\nGames Won: " + mediumStats.getGamesWon(); //add games won
					dialogContent += "\t\t\tPercentage: " + decimalFormatter.format(
					 mediumStats.getWinPercentage()) + "%"; //add win percentage
					break;
				case "HARD": //hard difficulty
					bestTime = hardStats.getBestTime(); //get the best time
					//add best time to dialog
					dialogContent += (bestTime > 0) ? ("\n\nBest Time: " +  bestTime + " seconds") :  "\n";
					dialogContent += "\nGames Played: " + hardStats.getGamesPlayed(); //add games played
					dialogContent += "\nGames Won: " + hardStats.getGamesWon(); //add games won
					dialogContent += "\t\t\tPercentage: " + decimalFormatter.format(
					 hardStats.getWinPercentage()) + "%"; //add win percentage
					break;
				default: //do nothing					
			} //end switch (gameDifficulty)
			//button options
			String[] btnOptions = new String[]{"Exit Program", "Restart Game", "New Game"}; 
			//create dialog
			gameOverDialog = new CustomDialog(mainWindow, GAME_OVER_DIALOG_WIDTH, 
			 (gameDifficulty.equals("CUSTOM")) ? CUSTOM_GAME_OVER_DIALOG_HEIGHT : GAME_OVER_DIALOG_HEIGHT, 
			 GAME_OVER_BTN_WIDTH, false, dialogTitle, dialogContent, btnOptions);
			//show dialog and wait for a result
			Optional<String> alertResult = gameOverDialog.showDialog();	
			
			if (alertResult.isPresent()) { 
				if (alertResult.get() == btnOptions[0]) //exit program option
					closeProgram(); //serialize statistics/game-state and exit the program
				else if (alertResult.get() == btnOptions[1]) //restart game option
					resetMinesweeperBoard(); //restart the finished game
				else //new game option
					createNewGame(); //create a new game			
			} //end if
    	} //end if
    } //close displayGameOverDialogBox()
    
   	
    /* This method returns a list of all of the excluded cells when populating a new Minesweeper game.
     * The clicked cell and all of it's immediate neighbors are excluded from containing a mine, so
     * this method returns a list containing all of those cells. This prevents a mine from randomly
     * being placed in one of those cells when creating the game.
     */
	private HashSet<Point> createExcludedCellList(MinesweeperCell cell){
		HashSet<Point> excludedCells = new HashSet<Point>(MAX_EXCLUDED_CELLS);	
		//Iterate through all of the surrounding cells and add them to the list (includes clicked cell)
		for (int xDelta = -1; xDelta <= 1; xDelta++) {
			for (int yDelta = -1; yDelta <= 1; yDelta++) {			 
				int newX = cell.getCellXInd() + xDelta; //x index of neighboring cell
				int newY = cell.getCellYInd() + yDelta; //y index of neighboring cell
				//make sure position is valid
				if ((newX >= 0 && newX < gameRows) && (newY >= 0 && newY < gameCols))
					excludedCells.add(new Point(newX, newY)); //add cell to list			
			}//end inner for
		}//end outer for
		return excludedCells; //return list of excluded cells
	}//close createExcludedCellList(...)
	
	
	//Add an MouseEvent EventHandler to each node on the GridPane
	private void addGridEvent() {			
        grid.getChildren().forEach(item -> { 	
            item.setOnMouseClicked( new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                	if (!gameOver) {
	                	MouseButton button = event.getButton();  
	                	if (item instanceof MinesweeperCell) {               		
	                		if (button == MouseButton.PRIMARY) {  	               
		                        if (event.getClickCount() == 1) //left click performed
		                        	leftClickAction(((MinesweeperCell) item));
		                        else if (event.getClickCount() == 2) //double left click performed
		                        	doubleClickAction(((MinesweeperCell) item));    
	    	                } else if (button == MouseButton.SECONDARY) { //right click performed
	                            rightClickAction(((MinesweeperCell) item));
	                        }//end if
	                	}//end if	
                	}//end if
                }}); //close handle()
        }); //close setonMouseClicked(...)   
    } //close addGridEvent()
	
	
	//Reset all applicable game variables and zero out every MinesweeperCell
	public void zeroOutMinesweeperBoard() {
		resetGameVariables();
		//Zero out every MinesweeperCell on the grid
		Platform.runLater(new Runnable() {
			@Override
			public void run() {	
				elapsedTimeLabel.setText("0"); //reset elapsed time text to 0
				//reset remaining mines text to total number of mines
				remainingMinesLabel.setText(String.valueOf(totalMines));
				//iterate through grid
				for (int i = 0; i < gameRows; i++) 
					for (int j = 0; j < gameCols; j++) 
						minesweeperBoard[i][j].zeroOutCell(); //zero out cell
			} //close run()
		}); //close Platform.runLater(...)
	} //close zeroOutMinesweeperBoard()
	
	
	//Reset all of the applicable game variables to their default values (empty game)
	public void resetGameVariables() {
		gameGenerated = false; //default value
		resetCurrentGame = false; //default value
		gameOver = false; //default value
		endCurrentGame = false; //default value
		mineRevealed = false; //default value
		customOptionSelected = false; //game parameters weren't changed, so reset flag
		changeDifficulty = false; //default value
		changeDifficultyWhenGameDone = false; //default value
		tempDifficulty = gameDifficulty; //reset temporary difficulty
		startTime = 0; //reset time
		elapsedTime = 0; //reset time
		resumeGameTime = 0; //reset time
		remainingMines = totalMines; //reset remaining mines
	} //close resetGameVariables()
	
	
	//Reset all applicable game variables and reset every MinesweeperCell back to it's original state
	public void resetMinesweeperBoard() {
		//reset all appropriate variables
		resetGameVariables();	
		gameGenerated = true; //game is still generated, so update variable
		resetCurrentGame = true; //the current game will be restarted, so update flag
		//Reset every cell in the grid to it's initial value
		Platform.runLater(new Runnable() {
			@Override
			public void run() {	
				elapsedTimeLabel.setText("0"); //reset elapsed time text to 0
				//reset remaining mines text to total number of mines
				remainingMinesLabel.setText(String.valueOf(remainingMines));
				//iterate through grid
				for (int i = 0; i < gameRows; i++) {
					for (int j = 0; j < gameCols; j++) {
						minesweeperBoard[i][j].resetCellToDefault(); //reset cell to default values
						//reset cell text to appropriate color
						minesweeperBoard[i][j].setCellTextColor(getColorVal(minesweeperBoard[i][j].getCellVal()));				
					} //end inner for
				} //end outer for										
			} //close run()
		}); //close Platform.runLater(...)
	} //close resetMinesweeperBoard()
	
	
	//Determine whether the game is finished or not and return the result
	public boolean gameWon() {
		//see how many hidden cells are still remaining (by using a filter)
		int hiddenCellsRemaining = Math.toIntExact(Arrays.stream(minesweeperBoard).mapToLong(
		 a -> Arrays.stream(a).filter(cell -> cell.isHidden()).count()).sum());		
		/* If the  number of hidden cells remaining equals the totalMines value, then that means that every 
		 * non-mine cell was revealed and only mines are left. This means that the game has been won.
		 */
		return hiddenCellsRemaining == totalMines; //return result
	} //close gameWon()
	
	
	//Retrieve the selected cell's surrounding neighbors
	private List<MinesweeperCell> getNeighbors(MinesweeperCell cell){
		List<MinesweeperCell> neighbors = new ArrayList<>(); //create a new ArrayList to store the values
		//Add each neighbor to the list
		for (int xDelta = -1; xDelta <= 1; xDelta++) {
			for (int yDelta = -1; yDelta <= 1; yDelta++) {			 
				if (xDelta == 0 && yDelta == 0) //skip the selected cell
					yDelta++; //increment y index (to skip selected cell)	
				int newX = cell.getCellXInd() + xDelta; //x index of the new cell
				int newY = cell.getCellYInd() + yDelta; //y index of the new cell
				//make sure position is valid
				if ((newX >= 0 && newX < gameRows) && (newY >= 0 && newY < gameCols))
					neighbors.add(minesweeperBoard[newX][newY]); //add cell to list	
			}//end inner for
		}//end outer for
		return neighbors; //return list of neighbors
	} //close getNeighbors(...)
	
	
	/* Reveal the cell's contents to the user. If the cell is empty, then also reveal any surrounding 
	 * neighbors that do not have mines in them. This is done recursively until all of the applicable 
	 * cells are revealed
	 */
	private void revealCells(MinesweeperCell cell){
		//only applies to cells that are hidden	
		if (cell.isHidden()) {			
			cell.revealCell(); //reveal the desired cell
			// If the cell is empty, reveal any adjacent cells that don't contain bombs
			if (cell.getCellText().isEmpty()) {
				//get all neighbors that aren't flagged
				List<MinesweeperCell> neighbors = getUnflaggedNeighbors(cell);
				//Iterate through the list and reveal each neighboring cell
				for (MinesweeperCell item : neighbors)
					revealCells(item); //recursively reveal the cell (and it's neighbors if applicable)
			} //end if	
		} //end if	
	} //close revealCells(...)
	

	/* Return a list of all unflagged neighbors for a given cell. A flag comment means that the user
	 * thinks that there is a mine located in the commented cell. This means that we do not add any
	 * of these cells with comments in them to the list (since the user does not want to reveal their
	 * contents).
	 */
	private List<MinesweeperCell> getUnflaggedNeighbors(MinesweeperCell cell){
		List<MinesweeperCell> neighbors = new ArrayList<>(); //create a new ArrayList to store the values
		//Add each neighbor to the list
		for (int xDelta = -1; xDelta <= 1; xDelta++) {
			for (int yDelta = -1; yDelta <= 1; yDelta++) {			 
				if (xDelta == 0 && yDelta == 0) //skip the selected cell
					yDelta++; //increment y index (to skip selected cell)
				int newX = cell.getCellXInd() + xDelta; //x index of the new cell
				int newY = cell.getCellYInd() + yDelta; //y index of the new cell
				//make sure position is valid
				if ((newX >= 0 && newX < gameRows) && (newY >= 0 && newY < gameCols)) {
					//make sure the new cell is hidden and it doesn't have a flag comment
					if(minesweeperBoard[newX][newY].isHidden() && 
					 !minesweeperBoard[newX][newY].getCellComment().equals("\u2691"))
						neighbors.add(minesweeperBoard[newX][newY]); //add cell to list					
				} //end if			
			} //end inner for
		} //end outer for 
		return neighbors; //return list of unflagged neighbors
	} //close getUnflaggedNeighbors(...)
	
	
	//Return a list of all of the cells containing a mine in the Minesweeper board
	private List<MinesweeperCell> getMineCells(){
		List<MinesweeperCell> mineCells = new ArrayList<>(); //create a new ArrayList to store the values
		//iterate through the Minesweeper grid
		for (int i = 0; i < gameRows; i++) {
			for (int j = 0; j < gameCols; j++) {
				if (minesweeperBoard[i][j].hasMine()) //check if cell has a mine
					mineCells.add(minesweeperBoard[i][j]); //add cell with a mine to list
			}//end inner for
		}//end outer for	
		return mineCells; //return list of cells containing mines
	} //close getMineCells(...)
	
	
	//Return a list of all of the cells containing a flag comment in the Minesweeper board
	private List<MinesweeperCell> getFlagCommentCells(){
		List<MinesweeperCell> flagCommentCells = new ArrayList<>(); //create a new ArrayList to store the values
		//iterate through the Minesweeper grid
		for (int i = 0; i < gameRows; i++) {
			for (int j = 0; j < gameCols; j++) {
				if (minesweeperBoard[i][j].getCellComment().equals("\u2691")) //check if cell has a flag comment
					flagCommentCells.add(minesweeperBoard[i][j]); //add cell with a flag comment to list
			}//end inner for
		}//end outer for	
		return flagCommentCells; //return list of cells containing a flag comment
	} //close getFlagCommentCells()
	

	/* Update any cells with incorrectly placed mine flags when the game is finished. This is done to show
	 * the user any incorrect logic that lead to losing the game by revealing a mine.
	 */
	private void updateIncorrectFlagCells() {
		List<MinesweeperCell> flagCells = getFlagCommentCells(); //get list of cells containing a flag comment 
		//Only include cells that have flag comments and also contain mines
		List<MinesweeperCell> incorrectCells = flagCells.stream().filter(c -> !c.hasMine()).toList();
		Platform.runLater(new Runnable() {
			@Override
			public void run() {	
				//reveal neighbor cells that don't have flag comments 
				incorrectCells.forEach(MinesweeperCell::updateIncorrectCell);
			} //close run()
		}); //close Platform.runLater(...)		
	} //close updateIncorrectFlagCell();
	
	
	//Display all of the cells that contain mines
	private void displayMines() {
		List<MinesweeperCell> mineCells = getMineCells(); //get list of cells containing mines
		Platform.runLater(new Runnable() {
			@Override
			public void run() {	
				//reveal every cell that contains a mine
				mineCells.forEach(MinesweeperCell::makeMineVisible);
			} //close run()
		}); //close Platform.runLater(...)	
	} //close displayMines()
	
	
	//Display all of the cells that contain mines which also don't have a flag comment
	private void displayUnflaggedMines() {
		//only include cells containing mines that also don't have a flag comment
		List<MinesweeperCell> unflaggedMineCells = getMineCells().stream().filter(
		 c -> !c.getCellComment().equals("\u2691")).toList();
		Platform.runLater(new Runnable() {
			@Override
			public void run() {	
				//reveal neighbor cells that don't have flag comments 
				unflaggedMineCells.forEach(MinesweeperCell::makeMineVisible);
			}//close run()
		}); //close Platform.runLater(...)	
	} //close displayUnflaggedMines()
	
	
	/* Adjust the window bounds for the game (these window bounds change whenever the game difficulty is
	 * updated to a new difficulty). Since the game size has changed, that means that the window bounds
	 * must be updated as well
	 */
	public void adjustWindowBounds() {
		switch (gameDifficulty) {
			case "EASY": //easy difficulty
				wndWidth = EASY_WND_WIDTH; //330
				wndHeight = EASY_WND_HEIGHT; //400
				break;
			case "MEDIUM": //medium difficulty
				wndWidth = MEDIUM_WND_WIDTH; //580
				wndHeight = MEDIUM_WND_HEIGHT; //640
				break;
			case "HARD": //hard difficulty
				wndWidth = HARD_WND_WIDTH; //1100
				wndHeight = HARD_WND_HEIGHT; //640
				break;
			case "CUSTOM":
				/* The custom case is interesting; the window width and height must be updated
				 * based on how many rows and columns are present in the custom game. Since
				 * there is a limit to how many rows can be displayed at once on the screen
				 * (the window height is naturally smaller than the window width), it is 
				 * essential to adjust the window bounds when this limit is reached.
				 */
				wndWidth = (EASY_WND_WIDTH + (SCREEN_WIDTH_OFFSET * (customCols - EASY_COLS)));
				wndHeight = ((customRows >= ROW_LIMIT)) ? MAX_SCREEN_HEIGHT : 
				 (EASY_WND_HEIGHT + (SCREEN_HEIGHT_OFFSET * (customRows - EASY_ROWS)));
				break;
			default : //give the window bounds of an easy game by default
				wndWidth = EASY_WND_WIDTH; //330
				wndHeight = EASY_WND_HEIGHT; //400
		} //end switch (gameDifficulty)
		//assign the width/height ratio (used to resize the grid proportionally)
		wndWidthRatio = wndWidth / wndHeight;
		Platform.runLater(new Runnable() {
			@Override
			public void run() {	
				mainWindow.setMinHeight(wndHeight); //set the minimum heigh of the window
				/* bind the primaryStage's width property so that it's always slightly smaller than the
				 *  scene's height (because the height has the added game information under the grid 
				 *  while the width doesn't have anything that is added to its overall size)
				*/
				mainWindow.minWidthProperty().bind(mainWindow.heightProperty().multiply(wndWidthRatio));
			} //close run()
		}); //close Platform.runLater(...)
		
		//adjust the window position if the difficulty changes or the window isn't maximized
		if (!screenMaximized && changeDifficulty)
			adjustScreen(); //adjust the window position
	
	} //close adjustWindowBounds()
	
	
	//Adjust the window position so that it stays within the computer's screen bounds
	public void adjustScreen() {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {	
				mainWindow.setHeight(wndHeight); //reset window height to minimum size
				mainWindow.setWidth(wndWidth); //reset window width to minimum size
				
				/* The ability to select custom game rows and columns makes it tricky to address any
				 * resizing when changing to a new game difficulty. This is mainly applicable to custom
				 * games with a small height and a large width (whose wndWidthRatio is above 2.0). This
				 * is why extra care should be taken before adjusting the window's width. All of the other 
				 * custom games scale fine when adjusting the window size. 
				 */
				if (wndHeight < oldHeight)
					mainWindow.setHeight(oldHeight); //set the window height to previous height					
				//update the width if the old width was bigger than the current width
				if ((wndWidthRatio < WIDTH_RATIO_LIMIT) && (wndWidth < oldWidth))
					mainWindow.setWidth(oldWidth); //set the window width to previous width

				//move the window back on-screen if resizing it positioned the window off-screen
				if(MAX_SCREEN_WIDTH < mainWindow.getX() + mainWindow.getWidth()) //1366
					mainWindow.setX(MAX_SCREEN_WIDTH - mainWindow.getWidth()); //set new x position
				if (MAX_SCREEN_HEIGHT < mainWindow.getY() + mainWindow.getHeight()) //728 
					mainWindow.setY(MAX_SCREEN_HEIGHT - mainWindow.getHeight()); //set new y position
			} //close run()
		}); //close Platform.runLater(...)	
	} //close adjustScreen()
	
	
	/* Serialize all of the GameStatistics variables so that all data from the games played during the
	 * current session is saved. This is done before the program is terminated so that all of the 
	 * GameStatistics variables save the most up-to-date data.
	 */
	public void serializeStatistics() {
		try {
			//A FileOutputStream must be created for each GameStatistics object (easy, medium, hard)
			FileOutputStream easyStatsFileOut = new FileOutputStream("BeginnerStatistics.ser");
			FileOutputStream mediumStatsFileOut = new FileOutputStream("IntermediateStatistics.ser");
			FileOutputStream hardStatsFileOut = new FileOutputStream("AdvancedStatistics.ser");
			//A ObjectOutputStream must also be created for each GameStatistics object
			ObjectOutputStream easyStatsObjectOut = new ObjectOutputStream(easyStatsFileOut);
			ObjectOutputStream mediumStatsObjectOut = new ObjectOutputStream(mediumStatsFileOut);
			ObjectOutputStream hardStatsObjectOut = new ObjectOutputStream(hardStatsFileOut);
			//Serialize all of the data (save it)
			easyStatsObjectOut.writeObject(easyStats);
			mediumStatsObjectOut.writeObject(mediumStats);
			hardStatsObjectOut.writeObject(hardStats);
			//close everything that is no longer needed
			easyStatsObjectOut.close();
			easyStatsFileOut.close();
			mediumStatsObjectOut.close();
			mediumStatsFileOut.close();
			hardStatsObjectOut.close();
			hardStatsFileOut.close();
		} catch (IOException e) {
			e.printStackTrace(); //print stack trace if there is an issue
		} //close catch(...) 
	} //close serializeStatistics()
	
	
	/* Serialize the MinesweeperGameState so that all data from the current game is saved before the 
	 * program terminates. This includes the elapsed time, the window position and size, the current game 
	 * difficulty selected, if the game is finished, the current size of the game and the total number of 
	 * mines, if a game was generated or not, and the cellData for each cell on the Minesweeper board.
	 */
	public void serializeGameState() {
		createGameState(); //Create the game state so that it consists of the most up-to-date data
		try {
			//Create a FileOutputStream 
			FileOutputStream gameStateFileOut = new FileOutputStream("GameState.ser");		
			//Create an ObjectOutputStream 
			ObjectOutputStream gameStateObjectOut = new ObjectOutputStream(gameStateFileOut);		
			gameStateObjectOut.writeObject(gameState); //serialize data (save it)
			gameStateObjectOut.close(); //close ObjectOutputStream (no longer needed)
			gameStateFileOut.close(); //close FileOutputStream (no longer needed)
		} catch (IOException e) {
			e.printStackTrace(); //print stack trace if there was an issue
		} //close catch(...)
	} //close serializeGameState()
	
	
	//Create the GameState so that it contains the most up-to-date data of the current game
	public void createGameState() {
		//stop the timeline if the game isn't over yet
		if (!gameOver)
			timeline.stop(); //stop timeline	
		/* Create an array to hold all of the MinesweeperCellData values for each cell in the game.
		 * This is done because there are variables in the MinesweeperCell class that cannot be
		 * serialized natively (Labels and Rectangles). 
		 */
		MinesweeperCellData[][] gameCellsData = new MinesweeperCellData[gameRows][gameCols];	
		//iterate through game grid and add each cell's data to the list.
		for (int i = 0; i < gameRows; i++)
			for (int j = 0; j < gameCols; j++) //get the cellData of each cell in the game
				gameCellsData[i][j] = minesweeperBoard[i][j].getCellData(); //add data to array
		//Create a new game state with all of the most up-to-date data
		gameState = new MinesweeperGameState(gameOver, gameDifficulty, gameRows, gameCols, totalMines, 
		 gameCellsData, elapsedTime, mainWindow.getHeight(), mainWindow.getWidth(), mainWindow.getX(), 
		 mainWindow.getY(), screenMaximized, gameGenerated);
	} //close createGameState() 
	
	
	//Retrieve the game state of the previous session (so the user can continue from where they left off
	public void getPreviousGameState() throws IOException, ClassNotFoundException {
		//Create a new FileInputStream
		FileInputStream gameStateFileIn = new FileInputStream("GameState.ser");
		//Create a new ObjectInputStream
		ObjectInputStream gameStateObjectIn = new ObjectInputStream(gameStateFileIn);
		//Retrieve the game state of the previous session
		gameState = (MinesweeperGameState) gameStateObjectIn.readObject();
		gameStateFileIn.close(); //close FileInputStream (no longer needed)
		gameStateObjectIn.close(); //close ObjectInputStream (no longer needed)
	} //close getPreviousGameState()
			
	
	/* Initialize all of the game variables. The MinesweeperGameState of the previous session is used
	 * to help initialize the game variables. In the event that the previous game state is not present,
	 * then the variables are initialized for a blank easy game difficulty.
	 */
	public void initializeGameVariables() throws ClassNotFoundException, IOException {
		getPreviousGameState(); //get game state of previous session if available
		//the previous game was already finished.
		if (gameState != null) {
			boolean lastGameCustom = gameState.getGameDifficulty().equals("CUSTOM");
			//adjust custom game information if the last game was a custom difficulty
			customRows = lastGameCustom ? gameState.getGameRows() : EASY_ROWS;
			customCols = lastGameCustom ? gameState.getGameCols() : EASY_COLS;
			customMines = lastGameCustom ? gameState.getGameMines() : EASY_MINES;	
			customOptionSelected = lastGameCustom; //retrieve from gameState
			oldWidth = 0; //default value
		    oldHeight = 0; //default value
		    gameDifficulty = gameState.getGameDifficulty(); //retrieve from gameState
		    tempDifficulty = gameState.getGameDifficulty(); //retrieve from gameState
		    gameRows = gameState.getGameRows(); //retrieve from gameState
		    gameCols = gameState.getGameCols(); //retrieve from gameState
			totalMines = gameState.getGameMines(); //retrieve from gameState
			gameGenerated = gameState.getGameGeneratedStatus(); //retrieve from gameState
			gameOver = gameState.isFinished(); //retrieve from gameState
			/* If the game isn't finished, start from the gameState's elapsed time variable,
			 * otherwise start from 0 since the game is already finished
			 */
			resumeGameTime = gameState.isFinished() ? 0 : gameState.getElapsedTime();
			screenMaximized = gameState.getFullScreenStatus(); //retrieve from gameState
		} else {
			customRows = 9; //default value
			customCols = 9; //default value
			customMines = 10; //default value
			customOptionSelected = false; //default value  
		    oldWidth = 0; //default value
		    oldHeight = 0; //default value
		    gameDifficulty = "EASY"; //default value
		    tempDifficulty = "EASY"; //default value
		    gameRows = EASY_ROWS; //default value
		    gameCols = EASY_COLS; //default value
			totalMines = EASY_MINES; //default value
			gameGenerated = false; //default value
			gameOver = false; //default value
			resumeGameTime = 0; //default value
			screenMaximized = false; //default value
		} //end else
		//create a new minesweeperBoard
		minesweeperBoard = new  MinesweeperCell[gameRows][gameCols];
		mineRevealed = false; //default value
		resetCurrentGame = false; //default value
		endCurrentGame = false; //default value
		closeParentWindow = false; //default value
		changeDifficulty = false; //default value
		changeDifficultyWhenGameDone = false; //default value
		maximizedGameDifficulty = gameDifficulty; //default value
		remainingMines = totalMines; //default value	
	} //close initializeGameVariables()
	
	
	/* Serialize game statistics to include any games played during the current session. Also serialize the
	 * current game-state to resume playing when a new session is launched. Then close the program.
	*/
	public void closeProgram() {
		serializeStatistics(); //serialize statistics (include games played during this session)
    	serializeGameState(); //serialize the current game state
    	Platform.exit(); //signal the JavaFX Toolkit to shut down
		System.exit(0); //terminate the Java Virtual Machine  
	} //close closeProgram()
	
	
	//Create / run the program
	@Override
	public void start(Stage primaryStage) throws Exception {
		//declare all of the different elements used in the scene
		initializeGameVariables(); //initialize all of the game variables
		root = new BorderPane(); //houses the menubar, grid, and gameInfo elements
		menubar = new MenuBar(); //holds the menu items used in the program
		grid = new GridPane(); //hold the minesweeper board	
		gameInfo = new HBox(); //displays the ongoing game information
		//initialize the scene with all of the window elements	
		Scene scene = new Scene(createContent()); //create the content of the program
		mainWindow = primaryStage; //assign the main window
		mainWindow.setScene(scene); //assign the scene to the primaryStage
		mainWindow.setMinHeight(wndHeight); //set the minimum height
		
		/* Bind the primaryStage's width property so that it is always slightly smaller than the scene's 
		 * height (because the height has the added game information under the grid while the width does 
		 * not have anything that is added to its overall size)
		 */
		mainWindow.minWidthProperty().bind(mainWindow.heightProperty().multiply(wndWidthRatio));			
		//add a listener that executes every time the screen is maximized and un-maximized.
		mainWindow.maximizedProperty().addListener(new ChangeListener<Boolean>() {
		    @Override
		    public void changed(ObservableValue<? extends Boolean> ov, Boolean oldVal, Boolean newVal) {
		        if (newVal) {
		        	/* screen was maximized, so keep track of the old height and width values so
		        	 * the screen can be restored to its previous values once un-maximized.
		        	 */
		        	screenMaximized = true; //screen maximized
		        	maximizedGameDifficulty = gameDifficulty; //keep track of game difficulty
		        	oldWidth = mainWindow.getWidth(); //keep track of old width
		        	oldHeight = mainWindow.getHeight(); //keep track of old height
		        } else {
		        	/* Screen is no longer maximized, so change the width and height of the window back
		        	 * to it's original values
		        	 */
		        	screenMaximized = false; //screen no longer maximized
		        	if (!maximizedGameDifficulty.equals(gameDifficulty)) {
		        		adjustScreen(); //adjust screen position if necessary
		        	} else {
		        		mainWindow.setWidth(oldWidth); //change back to old width
			        	mainWindow.setHeight(oldHeight); //change back to old height
		        	} //end else
		        } //end else
		    } //close changed(...)
		}); //close addListener(...)
		//adjust the width any time the window width exceeds the computer's screen bounds
		mainWindow.widthProperty().addListener((obs, oldVal, newVal)->{
			if(newVal.doubleValue() > MAX_SCREEN_WIDTH && !screenMaximized){
				mainWindow.setX(0); //change x position
				mainWindow.setWidth(MAX_SCREEN_WIDTH); //change width
			} //end if
		}); //close addListener(...)
		//add an EventHadler for when the program is closed
		mainWindow.setOnCloseRequest(new EventHandler<WindowEvent>() {
		    @Override
		    public void handle(WindowEvent t) {
				closeProgram(); //serialize statistics/game-state and exit the program
		    } //close handle(...)
		}); //close setOnCloseRequest(...)	
		//Update window position if the previous session's window was maximized
		if (screenMaximized) {
			mainWindow.setMaximized(true); //maximize current window
			mainWindow.setX(0); //update x position
			mainWindow.setY(0); //update y position
		} else {
			mainWindow.setX(gameState.getWindowXPos()); //update x position
			mainWindow.setY(gameState.getWindowYPos()); //update y position
			mainWindow.setHeight(gameState.getWindowHeight()); //update height
			mainWindow.setWidth(gameState.getWindowWidth()); //update width		
		} //end else
		
		//Create new FileInputStreams for each of the difficulties (easy, medium, hard)
		FileInputStream easyStatsFileIn = new FileInputStream("BeginnerStatistics.ser");
		FileInputStream mediumStatsFileIn = new FileInputStream("IntermediateStatistics.ser");
		FileInputStream hardStatsFileIn = new FileInputStream("AdvancedStatistics.ser");
		//Create new ObjectInputStreams for each of the difficulties (easy, medium, hard)
		ObjectInputStream easyStatsObjectIn = new ObjectInputStream(easyStatsFileIn);
		ObjectInputStream mediumStatsObjectIn = new ObjectInputStream(mediumStatsFileIn);
		ObjectInputStream hardStatsObjectIn = new ObjectInputStream(hardStatsFileIn);
		//Retrieve the statistics data of the previous session for each difficulty
		easyStats = (GameStatistics) easyStatsObjectIn.readObject();
		mediumStats = (GameStatistics) mediumStatsObjectIn.readObject();
		hardStats = (GameStatistics) hardStatsObjectIn.readObject();
		//close everything that is no longer needed
		easyStatsFileIn.close();
		mediumStatsFileIn.close();
		hardStatsFileIn.close();
		easyStatsObjectIn.close();
		mediumStatsObjectIn.close();
		hardStatsObjectIn.close(); 
	
		//Display the primary stage to the user
		mainWindow.show(); 
		//restart the timeline if a game was generated in the previous session
		if (gameGenerated) { 
			startTime = System.nanoTime(); //get the current time
	    	timeline.playFromStart(); //start the timeline
		} //end if
	} //close start(...)
	
	
	//Launch the program
	public static void main(String[] args) {
		launch(args);
	} //close main(...)
} //close class Main

