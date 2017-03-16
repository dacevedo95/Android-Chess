package com.example.dace_000.chessapp80.Chess;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Abstract class that defines all pieces
 * 
 * @author David Acevedo
 *
 */
public abstract class ChessPiece implements Serializable{
	
	int numberOfSpacesLastMoved = 0;
	boolean canBePassed = false;
	
	/**
	 * Keeps track of each piece'ss name, position and color
	 */
	
	protected String name, position, color;
	
	/**
	 * Keeps track of each piece's file and rank in Integers form
	 */
	
	protected int file, rank;
	
	/**
	 * Keeps track of whether the piece was moved or not
	 */
	
	protected boolean hasMoved;
	
	/**
	 * Number of times a piece has moved;
	 */
	
	protected int numberOfMoves;

	/**
	 * Determines whether to include this piece for checking whether a king is in check
	 */

	protected boolean disabled = false;

	/**
	 * Keeps track of each pieces possible moves
	 */
	
	protected ArrayList<String> possibleMoves;
	
	

	/**
	 * Method to see if a move can be executed
	 * @param board     The paying board
	 * @param newFile   X destination move
	 * @param newRank   Y destination move
	 * @return          Whether the piece can move or not
	 */
	
	public abstract boolean allowableMove(ChessPiece[][] board, int newFile, int newRank);
	
	/**
	 * Populates an ArrayList of all possible moves for a piece
	 * @param board    Board to be scanned  
	 */
	
	public void generatePossibleMoves(ChessBoard board, boolean initializing) {
		possibleMoves = new ArrayList<String>();
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 8; j++) {
				if(this.allowableMove(board.getBoard(), i, j)) {
					char file = (char) (i + 97);
					String rank = Integer.toString(j + 1);
					String position = file + rank;
					possibleMoves.add(position);
					if(initializing) {
						this.setNumberOfMoves(0);
					}
				}
			}
		}
	}	
	
	/**
	 * Method that checks to see if the piece can take the spot of another piece on the board based on it's color 
	 * @param beingMoved     Piece being moved
	 * @param inPlace        Piece that is about to be taken  
	 * @return			     True if pieces are the same color,false if they are not
	 */
	
	public boolean canTakePiece(ChessPiece beingMoved, ChessPiece inPlace) {	
		
		if(!beingMoved.getColor().equalsIgnoreCase(inPlace.getColor())) {
			return true;
		}
		return false;
	}
		
	/**
	 * Method to see if the King is in check
	 * @param board     Playing board with all pieces
	 * @return          Whether the king is in check or not
	 */
	public boolean isInCheck(ChessBoard board) {
			
		if(this.getName().endsWith("K")) {
			for(ChessPiece cP : board.getPiecesLeft()) {
				if(!cP.disabled && !this.color.equals(cP.getColor()) && cP.allowableMove(board.getBoard(), this.getFile(), this.getRank())) {
					return true;
				}
			}
		}
		return false;
	}
		
	/**
	 * Gets the name of the piece
	 * @return  Name of the piece
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Gets the position of the piece
	 * @return  Position of the piece
	 */
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	
	/**
	 * Gets the color of the piece
	 * @return  Color of the piece
	 */
	public String getColor() {
		return color;
	}
	
	/**
	 * Gets the File of the piece
	 * @return  File of the piece
	 */
	public int getFile() {
		return file;
	}
	
	/**
	 * Sets the File of the piece
	 */
	public void setFile(int file) {
		this.file = file;
	}
	
	/**
	 * Gets the Rank of the piece
	 * @return  Rank of the piece
	 */
	public int getRank() {
		return rank;
	}
	
	/**
	 * Sets the Rank of the piece
	 */
	public void setRank(int rank) {
		this.rank = rank;
	}

	boolean isWhite() {
		if (name.charAt(0) == 'w') {
			return true;
		}
		else {
			return false;
		}
	}
	
	public int getNumberOfSpacesLastMoved() {
		return this.numberOfSpacesLastMoved;
	}

	public int getNumberOfMoves() {
		return numberOfMoves;
	}

	public void setNumberOfMoves(int numberOfMoves) {
		this.numberOfMoves = numberOfMoves;
	}
	
	public ArrayList<String> getPossibleMoves() {
		return possibleMoves;
	}
}
