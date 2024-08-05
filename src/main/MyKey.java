package main;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class MyKey extends KeyAdapter {
    private SnakePanel snakePanel;

    public MyKey(SnakePanel snakePanel) {
        this.snakePanel = snakePanel;
    }

   
    public void keyPressed(KeyEvent e) {
        char currentDirection = snakePanel.getDirection();
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                if (currentDirection != 'R') {
                    snakePanel.setDirection('L');
                }
                break;

            case KeyEvent.VK_RIGHT:
                if (currentDirection != 'L') {
                    snakePanel.setDirection('R');
                }
                break;

            case KeyEvent.VK_UP:
                if (currentDirection != 'D') {
                    snakePanel.setDirection('U');
                }
                break;

            case KeyEvent.VK_DOWN:
                if (currentDirection != 'U') {
                    snakePanel.setDirection('D');
                }
                break;
        }
    }
}
