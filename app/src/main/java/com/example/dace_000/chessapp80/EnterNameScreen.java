package com.example.dace_000.chessapp80;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.dace_000.chessapp80.Chess.Game;
import com.example.dace_000.chessapp80.Chess.Player;

import java.util.Calendar;

public class EnterNameScreen extends AppCompatActivity {

    private static Button startGameButton;
    String playerOne, playerTwo;
    EditText p1Name, p2Name;
    Game game;
    Intent intent;
    Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_name_screen);
        enterGameScreen();
        intent = new Intent();
        bundle = new Bundle();
        intent.setClass(this, GameScreen.class);
        game = new Game();
    }

    public void enterGameScreen() {
        startGameButton = (Button) findViewById(R.id.startGameButton);
        startGameButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        p1Name = (EditText) findViewById(R.id.p1Name);
                        playerOne = p1Name.getText().toString();

                        if(playerOne.equals("")) {
                            playerOne = "Player 1";
                        }

                        Player p1 = new Player(playerOne);
                        game.setPlayer1(p1);

                        p2Name = (EditText) findViewById(R.id.p2Name);
                        playerTwo = p2Name.getText().toString();

                        if(playerTwo.equals("")) {
                            playerTwo = "Player 2";
                        }

                        Player p2 = new Player(playerTwo);
                        game.setPlayer2(p2);

                        bundle.putSerializable("Game Object", game);
                        intent.putExtras(bundle);

                        startActivity(intent);
                    }
                }
        );

    }


}
