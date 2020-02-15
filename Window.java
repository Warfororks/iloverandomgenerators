package main;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;
public class Window extends JPanel implements Runnable, KeyListener{
	
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

	
	@Override
	public void paintComponent(Graphics g) { 
		//render using Graphics context here.
		//need to draw image and stuff.
		super.paintComponent(g);
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		int code = arg0.getKeyCode();
		switch(code) {
			case KeyEvent.VK_UP:
				//move up
				System.out.println("move up");
				break;
			case KeyEvent.VK_LEFT:
				//move left
				System.out.println("move left");
				break;
			case KeyEvent.VK_RIGHT:
				//move right
				System.out.println("move right");
				break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
		
	}
}