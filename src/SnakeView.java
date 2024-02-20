import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;

public class SnakeView extends JPanel implements ActionListener {
    Timer timer;
    private final int width = 600;
    private final int height = 400;
    private SnakeModel snake;
    private SnakeController snakeC;
    private boolean ifGameOver = true;

    public SnakeView(SnakeModel s, SnakeController sc) {
        this.setPreferredSize(new Dimension(width, height));
        this.setBackground(Color.DARK_GRAY);
        this.setFocusable(true);
        this.addKeyListener(new MyKeyAdapter());
        this.snake = s;
        this.snakeC = sc;
        play();
    }

    public void play(){
        snake.setGameOver(false);

        timer = new Timer(100, this);
        timer.start();
    }

    public void draw(Graphics graphics) throws IOException, InterruptedException {
        if(!snake.getGameOver()) {
            graphics.setColor(new Color(210, 115, 90));
            graphics.fillOval(snake.getfruitX(), snake.getFruitY(), snake.getUnit_size(), snake.getUnit_size());

            graphics.setColor(Color.white);
            graphics.fillRect(snake.getTailX()[0], snake.getTailY()[0], snake.getUnit_size(), snake.getUnit_size());

            for (int i = 1; i < snake.getTailLength(); i++) {
                graphics.setColor(new Color(40, 200, 150));
                graphics.fillRect(snake.getTailX()[i], snake.getTailY()[i], snake.getUnit_size(), snake.getUnit_size());
            }

            int score = snake.getTailLength() - 1;
            graphics.setColor(Color.white);
            graphics.setFont(new Font("Sans serif", Font.ROMAN_BASELINE, 25));
            FontMetrics metrics = getFontMetrics(graphics.getFont());
            graphics.drawString("Score: " + score, (width - metrics.stringWidth("Score: " + score)) / 2, graphics.getFont().getSize());

        } else {
            gameOver(graphics);
        }
    }

    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        try {
            draw(graphics);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void gameOver(Graphics graphics) throws IOException, InterruptedException {
        graphics.setColor(Color.red);
        graphics.setFont(new Font("Sans serif", Font.ROMAN_BASELINE, 50));
        FontMetrics metrics = getFontMetrics(graphics.getFont());
        graphics.drawString("Game Over! :(", (width - metrics.stringWidth("Game Over! :(")) / 2, height / 2);

        graphics.setColor(Color.white);
        graphics.setFont(new Font("Sans serif", Font.ROMAN_BASELINE, 25));
        metrics = getFontMetrics(graphics.getFont());
        graphics.drawString("Score: " + snake.getTailLength(), (width - metrics.stringWidth("Score: " + snake.getTailLength())) / 2, graphics.getFont().getSize());

        if(ifGameOver) {
            System.out.println(1);
            ifGameOver = false;
            JButton restartButton = new JButton("Restart");
            JButton menuButton = new JButton("Menu");
            setLayout(null);
            restartButton.setBounds(400, 330, 80,20);
            restartButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    snakeC.dispose();
                    new SnakeController();
                }
            });
            menuButton.setBounds(490, 330, 80,20);
            menuButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        snakeC.dispose();
                        new MainMenu();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            });
            add(restartButton);
            add(menuButton);
            this.revalidate();
            this.repaint();
        }
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        if (!snake.getGameOver()) {
            snake.logic();
        } else timer.stop();
        repaint();
    }

    public class MyKeyAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            switch(e.getKeyCode()) {
                case KeyEvent.VK_LEFT:
                    if(!snake.getArrowRight()) {
                        snake.setArrowUp(false);
                        snake.setArrowDown(false);
                        snake.setArrowLeft(true);
                        snake.setArrowRight(false);
                    }
                    break;

                case KeyEvent.VK_RIGHT:
                    if(!snake.getArrowLeft()) {
                        snake.setArrowUp(false);
                        snake.setArrowDown(false);
                        snake.setArrowLeft(false);
                        snake.setArrowRight(true);
                    }
                    break;

                case KeyEvent.VK_UP:
                    if(!snake.getArrowDown()) {
                        snake.setArrowUp(true);
                        snake.setArrowDown(false);
                        snake.setArrowLeft(false);
                        snake.setArrowRight(false);
                    }
                    break;

                case KeyEvent.VK_DOWN:
                    if(!snake.getArrowUp()) {
                        snake.setArrowUp(false);
                        snake.setArrowDown(true);
                        snake.setArrowLeft(false);
                        snake.setArrowRight(false);
                    }
                    break;
            }
        }
    }
}
