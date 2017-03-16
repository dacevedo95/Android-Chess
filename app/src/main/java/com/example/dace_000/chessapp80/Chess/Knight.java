package com.example.dace_000.chessapp80.Chess;

/**
 * Class for the Knight Pieces
 * 
 * @author David Acevedo
 *
 */

public class Knight extends ChessPiece{

	/**
	 * Constructor that initializes all of the variables for a Knight
	 * @param color    Color of the Knight
	 * @param file     X position of the Knight
	 * @param rank	   Y position of the Knight	
	 */
	public Knight(String color, int file, int rank, ChessPiece[][] board) {
		this.file = file;
		this.rank = rank;
		this.color = color;
		
		if(color.equalsIgnoreCase("white")) {
			name = "wN";
		} else {
			name = "bN";
		}
		
		char tempfile = (char)(file + 97);
		String tempRank = Integer.toString(rank);
		position = tempfile + tempRank;
	}
	
	/**
	 * Method to see if the Knight can move to a certain spot
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
		
		if(board[newFile][newRank] != null) {
			if(!this.canTakePiece(board[this.file][this.rank], board[newFile][newRank])) {
				return false; // Checks if the newest spot can be taken or not. If it can take, it's a valid move;
			}
		}
		
		if((Math.abs(newFile - file) == 2 && Math.abs(newRank - rank) == 1) || (Math.abs(newRank - rank) == 2 && Math.abs(newFile - file) == 1)) {
			return true; // Checks if requested move maintains the kinght's given movement pattern
		}
		
		return false;
	}
}
