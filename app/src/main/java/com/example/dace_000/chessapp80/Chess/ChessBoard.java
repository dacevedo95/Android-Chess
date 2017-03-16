package com.example.dace_000.chessapp80.Chess;

import android.util.Log;
import android.widget.ImageButton;

import com.example.dace_000.chessapp80.R;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * 
 * @author Hubert Sulich
 *
 */

public class ChessBoard implements Serializable{

	private Game game;
	private ChessPiece[][] previousBoard;
	private ChessPiece[][] board;
	private final String validRank = "abcdefgh";
	private final String validFile = "12345678";
	private final String validPromotion = "NBRQ";
	private String previousMove;
	boolean isWhiteMove;
	private King wK, bK;
	private ArrayList<ChessPiece> piecesLeft;
	ImageButton[][] displayBoard;
	private static final String TAG = "MyActivity";
	int counter = 0;

	public ChessBoard(ImageButton[][] displayBoard, Game game, boolean replaying) {
		this.displayBoard();
		this.game = game;
	}
	
	public ChessBoard(ImageButton[][] displayBoard, Game game) {
		this.game = game;
		this.displayBoard = displayBoard;
		board = new ChessPiece[8][8];
		piecesLeft = new ArrayList<ChessPiece>();
		
		board[0][0] = new Rook("white", 0, 0, board);
		board[1][0] = new Knight("white", 1, 0, board);
		board[2][0] = new Bishop("white", 2, 0, board);
		board[3][0] = new Queen("white", 3, 0, board);
		board[4][0] = new King("white", 4, 0, board);
		wK = (King) board[4][0];
		board[5][0] = new Bishop("white", 5, 0, board);
		board[6][0] = new Knight("white", 6, 0, board);
		board[7][0] = new Rook("white", 7, 0, board);
		board[0][1] = new Pawn("white", 0, 1, board);
		board[1][1] = new Pawn("white", 1, 1, board);
		board[2][1] = new Pawn("white", 2, 1, board);
		board[3][1] = new Pawn("white", 3, 1, board);
		board[4][1] = new Pawn("white", 4, 1, board);
		board[5][1] = new Pawn("white", 5, 1, board);
		board[6][1] = new Pawn("white", 6, 1, board);
		board[7][1] = new Pawn("white", 7, 1, board);

		board[0][7] = new Rook("black", 0, 7, board);
		board[1][7] = new Knight("black", 1, 7, board);
		board[2][7] = new Bishop("black", 2, 7, board);
		board[3][7] = new Queen("black", 3, 7, board);
		board[4][7] = new King("black", 4, 7, board);
		bK = (King) board[4][7]; 
		board[5][7] = new Bishop("black", 5, 7, board);
		board[6][7] = new Knight("black", 6, 7, board);
		board[7][7] = new Rook("black", 7, 7, board);
		board[0][6] = new Pawn("black", 0, 6, board);
		board[1][6] = new Pawn("black", 1, 6, board);
		board[2][6] = new Pawn("black", 2, 6, board);
		board[3][6] = new Pawn("black", 3, 6, board);
		board[4][6] = new Pawn("black", 4, 6, board);
		board[5][6] = new Pawn("black", 5, 6, board);
		board[6][6] = new Pawn("black", 6, 6, board);
		board[7][6] = new Pawn("black", 7, 6, board);
		
		for(int x = 0; x < 8; x++) {
			for(int y = 0; y < 8; y++) {
				if(board[x][y] != null) {
					piecesLeft.add(board[x][y]);
					board[x][y].generatePossibleMoves(this, true);
				}
			}
		}

		previousBoard = new ChessPiece[8][8];
		isWhiteMove = true;
	}
	
	/**
	 * Method to make the user-specified move
	 * @param move      the user-specified move
	 * @return          GameState enum that provides information about the current state of the game
	 */

	public GameState makeMove(String move, String colorTurn) {
		previousBoard = copyBoard(board);

		move = move.trim();
		previousMove = move;

		if (!isValidMove(move, colorTurn)) {
			return GameState.INVALID_MOVE;
		}
		if (previousMove != null && previousMove.contains("draw?") && move.equals("draw")) {
			return GameState.DRAW;
		}
		if (move.equals("resign")) {
			if (isWhiteMove) {
				return GameState.WHITE_RESIGN;
			}
			else {
				return GameState.BLACK_RESIGN;
			}
		}
		return movePiece(move);
	}
	
