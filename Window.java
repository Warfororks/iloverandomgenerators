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
	private Player p, enemy;
	private Floor floor;
	
	public Window(int h, int w) {
		WIDTH = w;
		HEIGHT = h;
		this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		 p = new Player(0,0,40,40, new File("src/lebron-player-right.png"));
		 floor = new Floor(1, h-80, 800,w, new File("src/floor.png"));
		this.setBackground(Color.BLACK);
		floor.resizeImage(w+10, 100);
		p.resizeImage(80, 80);
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
			title = ImageIO.read(new File("src/menu.png"));
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
		if(p != null) {
			g.drawImage(p.getImage(), (int)p.getX(), (int)p.getY(), this);
			
		}
			g.drawImage(floor.getImage(), (int)floor.getX(), (int)floor.getY(), this);
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		removeAll();
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
			else if((p.getX() + p.getWidth()) >= getWidth()-10) {
				cantMove = true;
				p.setX(p.getX() - 10);
				//System.out.println(this.getWidth());
			}
			else if(p.getY() == 0) {
				cantMove = true;
				p.setY(p.getY() + 10);
			}
			else if((p.getY() + p.getLength()) >= getHeight()-10) {
				cantMove = true;
				p.setY(p.getY() - 10);
				System.out.println(getHeight());
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