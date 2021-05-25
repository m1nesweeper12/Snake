package com.grey.snake.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import com.grey.snake.states.Game;

public class KeyManager implements KeyListener {
	
	private boolean[] keys = new boolean[256];
	
	public void keyTyped(KeyEvent e) {
		
	}

	public void keyPressed(KeyEvent e) {
		keys[e.getKeyCode()] = true;
	}

	public void keyReleased(KeyEvent e) {
		keys[e.getKeyCode()] = false;
	}
	
	public void tick() {
		if(keys[KeyEvent.VK_W] || keys[KeyEvent.VK_UP]) {
			if(Game.direction != 'D') Game.direction = 'U';
		}
		if(keys[KeyEvent.VK_S] || keys[KeyEvent.VK_DOWN]) {
			if(Game.direction != 'U') Game.direction = 'D';
		}
		if(keys[KeyEvent.VK_A] || keys[KeyEvent.VK_LEFT]) {
			if(Game.direction != 'R') Game.direction = 'L';
		}
		if(keys[KeyEvent.VK_D] || keys[KeyEvent.VK_RIGHT]) {
			if(Game.direction != 'L') Game.direction = 'R';
		}
	}
	
}