	/**
	 * Method to make a piece according to the user-specified move
	 * @param move      the user-specified move
	 * @return          GameState enum that provides information about the current state of the game
	 */
	
	private GameState movePiece(String move) {
		
		String[] coordinates = move.split(" ");
		String fileRank1 = coordinates[0];
		int pieceFile1 = fileRank1.charAt(0) - 97;
		int pieceRank1 = fileRank1.charAt(1) - 49;
		ChessPiece piece = board[pieceFile1][pieceRank1];
		ChessPiece destination = null;
		
		String fileRank2 = coordinates[1];
		int pieceFile2 = fileRank2.charAt(0) - 97;
		int pieceRank2 = fileRank2.charAt(1) - 49;

		if (isPromotion(piece, move)) {
			if (board[pieceFile2][pieceRank2] != null) {
				piecesLeft.remove(board[pieceFile2][pieceFile1]);
			}
			if (isWhiteMove) {
				if (coordinates.length == 3) {
					String promotionPiece = coordinates[2];
					switch (promotionPiece) {
						case ("N"):  board[pieceFile2][pieceRank2] = new Knight("white", pieceFile2, pieceRank2, board);
									 break;
						case ("B"):  board[pieceFile2][pieceRank2] = new Bishop("white", pieceFile2, pieceRank2, board);
									 break;
						case ("R"):  board[pieceFile2][pieceRank2] = new Rook("white", pieceFile2, pieceRank2, board);
									 break;
						case ("Q"):  board[pieceFile2][pieceRank2] = new Queen("white", pieceFile2, pieceRank2, board);
									 break;
						default:  
					}
				}
				else {
					board[pieceFile2][pieceRank2] = new Queen("white", pieceFile2, pieceRank2, board);
				}
			}
			else {
				if (coordinates.length == 3) {
					String promotionPiece = coordinates[2];
					switch (promotionPiece) {
						case ("N"):  board[pieceFile2][pieceRank2] = new Knight("black", pieceFile2, pieceRank2, board);
									 break;
						case ("B"):  board[pieceFile2][pieceRank2] = new Bishop("black", pieceFile2, pieceRank2, board);
									 break;
						case ("R"):  board[pieceFile2][pieceRank2] = new Rook("black", pieceFile2, pieceRank2, board);
									 break;
						case ("Q"):  board[pieceFile2][pieceRank2] = new Queen("black", pieceFile2, pieceRank2, board);
									 break;
						default:
					}
				}
				else {
					board[pieceFile2][pieceRank2] = new Queen("black", pieceFile2, pieceRank2, board);
				}
			}
			board[pieceFile2][pieceRank2].setFile(pieceFile2);
			board[pieceFile2][pieceRank2].setRank(pieceRank2);
			board[pieceFile2][pieceRank2].setPosition(generatePosition(pieceFile2, pieceRank2));
			piecesLeft.add(board[pieceFile2][pieceRank2]);
			piecesLeft.remove(board[pieceFile1][pieceRank1]);
			board[pieceFile1][pieceRank1] = null;
			isWhiteMove = !isWhiteMove;
		} else if (piece.name.endsWith("K") && Math.abs(pieceFile1 - pieceFile2) == 2) {
			castleKing(piece, pieceFile2);
		}
		else {
			if(board[pieceFile2][pieceRank2] != null) {
				destination = board[pieceFile2][pieceRank2];
				piecesLeft.remove(destination);
			}
			
			board[pieceFile2][pieceRank2] = piece;
			piece.setFile(pieceFile2);
			piece.setRank(pieceRank2);
			piece.setPosition(generatePosition(pieceFile2, pieceRank2));
			board[pieceFile1][pieceRank1] = null;
			
			if(wK.isInCheck(this) || bK.isInCheck(this)) {
				for(int x = 0; x < 8; x++) {
					for(int y = 0; y < 8; y++) {
						if(board[x][y] != null) {	
							if(board[x][y].allowableMove(board, wK.getFile(), wK.getRank()) || board[x][y].allowableMove(board, bK.getFile(), bK.getRank())) {
								if(piece == board[x][y]) {
									break;
								} else {
									System.out.println("King will be in check");
									board[pieceFile1][pieceRank1] = piece;
									piece.setFile(pieceFile1);
									piece.setRank(pieceRank1);
									piece.setPosition(generatePosition(pieceFile1, pieceRank1));
									board[pieceFile2][pieceRank2] = destination;
									if(destination != null) {
										piecesLeft.add(destination);
									}
									return GameState.INVALID_MOVE;
								}
							} else {
								continue;
							}
						}
					}
				}
			}
		}

		for(ChessPiece cP : piecesLeft) {
			cP.generatePossibleMoves(this, false);
		}
		piece.numberOfMoves++;
		piece.hasMoved = true;
		if (isWhiteMove && bK.isInCheckMate(this, piece)) {
			return GameState.WHITE_CHECKMATE;
		}
		else if (!isWhiteMove && wK.isInCheckMate(this, piece)) {
			return GameState.BLACK_CHECKMATE;
		}
		isWhiteMove = !isWhiteMove;
		game.getBoards().add(previousBoard);
		return GameState.NEXT_MOVE;
	}
	
