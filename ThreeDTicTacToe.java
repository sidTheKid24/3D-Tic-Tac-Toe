/*
  This program was completed using pair programming by
  Siddharth Buddharaju and my partner Suhas Mohan.
  I acknowledge that each partner in a programming pair should "drive"
  roughly 50% of the time the pair is working together, and at most 25%
  of an individual's effort for an assignment should be spent working
  alone. Any work done by a solitary programmer must be reviewed by the
  partner. The object is to work together, learning from each other, not
  to divide the work into two pieces with each partner working on a
  different piece.
  I spent 0 HOURS working alone.
  I spent 20 HOURS working with my partner on this assignment.
  I estimate that of the time spent with my partner, I "drove" 50%
  PERCENT of the time. 

  Put any comment or explanation about variations from the 
  expected pair programming practice here. E.g. if you didn't complete
  the assignment with your partner, this is the place to explain why
  not. 
*/

/**
 * Assignment #4.
 * This program allows the user to play 3D Tic Tac Toe against the computer
 * The computer detects immediate win/loss situations and forks
 * 
 * Authors: Siddharth Buddharaju (sbuddhar@ucsc.edu) 
 *          and Suhas Mohan (smohan6@ucsc.edu)
 */

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class ThreeDTicTacToe {
	// boolean variable used to prioritize which strategy to follow
	public static boolean move = false;
	// checks is computer has won
	public static boolean compHasWon = false;
	// checks is user has won
	public static boolean userHasWon = false;
	// int array that stores the value of the line
	// this is used to determine how many points are
	// in each solution set
	public static int[] sumOfLines = new int[76];
	// board is used to keep track of x's, o's and
	// blank spots as well as to draw the board
	public static int[][][] board = new int[4][4][4];
	// stores all the points based on their coordinate address
	// in the order that they appear in lines. Basically transfers
	// the contents of the 3D array lines to a 1D arraylist
	public static ArrayList<Integer> array = new ArrayList<Integer>();
	// 3D array that contains all the possible solutions
	public static final int[][][] lines = {
		{{0,0,0},{0,0,1},{0,0,2},{0,0,3}},  //lev 0; row 0   rows in each level     
		{{0,1,0},{0,1,1},{0,1,2},{0,1,3}},  //       row 1     
		{{0,2,0},{0,2,1},{0,2,2},{0,2,3}},  //       row 2     
		{{0,3,0},{0,3,1},{0,3,2},{0,3,3}},  //       row 3     
		{{1,0,0},{1,0,1},{1,0,2},{1,0,3}},  //lev 1; row 0     
		{{1,1,0},{1,1,1},{1,1,2},{1,1,3}},  //       row 1     
		{{1,2,0},{1,2,1},{1,2,2},{1,2,3}},  //       row 2    
		{{1,3,0},{1,3,1},{1,3,2},{1,3,3}},  //       row 3     
		{{2,0,0},{2,0,1},{2,0,2},{2,0,3}},  //lev 2; row 0     
		{{2,1,0},{2,1,1},{2,1,2},{2,1,3}},  //       row 1     
		{{2,2,0},{2,2,1},{2,2,2},{2,2,3}},  //       row 2       
		{{2,3,0},{2,3,1},{2,3,2},{2,3,3}},  //       row 3     
		{{3,0,0},{3,0,1},{3,0,2},{3,0,3}},  //lev 3; row 0     
		{{3,1,0},{3,1,1},{3,1,2},{3,1,3}},  //       row 1 
		{{3,2,0},{3,2,1},{3,2,2},{3,2,3}},  //       row 2       
		{{3,3,0},{3,3,1},{3,3,2},{3,3,3}},  //       row 3           
		{{0,0,0},{0,1,0},{0,2,0},{0,3,0}},  //lev 0; col 0   columns in each level  
		{{0,0,1},{0,1,1},{0,2,1},{0,3,1}},  //       col 1    
		{{0,0,2},{0,1,2},{0,2,2},{0,3,2}},  //       col 2    
		{{0,0,3},{0,1,3},{0,2,3},{0,3,3}},  //       col 3    
		{{1,0,0},{1,1,0},{1,2,0},{1,3,0}},  //lev 1; col 0     
		{{1,0,1},{1,1,1},{1,2,1},{1,3,1}},  //       col 1    
		{{1,0,2},{1,1,2},{1,2,2},{1,3,2}},  //       col 2    
		{{1,0,3},{1,1,3},{1,2,3},{1,3,3}},  //       col 3    
		{{2,0,0},{2,1,0},{2,2,0},{2,3,0}},  //lev 2; col 0     
		{{2,0,1},{2,1,1},{2,2,1},{2,3,1}},  //       col 1    
		{{2,0,2},{2,1,2},{2,2,2},{2,3,2}},  //       col 2    
		{{2,0,3},{2,1,3},{2,2,3},{2,3,3}},  //       col 3    
		{{3,0,0},{3,1,0},{3,2,0},{3,3,0}},  //lev 3; col 0     
		{{3,0,1},{3,1,1},{3,2,1},{3,3,1}},  //       col 1
		{{3,0,2},{3,1,2},{3,2,2},{3,3,2}},  //       col 2
		{{3,0,3},{3,1,3},{3,2,3},{3,3,3}},  //       col 3
		{{0,0,0},{1,0,0},{2,0,0},{3,0,0}},  //cols in vert plane in front
		{{0,0,1},{1,0,1},{2,0,1},{3,0,1}},
		{{0,0,2},{1,0,2},{2,0,2},{3,0,2}},
		{{0,0,3},{1,0,3},{2,0,3},{3,0,3}},
		{{0,1,0},{1,1,0},{2,1,0},{3,1,0}},  //cols in vert plane one back
		{{0,1,1},{1,1,1},{2,1,1},{3,1,1}},
		{{0,1,2},{1,1,2},{2,1,2},{3,1,2}},
		{{0,1,3},{1,1,3},{2,1,3},{3,1,3}},
		{{0,2,0},{1,2,0},{2,2,0},{3,2,0}},  //cols in vert plane two back
		{{0,2,1},{1,2,1},{2,2,1},{3,2,1}},
		{{0,2,2},{1,2,2},{2,2,2},{3,2,2}},
		{{0,2,3},{1,2,3},{2,2,3},{3,2,3}},
		{{0,3,0},{1,3,0},{2,3,0},{3,3,0}},  //cols in vert plane in rear
		{{0,3,1},{1,3,1},{2,3,1},{3,3,1}},
		{{0,3,2},{1,3,2},{2,3,2},{3,3,2}},
		{{0,3,3},{1,3,3},{2,3,3},{3,3,3}},
		{{0,0,0},{0,1,1},{0,2,2},{0,3,3}},  //diags in lev 0
		{{0,3,0},{0,2,1},{0,1,2},{0,0,3}},
		{{1,0,0},{1,1,1},{1,2,2},{1,3,3}},  //diags in lev 1
		{{1,3,0},{1,2,1},{1,1,2},{1,0,3}},
		{{2,0,0},{2,1,1},{2,2,2},{2,3,3}},  //diags in lev 2
		{{2,3,0},{2,2,1},{2,1,2},{2,0,3}},
		{{3,0,0},{3,1,1},{3,2,2},{3,3,3}},  //diags in lev 3
		{{3,3,0},{3,2,1},{3,1,2},{3,0,3}},
		{{0,0,0},{1,0,1},{2,0,2},{3,0,3}},  //diags in vert plane in front
		{{3,0,0},{2,0,1},{1,0,2},{0,0,3}},
		{{0,1,0},{1,1,1},{2,1,2},{3,1,3}},  //diags in vert plane one back
		{{3,1,0},{2,1,1},{1,1,2},{0,1,3}},
		{{0,2,0},{1,2,1},{2,2,2},{3,2,3}},  //diags in vert plane two back
		{{3,2,0},{2,2,1},{1,2,2},{0,2,3}},
		{{0,3,0},{1,3,1},{2,3,2},{3,3,3}},  //diags in vert plane in rear
		{{3,3,0},{2,3,1},{1,3,2},{0,3,3}},
		{{0,0,0},{1,1,0},{2,2,0},{3,3,0}},  //diags left slice      
		{{3,0,0},{2,1,0},{1,2,0},{0,3,0}},        
		{{0,0,1},{1,1,1},{2,2,1},{3,3,1}},  //diags slice one to right
		{{3,0,1},{2,1,1},{1,2,1},{0,3,1}},        
		{{0,0,2},{1,1,2},{2,2,2},{3,3,2}},  //diags slice two to right      
		{{3,0,2},{2,1,2},{1,2,2},{0,3,2}},        
		{{0,0,3},{1,1,3},{2,2,3},{3,3,3}},  //diags right slice      
		{{3,0,3},{2,1,3},{1,2,3},{0,3,3}},        
		{{0,0,0},{1,1,1},{2,2,2},{3,3,3}},  //cube vertex diags
		{{3,0,0},{2,1,1},{1,2,2},{0,3,3}},
		{{0,3,0},{1,2,1},{2,1,2},{3,0,3}},
		{{3,3,0},{2,2,1},{1,1,2},{0,0,3}}        
	};
	public static void main(String[] args){
		// displays the board for the user to see
		displayBoard();
		// loops until either the player or the computer wins
		while(userHasWon != true && compHasWon != true){
			// prompts the user for input
			userInput1();
			// adds that input value to sumOfLines
			sum();
			// checks to see if the user has won
			hasWon();
			// computer runs immediate block strategy
			// if there is no immediate win/lose situation
			// it checks for forks, if there are no possible
			// forks then it makes a random move
			immediateWinAndBlock();
			// adds that input value to sumOfLines
			sum();
			// checks to see if the computer has won
			hasWon();
			// displays the board
			displayBoard();
		}
	}
	public static void displayBoard(){
		// outside loop goes through the levels
		// starts at level 3 because we need to 
		// print level 3 first and move down
		for (int lev=3; lev >= 0; lev--){
			// loop goes through the rows
			// starts at row 3 because we need to 
			// print row 3 first and move down
			for (int row=3; row >= 0; row--){
				// loop goes through the columns
				for (int col=0; col<4; col++){
					// checks if the value of col
					// is zero , if it is it indents
					// the rows
					if(col == 0){
						for(int i = 0; i < row + 1; i++){
							System.out.print(" ");
						}
						System.out.print(lev);
						System.out.print(row);
						System.out.print(" ");
					}
					// checks if the value at 
					// value of the current location
					// is equal to zero, if true
					// than it prints a dash
					// if the address has a value
					// 1 it prints an x
					if(board[lev][row][col] == 0){
						System.out.print(" _");
					}
					else if (board[lev][row][col] == 1){
						System.out.print(" x");
					}
					else if (board[lev][row][col] == 5){
						System.out.print(" o");
					}
				}
				System.out.println();
			}
			System.out.println();
			//prints the columns after at the bottom after the board has been printed
		}
		System.out.println("   0 1 2 3");
	}

	//takes the users input and alters the board value at that spot to 1 so it prints a X
	public static void userInput1(){
		int[] address = new int[3];        // level, row, column
		Scanner in = new Scanner(System.in);
		System.out.println("Type your move as one three digit number(LRC)");
		int number = in.nextInt();
		int i = 2;
		while(number > 0)
		{
			address[i] = number%10;   
			number /= 10;
			i--;
		}
		// if the cell is occupied by the user or the computer doesnt allow the user or computer to change it
		if(board[address[0]][address[1]][address[2]] == 1 || board[address[0]][address[1]][address[2]] == 5){
			System.out.println("Sorry that cell is occupied");
			userInput1();
		}
		else{
			board[address[0]][address[1]][address[2]] = 1;   
		}
	}
	//checks the value at board and compares them to the solution set to see if a solution, or parts of a solution is filled eg returning
	// a value of 2 means the user has two points in a solution set filled.
	public static void sum(){
		for(int i = 0; i < lines.length; i++){
			for(int j = 0; j < lines[0].length; j++){
				for(int k = 0; k < lines[0][0].length; k++){
					array.add(lines[i][j][k]);
				}
			}
		}
		int sum = 0;
		int index = 0;
		int j = 0;
		for(int i = 0; i < 912; i += 3){
			sum += board[array.get(i)][array.get(i+1)][array.get(i+2)];
			j++;
			if(j == 4){
				sumOfLines[index] = sum;
				sum = 0;
				j = 0;
				index ++;
			}
		}
	}
	//Checks to see if the user or computer is one move away from winning and either blocks it or makes the winning move
	public static void immediateWinAndBlock(){
		move = false;
		outerloop:
		for(int i = 0; i < sumOfLines.length; i++){
			if(sumOfLines[i] == 15 || sumOfLines[i] == 3){
				for(int j = 0; j < 4; j++){
					if(board[array.get((i*12) + (j*3))][array.get((i*12) + 1 + (j*3))][array.get((i*12) + 2 + (j*3))] == 0){
						board[array.get((i*12) + (j*3))][array.get((i*12) + 1 + (j*3))][array.get((i*12) + 2 + (j*3))] = 5;
						move = true;
						break outerloop;
					}
				}
			}
		}
		if(!move){
			fork();
		}
	}
	//If there are no forks to be made or blocked, or if there aren't any moves that are one away from winning 
	//the game the computer will makes a ranom move
	public static void randomMove(){
		Random r1 = new Random();
		Random r2 = new Random();
		Random r3 = new Random();
		if(board[r1.nextInt(4)][r2.nextInt(4)][r3.nextInt(4)] == 0){
			board[r1.nextInt(4)][r2.nextInt(4)][r3.nextInt(4)] = 5;
		}
		else{
			randomMove();
		}
	}
	//checks if the user or the computer has won the game uses sumOfLines to determine if the player or computer has won
	public static void hasWon(){
		for(int i = 0; i < sumOfLines.length; i++){
			if(sumOfLines[i] == 20){
				compHasWon = true;
				System.out.println("Computer wins again!");
			}
			else if(sumOfLines[i] == 4){
				userHasWon = true;
				System.out.println("Congratulations you won");
				displayBoard();
				System.exit(0);
			}
		}
	}
	//detects if the user is about to fork, or if the computer can create a fork and either stops it or creates a fork
	public static void fork(){
		move = false;
		ArrayList<Integer> possibleForksComp = new ArrayList<Integer>();
		ArrayList<Integer> possibleForksUser = new ArrayList<Integer>();
		for(int i = 0; i < sumOfLines.length; i++){
			if(sumOfLines[i] == 10){
				for(int j = 0; j < 4; j++){
					if(board[array.get((i*12) + (j*3))][array.get((i*12) + 1 + (j*3))][array.get((i*12) + 2 + (j*3))] ==  0){
						possibleForksComp.add(array.get((i*12) + (j*3)));
						possibleForksComp.add(array.get((i*12) + 1 + (j*3)));
						possibleForksComp.add(array.get((i*12) + 2 + (j*3)));
					}
				}
			}
			if(sumOfLines[i] == 2){
				for(int j = 0; j < 4; j++){
					if(board[array.get((i*12) + (j*3))][array.get((i*12) + 1 + (j*3))][array.get((i*12) + 2 + (j*3))] == 0){
						possibleForksUser.add(array.get((i*12) + (j*3)));
						possibleForksUser.add(array.get((i*12) + 1 + (j*3)));
						possibleForksUser.add(array.get((i*12) + 2 + (j*3)));
					}
				}
			}
		}
		int l,r,c;
		outerloop1:
		for(int i = 0; i < possibleForksComp.size() - 3; i += 3){
			l = possibleForksComp.get(i);
			r = possibleForksComp.get(i + 1);
			c = possibleForksComp.get(i + 2);
			for(int j = i + 3; j < possibleForksComp.size(); j += 3){
				if(l == possibleForksComp.get(j) && r == possibleForksComp.get(j + 1) && c == possibleForksComp.get(j + 2) && board[l][r][c] == 0){
					board[l][r][c] = 5;
					move = true;
					break outerloop1;
				}
			}
		}
		outerloop2:
		for(int i = 0; i < possibleForksUser.size() - 3; i += 3){
			l = possibleForksUser.get(i);
			r = possibleForksUser.get(i + 1);
			c = possibleForksUser.get(i + 2);
			for(int j = i + 3; j < possibleForksUser.size(); j += 3){
				if(l == possibleForksUser.get(j) && r == possibleForksUser.get(j + 1) && c == possibleForksUser.get(j + 2) && board[l][r][c] == 0){
					board[l][r][c] = 5;
					move = true;
					break outerloop2;
				}
			}
		}
		if(!move){
			randomMove();
		}
	}
}
