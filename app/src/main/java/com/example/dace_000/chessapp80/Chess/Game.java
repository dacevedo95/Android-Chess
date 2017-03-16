package com.example.dace_000.chessapp80.Chess;

import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by David Acevedo on 12/3/2016.
 */

public class Game implements Serializable{

    Player player1;
    Player player2;
    ChessBoard board;
    String title;
    Date date;
    private ArrayList<ChessPiece[][]> previousBoards;

    public Game() {
        previousBoards = new ArrayList<ChessPiece[][]>();
        date = new Date();
    }

    public Player getPlayer1() {
        return player1;
    }

    public void setPlayer1(Player player1) {
        this.player1 = player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public void setPlayer2(Player player2) {
        this.player2 = player2;
    }

    public ChessBoard getBoard() {
        return board;
    }

    public void setBoard(ChessBoard board) {
        this.board = board;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getDate() {
        return date;
    }

    public ArrayList<ChessPiece[][]> getBoards() {
        return previousBoards;
    }
}