	/**
	 * Method to determine whether a user-specified move is valid
	 * @param move      the user-specified move
	 * @return          true if the move is valid, false otherwise
	 */
	
	private boolean isValidMove(String move, String turnColor) {
		System.out.println(move);
		if (previousMove != null && previousMove.contains("draw?") && move.contains("draw?")) {
			return false;
		}
		if (previousMove != null && previousMove.contains("draw?") && move.equals("draw")) {
			return true;
		}
		if (move.equals("resign")) {
			return true;
		}
		String[] coordinates = move.split(" ");
		
		//Checks if coordinates are wrong
		if (coordinates.length != 2 && coordinates.length != 3) {
			return false;
		}
		
		String fileRank1 = coordinates[0];
		if (!isValidCoordinate(fileRank1)) {
			return false;
		}
		String fileRank2 = coordinates[1];
		if (!isValidCoordinate(fileRank2)) {
			return false;
		}
		
		int pieceFile1 = fileRank1.charAt(0) - 97;
		int pieceRank1 = fileRank1.charAt(1) - 49;
		ChessPiece piece = board[pieceFile1][pieceRank1];
		System.out.println(pieceFile1 + ", " + pieceRank1);
		if((turnColor.equalsIgnoreCase("white") && !piece.name.startsWith("w")) || (turnColor.equalsIgnoreCase("black") && !piece.name.startsWith("b"))) {
			return false;
		}
		
		int pieceFile2 = fileRank2.charAt(0) - 97;
		int pieceRank2 = fileRank2.charAt(1) - 49;
		System.out.println(pieceFile2 + ", " + pieceRank2);
		
		if (!piece.allowableMove(board, pieceFile2, pieceRank2)) {
			return false;
		}
		if (isInvalidPromotion(piece, move)) {
			return false;
		}
		
		
		
		return true;
	}
	
	/**
	 * Method to determine whether a user-specified coordinate (FileRank) is valid
	 * @param fileRank      the user-specified coordinate
	 * @return          	true if the coordinate is valid, false otherwise
	 */
	
	private boolean isValidCoordinate(String fileRank) {
		if (!validRank.contains(fileRank.substring(0, 1)) || 
				!validFile.contains(fileRank.substring(1, 2))) {
			return false;
		}
		return true;
	}
	
	/**
	 * Method to determine whether a promotion move is valid
	 * @param piece		the piece being promoted
	 * @param move		the user-specified move
	 * @return          true if the move is invalid, false otherwise
	 */
	
	private boolean isInvalidPromotion(ChessPiece piece, String move) {
		String[] coordinates = move.split(" ");
		String fileRank1 = coordinates[0];
		String fileRank2 = coordinates[1];
		
		if (fileRank1.charAt(1) == '7' && fileRank2.charAt(1) == '8' && piece.isWhite() ||
				fileRank1.charAt(1) == '2' && fileRank2.charAt(1) == '1' && !piece.isWhite()) {
			if (coordinates.length == 3) { 
				if (coordinates[2].equals("draw?")) {
					return false;
				}
				if (!validPromotion.contains(coordinates[2])) {
					return true;
				}
			}
		}
		return false;
	}
	
	private boolean isPromotion(ChessPiece piece, String move) {
		String[] coordinates = move.split(" ");
		String fileRank1 = coordinates[0];
		String fileRank2 = coordinates[1];
		
		if ((fileRank1.charAt(1) == '7' && fileRank2.charAt(1) == '8' && piece.isWhite() && piece.getName().endsWith("p")) ||
				(fileRank1.charAt(1) == '2' && fileRank2.charAt(1) == '1' && !piece.isWhite() && piece.getName().endsWith("p"))) {
			if (coordinates.length == 3) { 
				if (!validPromotion.contains(coordinates[2])) {
					return false;
				}
			}
			return true;
		}
		return false;
	}	
	
