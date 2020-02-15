package main;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Enemy {
	private double x;
	private double y;
	private double length;
	private double width;
	private Image img;
	
	public Enemy(double xval, double yval, double lenval, double widval, File file) {
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
	
	public double getLength() {
		return length;
	}
	
	public double getWidth() {
		return width;
	}
	
	public double getX() {
		return x;
	}
	
	
	public double getY() {
		return y;
	}
	
	public Image getImage() {
		return img;
	}
	public void setX(double yeet) {
		x = yeet;
	}
}
