package com.example.dace_000.chessapp80.Chess;

import java.io.Serializable;

/**
 * Created by dace_000 on 12/3/2016.
 */

public class Player implements Serializable{

    private String name;

    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
