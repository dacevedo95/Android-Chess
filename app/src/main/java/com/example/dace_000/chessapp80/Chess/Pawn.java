package com.example.dace_000.chessapp80.Chess;

/**
 * Class for the Pawn Pieces
 * 
 * @author David Acevedo
 *
 */

public class Pawn extends ChessPiece{
	
	/**
	 * tracks the pawn's starting position 
	 */
	
	String initPosition;
	
	/**
	 * Constructor that initializes all of the variables for a Pawn
	 * @param color    Color of the Pawn
	 * @param file     X position of the Pawn
	 * @param rank	   Y position of the Pawn	
	 */
	public Pawn(String color, int file, int rank, ChessPiece[][] board) {
		this.file = file;
		this.rank = rank;
		this.color = color;
		
		if(color.equalsIgnoreCase("white")) {
			name = "wp";
		} else {
			name = "bp";
		}
		
		char tempFile = (char)(file + 97);
		String tempRank = Integer.toString(rank);
		initPosition = tempFile + tempRank;
		position = tempFile + tempRank;
	}
	
	/**
	 * Method to see if the Pawn can move to a certain spot
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
		
		if(board[newFile][newRank] != null && Math.abs(newFile - file) == 0) {
			return false;
		}
				
		if(hasMoved == true && newFile - file == 0 && Math.abs(newRank - rank) == 2) {
			return false; // Checks to see if the user is moving two spaces after first move 
		}
		
		if(newFile - file == 0 && newRank - rank == 2 && hasMoved == false && this.color.equalsIgnoreCase("white")) { // Move North
			for(int i = rank + 1; i <= newRank; i++) {
				if(board[file][i] == null) {
					continue;
				} else {
					if(board[file][i].equals(board[newFile][newRank])) {

						this.numberOfSpacesLastMoved = 2;
						this.canBePassed = true;
						return true;
					} else {
						return false; // Checks to see if there is a piece in the way
					}
				}
			}
			this.numberOfSpacesLastMoved = 2;
			this.canBePassed = true;
			return true;
		} else if(newFile - file == 0 && newRank - rank == -2 && hasMoved == false && this.color.equalsIgnoreCase("black")) { // Move South
			for(int i = rank - 1; i >= newRank; i--) {
				if(board[file][i] == null) {
					continue;
				} else {
					if(board[file][i].equals(board[newFile][newRank])) {

						this.numberOfSpacesLastMoved = 2;
						this.canBePassed = true;
						return true;
					} else {
						return false; // Checks to see if there is a piece in the way
					}
				}
			}
			this.numberOfSpacesLastMoved = 2;
			this.canBePassed = true;
			return true;
		}
		
		if(newFile - file == 0 && newRank - rank == 1 && this.color.equalsIgnoreCase("white")) { // Move North
			this.numberOfSpacesLastMoved = 1;
			return true;
		}
		
		if(newFile - file == 0 && newRank - rank == -1 && this.color.equalsIgnoreCase("black")) { // Move North
			this.numberOfSpacesLastMoved = 1;
			return true;
		}
		
		if(this.color.equalsIgnoreCase("white") && newFile - file == 1 && newRank - rank == 1 && board[newFile][newRank] == null) {
			
			if(board[file + 1][rank] == null || !board[file + 1][rank].name.equals("bp")) {
				return false;
			}
			
			if(board[newFile][newRank] != null) {
				return false;
			}
			
			if(board[file + 1][rank].numberOfSpacesLastMoved != 2) {
				return false;
			}
			
			board[file + 1][rank] = null;
			this.numberOfSpacesLastMoved = 1;
			return true;
		}
		
		if(this.color.equalsIgnoreCase("white") && newFile - file == 1 && newRank - rank == 1 && board[newFile][newRank] != null) {
			if(!board[newFile][newRank].getColor().equalsIgnoreCase("black")) {
				return false;
			} else {
				this.numberOfSpacesLastMoved = 1;
				return true;
			}
		}
		
		if(this.color.equalsIgnoreCase("white") && newFile - file == -1 && newRank - rank == 1 && board[newFile][newRank] != null) {
			if(!board[newFile][newRank].getColor().equalsIgnoreCase("black")) {
				return false;
			} else {
				this.numberOfSpacesLastMoved = 1;
				return true;
			}
		}
		
		if(this.color.equalsIgnoreCase("black") && newFile - file == 1 && newRank - rank == -1 && board[newFile][newRank] != null) {
			if(!board[newFile][newRank].getColor().equalsIgnoreCase("white")) {
				return false;
			} else {
				this.numberOfSpacesLastMoved = 1;
				return true;
			}
		}
		
		if(this.color.equalsIgnoreCase("black") && newFile - file == -1 && newRank - rank == -1 && board[newFile][newRank] != null) {
			if(!board[newFile][newRank].getColor().equalsIgnoreCase("white")) {
				return false;
			} else {
				this.numberOfSpacesLastMoved = 1;
				return true;
			}
		}
		
		if(this.color.equalsIgnoreCase("white") && newFile - file == -1 && newRank - rank == 1 && board[newFile][newRank] == null) {
			
			if(board[file - 1][rank] == null || !board[file - 1][rank].name.equals("bp")) {
				return false;
			}
			
			if(board[newFile][newRank] != null) {
				return false;
			}
			
			if(board[file - 1][rank].numberOfSpacesLastMoved != 2) {
				return false;
			}
			
			board[file - 1][rank] = null;
			this.numberOfSpacesLastMoved = 1;
			return true;
		}
		
		if(this.color.equalsIgnoreCase("black") && newFile - file == 1 && newRank - rank == -1 && board[newFile][newRank] == null) {
			
			if(board[file + 1][rank] == null || !board[file + 1][rank].name.equals("wp")) {
				return false;
			}
			
			if(board[newFile][newRank] != null) {
				return false;
			}
			
			if(board[file + 1][rank].numberOfSpacesLastMoved != 2) {
				return false;
			}
			
			board[file + 1][rank] = null;
			this.numberOfSpacesLastMoved = 1;
			return true;
		}
		
		if(this.color.equalsIgnoreCase("black") && newFile - file == -1 && newRank - rank == -1 && board[newFile][newRank] == null) {
			
			if(board[file - 1][rank] == null || !board[file - 1][rank].name.equals("wp")) {
				return false;
			}
			
			if(board[newFile][newRank] != null) {
				return false;
			}
			
			if(board[file - 1][rank].numberOfSpacesLastMoved != 2) {
				return false;
			}
			
			board[file - 1][rank] = null;
			this.numberOfSpacesLastMoved = 1;
			return true;
		}
		
		return false;		
	}
	
	/**
	 * Method that promotes a pawn in another Piece specified by the parameter
	 * @param symbol      Indicates which Piece to promote to
	 * @return			  Returns a piece
	 */
	public ChessPiece promote(char symbol, ChessPiece[][] board) {
				
		if(symbol == 'R') {
			return new Rook(this.color, this.file, this.rank, board);
		} else if(symbol == 'N') {
			return new Knight(this.color, this.file, this.rank, board);
		} else if(symbol == 'B') {
			return new Bishop(this.color, this.file, this.rank, board);
		} else {
			return new Queen(this.color, this.file, this.rank, board);
		}
	}

}
