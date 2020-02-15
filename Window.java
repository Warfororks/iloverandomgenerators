package main;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.*;
public class Window extends JPanel implements Runnable{
	
	private static int WIDTH;
	private static int HEIGHT;
	private static boolean running = false;
	private static int fps = 60;
	private static int maintime = 1000/ fps;
	
	
	public Window(int h, int w) {
		WIDTH = w;
		HEIGHT = h;
		this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		start();
	}

	@Override
	public void run() {
		while(running) {
			long startT = System.nanoTime();
			//update();
			//render();
			update();
			repaint();
			long endT = System.nanoTime() - startT;
			long wait = maintime - endT / 1000000;
			
			//when game ends, set running to false to break from the loop.
			if(wait < 10) {
				wait = 10;
			}
			
			try {
				Thread.sleep(wait);
			} 
			catch (InterruptedException e) {
				e.printStackTrace();
			}
		
			
		}
	}
	
	
	public void update() {
	}
	
	
	public void start() {
		Thread n = new Thread(this);
		running = true;
		n.start();
	}
	
	
	public void keyListener() { //set key listening for the game
		
	}
	
	@Override
	public void paintComponent(Graphics g) { 
		//render using Graphics context here.
		//need to draw image and stuff.
		super.paintComponent(g);
	}
}