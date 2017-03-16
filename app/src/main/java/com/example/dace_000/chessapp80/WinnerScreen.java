package com.example.dace_000.chessapp80;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.dace_000.chessapp80.Chess.Game;

public class WinnerScreen extends AppCompatActivity {

    Button saveGame, dontSaveGame;
    TextView resultLabel;
    Intent intent1, intent2;
    Bundle bundle;
    Game game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_winner_screen);

        Intent i = getIntent();
        String label = i.getExtras().getString("Message");

        Bundle b = this.getIntent().getExtras();
        game = (Game) b.getSerializable("Game Object");

        intent1 = new Intent(this, StartScreen.class);

        intent2 = new Intent();
        bundle = new Bundle();
        intent2.setClass(this, LoadScreen.class);

        saveGame = (Button) findViewById(R.id.saveGameButton);
        saveGame.setOnClickListener(save);

        resultLabel = (TextView) findViewById(R.id.resultLabel);
        resultLabel.setText(label);

        dontSaveGame = (Button) findViewById(R.id.dontSaveGameButton);
        dontSaveGame.setOnClickListener(dontSave);
    }

    View.OnClickListener save = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            bundle.putSerializable("Game Object", game);
            intent2.putExtras(bundle);

            EditText title = (EditText) findViewById(R.id.gameTitle);
            String gameTitle = title.getText().toString();

            if(gameTitle.equals("")) {
                showError();
                return;
            }

            game.setTitle(gameTitle);

            startActivity(intent2);
        }
    };

    View.OnClickListener dontSave = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            startActivity(intent1);
        }
    };

    private void showError() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        AlertDialog.Builder dlgAlert = new AlertDialog.Builder(this);
        dlgAlert.setMessage("Please Enter a title");
        dlgAlert.setTitle("Alert");
        dlgAlert.setPositiveButton("OK", null);
        dlgAlert.setCancelable(true);
        dlgAlert.create().show();
    }
}
