package com.grey.snake.main;

import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;

public class Window {
	
	private JFrame frame;
	private Canvas canvas;
	
	public JFrame getFrame() {
		return frame;
	}

	public Canvas getCanvas() {
		return canvas;
	}

	public Window(int width, int height, String title) {
		frame = new JFrame(title);
		frame.setSize(new Dimension(width, height));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setVisible(true);
		frame.requestFocus();
		
		canvas = new Canvas();
		canvas.setSize(new Dimension(width, height));
		canvas.setFocusable(false);
		
		frame.add(canvas);
		frame.pack();
	}
	
}
