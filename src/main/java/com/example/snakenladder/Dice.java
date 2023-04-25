package com.example.snakenladder;

import javafx.scene.shape.Circle;

public class Dice {
    public int getRolledDiceValue(){
        return (int)(Math.random()*6+1);
    }
}
