package com.thecherno.app.core;

import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import com.thecherno.app.events.Event;
import com.thecherno.app.events.types.MouseMovedEvent;
import com.thecherno.app.events.types.MousePressedEvent;
import com.thecherno.app.events.types.MouseReleasedEvent;
import com.thecherno.app.layers.Layer;

@SuppressWarnings("serial")
public class Window extends JFrame {
	
	private Screen screen;
	private List<Layer> layerStack = new ArrayList<Layer>();
	
	public Window(String name, int width, int height) {
		screen = new Screen(width, height);
		
		setTitle(name);
		add(screen);
		pack();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
		
		screen.addMouseListener(new MouseAdapter() {
			
			public void mouseReleased(MouseEvent e) {
				MouseReleasedEvent event = new MouseReleasedEvent(e.getButton(), e.getX(), e.getY());
				onEvent(event);
			}
			
			public void mousePressed(MouseEvent e) {
				MousePressedEvent event = new MousePressedEvent(e.getButton(), e.getX(), e.getY());
				onEvent(event);
			}

		});
		
		screen.addMouseMotionListener(new MouseMotionListener() {
			
			public void mouseMoved(MouseEvent e) {
				MouseMovedEvent event = new MouseMovedEvent(e.getX(), e.getY(), false);
				onEvent(event);
			}
			
			public void mouseDragged(MouseEvent e) {
				MouseMovedEvent event = new MouseMovedEvent(e.getX(), e.getY(), true);
				onEvent(event);
			}
		});
		
		screen.init();
		run();
	}
	
	private void run() {
		screen.beginRendering();
		screen.clear();
		onRender(screen.getGraphicsObject());
		screen.endRendering();
		try {
			Thread.sleep(3);
		} catch (InterruptedException e) {
		}
		
		SwingUtilities.invokeLater(() -> run());
	}
	
	public void onEvent(Event event) {
		for (int i = layerStack.size() - 1; i >= 0; i--)
			layerStack.get(i).onEvent(event);
	}
	
	private void onRender(Graphics g) {
		for (int i = 0; i < layerStack.size(); i++)
			layerStack.get(i).onRender(g);
	}
	
	public void addLayer(Layer layer) {
		System.out.println(layer);
		layerStack.add(layer);
	}
	
	
}