	public void displayBoard() {
		for(int x = 0; x < 8; x++) {
			for(int y = 0; y < 8; y++) {
				if(board[x][y] != null) {
					if (board[x][y].getName().equals("wp")) {
						displayBoard[x][y].setImageResource(R.drawable.white_pawn_44);
					}
					if (board[x][y].getName().equals("bp")) {
						displayBoard[x][y].setImageResource(R.drawable.black_pawn_44);
					}
					if (board[x][y].getName().equals("wR")) {
						displayBoard[x][y].setImageResource(R.drawable.white_rook_44);
					}
					if (board[x][y].getName().equals("bR")) {
						displayBoard[x][y].setImageResource(R.drawable.black_rook_44);
					}
					if (board[x][y].getName().equals("wN")) {
						displayBoard[x][y].setImageResource(R.drawable.white_knight_44);
					}
					if (board[x][y].getName().equals("bN")) {
						displayBoard[x][y].setImageResource(R.drawable.black_knight_44);
					}
					if (board[x][y].getName().equals("wB")) {
						displayBoard[x][y].setImageResource(R.drawable.white_bishop_44);
					}
					if (board[x][y].getName().equals("bB")) {
						displayBoard[x][y].setImageResource(R.drawable.black_bishop_44);
					}
					if (board[x][y].getName().equals("wQ")) {
						displayBoard[x][y].setImageResource(R.drawable.white_queen_44);
					}
					if (board[x][y].getName().equals("bQ")) {
						displayBoard[x][y].setImageResource(R.drawable.black_queen_44);
					}
					if (board[x][y].getName().equals("wK")) {
						displayBoard[x][y].setImageResource(R.drawable.white_king_44);
					}
					if (board[x][y].getName().equals("bK")) {
						displayBoard[x][y].setImageResource(R.drawable.black_king_44);
					}
				} else {
					displayBoard[x][y].setImageResource(R.drawable.blank);
				}
			}
		}

		for (int i = 7; i >= 0; i--) {
			for (int j = 0; j < board[0].length; j++) {
				if (board[j][i] == null) {
					if (isBlackSquare(j, i)) {
						System.out.print("## ");
					}
					else {
						System.out.print("   ");
					}
				}
				else {
					System.out.print(board[j][i].getName() + " ");
				}
			}
			System.out.println("" + (i + 1));
		}
		for (int i = 0; i < 8; i++) {
			char column = 'a';
			System.out.print(" " + (char)(column + i) + " ");
		}
		System.out.println("\n");


	}

	public ChessPiece getPieceAt(String position) {
		String file = position.substring(0, 1);
		String rank = position.substring(1);
		int numRank = Integer.parseInt(rank);
		numRank--;
		String newRank = Integer.toString(numRank);



		String newPosition = file + newRank;
		Log.v(TAG, newPosition + "!");

		ChessPiece piece = null;
		for(ChessPiece cP : piecesLeft) {
			int rank1 = cP.getRank();
			int file1 = cP.getFile();
			char file2 = (char)(file1 + 97);
			char rank2 = (char)(rank1 + 48);
			String position1 = String.valueOf(file2) + String.valueOf(rank2);
			if(newPosition.equalsIgnoreCase(position1)) {
				piece = cP;
			}
		}
		return piece;
	}
	
	private boolean isBlackSquare(int col, int row) {
		if ((row % 2 == 0 && col % 2 == 0) || (row % 2 == 1 && col % 2 == 1)) {
			return true;
		}
		return false;
	}

	public void undo() {

		if(previousMove != null) {
			board = previousBoard;
			String[] coordinates = previousMove.split(" ");
			String fileRank1 = coordinates[0];
			int pieceFile1 = fileRank1.charAt(0) - 97;
			int pieceRank1 = fileRank1.charAt(1) - 49;
			ChessPiece piece = board[pieceFile1][pieceRank1];
			piece.setFile(pieceFile1);
			piece.setRank(pieceRank1);
		}
	}

