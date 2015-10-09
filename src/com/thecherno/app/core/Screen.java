package com.thecherno.app.core;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

@SuppressWarnings("serial")
public class Screen extends Canvas {
	
	private BufferStrategy bs;
	private Graphics g;
	
	public Screen(int width, int height) {
		setPreferredSize(new Dimension(width, height));
	}
	
	public void init() {
		createBufferStrategy(3);
	}
	
	public void beginRendering() {
		bs = getBufferStrategy();
		g = bs.getDrawGraphics();
	}
	
	public void clear() {
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, getWidth(), getHeight());
	}
	
	public Graphics getGraphicsObject() {
		return g;
	}
	
	public void endRendering() {
		g.dispose();
		bs.show();
	}

}
