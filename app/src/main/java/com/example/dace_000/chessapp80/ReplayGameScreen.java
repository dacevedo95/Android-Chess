package com.example.dace_000.chessapp80;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;

import com.example.dace_000.chessapp80.Chess.ChessBoard;
import com.example.dace_000.chessapp80.Chess.ChessPiece;
import com.example.dace_000.chessapp80.Chess.Game;

public class ReplayGameScreen extends AppCompatActivity {

    Game game;
    Button nextMove, previousMove;
    GridView displayOnly;
    ImageButton[][] displayBoard;
    int counter = 0;
    ChessPiece[][] board;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_replay_game_screen);

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

        nextMove = (Button) findViewById(R.id.next_move_button);
        nextMove.setOnClickListener(nextClick);

        previousMove = (Button) findViewById(R.id.previous_move_button);
        previousMove.setOnClickListener(previousClick);

        board = game.getBoards().get(counter);
        displayBoard();
    }

    View.OnClickListener nextClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            counter++;
            nextMove();
            displayBoard();
        }
    };

    View.OnClickListener previousClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            counter--;
            previousMove();
            displayBoard();;
        }
    };

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
    }

    public void nextMove() {
        System.out.println(game.getBoards().size());
        if(counter > game.getBoards().size() - 1) {
            counter = game.getBoards().size() - 1;
        } else {
            board = game.getBoards().get(counter);
        }
    }

    public void previousMove() {
        if(counter < 0) {
            counter = 0;
        } else {
            board = game.getBoards().get(counter);
        }
    }
}
