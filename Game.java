package main;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.LayoutManager;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;
public class Game extends JFrame{

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		Window w = new Window(400, 400);
		frame.addKeyListener(w);
		frame.add(w, BorderLayout.CENTER);
		frame.setSize(400, 400);
		frame.setTitle("Schwartz");
		frame.setVisible(true);
		//frame.pack();
		
	}

}
