package com.example.snakenladder;

import javafx.animation.PauseTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

public class Player {
    private Circle coin;
    private int currentPosition;
    private String name;
    private static Board gameBoard = new Board();



    public Player(int tileSize, Color coinColor, String playerName){
        coin = new Circle(tileSize/2);
        coin.setFill(coinColor);
        currentPosition = 0;
        movePlayer(1);
        name = playerName;
    }

    public void movePlayer(int dicevalue){
        if(currentPosition + dicevalue <= 100)
            currentPosition += dicevalue;

        TranslateTransition firstMove = translateAnimation(dicevalue), secondMove = null;


        int newPosition = gameBoard.getNewPosition(currentPosition);
        if(newPosition != currentPosition && newPosition!= -1){
            currentPosition = newPosition;
            secondMove = translateAnimation(6);
        }
        if(secondMove == null){
            firstMove.play();
        } else {
            SequentialTransition sequentialTransition = new SequentialTransition(firstMove,
                    new PauseTransition(Duration.millis(500)), secondMove);
            sequentialTransition.play();
        }
    }

    private TranslateTransition translateAnimation(int dicevalue){
        TranslateTransition animate = new TranslateTransition(Duration.millis(1000), coin);
        animate.setToX(gameBoard.getXCoordinate(currentPosition));
        animate.setToY(gameBoard.getYCoordinate(currentPosition));
        animate.setAutoReverse(false);
        return animate;
    }

    public void startingPosition(){
        currentPosition = 1;
        movePlayer(0);
    }

    boolean isWinner(){
        if(currentPosition==100)
            return true;
        return false;
    }
    public Circle getCoin() {
        return coin;
    }

    public int getCurrentPosition() {
        return currentPosition;
    }

    public String getName() {
        return name;
    }
}
