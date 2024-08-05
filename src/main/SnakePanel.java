package main;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.JPanel;
import javax.swing.Timer;

public class SnakePanel extends JPanel implements ActionListener, Game {
    static final int panelheight = 500;
    static final int panelwidth = 500;
    static final int unit_size = 20;
    static final int num_of_units = (panelheight * panelwidth) / (unit_size * unit_size);
 // hold x and y coordinates for body parts of the snake
    final int x[] = new int[num_of_units];
    final int y[] = new int[num_of_units];

    int snakelength = 5;
    int foodswallowed;//
    private char direction = 'D'; // Initialize with default direction

    int foodX;
    int foodY;

    boolean running = false;
    Random random;
    Timer timer;

    public SnakePanel() {
        random = new Random();
        this.setSize(panelwidth, panelheight);
        this.setBackground(Color.black);
        this.setFocusable(true);
        this.addKeyListener(new MyKey(this)); // Pass reference to SnakePanel
        playGame();
    }

    public char getDirection() {
        return direction;
    }

    public void setDirection(char direction) {
        this.direction = direction;
    }

    public void playGame() {
        addFood();
        running = true;
		timer = new Timer(130, this);
        timer.start();
    }

    public void addFood() {
        foodX = random.nextInt((int) (panelwidth / unit_size)) * unit_size;
        foodY = random.nextInt((int) (panelheight / unit_size)) * unit_size;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (running) {
            move();
            checkFood();
            checkHit();
        }
        repaint();
    }

    @Override
    public void move() {
        for (int i = snakelength; i > 0; i--) {
            x[i] = x[i - 1];
            y[i] = y[i - 1];
        }

        if (direction == 'L') {
            x[0] = x[0] - unit_size;
        } else if (direction == 'R') {
            x[0] = x[0] + unit_size;
        } else if (direction == 'U') {
            y[0] = y[0] - unit_size;
        } else {
            y[0] = y[0] + unit_size;
        }
    }

    @Override
    public void gameOver(Graphics graphics) {
        graphics.setColor(Color.white);//color for gameover
        graphics.setFont(new Font("Sans serif", Font.ROMAN_BASELINE, 50));
        FontMetrics metrics = getFontMetrics(graphics.getFont());
        graphics.drawString("Game Over", (panelwidth - metrics.stringWidth("Game Over")) / 2, panelheight / 2);

        graphics.setColor(Color.white);
        graphics.setFont(new Font("Sans serif", Font.ROMAN_BASELINE, 25));
        metrics = getFontMetrics(graphics.getFont());
        graphics.drawString("Score: " + foodswallowed, (panelwidth - metrics.stringWidth("Score: " + foodswallowed)) / 2, graphics.getFont().getSize());
    }

    @Override
    public void checkHit() {
        for (int i = snakelength; i > 0; i--) {
            if (x[0] == x[i] && y[0] == y[i]) {
                running = false;
            }
        }
        if (x[0] < 0 || x[0] > panelwidth || y[0] < 0 || y[0] > panelheight) {
            running = false;
        }

        if (!running) {
            timer.stop();
        }
    }

    public void draw(Graphics graphics) {
        if (running) {
            graphics.setColor(new Color(214, 00, 00));
            graphics.fillOval(foodX, foodY, unit_size, unit_size);

            graphics.setColor(Color.white);//headpart
            graphics.fillRect(x[0], y[0], unit_size, unit_size);

            for (int i = 1; i < snakelength; i++) {
                graphics.setColor(new Color(212, 100, 215));//snakebody
                graphics.fillRect(x[i], y[i], unit_size, unit_size);
            }

            graphics.setColor(Color.red);
            graphics.setFont(new Font("Sans serif", Font.ROMAN_BASELINE, 25));
            FontMetrics metrics = getFontMetrics(graphics.getFont());
            graphics.drawString("Score: " + foodswallowed, (panelwidth - metrics.stringWidth("Score: " + foodswallowed)) / 2, graphics.getFont().getSize());
        } else {
            gameOver(graphics);
        }
    }

    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        draw(graphics);
    }

    @Override
    public void checkFood() {
        if (x[0] == foodX && y[0] == foodY) {
            snakelength++;
            foodswallowed++;
            addFood();
        }
    }
}
