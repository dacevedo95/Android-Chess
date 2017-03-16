package com.example.dace_000.chessapp80;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dace_000.chessapp80.Chess.*;

public class GameScreen extends AppCompatActivity {

    ChessBoard chessBoard;
    Game game;
    ImageButton[][] displayBoard;
    private static boolean gameOver = false;
    private static String turn = "white";
    private String origin = null, destination = null;
    private static final String TAG = "MyActivity";
    String message;
    AlertDialog.Builder dlgAlert;
    Button aI, undo, draw, resign;
    Intent intent;
    Bundle bundle;
    TextView turnDisplay;
    AlertDialog.Builder builder;
    int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_screen);

        intent = new Intent();
        bundle = new Bundle();
        intent.setClass(this, WinnerScreen.class);

        dlgAlert = new AlertDialog.Builder(this);
        builder = new AlertDialog.Builder(this);

        Bundle b = this.getIntent().getExtras();
        game = (Game) b.getSerializable("Game Object");

        displayBoard = new ImageButton[8][8];

        displayBoard[0][0] = (ImageButton) findViewById(R.id.a1);
        displayBoard[0][1] = (ImageButton) findViewById(R.id.a2);
        displayBoard[0][2] = (ImageButton) findViewById(R.id.a3);
        displayBoard[0][3] = (ImageButton) findViewById(R.id.a4);
        displayBoard[0][4] = (ImageButton) findViewById(R.id.a5);
        displayBoard[0][5] = (ImageButton) findViewById(R.id.a6);
        displayBoard[0][6] = (ImageButton) findViewById(R.id.a7);
        displayBoard[0][7] = (ImageButton) findViewById(R.id.a8);
        displayBoard[1][0] = (ImageButton) findViewById(R.id.b1);
        displayBoard[1][1] = (ImageButton) findViewById(R.id.b2);
        displayBoard[1][2] = (ImageButton) findViewById(R.id.b3);
        displayBoard[1][3] = (ImageButton) findViewById(R.id.b4);
        displayBoard[1][4] = (ImageButton) findViewById(R.id.b5);
        displayBoard[1][5] = (ImageButton) findViewById(R.id.b6);
        displayBoard[1][6] = (ImageButton) findViewById(R.id.b7);
        displayBoard[1][7] = (ImageButton) findViewById(R.id.b8);
        displayBoard[2][0] = (ImageButton) findViewById(R.id.c1);
        displayBoard[2][1] = (ImageButton) findViewById(R.id.c2);
        displayBoard[2][2] = (ImageButton) findViewById(R.id.c3);
        displayBoard[2][3] = (ImageButton) findViewById(R.id.c4);
        displayBoard[2][4] = (ImageButton) findViewById(R.id.c5);
        displayBoard[2][5] = (ImageButton) findViewById(R.id.c6);
        displayBoard[2][6] = (ImageButton) findViewById(R.id.c7);
        displayBoard[2][7] = (ImageButton) findViewById(R.id.c8);
        displayBoard[3][0] = (ImageButton) findViewById(R.id.d1);
        displayBoard[3][1] = (ImageButton) findViewById(R.id.d2);
        displayBoard[3][2] = (ImageButton) findViewById(R.id.d3);
        displayBoard[3][3] = (ImageButton) findViewById(R.id.d4);
        displayBoard[3][4] = (ImageButton) findViewById(R.id.d5);
        displayBoard[3][5] = (ImageButton) findViewById(R.id.d6);
        displayBoard[3][6] = (ImageButton) findViewById(R.id.d7);
        displayBoard[3][7] = (ImageButton) findViewById(R.id.d8);
        displayBoard[4][0] = (ImageButton) findViewById(R.id.e1);
        displayBoard[4][1] = (ImageButton) findViewById(R.id.e2);
        displayBoard[4][2] = (ImageButton) findViewById(R.id.e3);
        displayBoard[4][3] = (ImageButton) findViewById(R.id.e4);
        displayBoard[4][4] = (ImageButton) findViewById(R.id.e5);
        displayBoard[4][5] = (ImageButton) findViewById(R.id.e6);
        displayBoard[4][6] = (ImageButton) findViewById(R.id.e7);
        displayBoard[4][7] = (ImageButton) findViewById(R.id.e8);
        displayBoard[5][0] = (ImageButton) findViewById(R.id.f1);
        displayBoard[5][1] = (ImageButton) findViewById(R.id.f2);
        displayBoard[5][2] = (ImageButton) findViewById(R.id.f3);
        displayBoard[5][3] = (ImageButton) findViewById(R.id.f4);
        displayBoard[5][4] = (ImageButton) findViewById(R.id.f5);
        displayBoard[5][5] = (ImageButton) findViewById(R.id.f6);
        displayBoard[5][6] = (ImageButton) findViewById(R.id.f7);
        displayBoard[5][7] = (ImageButton) findViewById(R.id.f8);
        displayBoard[6][0] = (ImageButton) findViewById(R.id.g1);
        displayBoard[6][1] = (ImageButton) findViewById(R.id.g2);
        displayBoard[6][2] = (ImageButton) findViewById(R.id.g3);
        displayBoard[6][3] = (ImageButton) findViewById(R.id.g4);
        displayBoard[6][4] = (ImageButton) findViewById(R.id.g5);
        displayBoard[6][5] = (ImageButton) findViewById(R.id.g6);
        displayBoard[6][6] = (ImageButton) findViewById(R.id.g7);
        displayBoard[6][7] = (ImageButton) findViewById(R.id.g8);
        displayBoard[7][0] = (ImageButton) findViewById(R.id.h1);
        displayBoard[7][1] = (ImageButton) findViewById(R.id.h2);
        displayBoard[7][2] = (ImageButton) findViewById(R.id.h3);
        displayBoard[7][3] = (ImageButton) findViewById(R.id.h4);
        displayBoard[7][4] = (ImageButton) findViewById(R.id.h5);
        displayBoard[7][5] = (ImageButton) findViewById(R.id.h6);
        displayBoard[7][6] = (ImageButton) findViewById(R.id.h7);
        displayBoard[7][7] = (ImageButton) findViewById(R.id.h8);

        for(int x = 0; x < 8; x++) {
            for(int y = 0; y < 8; y++) {
                displayBoard[x][y].setOnClickListener(buttonClick);
            }
        }

        undo = (Button) findViewById(R.id.undo_button);
        undo.setOnClickListener(undoButton);

        aI = (Button) findViewById(R.id.AI_button);
        aI.setOnClickListener(aiButton);

        draw = (Button) findViewById(R.id.draw_button);
        draw.setOnClickListener(drawButton);

        resign = (Button) findViewById(R.id.resign_button);
        resign.setOnClickListener(resignButton);

        turnDisplay = (TextView) findViewById(R.id.resultLabel);
        turnDisplay.setText(game.getPlayer1().getName() + "'s Turn");

        chessBoard = new ChessBoard(displayBoard, game);
        chessBoard.displayBoard();
    }

    View.OnClickListener buttonClick = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            Log.v(TAG, "Clicked");
            String position = v.getTag().toString();
            ChessPiece piece = chessBoard.getPieceAt(position);
            if(piece != null) {
                Log.v(TAG, "Enter1");
                if((piece.getName().startsWith("w") && turn.equals("white") && origin == null) || (piece.getName().startsWith("b") && turn.equals("black") && origin == null)) {
                    Log.v(TAG, "Enter2");
                    if (origin == null) {
                        Log.v(TAG, "Enter3");
                        origin = position;
                    }
                } else {
                    Log.v(TAG, "Enter5");
                    if((piece.getName().startsWith("b") && turn.equals("white") && origin != null) || (piece.getName().startsWith("w") && turn.equals("black") && origin != null)) {
                        Log.v(TAG, "Enter6");
                        int rank1 = piece.getRank();
                        int file1 = piece.getFile();
                        char file2 = (char)(file1 + 97);
                        char rank2 = (char)(rank1 + 49);
                        String position1 = String.valueOf(file2) + String.valueOf(rank2);
                        count = 0;
                        makeMove(origin, position1);
                    } else {
                        Log.v(TAG, "Enter7");
                        //Invalid Move
                    }
                }
            } else {
                Log.v(TAG, "Enter8");
                if(origin == null) {
                    Log.v(TAG, "Enter9");
                    //Do Nothing
                } else {
                    Log.v(TAG, "Enter10");
                    destination = position;
                    count = 0;
                    makeMove(origin, position);
                }
            }
        }
    };

    View.OnClickListener undoButton = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(count == 0) {
                chessBoard.undo();
                alternateTurn();
                chessBoard.displayBoard();
            }
            count++;
        }
    };

    View.OnClickListener aiButton = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            GameState gameState = chessBoard.autoMove(turn);
            chessBoard.displayBoard();
            returnGameState(gameState);
        }
    };

    View.OnClickListener drawButton = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String name;
            if(turn.equals("white")) {
                name = game.getPlayer2().getName();
            } else {
                name = game.getPlayer1().getName();
            }

            builder.setMessage("Do you accept a draw " + name + "?").setPositiveButton("Yes", dialogClickListener)
                    .setNegativeButton("No", dialogClickListener).show();
        }
    };

    View.OnClickListener resignButton = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String label;
            if(turn.equals("white")) {
                label = game.getPlayer2().getName() + " Wins!";
            } else {
                label = game.getPlayer1().getName() + " Wins!";
            }
            chessBoard.getLastBoard();
            bundle.putSerializable("Game Object", game);
            intent.putExtras(bundle);
            intent.putExtra("Message", label);
            startActivity(intent);
        }
    };

    DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            switch (which){
                case DialogInterface.BUTTON_POSITIVE:
                    chessBoard.getLastBoard();
                    bundle.putSerializable("Game Object", game);
                    intent.putExtras(bundle);
                    intent.putExtra("Message", "Draw");
                    startActivity(intent);
                    break;

                case DialogInterface.BUTTON_NEGATIVE:
                    //No button clicked
                    break;
            }
        }
    };

    private void makeMove(String origin, String destination) {
        String move = origin + " " + destination;
        GameState gameState = chessBoard.makeMove(move, turn);
        returnGameState(gameState);
    }

    private void alternateTurn() {
        if(turn.equals("white")) {
            turnDisplay.setText(game.getPlayer2().getName() + "'s Turn");
            turn = "black";
        } else {
            turnDisplay.setText(game.getPlayer1().getName() + "'s Turn");
            turn = "white";
        }
    }

    private void returnGameState(GameState gameState) {
        switch (gameState) {
            case CHECK:
                message = gameState.getMessage();
                dlgAlert.setMessage(message);
                dlgAlert.setTitle("Alert");
                dlgAlert.setPositiveButton("OK", null);
                dlgAlert.setCancelable(true);
                dlgAlert.create().show();
                return;
            case DRAW:
                message = gameState.getMessage();
                dlgAlert.setMessage(message);
                dlgAlert.setTitle("Alert");
                dlgAlert.setPositiveButton("OK", null);
                dlgAlert.setCancelable(true);
                dlgAlert.create().show();
                gameOver = true;
                return;
            case INVALID_MOVE:
                this.origin = null;
                this.destination = null;
                message = gameState.getMessage();
                dlgAlert.setMessage(message);
                dlgAlert.setTitle("Alert");
                dlgAlert.setPositiveButton("OK", null);
                dlgAlert.setCancelable(true);
                dlgAlert.create().show();
                return;
            case NEXT_MOVE:
                this.origin = null;
                this.destination = null;
                System.out.println("test2");
                chessBoard.displayBoard();
                alternateTurn();
                return;
            case WHITE_CHECKMATE:
                message = gameState.getMessage();
                dlgAlert.setMessage(message);
                dlgAlert.setTitle("Alert");
                dlgAlert.setPositiveButton("OK", null);
                dlgAlert.setCancelable(true);
                dlgAlert.create().show();
                gameOver = true;
                return;
            case WHITE_RESIGN:
                message = gameState.getMessage();
                dlgAlert.setMessage(message);
                dlgAlert.setTitle("Alert");
                dlgAlert.setPositiveButton("OK", null);
                dlgAlert.setCancelable(true);
                dlgAlert.create().show();
                gameOver = true;
                return;
            case BLACK_CHECKMATE:
                message = gameState.getMessage();
                dlgAlert.setMessage(message);
                dlgAlert.setTitle("Alert");
                dlgAlert.setPositiveButton("OK", null);
                dlgAlert.setCancelable(true);
                dlgAlert.create().show();
                gameOver = true;
                return;
            case BLACK_RESIGN:
                message = gameState.getMessage();
                dlgAlert.setMessage(message);
                dlgAlert.setTitle("Alert");
                dlgAlert.setPositiveButton("OK", null);
                dlgAlert.setCancelable(true);
                dlgAlert.create().show();
                gameOver = true;
                return;
            default:
        }
    }
}
