package com.example.dace_000.chessapp80.Chess;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Class for the King Pieces
 * 
 * @author David Acevedo
 *
 */

public class King extends ChessPiece{

	/**
	 * Records the pieces initial position
	 */

	String initPosition;

	/**
	 * Constructor that initializes all of the variables for a King
	 * @param color    Color of the King
	 * @param file     X position of the King
	 * @param rank	   Y position of the King
	 */
	public King(String color, int file, int rank, ChessPiece[][] board) {
		this.file = file;
		this.rank = rank;
		this.color = color;

		if(color.equalsIgnoreCase("white")) {
			name = "wK";
		} else {
			name = "bK";
		}

		char tempFile = (char)(file + 97);
		String tempRank = Integer.toString(rank);
		initPosition = tempFile + tempRank;
		position = tempFile + tempRank;
	}

	/**
	 * Method to see if the King can move to a certain spot
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

		if(newFile - file == 0 && newRank - rank == 1 && this.willBeInCheck(board, newFile, newRank) == false) { // Move North
			return true;
		}
		if(newFile - file == 1 && newRank - rank == 1 && this.willBeInCheck(board, newFile, newRank) == false) { // Move NorthEast
			return true;
		}
		if(newFile - file == 1 && newRank - rank == 0 && this.willBeInCheck(board, newFile, newRank) == false) { // Move East
			return true;
		}
		if(newFile - file == 1 && newRank - rank == -1 && this.willBeInCheck(board, newFile, newRank) == false) { // Move SouthEast
			return true;
		}
		if(newFile - file == 0 && newRank - rank == -1 && this.willBeInCheck(board, newFile, newRank) == false) { // Move South
			return true;
		}
		if(newFile - file == -1 && newRank - rank == -1 && this.willBeInCheck(board, newFile, newRank) == false) { // Move SouthWest
			return true;
		}
		if(newFile - file == -1 && newRank - rank == 0 && this.willBeInCheck(board, newFile, newRank) == false) { // Move West
			return true;
		}
		if(newFile - file == -1 && newRank - rank == 1 && this.willBeInCheck(board, newFile, newRank) == false) { // Move West
			return true;
		}
		if(Math.abs(newFile - file) == 2 && newRank == rank) {
			if(this.isACastlingMove(board, newFile, newRank)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Checks to see if the Position the King is moving into would put it in Check
	 * @param board       The playing board
	 * @param newFile     X position of the location being tested
	 * @param newRank     Y position of the location being tested
	 * @return            Whether the king will be in check
	 */
	public boolean willBeInCheck(ChessPiece[][] board, int newFile, int newRank) {
		ChessPiece temp = board[newFile][newRank];
		board[newFile][newRank] = null;
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 8; j++) {
				if(board[i][j] == null || this.color.equalsIgnoreCase(board[i][j].color)) {
					continue;
				} else {
					if(board[i][j].allowableMove(board, newFile, newRank) == true) {
						board[newFile][newRank] = temp;
						return true;
					}
				}
			}
		}
		board[newFile][newRank] = temp;
		return false;
	}

	public boolean isInCheckMate(ChessBoard board, ChessPiece justMoved) {

		if (!this.isInCheck(board)) {
			return false;
		}
		if (this.getPossibleMoves().size() != 0) {
			return false;
		}

		ChessPiece[][] boardArray = board.getBoard();
		for (ChessPiece cP : board.getPiecesLeft()) {
			if (cP.color.equalsIgnoreCase(this.color)) {
				List<String> possibleMoves = cP.getPossibleMoves();
				for (String move : possibleMoves) {
					System.out.println(move);
					int pieceFile = cP.getFile();
					int pieceRank = cP.getRank();
					int moveFile = move.charAt(0) - 97;
					int moveRank = move.charAt(1) - 49;
					ChessPiece temp = boardArray[moveFile][moveRank];
					if (temp != null) {
						temp.disabled = true;
					}
					boardArray[moveFile][moveRank] = cP;
					cP.setFile(moveFile);
					cP.setRank(moveRank);
					boardArray[pieceFile][pieceRank] = null;
					if(!this.isInCheck(board)) {
						boardArray[pieceFile][pieceRank] = cP;
						cP.setFile(pieceFile);
						cP.setRank(pieceRank);
						boardArray[moveFile][moveRank] = temp;
						if (temp != null) {
							temp.disabled = false;
						}
						return false;
					}
					boardArray[pieceFile][pieceRank] = cP;
					cP.setFile(pieceFile);
					cP.setRank(pieceRank);
					boardArray[moveFile][moveRank] = temp;
					if (temp != null) {
						temp.disabled = false;
					}
				}
			}
		}
		board.displayBoard();
		return true;
	}
	
	private boolean isACastlingMove(ChessPiece[][] board, int newFile, int newRank) {
		
		if(this.hasMoved == true) {
			return false;
		}
		
		if(this.color.equalsIgnoreCase("white")) {	
			if(newFile - file == 2) {
				if(board[7][0] != null && board[7][0].getName().endsWith("R") && !board[7][0].hasMoved) {
					for(int i = 5; i < 7; i++) {
						if(board[i][0] == null) {
							for(int x = 0; x < 8; x++) {
								for(int y = 0; y < 8; y++) {
									if(board[x][y] == null) {
										continue;
									} else {
										if(!board[x][y].color.equalsIgnoreCase(this.color) && board[x][y].allowableMove(board, i, 0)) {
											return false;
										} else {
											continue;
										}
									}
								}
							}
						} else {
							return false;
						}
					}
					return true;
				} else {
					return false;
				}
			} else if(newFile - file == -2) {
				if(board[0][0] != null && board[0][0].getName().endsWith("R") && !board[0][0].hasMoved) {
					for(int i = 3; i >= 1; i--) {
						if(board[i][0] == null) {
							for(int x = 0; x < 8; x++) {
								for(int y = 0; y < 8; y++) {
									if(board[x][y] == null) {
										continue;
									} else {
										if(!board[x][y].color.equalsIgnoreCase(this.color) && board[x][y].allowableMove(board, i, 0)) {
											return false;
										} else {
											continue;
										}
									}
								}
							}
						} else {
							return false;
						}
					}
					return true;
				} else {
					return false;
				} 
			}
		} else {
			if(newFile - file == 2) {
				if(board[7][7] != null && board[7][7].getName().endsWith("R") && !board[7][7].hasMoved) {
					for(int i = 5; i < 7; i++) {
						if(board[i][7] == null) {
							for(int x = 0; x < 8; x++) {
								for(int y = 0; y < 8; y++) {
									if(board[x][y] == null) {
										continue;
									} else {
										if(!board[x][y].color.equalsIgnoreCase(this.color) && board[x][y].allowableMove(board, i, 7)) {
											return false;
										} else {
											continue;
										}
									}
								}
							}
						} else {
							return false;
						}
					}
					return true;
				} else {
					return false;
				}
			} else if(newFile - file == -2) {
				if(board[0][7] != null && board[0][7].getName().endsWith("R") && !board[0][7].hasMoved) {
					for(int i = 3; i >= 1; i--) {
						if(board[i][7] == null) {
							for(int x = 0; x < 8; x++) {
								for(int y = 0; y < 8; y++) {
									if(board[x][y] == null) {
										continue;
									} else {
										if(!board[x][y].color.equalsIgnoreCase(this.color) && board[x][y].allowableMove(board, i, 7)) {
											return false;
										} else {
											continue;
										}
									}
								}
							}
						} else {
							return false;
						}
					}
					return true;
				} else {
					return false;
				}
			}
		}
		return true;
	}
}
