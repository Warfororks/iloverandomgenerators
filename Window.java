package main;
import java.awt.Color;
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
	private Player p;

	
	public Window(int h, int w) {
		WIDTH = w;
		HEIGHT = h;
		this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		 p = new Player(10,10,40,40, new File("C:\\Users\\zohai\\new_workspace\\Schwartz\\src\\main\\basketball.png"));
		this.setBackground(Color.BLACK);
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
		super.paintComponent(g);
		//render using Graphics context here.
		//need to draw image and stuff.
		if(p != null)
			g.drawImage(p.getImage(), (int)p.getX(), (int)p.getY(), this);
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		int code = arg0.getKeyCode();
		switch(code) {
			case KeyEvent.VK_UP:
				if(!isCollisionWithWall(p))
					p.setY(p.getY() - 10);
			
				//move up
				//System.out.println("move up");
				break;
			case KeyEvent.VK_LEFT:
				if(!isCollisionWithWall(p))
					p.setX(p.getX() - 10);
				//move left
				//System.out.println("move left");
				break;
			case KeyEvent.VK_RIGHT:
				if(!isCollisionWithWall(p))
				p.setX(p.getX() + 10);
				//move right
				//System.out.println("move right");
				break;
			case KeyEvent.VK_DOWN:
				if(!isCollisionWithWall(p))
				p.setY(p.getY() + 10);
				break;
		}
	}

	public boolean isCollisionWithWall(Player p) {
			boolean cantMove = false;
			if(p.getX() == 0) {
				cantMove = true;
				p.setX(p.getX() + 10);
			}
			else if((p.getX() + p.getWidth()) == this.getParent().getWidth()) {
				cantMove = true;
				p.setX(p.getX() - 10);
				//System.out.println(this.getWidth());
			}
			else if(p.getY() == 0) {
				cantMove = true;
				p.setY(p.getY() + 10);
			}
			else if((p.getY() + p.getLength()) == this.getParent().getHeight()) {
				cantMove = true;
				p.setY(p.getY() - 10);
				System.out.println(this.getParent().getHeight());
			}
				
			return cantMove;
	}
	
	
	@Override
	public void keyReleased(KeyEvent e) {
		
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
		
	}
}