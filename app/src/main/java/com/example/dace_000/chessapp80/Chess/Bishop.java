package com.example.dace_000.chessapp80.Chess;

/**
 * Class for the Bishop Pieces
 * 
 * @author David Acevedo
 *
 */

public class Bishop extends ChessPiece{
	
	/**
	 * Constructor that initializes all of the variables for a Bishop
	 * @param color    Color of the Bishop
	 * @param file     X position of the Bishop
	 * @param rank	   Y position of the Bishop	
	 */
	public Bishop(String color, int file, int rank, ChessPiece[][] board) {
		this.file = file;
		this.rank = rank;
		this.color = color;
		
		if(color.equalsIgnoreCase("white")) {
			name = "wB";
		} else {
			name = "bB";
		}
		
		char tempFile = (char)(file + 97);
		String tempRank = Integer.toString(rank);
		position = tempFile + tempRank;
	}
	
	
	/**
	 * Method to see if the Bishop can move to a certain spot
	 * @param board       The playing board
	 * @param newFile     X position that piece is moving to
	 * @param newRank     Y position that piece is moving to 
	 * @return            Whether the piece can move or not
	 * 
	 */
	public boolean allowableMove(ChessPiece[][] board, int newFile, int newRank) {
				
		if(newFile > 7 || newRank > 7) {
			return false; // Checks to see if the requested move is off of the board
		}
		
		if(Math.abs(newFile - this.file) != Math.abs(newRank - this.rank)) {
			return false; // Checks to see if it maintains diagonal movement
		}
		
		if(board[newFile][newRank] != null) {
			System.out.println(this.file + ", " + this.rank);
			if(this.canTakePiece(board[this.file][this.rank], board[newFile][newRank]) == false) {
				return false; // Checks if the newest spot can be taken or not. If it can take, it's a valid move;
			}
		}
		
		if(newFile - file > 0 && newRank - rank > 0) { // Moves NorthEast
			int j = rank + 1;
			for(int i = file + 1; i <= newFile; i++) {
				if(board[i][j] == null) {
					j++;
					continue;
				} else {
					if(board[i][j].equals(board[newFile][newRank])) {
						//this.updatePosition(newFile, newRank);
						return true;
					} else {
						return false; // Checks to see if there is a piece in the way
					}
				}
			}
		} else if(newFile - file > 0 && newRank - rank < 0) { // Moves SouthEast
			int l = rank - 1;
			for(int k = file + 1; k <= newFile; k++) {
				if(board[k][l] == null) {
					l--;
					continue;
				} else {
					if(board[k][l].equals(board[newFile][newRank])) {
						//this.updatePosition(newFile, newRank);
						return true;
					} else {
						return false; // Checks to see if there is a piece in the way
					}
				}
			}
		} else if(newFile - file < 0 && newRank - rank < 0) { // Moves SouthWest
			int n = rank - 1;
			for(int m = file - 1; m >= newFile; m--) {
				if(board[m][n] == null) {
					n--;
					continue;
				} else {
					if(board[m][n].equals(board[newFile][newRank])) {
						//this.updatePosition(newFile, newRank);
						return true;
					} else {
						return false; // Checks to see if there is a piece in the way
					}
				}
			}
		} else if(newFile - file < 0 && newRank - rank > 0) { // Moves NorthWest
			int p = rank + 1;
			for(int o = file - 1; o >= newFile; o--) {
				if(board[o][p] == null) {
					p++;
					continue;
				} else {
					if(board[o][p].equals(board[newFile][newRank])) {
						//this.updatePosition(newFile, newRank);
						return true;
					} else {
						return false; // Checks to see if there is a piece in the way
					}
				}
			}
		}
		//this.updatePosition(newFile, newRank);		
		return true;
	}
}
