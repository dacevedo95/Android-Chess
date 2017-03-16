package com.example.dace_000.chessapp80;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dace_000.chessapp80.Chess.Game;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Created by dace_000 on 12/15/2016.
 */

public class CustomAdapter extends ArrayAdapter<Game>{
    public CustomAdapter(Context context, ArrayList<Game> games) {
        super(context, R.layout.custom_row, games);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View customView = inflater.inflate(R.layout.custom_row, parent, false);

        Game game = getItem(position);
        TextView title = (TextView) customView.findViewById(R.id.gameTitle);
        TextView date = (TextView) customView.findViewById(R.id.date);
        ImageView icon = (ImageView) customView.findViewById(R.id.icon);

        title.setText(game.getTitle());

        SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("MM/dd/yyyy");
        String date1 = DATE_FORMAT.format(game.getDate());

        date.setText("Date: " + date1);
        icon.setImageResource(R.drawable.chess_icon);

        return customView;
    }






}
