package com.example.dace_000.chessapp80.Chess;

/**
 * Class for the Queen Pieces
 * 
 * @author David Acevedo
 *
 */

public class Queen extends ChessPiece{

	/**
	 * Constructor that initializes all of the variables for a Queen
	 * @param color    Color of the Queen
	 * @param file     X position of the Queen
	 * @param rank	   Y position of the Queen	
	 */
	public Queen(String color, int file, int rank, ChessPiece[][] board) {
		this.file = file;
		this.rank = rank;
		this.color = color;
		
		if(color.equalsIgnoreCase("white")) {
			name = "wQ";
		} else {
			name = "bQ";
		}
		
		char tempFile = (char)(file + 97);
		String tempRank = Integer.toString(rank);
		position = tempFile + tempRank;
	}
	
	/**
	 * Method to see if the Queen can move to a certain spot
	 * @param board       The playing board
	 * @param newFile     X position that piece is moving to
	 * @param newRank     Y position that piece is moving to 
	 * @return            Whether the piece can move or not
	 * 
	 */
	public boolean allowableMove(ChessPiece[][] board, int newFile, int newRank) {
		
		if(newFile > 7 || newRank > 7 || newFile < 0 || newRank < 0) {
			return false; // Checks to see if the requested move is off of the board
		}
		
		if(board[newFile][newRank] != null) {
			if(this.canTakePiece(board[this.file][this.rank], board[newFile][newRank]) == false) {
				return false; // Checks if the newest spot can be taken or not. If it can take, it's a valid move;
			}
		}

		if(Math.abs(newFile - file) == 0 || Math.abs(newRank - rank) == 0) {
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
				for(int l = newFile + 1; l < file; l++) {
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
			return true;
		} else if(Math.abs(newFile - file) == Math.abs(newRank - rank)) {
			if(newFile - file > 0 && newRank - rank > 0) { // Moves NorthEast
				int j = rank + 1;
				for(int i = file + 1; i <= newFile; i++) {
					if(board[i][j] == null) {
						j++;
						continue;
					} else {
						if(board[i][j].equals(board[newFile][newRank])) {
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
							return true;
						} else {
							return false; // Checks to see if there is a piece in the way
						}
					}
				}
			}
			return true;
		} else {
			return false;
		}
	}
}
