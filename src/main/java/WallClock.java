import lombok.extern.slf4j.Slf4j;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;
import java.util.concurrent.TimeUnit;

@Slf4j
public class WallClock extends JPanel {
    /* Constants */
    private static final int X_OFFSET = 40;
    private static final int Y_OFFSET = 30;
    private static final int RADIUS = 100;
    private static final int HOUR_HAND_LENGTH = RADIUS - 40;
    private static final int MINUTE_HAND_LENGTH = RADIUS - 20;
    private static final int SECOND_HAND_LENGTH = RADIUS - 5;
    private static final int NUMBER_OFFSET = 5;
    /* Instance Variables */
    private int hour;
    private int minute;
    private int second;

    public void update(int hour, int minute, int second) {
        /* Update Properties */
        this.hour = hour;
        this.minute = minute;
        this.second = second;
        log.debug("update: hour={}, minute={}, second={}", hour, minute, second);
        this.repaint();
    }


    public WallClock() {
        // Register Timer for Updating.
        TimerManager.executor.scheduleAtFixedRate(new WallClockTimer(this), 0, 1, TimeUnit.SECONDS);
    }


    @Override
    protected void paintComponent(Graphics g) {
        // Clear previous drawing.
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        // Draw the clock face.
        g2.setFont(new Font("Arial", Font.BOLD, 20));
        g2.setColor(Color.BLUE);
        g2.drawOval(X_OFFSET, Y_OFFSET, RADIUS * 2, RADIUS * 2);
        g2.setColor(Color.BLACK);
        g2.drawString("12", X_OFFSET + RADIUS - NUMBER_OFFSET, Y_OFFSET + 4 * NUMBER_OFFSET);
        g2.drawString("6", X_OFFSET + RADIUS - NUMBER_OFFSET, Y_OFFSET + 2 * RADIUS - 2 * NUMBER_OFFSET);
        g2.drawString("3", X_OFFSET + 2 * RADIUS - 4 * NUMBER_OFFSET, Y_OFFSET + RADIUS);
        g2.drawString("9", X_OFFSET + 2 * NUMBER_OFFSET, Y_OFFSET + RADIUS);

        // Draw hands.
        g2.setColor(Color.BLUE);
        Line2D hourHand = new Line2D.Double(X_OFFSET + RADIUS, Y_OFFSET + RADIUS, X_OFFSET + RADIUS, Y_OFFSET + RADIUS - HOUR_HAND_LENGTH);
        drawRotatedLine(hourHand, (360 / 12) * hour, g2);
        Line2D minuteHand = new Line2D.Double(X_OFFSET + RADIUS, Y_OFFSET + RADIUS, X_OFFSET + RADIUS, Y_OFFSET + RADIUS - MINUTE_HAND_LENGTH);
        drawRotatedLine(minuteHand, (360 / 60) * minute, g2);
        Line2D secondHand = new Line2D.Double(X_OFFSET + RADIUS, Y_OFFSET + RADIUS, X_OFFSET + RADIUS, Y_OFFSET + RADIUS - SECOND_HAND_LENGTH);
        drawRotatedLine(secondHand, (360 / 60) * second, g2);
    }

    protected void drawRotatedLine(Line2D line, int rotateDegree, Graphics2D g2) {
        AffineTransform at = AffineTransform.getRotateInstance(
                Math.toRadians(rotateDegree), line.getX1(), line.getY1());
        g2.draw(at.createTransformedShape(line));
    }
}
