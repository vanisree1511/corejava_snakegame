package main;

import java.awt.Dimension;

import javax.swing.JFrame;

public class GameFrame extends JFrame {

	public static void Frame() {
		GameFrame gm = new GameFrame();
		SnakePanel gp = new SnakePanel();

		gm.setTitle("Snake Game");
		gm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gm.setResizable(false);

		gm.add(gp); // Add the panel to the frame

		gp.setPreferredSize(new Dimension(SnakePanel.panelwidth, SnakePanel.panelheight));

		gm.pack();
		gm.setVisible(true);
		gm.setLocationRelativeTo(null); // Center the frame
	}
}
