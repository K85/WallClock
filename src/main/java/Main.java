import javax.swing.*;

public class Main {


    public static void main(String[] args) {
        JFrame frame = new JFrame("WallClock");
        frame.add(new WallClock());
        frame.setSize(300, 300);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