	public GameState autoMove(String turn) {
		String move = null;
		if(turn.equals("white")) {
			Log.v(TAG, "White's AI");
			for(ChessPiece cP: piecesLeft) {
				if(cP.getName().startsWith("w")) {
					if(cP.getPossibleMoves().size() > 0) {
						String destination = cP.getPossibleMoves().get(0);
						int rank1 = cP.getRank();
						int file1 = cP.getFile();
						char file2 = (char) (file1 + 97);
						char rank2 = (char) (rank1 + 49);
						String position1 = String.valueOf(file2) + String.valueOf(rank2);
						move = position1 + " " + destination;
						break;
					}
				}
			}
			Log.v(TAG, "Moving " + move);
			return makeMove(move, turn);
		} else {
			Log.v(TAG, "Black's AI");
			for(ChessPiece cP: piecesLeft) {
				if(cP.getName().startsWith("b")) {
					if(cP.getPossibleMoves().size() > 0) {
						String destination = cP.getPossibleMoves().get(0);
						int rank1 = cP.getRank();
						int file1 = cP.getFile();
						char file2 = (char) (file1 + 97);
						char rank2 = (char) (rank1 + 49);
						String position1 = String.valueOf(file2) + String.valueOf(rank2);
						move = position1 + " " + destination;
						break;
					}
				}
			}
			Log.v(TAG, "Moving " + move);
			return makeMove(move, turn);
		}
	}

	private String generatePosition(int file, int rank) {
		char pieceFile1 = (char) (file + 97);
		char pieceRank1 = (char) (file + 49);
		String position = String.valueOf(pieceFile1) + String.valueOf(pieceRank1);
		return position;
	}

	private ChessPiece[][] copyBoard(ChessPiece[][] board) {
		ChessPiece[][] copy = new ChessPiece[8][8];
		for(int x = 0; x < 8; x++) {
			for(int y = 0; y < 8; y++) {
				copy[x][y] = board[x][y];
			}
		}

		return copy;
	}

	private void castleKing(ChessPiece king, int newFile) {
		if(king.color.equalsIgnoreCase("white")) {
			if(newFile - king.file == 2) {
				board[5][0] = board[7][0];
				board[5][0].setFile(5);
				board[5][0].setPosition(generatePosition(5, 0));
				board[5][0].numberOfMoves++;
				board[5][0].hasMoved = true;
				board[7][0] = null;
				board[6][0] = king;
				king.setFile(6);
				king.setPosition(generatePosition(6, 0));
				king.numberOfMoves++;
				king.hasMoved = true;
				board[4][0] = null;
			} else if(newFile - king.file == -2) {
				board[3][0] = board[0][0];
				board[3][0].setFile(3);
				board[3][0].setRank(0);
				board[3][0].setPosition(generatePosition(3, 0));
				board[3][0].numberOfMoves++;
				board[3][0].hasMoved = true;
				board[0][0] = null;
				board[2][0] = king;
				king.setFile(2);
				king.setPosition(generatePosition(2, 0));
				king.numberOfMoves++;
				king.hasMoved = true;
				board[4][0] = null;
			}
		} else {
			if (newFile - king.file == 2) {
				board[5][7] = board[7][7];
				board[5][7].setFile(5);
				board[5][7].setRank(7);
				board[5][7].setPosition(generatePosition(5, 7));
				board[5][7].numberOfMoves++;
				board[5][7].hasMoved = true;
				board[7][7] = null;
				board[6][7] = king;
				king.setFile(6);
				king.setPosition(generatePosition(6, 7));
				king.numberOfMoves++;
				king.hasMoved = true;
				board[4][7] = null;
			} else if (newFile - king.file == -2) {
				board[3][7] = board[0][7];
				board[3][7].setFile(3);
				board[3][7].setRank(7);
				board[3][7].setPosition(generatePosition(3, 7));
				board[3][7].numberOfMoves++;
				board[3][7].hasMoved = true;
				board[0][7] = null;
				board[2][7] = king;
				king.setFile(2);
				king.setPosition(generatePosition(2, 7));
				king.numberOfMoves++;
				king.hasMoved = true;
				board[4][7] = null;
			}
		}
	}

	private boolean isOpponentKingInCheck() {
		return false;
	}
	
	private boolean isOpponentKingInCheckmate() {
		return false;
	}
	
	public ChessPiece[][] getBoard() {
		return board;
	}

	public ArrayList<ChessPiece> getPiecesLeft() {
		return piecesLeft;
	}

	public void getLastBoard() {
		game.getBoards().add(board);
	}
}