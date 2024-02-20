import javax.swing.*;
import java.util.Random;

public class SnakeModel {
    private int x1, x2, y1, y2, score;
    private final int width = 600;
    private final int height = 400;
    private final int unit_size = 20;
    private final int number_of_units = (width * height) / (unit_size * unit_size);

    private int fruitX, fruitY, tailLength = 1;
    private int[] tailX = new int[number_of_units];
    private int[] tailY = new int[number_of_units];
    private boolean gameOver, arrowUp, arrowDown, arrowLeft, arrowRight;

    /*public SnakeModel(int x1,  int x2,  int y1, int y2) {
        this.x1 = x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;
    }*/
    private void setFruitPosition() {
        Random random = new Random();
        fruitX = random.nextInt((int)((width - 60) / unit_size))*unit_size;
        fruitY = random.nextInt((int)((height - 60) / unit_size))*unit_size;
        for(int i = 0; i < tailLength; i++) {
            if (fruitX == tailX[i] && fruitY == tailY[i])
                setFruitPosition();
        }
    }

    public void setup(){
        gameOver = false;
        setFruitPosition();
        for(int i = 0; i < tailLength; i++){
            tailX[i] = 0;
            tailY[i] = 0;
        }
        arrowUp = false;
        arrowDown = false;
        arrowLeft = false;
        arrowRight = true;
    }

    public void logic() {
        for(int i = tailLength; i > 0; i--){
            tailX[i] = tailX[i-1];
            tailY[i] = tailY[i-1];
        }

        if(arrowUp && !arrowDown) { tailY[0] = tailY[0] - unit_size; }
        if(arrowDown && !arrowUp) { tailY[0] = tailY[0] + unit_size; }
        if(arrowLeft && !arrowRight) { tailX[0] = tailX[0] - unit_size; }
        if(arrowRight && !arrowLeft) { tailX[0] = tailX[0] + unit_size; }

        if(tailX[0] < 0 || tailX[0] > (width - 40) || tailY[0] < 0 || tailY[0] > (height - 45)){
            gameOver = true;
        }

        for(int i = tailLength; i > 0; i--){
            if(tailX[0] == tailX[i] && tailY[0] == tailY[i]) {
                gameOver = true;
                break;
            }
        }

        if(tailX[0] == fruitX && tailY[0] == fruitY){
            score += 1;
            setFruitPosition();
            tailLength += 1;
        }
    }
    public boolean getGameOver(){
        return gameOver;
    }

    public int getX1() {
        return x1;
    }
    public int getX2() {
        return x2;
    }

    public int getY1() {
        return y1;
    }
    public int getY2() {
        return y2;
    }

    public int getfruitX() {
        return fruitX;
    }
    public int getFruitY() {
        return fruitY;
    }

    public int getTailLength(){
        return tailLength;
    }

    public int[] getTailX(){
        return tailX;
    }

    public int[] getTailY(){
        return tailY;
    }

    public int getScore(){
        return score;
    }

    public void setArrowDown(boolean arrowDown) {
        this.arrowDown = arrowDown;
    }
    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    public void setArrowUp(boolean arrowUp) {
        this.arrowUp = arrowUp;
    }

    public void setArrowLeft(boolean arrowLeft) {
        this.arrowLeft = arrowLeft;
    }

    public void setArrowRight(boolean arrowRight) {
        this.arrowRight = arrowRight;
    }
    public boolean getArrowUp(){
        return arrowUp;
    }

    public boolean getArrowDown() {
        return arrowDown;
    }

    public boolean getArrowRight() {
        return arrowRight;
    }

    public boolean getArrowLeft() {
        return arrowLeft;
    }
    public int getUnit_size() {
        return unit_size;
    }
}
