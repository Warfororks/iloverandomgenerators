package steelhack2020;

import javax.swing.*;
import java.awt.*;
class gui {
	public static void main(String[] args) {
		JFrame frame = new JFrame("basketball game");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(800, 400);
		JButton start = new JButton("Start");
		JButton quit = new JButton("Quit");
		frame.getContentPane().add(BorderLayout.CENTER, start);
		frame.getContentPane().add(BorderLayout.SOUTH, quit);
		frame.setVisible(true);
		
		
	}
}