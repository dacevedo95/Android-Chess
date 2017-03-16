package com.example.dace_000.chessapp80;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.dace_000.chessapp80.Chess.Game;

import java.util.ArrayList;

public class LoadScreen extends Activity {

    ArrayList<Game> games = new ArrayList<Game>();
    Game game;
    Button back;
    Intent intent;
    Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_screen);

        intent = new Intent();
        bundle = new Bundle();
        intent.setClass(this, ReplayGameScreen.class);

        Bundle b = this.getIntent().getExtras();
        game = (Game) b.getSerializable("Game Object");

        games.add(game);
        System.out.println(games.size());

        back = (Button) findViewById(R.id.backButton);
        back.setOnClickListener(goBack);

        ListAdapter adapter = new CustomAdapter(this, games);
        ListView savedGamesList = (ListView) findViewById(R.id.saveGameList);
        savedGamesList.setAdapter(adapter);

        savedGamesList.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Game game = (Game) parent.getItemAtPosition(position);

                        bundle.putSerializable("Game Object", game);
                        intent.putExtras(bundle);

                        startActivity(intent);
                    }
                }
        );


    }

    View.OnClickListener goBack = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent("com.example.dace_000.chessapp80.EnterNameScreen");
            startActivity(intent);
        }
    };
}
