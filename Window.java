package main;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
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
		 p = new Player(0,0,10,10, new File("src/basketball.png"));
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
		BufferedImage title;
		try {
			title = ImageIO.read(new File("src/titlebig.png"));
			JLabel titleLabel = new JLabel(new ImageIcon(title));
			add(titleLabel);
			titleLabel.addKeyListener(this);
			//titleLabel.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("title image not found");
		}
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
			g.drawImage(p.getImage(), 0, 0, this);
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		removeAll();
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