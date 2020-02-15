package main;

import java.awt.Image;
import java.io.File;

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
}
