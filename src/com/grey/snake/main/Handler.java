package com.grey.snake.main;

import java.awt.Graphics;

import com.grey.snake.input.KeyManager;
import com.grey.snake.input.MouseManager;
import com.grey.snake.states.STATE;

public class Handler {
	
	private Main main;
	
	public Handler(Main main) {
		this.main = main;
	}
	
	public void setGameState(STATE gameState) {
		main.gameState = gameState;
	}
	
	public int getWidth() {
		return main.getWidth();
	}
	
	public int getHeight() {
		return main.getHeight();
	}
	
	public KeyManager getKeyManager() {
		return main.keyManager;
	}
	
	public MouseManager getMouseManager() {
		return main.mouseManager;
	}
	
	public void tick() {
		
	}
	
	public void render(Graphics g) {
		
	}
	
}
