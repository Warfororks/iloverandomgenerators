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
import javafx.*;


import javafx.application.Application;
import javafx.embed.swing.JFXPanel;



public class Window extends JPanel implements Runnable, KeyListener{
	
	private static int WIDTH;
	private static int HEIGHT;
	private static boolean running = false;
	private static boolean start = false;
	private static int fps = 20;
	private static int maintime = 1000/ fps;
	private Floor floor;
	private Player p;
	private BufferedImage end;
	private File leftbron;
	private File rightbron;
	private boolean isJumping = false;
	private boolean num = false;
	private boolean isFalling = false;
	private int height;
	private Enemy[] blocks;
	Image schultz;
	public Window(int h, int w) {
		blocks = new Enemy[20];
		WIDTH = w;
		HEIGHT = h;
		leftbron = new File("src/lebron-player-left.png");
		rightbron = new File("src/lebron-player-right.png");
		try {
			schultz = ImageIO.read(new File("src/schwartz_meme.png"));
			schultz = schultz.getScaledInstance(200, 200, schultz.SCALE_DEFAULT);
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		p = new Player(600,h-160,40,40, rightbron);
		floor = new Floor(1, h-80, 800,w, new File("src/floor.png"));
		blocks[0] = new Enemy(1200, h-120, 40, 40, new File("src/block.png"));
		blocks[1] = new Enemy(1500, h-120, 40, 40, new File("src/block.png"));
		blocks[2] = new Enemy(1850, h-120, 40, 40, new File("src/block.png"));
		blocks[3] = new Enemy(2000, h-120, 40, 40, new File("src/block.png"));
		blocks[4] = new Enemy(2000, h-160, 40, 40, new File("src/block.png"));
		blocks[5] = new Enemy(2200, h-120, 40, 40, new File("src/block.png"));
		blocks[6] = new Enemy(2350, h-120, 40, 40, new File("src/block.png"));
		blocks[7] = new Enemy(2350, h-160, 40, 40, new File("src/block.png"));
		blocks[8] = new Enemy(2350, h-200, 40, 40, new File("src/block.png"));
		blocks[9] = new Enemy(2500, h-120, 40, 40, new File("src/block.png"));
		blocks[10] = new Enemy(2650, h-120, 40, 40, new File("src/block.png"));
		blocks[11] = new Enemy(2750, h-120, 40, 40, new File("src/block.png"));
		blocks[12] = new Enemy(2750, h-160, 40, 40, new File("src/block.png"));
		blocks[13] = new Enemy(2850, h-120, 40, 40, new File("src/block.png"));
		blocks[14] = new Enemy(2850, h-160, 40, 40, new File("src/block.png"));
		blocks[15] = new Enemy(2850, h-200, 40, 40, new File("src/block.png"));
		blocks[16] = new Enemy(3000, h-120, 40, 40, new File("src/block.png"));
		blocks[17] = new Enemy(3000, h-160, 40, 40, new File("src/block.png"));
		blocks[18] = new Enemy(3000, h-200, 40, 40, new File("src/block.png"));
		blocks[19] = new Enemy(3000, h-240, 40, 40, new File("src/block.png"));

		this.setBackground(Color.BLACK);
		floor.resizeImage(w+10, 100);
		p.resizeImage(80, 80);
		start();
	}

	@Override
	public void run() {
		
		
		while(running) {
			long startT = System.nanoTime();
			repaint();
			if(start) {
				for(int i = 0; i < 20; i++) {
					if(blocks[i] != null) blocks[i].setX(blocks[i].getX()-10);
				}
			}
			isCollisionWithBlock(p);
			updateJump();
			update();
			repaint();
			if(num == true) {
				for(int i = 0; i < 20; i++) {
					if(blocks[i] != null) blocks[i].setX(blocks[i].getX()-10);
				}
			}
			
			
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
	
	public void updateJump() {
		if(isJumping && height > 0) {
			p.setY(p.getY() - 10);
			height--;
		}
		else if(isJumping && height == 0) {
			isJumping = false;
			isFalling = true;
		}
		else if(isFalling && height < 30) {
			p.setY(p.getY() + 10);
			height++;
		}
		else if(isFalling && height == 30) {
			isFalling = false;
			height = 0;
		}
	}
	
	public void update() {
		if(p.getX() > blocks[19].getX()+blocks[19].getWidth()+200) {
			try {
				end = ImageIO.read(new File("src/youwin.png"));
				running = false;
			}
			catch (IOException e) {
				e.printStackTrace();
				System.out.println("end image not found");
			}
		}
	}
	
	
	public void start() {
		BufferedImage title;
		try {
			title = ImageIO.read(new File("src/escape_schwartz.png"));
			JLabel titleLabel = new JLabel(new ImageIcon(title));
			add(titleLabel);
			titleLabel.addKeyListener(this);
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
		g.drawImage(schultz, getWidth()/2, getHeight()/2, this);
		if(p != null) {
			g.drawImage(p.getImage(), (int)p.getX(), (int)p.getY(), this);
		}
		if(end != null) g.drawImage(end, 0, 0, this);
		g.drawImage(floor.getImage(), (int)floor.getX(), (int)floor.getY(), this);
		for(int i = 0; i < 20; i++) {
			if(blocks[i] != null) {
				g.drawImage(blocks[i].getImage(), (int)blocks[i].getX(), (int)blocks[i].getY(), this);
			}
		}
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		removeAll();
		if(num == false)
			num = true;
		start = true;
		int code = arg0.getKeyCode();
		switch(code) {
			case KeyEvent.VK_UP:
				if(!isCollisionWithWall(p)) {
				//	p.setY(p.getY() - 10);
					jump();
				}
				break;
			case KeyEvent.VK_LEFT:
				if(!isCollisionWithWall(p))
					p.setX(p.getX() - 10);
					p.setImage(leftbron);
				break;
			case KeyEvent.VK_RIGHT:
				if(!isCollisionWithWall(p))
				p.setX(p.getX() + 10);
				p.setImage(rightbron);
				break;
			case KeyEvent.VK_DOWN:
				if(!isCollisionWithWall(p))
					p.setY(p.getY() + 10);
				break;
		}
	}

	public boolean isCollisionWithWall(Player p) {
		boolean cantMove = false;
		if(p.getX() <= 100) {
			cantMove = true;
			endScreen();
			p.setX(p.getX() + 10);
		}
		else if((p.getX() + p.getWidth()) >= this.getParent().getWidth()-20) {
			cantMove = true;
			p.setX(p.getX() - 20);
			//System.out.println(this.getWidth());
		}
		else if(p.getY() == 0) {
			cantMove = true;
			p.setY(p.getY() + 10);
		}
		else if((p.getY() + p.getLength()) >= this.getParent().getHeight()-20) {
			cantMove = true;
			p.setY(p.getY() - 20);
			//System.out.println(this.getParent().getHeight());
		}
			
		return cantMove;
	}
	
	public boolean isCollisionWithBlock(Player p) {
		boolean cantMove = false;
		for(int i = 0; i < 20; i++) {
			if(blocks[i] == null) continue;
			if(blocks[i].getX() > 100 && blocks[i].getX() < 1220) {
				if(blocks[i].getX() > p.getX()-20 && blocks[i].getX() < p.getX()+p.getWidth()+30) {
					if(blocks[i].getY() <= p.getY()+p.getLength()) {
						p.setX(p.getX()-20);
						//p.setY(p.getY()+1);
						cantMove = true;
					}
				}
			} 
		}
		if(p.getX() <= 100) {
			cantMove = true;
			endScreen();
		}
		return cantMove;
	}
	
	
	
	@Override
	public void keyReleased(KeyEvent e) {
		
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
		
	}
	
	public void jump() {
		if(!notOkToJump()) {
			isJumping = true;
			height = 30;
		}
	}
	
	public boolean iscollisionWith() {
		boolean cantMove = false;
		
		return cantMove;
	}
	
	public boolean notOkToJump() {
		boolean cantMove = false;
//			for(int i=0; i < list.size(); i++) {
//				if(p.getY() - 20 < list.get(i).getY())
//					cantMove = true;
//			}
		return cantMove;
	}
	
	public void endScreen() {
		try {
			end = ImageIO.read(new File("src/gameover.png"));
			repaint();
			running = false;
		}
		catch (IOException e) {
			e.printStackTrace();
			System.out.println("end image not found");
		}
	}
}