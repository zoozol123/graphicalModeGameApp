import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CarController extends JFrame {
    CarModel car;

    public CarController(){
        this.car = new CarModel();
        this.setTitle("Car");
        this.setSize(new Dimension(600, 400));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setLocationRelativeTo(null);

        this.car.setup();
        CarView carView = new CarView(car, this);
        this.add(carView);
    }
}
