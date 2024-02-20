import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class MainMenu extends JFrame{

    public MainMenu() throws IOException {
        this.setTitle("Game app");
        this.setSize(new Dimension(600, 420));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setLocationRelativeTo(null);

        ImageIcon image = new ImageIcon();

        InputStream stream = Main.class.getResourceAsStream("logo.png");
        if (stream != null) {
            try {
                image = new ImageIcon(ImageIO.read(stream));
                this.setIconImage(image.getImage());
            } catch (IOException e) {
                throw new RuntimeException(e);
            } finally {
                stream.close();
            }
        }

        BufferedImage bufferedImage = ImageIO.read(Main.class.getResourceAsStream("snake.png"));
        Image imageIcon = bufferedImage.getScaledInstance(230, 170, Image.SCALE_DEFAULT);
        ImageIcon icon = new ImageIcon(imageIcon);

        JButton button1 = new JButton("Snake", icon);
        button1.setBounds(50, 75, 230,170);

        bufferedImage = ImageIO.read(Main.class.getResourceAsStream("car.png"));
        imageIcon = bufferedImage.getScaledInstance(300, 170, Image.SCALE_DEFAULT);
        icon = new ImageIcon(imageIcon);

        JButton button2 = new JButton("Car game", icon);
        button2.setBounds(300, 75, 230,170);

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new SnakeController();
                dispose();
            }
        });

        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CarController();
                dispose();
            }
        });

        JButton exitButton = new JButton("Exit");
        exitButton.setBounds(510, 350, 60,20);
        exitButton.addActionListener(e -> System.exit(0));

        JLabel titleLabel = new JLabel("Welcome to the game center!");
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        Font titleFont = new Font("Arial", Font.PLAIN,20);
        titleLabel.setFont(titleFont);
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBounds(80, 10, 400, 30);

        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setBounds(0, 0, 600, 420);
        layeredPane.add(titleLabel, Integer.valueOf(0));
        layeredPane.add(button1, Integer.valueOf(1));
        layeredPane.add(button2, Integer.valueOf(1));
        layeredPane.add(exitButton, Integer.valueOf(1));
        this.getContentPane().setBackground(new Color(18, 62, 66));
        this.getContentPane().add(layeredPane);

        this.setVisible(true);
    }
}
