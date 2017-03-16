package com.example.dace_000.chessapp80.Chess;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageButton;

import com.example.dace_000.chessapp80.R;

/**
 * Created by dace_000 on 12/13/2016.
 */

public class DisplayBoard extends AppCompatActivity{

    private ImageButton[][] displayBoard;

    public DisplayBoard() {
        displayBoard = new ImageButton[8][8];
    }

    public void initializeBoard(){
        displayBoard[0][0] = (ImageButton) findViewById(R.id.a1);
        displayBoard[0][0].setImageResource(R.drawable.white_rook_44);
    }

}
