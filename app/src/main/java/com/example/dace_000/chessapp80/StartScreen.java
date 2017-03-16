package com.example.dace_000.chessapp80;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.dace_000.chessapp80.Chess.Game;

public class StartScreen extends AppCompatActivity {

    private static Button loadGameButton;
    private static Button startGameButton;
    public Game game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_screen);
        game = new Game();
        enterLoadScreen();
        enterNameScreen();
    }

    public void enterLoadScreen() {
        loadGameButton = (Button) findViewById(R.id.loadGameButton);
        loadGameButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent("com.example.dace_000.chessapp80.LoadScreen");
                        startActivity(intent);
                    }
                }
        );

    }

    public void enterNameScreen() {
        startGameButton = (Button) findViewById(R.id.newGameButton);
        startGameButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent("com.example.dace_000.chessapp80.EnterNameScreen");
                        startActivity(intent);
                    }
                }
        );

    }
}
