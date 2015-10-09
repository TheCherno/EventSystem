package com.thecherno.app;

import java.awt.Color;

import com.thecherno.app.core.Window;

public class Main {
	
	public static void main(String[] args) {
		Window window = new Window("Events", 640, 360);
		window.addLayer(new ExampleLayer("Bottom", new Color(0x2233CC)));
		window.addLayer(new ExampleLayer("Top", new Color(0xCC2233)));
	}
	
}
