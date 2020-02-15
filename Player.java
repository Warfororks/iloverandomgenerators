package main;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Player {
	private double x;
	private double y;
	private double length;
	private double width;
	private boolean isJumping = false;
	private int lives = 3;
	private Image img;
	
	
	public Player(double xval, double yval, double lenval, double widval, File file) {
		x = xval;
		y = yval;
		length = lenval;
		width = widval;
		try {
			img = ImageIO.read(file);
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void jump() {
		if(!isJumping) {
			
		}
	}
	
	public void moveRight() {
		//will have to check for isJumping.
		x++;
	}
	
	public void moveLeft() { //will have to check for isJumping.
		x--;
	}
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}
	
	public double getLength() {
		return length;
	}
	
	public double getWidth() {
		return width;
	}
	
	public Image getImage() {
		return img;
	}
	
	public void setX(double yeet) {
		x = yeet;
	}
	public void setY(double yeet) {
		y = yeet;
	}
	public void resizeImage(int w, int h) {
		img = img.getScaledInstance(w, h, img.SCALE_DEFAULT);
	}
}
