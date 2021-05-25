package com.grey.snake.states;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;

import com.grey.snake.main.Handler;

public class Lose {
	
	private Handler handler;
	private Color buttonColor = Color.gray;
	private Rectangle startButtonBounds;
	
	public Lose(Handler handler) {
		this.handler = handler;
	}
	
	public void tick() {
		if(startButtonBounds.intersects(handler.getMouseManager().getBounds())) {
			buttonColor = Color.darkGray;
		} else {
			buttonColor = Color.gray;
		}
		
		if(startButtonBounds.intersects(handler.getMouseManager().getBounds()) && handler.getMouseManager().leftPressed) {
			System.exit(1);
		}
	}
	
	public void render(Graphics g) {
		g.setColor(Color.white);
		g.setFont(new Font("serif", Font.BOLD, 75));
		g.drawString("YOU LOSE", (handler.getWidth() - g.getFontMetrics().stringWidth("YOU LOSE"))/2, 200);
		g.setFont(new Font("serif", Font.BOLD, 50));
		g.drawRect(256, 415 - g.getFontMetrics().getHeight(), g.getFontMetrics().stringWidth("QUIT"), g.getFontMetrics().getHeight());
		g.setColor(buttonColor);
		g.fillRect(256, 415 - g.getFontMetrics().getHeight(), g.getFontMetrics().stringWidth("QUIT"), g.getFontMetrics().getHeight());
		g.setColor(Color.white);
		g.drawString("QUIT", (handler.getWidth() - g.getFontMetrics().stringWidth("QUIT"))/2, 400);
		startButtonBounds = new Rectangle(236, 415 - g.getFontMetrics().getHeight(), g.getFontMetrics().stringWidth("START"), g.getFontMetrics().getHeight());
	}
	
}
