package com.example.dace_000.chessapp80.Chess;

/**
 * 
 * @author Hubert Sulich
 *
 */

public enum GameState {

	BLACK_CHECKMATE("Checkmate\nBlack wins"),
	BLACK_RESIGN("White wins"),
	CHECK("Check"),
	DRAW("Draw"),
	INVALID_MOVE("Illegal move, try again"),
	NEXT_MOVE(""),
	WHITE_CHECKMATE("Checkmate\nWhite wins"),
	WHITE_RESIGN("Black wins");
	
	String message;
	
	GameState(String message) {
		this.message = message;
	}
	
	public String getMessage() {
		return this.message;
	}
}