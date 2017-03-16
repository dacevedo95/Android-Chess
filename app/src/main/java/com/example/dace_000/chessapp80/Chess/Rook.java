package com.example.dace_000.chessapp80.Chess;

/**
 * Class for the Rook Pieces
 * 
 * @author David Acevedo
 *
 */

public class Rook extends ChessPiece{
	
	/**
	 * Constructor that initializes all of the variables for a Rook
	 * @param color    Color of the Rook
	 * @param file     X position of the Rook
	 * @param rank	   Y position of the Rook	
	 */

	public Rook(String color, int file, int rank, ChessPiece[][] board) {
		this.file = file;
		this.rank = rank;
		this.color = color;
		
		if(color.equalsIgnoreCase("white")) {
			name = "wR";
		} else {
			name = "bR";
		}
		
		char tempFile = (char)(file + 97);
		String tempRank = Integer.toString(rank);
		position = tempFile + tempRank;
	}
	
	/**
	 * Method to see if the Rook can move to a certain spot
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
			if(this.canTakePiece(board[this.file][this.rank], board[newFile][newRank]) == false) {
				return false; // Checks if the newest spot can be taken or not. If it can take, it's a valid move;
			}
		}
		
		if(newFile - file == 0 && newRank - rank > 0) { // Moves North
			for(int i = rank + 1; i < newRank; i++) {
				if(board[file][i] == null) {
					continue;
				} else {
					return false; // Checks if there is a piece in the way
				}
			}
			if(board[newFile][newRank] != null) {
				if(this.canTakePiece(board[file][rank], board[newFile][newRank])) {
					return true;
				} else {
					return false;
				}
			} else {
				return true;
			}
		} else if(newFile - file == 0 && newRank - rank < 0) { // Moves South
			for(int j = rank - 1; j > newRank; j--) {
				if(board[file][j] == null) {
					continue;
				} else {
					return false; // Checks if there is a piece in the way
				}
			}
			if(board[newFile][newRank] != null) {
				if(this.canTakePiece(board[file][rank], board[newFile][newRank])) {
					return true;
				} else {
					return false;
				}
			} else {
				return true;
			}
		} else if(newFile - file > 0 && newRank - rank == 0) { // Moves East
			for(int k = file + 1; k < newFile; k++) {
				if(board[k][rank] == null) {
					continue;
				} else {
					return false; // Checks if there is a piece in the way
				}
			}
			if(board[newFile][newRank] != null) {
				if(this.canTakePiece(board[file][rank], board[newFile][newRank])) {
					return true;
				} else {
					return false;
				}
			} else {
				return true;
			}
		} else if(newFile - file < 0 && newRank - rank == 0) { // Moves West
			for(int l = newFile; l < file; l++) {
				if(board[l][rank] == null) {
					continue;
				} else {
					return false; // Checks if there is a piece in the way
				}
			}
			if(board[newFile][newRank] != null) {
				if(this.canTakePiece(board[file][rank], board[newFile][newRank])) {
					return true;
				} else {
					return false;
				}
			} else {
				return true;
			}
		}
		
		if((Math.abs(newFile - file) < 8 && Math.abs(newRank - rank) == 0) || (Math.abs(newRank - rank) < 8 && Math.abs(newFile - file) == 0)) {
			return true; // Checks if requested move maintains the rook's given movement pattern
		}
		
		return false;
	}
}
