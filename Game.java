package main;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.LayoutManager;

import javax.swing.*;
public class Game extends JFrame{

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		frame.add(new Window(400, 400), BorderLayout.CENTER);
		frame.setSize(400, 400);
		frame.setTitle("Schwartz");
		frame.setVisible(true);
		//frame.pack();
		
	}

}
