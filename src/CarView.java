import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;

public class CarView extends JPanel implements ActionListener {
    Timer timer;
    private final int width = 600;
    private final int height = 400;
    private boolean ifGameOver = true;
    private CarModel car;
    private CarController carC;

    public CarView(CarModel car, CarController carC){
        this.setPreferredSize(new Dimension(width, height));
        this.setBackground(Color.darkGray);
        this.setFocusable(true);
        this.addKeyListener(new CarView.MyKeyAdapter());
        this.car = car;
        this.carC = carC;

        play();
    }
    public void play(){
        car.setGameover(false);

        timer = new Timer(140, this);
        timer.start();
    }

    public void drawCar(Graphics graphics){
        if(!car.getGameOver()) {
            graphics.setColor(Color.ORANGE);
            graphics.fillRect(0, 0, 140, 400);
            graphics.fillRect(460, 0, 140, 400);

            graphics.setColor(Color.white);

            int drawingHeight = 280, unit_size = car.getUnit_size(), carPos = car.getCarPos();
            graphics.fillRect(car.getCarPos() + car.getUnit_size(), drawingHeight, car.getUnit_size(), car.getUnit_size());
            graphics.fillRect(car.getCarPos() + car.getUnit_size() + car.getUnit_size(), drawingHeight, car.getUnit_size(), car.getUnit_size());
            graphics.fillRect(car.getCarPos(), drawingHeight + car.getUnit_size(), car.getUnit_size(), car.getUnit_size());
            graphics.fillRect(car.getCarPos() + car.getUnit_size(), drawingHeight + car.getUnit_size(), car.getUnit_size(), car.getUnit_size());
            graphics.fillRect(car.getCarPos() + car.getUnit_size() + car.getUnit_size(), drawingHeight + car.getUnit_size(), car.getUnit_size(), car.getUnit_size());
            graphics.fillRect(car.getCarPos() + car.getUnit_size() + car.getUnit_size() + car.getUnit_size(), drawingHeight + car.getUnit_size(), car.getUnit_size(), car.getUnit_size());
            graphics.fillRect(car.getCarPos() + car.getUnit_size(), drawingHeight + car.getUnit_size() + car.getUnit_size(), car.getUnit_size(), car.getUnit_size());
            graphics.fillRect(car.getCarPos() + car.getUnit_size() + car.getUnit_size(), drawingHeight + car.getUnit_size() + car.getUnit_size(), car.getUnit_size(), car.getUnit_size());
            graphics.fillRect(car.getCarPos(), drawingHeight + car.getUnit_size() + car.getUnit_size() + car.getUnit_size(), car.getUnit_size(), car.getUnit_size());
            graphics.fillRect(car.getCarPos()  + car.getUnit_size(), drawingHeight + car.getUnit_size() + car.getUnit_size() + car.getUnit_size(), car.getUnit_size(), car.getUnit_size());
            graphics.fillRect(car.getCarPos()  + car.getUnit_size() + car.getUnit_size(), drawingHeight + car.getUnit_size() + car.getUnit_size() + car.getUnit_size(), car.getUnit_size(), car.getUnit_size());
            graphics.fillRect(car.getCarPos()  + car.getUnit_size() + car.getUnit_size() + car.getUnit_size(), drawingHeight + car.getUnit_size() + car.getUnit_size() + car.getUnit_size(), car.getUnit_size(), car.getUnit_size());

            graphics.setColor(Color.white);
            graphics.setFont(new Font("Sans serif", Font.ROMAN_BASELINE, 25));
            FontMetrics metrics = getFontMetrics(graphics.getFont());
            graphics.drawString("Score: " + car.getScore(), (width - metrics.stringWidth("Score: " + car.getScore())) / 2, graphics.getFont().getSize());

        } else {
            gameOver(graphics);
        }
    }

    public void drawEnemy(Graphics graphics, int idx){
        if(!car.getGameOver()) {
            graphics.setColor(Color.blue);

            if(car.getEnemyFlag()[idx]) {
                int us = car.getUnit_size(), x = car.getEnemyX()[idx], y = car.getEnemyY()[idx];
                graphics.fillRect(x, y, us, us);
                graphics.fillRect(x + us, y, us, us);
                graphics.fillRect(x + us + us, y, us, us);
                graphics.fillRect(x + us + us + us, y, us, us);
                graphics.fillRect(x + us, y + us, us, us);
                graphics.fillRect(x + us + us, y + us, us, us);
                graphics.fillRect(x, y + us + us, us, us);
                graphics.fillRect(x + us, y + us + us, us, us);
                graphics.fillRect(x + us + us, y + us + us, us, us);
                graphics.fillRect(x + us + us + us, y + us + us, us, us);
                graphics.fillRect(x + us, y + us + us + us, us, us);
                graphics.fillRect(x + us + us, y + us + us + us, us, us);
            }
        } else {
            gameOver(graphics);
        }
    }

    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        drawCar(graphics);
        drawEnemy(graphics, 0);

        drawEnemy(graphics, 1);

        car.logic();
        car.checkCollision();
    }

    public void gameOver(Graphics graphics) {
        graphics.setColor(Color.red);
        graphics.setFont(new Font("Sans serif", Font.ROMAN_BASELINE, 50));
        FontMetrics metrics = getFontMetrics(graphics.getFont());
        graphics.drawString("Game Over! :(", (width - metrics.stringWidth("Game Over! :(")) / 2, height / 2);

        graphics.setColor(Color.white);
        graphics.setFont(new Font("Sans serif", Font.ROMAN_BASELINE, 25));
        metrics = getFontMetrics(graphics.getFont());
        graphics.drawString("Score: " + car.getScore(), (width - metrics.stringWidth("Score: " + car.getScore())) / 2, graphics.getFont().getSize());

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
                    carC.dispose();
                    new CarController();
                }
            });
            menuButton.setBounds(490, 330, 80,20);
            menuButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        carC.dispose();
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
    public void actionPerformed(ActionEvent e) {
        if (!car.getGameOver()) {
            car.direction();
            car.logic();
            //car.checkCollision();
        } else timer.stop();
        repaint();
    }

    public class MyKeyAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            switch(e.getKeyCode()) {
                case KeyEvent.VK_LEFT:
                        car.setArrowLeft(true);
                        car.setArrowRight(false);
                    break;

                case KeyEvent.VK_RIGHT:
                        car.setArrowLeft(false);
                        car.setArrowRight(true);
                    break;
            }
        }
    }
}
