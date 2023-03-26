/*
 * Victor Espinoza 
 * Created December 2022 - February 2023
 * Project: Minesweeper
 *
 * File Name: GameStatistics.java
 * 
 * Description: This class is used to keep track of the game statistics for all of the standard 
 * difficulties (easy, medium, and hard) when the user exits out of the program. Each difficulty has its
 * own set of statistic data. This data includes the 5 best times achieved, the number of games played, 
 * the number of games won, the longest winning streak, the longest losing streak, the current streak, 
 * and and the overall win percentage. By keeping track of all of this information, I am able to preserve 
 * the statistic data for each of these difficulties so that it persists between sessions.
 * 
 */

package application;
import java.util.List;
import java.io.Serializable;
import java.util.ArrayList;

public class GameStatistics implements Serializable {
	private static final long serialVersionUID = 1L; //unique UID
	private static final int MAX_ELEMENTS_ALLOWED = 5; //maximum best times allowed
	private String modeDifficulty; //game difficulty
	private List<String> bestTimes; //list of the 5 best times
	private int gamesPlayed; //number of games played
	private int gamesWon; //number of games won
	private int longestWinningStreak; //longest winning streak
	private int longestLosingStreak; //longest losing streak
	private int currentStreak; //current streak
	private double winPercentage; //win percentage (gamesWon / gamesPlayed)

	
	//Constructor method for the GameStatistics
	public GameStatistics(String difficulty) {
		modeDifficulty = difficulty; //assign the given difficulty
		bestTimes = new ArrayList<String>(MAX_ELEMENTS_ALLOWED + 1); //create a new list
		gamesPlayed = 0; //default games played
		gamesWon = 0; //default games won
		winPercentage = 0; //default win percentage
		longestWinningStreak = 0; //default longest winning streak
		longestLosingStreak = 0; //default longest losing streak
		currentStreak = 0; //default current streak
	} //close GameStatistics(...)
	
	
	//Reset all of the current Game Statistics (this cannot be undone later)
	public void resetGameStatistics() {
		bestTimes.clear(); //clear the best times list
		gamesPlayed = 0; //reset games played
		gamesWon = 0; //reset games won
		winPercentage = 0; //reset win percentage
		longestWinningStreak = 0; //reset longest winning streak
		longestLosingStreak = 0; //reset longest losing streak
		currentStreak = 0; //reset current streak
	} //close resetGameStatistics()
	
	
	//Return the game difficulty
	public String getModeDifficulty() {
		return modeDifficulty;
	} //close getModeDifficulty()
	
	
	//Return the total number of games played
	public int getGamesPlayed() {
		return gamesPlayed;
	} //close getGamesPlayed()
	
	
	//Increment the total number of games played
	public void incrementGamesPlayed() {
		gamesPlayed++; //increment gamesPlayed
	} //close incrementGamesPlayed()
	
	
	//Return the total number of games won
	public int getGamesWon() {
		return gamesWon;
	} //close getGamesWon()
	
	
	//Increment the total number of games won
	public void incrementGamesWon() {
		gamesWon++; //increment gamesWon
	} //close incrementGamesWon()
	

	//Return the current win percentage
	public double getWinPercentage() {
		return winPercentage;
	} //close getWinPercentage()	
	
	
	//Calculate the current win percentage
	public void calculateWinPercentage() {
		winPercentage = (Double.valueOf(gamesWon)/Double.valueOf(gamesPlayed)) * 100;
	} //close calculateWinPercentage()
	
	
	//Return the current streak
	public int getCurrentStreak() {
		return currentStreak;
	} //close getCurrentStreak()
	
	
	//Adjust the current streak based on whether the user won the game or not
	public void adjustCurrentStreak(boolean userWonGame) {
		boolean currentStreakPositive = currentStreak >= 0; //is the current streak positive
		if (userWonGame)
			currentStreak = currentStreakPositive ? currentStreak + 1 : 1; //increment current streak
		else
			currentStreak = !currentStreakPositive ? currentStreak - 1 : -1; //decrement current streak	
	} //close adjustCurrentStreak

	
	//Return the longest winning streak
	public int getLongestWinningStreak() {
		return longestWinningStreak;
	} //close getLongestWinningStreak()
	
	
	//Adjust the longest winning streak
	public void adjustLongestWinningStreak() {
		//check if the current streak is greater than the longest winning streak
		if(currentStreak > longestWinningStreak)
			longestWinningStreak = currentStreak; //adjust value
	}
	
	
	//Return the longest losing streak
	public int getLongestLosingStreak() {
		return longestLosingStreak;
	} //close getLongestLosingStreak()
	
	
	//Adjust the longest losing streak
	public void adjustLongestLosingStreak() {
		//check if the current streak is greater than the longest losing streak
		if(currentStreak < longestLosingStreak)
			longestLosingStreak = currentStreak; //adjust value
	} //close adjustLongestLosingStreak()
	
	
	//Return the index where the current time should be placed in the best times list
	public int getInputIndex(int gameTime) {
		//if the list is empty, make the time the first element in the list
		if (bestTimes.isEmpty())
			return 0;
		
		try {
			//iterate through the best times list
			for (int i = 0; i < bestTimes.size(); i++) {
				String time = bestTimes.get(i); //get the time located at the given index
				//parse the time into an integer
				int currentTime = Integer.parseInt(time.substring(0, time.indexOf(" ")));
				/* Check to see if the given time is faster than the time at the designated
				 * index. If it is, then return this index so that we can place the new time
				 * in the correct spot on the best times list.
				 */
				if (gameTime <= currentTime)
					return i; //return index of the currentTime
			} //end for
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace(); //print stack trace if the time could not be formatted correctly
    	} //close catch(...)
		
		//The given time wasn't less than any of the current times in the list.
		if (bestTimes.size() < MAX_ELEMENTS_ALLOWED)
			return bestTimes.size(); //add time to back of the list if it isn't full yet.
		else
			//The input is not a best time, so return a -1 (not a valid index)
			return -1;
	} //close getInputIndex(...)
	
	
	//Return the best times list
	public List<String> getBestTimes(){
		return bestTimes;
	} //close getBestTimes()
	
	
	//Add a time to the designated index on the best times list
	public void addBestTime(int index, String gameTimeInfo) {
		//only add a time if the index is valid
		if (index < 0)
			return;
		bestTimes.add(index, gameTimeInfo); //add time to the list at the designated index
		//remove the last element from bestTimes if there are more than 5 elements in the ArrayList
		if (bestTimes.size() > MAX_ELEMENTS_ALLOWED)
			bestTimes.remove(bestTimes.size() - 1); //remove last element in bestTimes
	} //close addBestTime(...)

	
	//Return the best time in the best times list (the first element in the Array List)
	public int getBestTime() {
		if (bestTimes.isEmpty()) //there are no times in the list yet, return -1
			return -1; 
		try { //the best time will be located as the first element in the ArrayList
			return Integer.parseInt(bestTimes.get(0).substring(0, bestTimes.get(0).indexOf(" ")));
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace(); //print stack trace if the time could not be formatted correctly
    	} //close catch(...)
		//There was an error getting the best time, so return a -1
		return -1;
	} //close getBestTime()	
	
} //close class GameStatistics 
