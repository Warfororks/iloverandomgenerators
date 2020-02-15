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
	private Floor floor;
	private Player p;
	private BufferedImage end;
	private Enemy b1;
	private File leftbron;
	private File rightbron;
	private boolean isJumping = false;
	private boolean isFalling = false;
	private int height;
	
	
	public Window(int h, int w) {
		WIDTH = w;
		HEIGHT = h;
		leftbron = new File("src/lebron-player-left.png");
		rightbron = new File("src/lebron-player-right.png");
		
;		this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		p = new Player(600,300,40,40, rightbron);
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
			//System.out.println(isJumping);
//			
			updateJump();
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
	
	
	public void updateJump() {
		if(isJumping && height > 0) {
			p.setY(p.getY() - 10);
			height--;
		}
		else if(isJumping && height == 0) {
			isJumping = false;
			isFalling = true;
		}
		else if(isFalling && height < 31) {
			p.setY(p.getY() + 10);
			height++;
		}
		else if(isFalling && height == 31) {
			isFalling = false;
			height = 0;
		}
	
	}
	
	
	public void start() {
		BufferedImage title;
		try {
			title = ImageIO.read(new File("src/menu.png"));
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
		if(p != null) {
			g.drawImage(p.getImage(), (int)p.getX(), (int)p.getY(), this);
		}
			g.drawImage(floor.getImage(), (int)floor.getX(), (int)floor.getY(), this);
		if(end != null)
			g.drawImage(end, 0, 0, this);
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		removeAll();
		int code = arg0.getKeyCode();
		switch(code) {
			case KeyEvent.VK_UP:
				if(!isCollisionWithWall(p)) {
					p.setY(p.getY() - 10);
					jump();
				}
				//move up
				//System.out.println("move up");
				break;
			case KeyEvent.VK_LEFT:
				if(!isCollisionWithWall(p))
					p.setX(p.getX() - 10);
					p.setImage(leftbron);
					
				//move left
				//System.out.println("move left");
				break;
			case KeyEvent.VK_RIGHT:
				if(!isCollisionWithWall(p))
				p.setX(p.getX() + 10);
				p.setImage(rightbron);
		
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
		if(p.getX() == 100) {
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
			running = false;
		}
		catch (IOException e) {
			e.printStackTrace();
			System.out.println("end image not found");
		}
	}
}