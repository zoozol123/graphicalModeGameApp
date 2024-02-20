import javax.swing.*;
import java.awt.*;

public class SnakeController extends JFrame {
    private SnakeModel snake;

    public SnakeController(){
        this.snake = new SnakeModel();
        this.setTitle("Snake");
        this.setSize(new Dimension(600, 400));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setLocationRelativeTo(null);

        this.snake.setup();
        SnakeView snakeView = new SnakeView(snake, this);
        this.add(snakeView);
    }
}
