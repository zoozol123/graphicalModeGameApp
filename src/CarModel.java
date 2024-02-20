import java.util.Objects;
import java.util.Random;

public class CarModel {
    private int WIDTH = 600;
    private int HEIGHT = 400;
    private final int unit_size = 20;
    private final int number_of_units = (WIDTH * HEIGHT) / (unit_size * unit_size);
    private int width = 24, height = 24;
    private int[] enemyX = new int[2];
    private int[] enemyY = new int[2];
    private boolean[] enemyFlag = new boolean[2];
    private int eraseIndex;
    private char[][] car = {
            {' ', '±', '±', ' '},
            {'±', '±', '±', '±'},
            {' ', '±', '±', ' '},
            {'±', '±', '±', '±'}
    };
    private int carPos;
    private int score = 0;
    private boolean gameover = false, arrowRight, arrowLeft;

    public void generateEnemy(int i){
        Random random = new Random();
        enemyX[i] = 140 + random.nextInt(4) * 80;
    }

    private int collision(){
        if(enemyY[0] + 4 * unit_size >= 320){
            if(enemyX[0] + 4 * unit_size - carPos >= 0 && enemyX[0] + 4 * unit_size - carPos <= 80){
                return 1;
            }
        }
        if(enemyY[1] + 4 * unit_size >= 320){
            if(enemyX[1] + 4 * unit_size - carPos >= 0 && enemyX[1] + 4 * unit_size - carPos <= 80){
                return 1;
            }
        }
        return 0;
    }

    public void setup() {
        carPos = 221;
        score = 0;
        enemyFlag[0] = true;
        enemyFlag[1] = false;
        enemyY[0] = enemyY[1] = 0;

        generateEnemy(0);
        generateEnemy(1);
    }
    public void direction() {
        if(arrowLeft){
            if( carPos >= 150 ) {
                carPos -= 4 * unit_size;
                arrowLeft = false;
            }
        }
        if(arrowRight){
            if( carPos <= 379 ) {
                carPos += 4 * unit_size;
                arrowRight = false;
            }
        }
    }
    public void checkCollision(){
        if(collision() == 1){
            gameover = true;
        }
    }
    public boolean logic(){
        if(enemyY[0] == 80)
            if(!enemyFlag[1])
                enemyFlag[1] = true;

        if(enemyFlag[0])
            enemyY[0] += 20;

        if(enemyFlag[1])
            enemyY[1] += 20;

        if(enemyY[0] > HEIGHT - 80){
            enemyY[0] = 1;
            generateEnemy(0);
            score++;
            //eraseIndex = 0;
            return true;
        }
        if(enemyY[1] > HEIGHT - 80){
            enemyY[1] = 1;
            generateEnemy(1);
            score++;
            //eraseIndex = 1;
            return true;
        }
        return false;
    }

    public char[][] getCar() {
        return car;
    }
    public int getCarPos() {
        return carPos;
    }
    public boolean[] getEnemyFlag() {
        return enemyFlag;
    }
    public int[] getEnemyX() {
        return enemyX;
    }
    public int[] getEnemyY() {
        return enemyY;
    }
    public boolean getGameOver() {
        return gameover;
    }
    public int getScore() {
        return score;
    }
    public int getHeight() {
        return height;
    }
    public int getWidth() {
        return width;
    }
    public int getEraseIndex() {
        return eraseIndex;
    }
    public void setArrowLeft(boolean bool) { arrowLeft = bool; }
    public void setArrowRight(boolean bool) { arrowRight = bool; }
    public boolean getGameover() { return gameover; }
    public void setGameover(boolean bool) { gameover = bool; }
    public char[][] getCar2Darray() { return car; }
    public int getUnit_size() { return unit_size; }
}
