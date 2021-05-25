package com.grey.snake.states;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.Random;

import com.grey.snake.main.Handler;

public class Game {
	
	public static final int SIZE = 16;
	
	private Handler handler;
	private int timer = 0;
	
	int units;
	final int[] x;
	final int[] y;
	int bodyParts = 3;
	int score;
	int foodX, foodY;
	public static char direction = 'R';
	Random r = new Random();
	
	private int j = 0;
	
	public Game(Handler handler) {
		this.handler = handler;
		
		units = handler.getWidth()*handler.getHeight()/SIZE;
		y = new int[units];
		x = new int[units];
		
		score = 0;
		
		newFood();
	}
	
	public void tick() {
		move();
		checkCollisions();
		checkFood();
	}
	
	private void checkCollisions() {
		// check body collisions
		j++;
		if(j > 60) {
			for(int i = bodyParts; i > 0; i--) {
				if(x[0] == x[i] && y[0] == y[i]) {
					handler.setGameState(STATE.Lose);
				}
			}
		}
		// check head/left border collision
		if(x[0] < 0) {
			handler.setGameState(STATE.Lose);
		}
		// check head/right border collision
		if(x[0] > handler.getWidth()) {
			handler.setGameState(STATE.Lose);
		}
		// check head/top collision
		if(y[0] < 0) {
			handler.setGameState(STATE.Lose);
		}
		//check head/bottom collision
		if(y[0] > handler.getHeight()) {
			handler.setGameState(STATE.Lose);
		}
	}
	
	public void checkFood() {
		if(x[0] == foodX && y[0] == foodY) {
			bodyParts++;
			score++;
			newFood();
		}
	}
	
	private void newFood() {
		foodX = r.nextInt(handler.getWidth()/SIZE)*SIZE;
		foodY = r.nextInt(handler.getHeight()/SIZE)*SIZE;
	}
	
	private void move() {
		timer++;
		if(timer >= 10) {
			for(int i = bodyParts; i > 0; i--) {
				x[i] = x[i-1];
				y[i] = y[i-1];
			}
			
			switch(direction) {
			case 'U':
				y[0] = y[0] - SIZE;
				break;
			case 'D':
				y[0] = y[0] + SIZE;
				break;
			case 'L':
				x[0] = x[0] - SIZE;
				break;
			case 'R':
				x[0] = x[0] + SIZE;
				break;
			}
			timer = 0;
		}
	}
	
	public void render(Graphics g) {
		// lines
//		g.setColor(Color.darkGray);
//		for(int i = 0; i < handler.getHeight()/SIZE; i++) {
//			g.setColor(Color.darkGray);
//			g.drawLine(i*SIZE, 0, i*SIZE, handler.getHeight());
//			g.drawLine(0, i*SIZE, handler.getWidth(), i*SIZE);
//		}
		
		// score
		g.setColor(Color.red);
		g.setFont(new Font("serif", Font.BOLD, 25));
		g.drawString("SCORE: " + score, (handler.getWidth() - g.getFontMetrics().stringWidth("SCORE: " + score))/2, g.getFont().getSize());
		
		// food
		g.setColor(Color.yellow);
		g.fillOval(foodX, foodY, SIZE, SIZE);
		
		// body parts
		for(int i = 0; i < bodyParts; i++) {
				g.setColor(Color.green);
				g.fillRect(x[i], y[i], SIZE, SIZE);
		}
	}
	
}
