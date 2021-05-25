package com.grey.snake.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import com.grey.snake.input.KeyManager;
import com.grey.snake.input.MouseManager;
import com.grey.snake.states.Game;
import com.grey.snake.states.Lose;
import com.grey.snake.states.Menu;
import com.grey.snake.states.STATE;

public class Main implements Runnable {
	
	private int width, height;
	private String title;
	private boolean running = false;
	private Thread thread;
	private Window window;
	private BufferStrategy bs;
	private Graphics g;
	
	// handler
	private Handler handler;
	
	// states
	public STATE gameState = STATE.Menu;
	private Game game;
	private Menu menu;
	private Lose lose;
	
	// input
	public KeyManager keyManager;
	public MouseManager mouseManager;
	
	public Main(int width, int height, String title) {
		this.width = width;
		this.height = height;
		this.title = title;
		keyManager = new KeyManager();
		mouseManager = new MouseManager();
	}
	
	public void init() {
		window = new Window(width, height, title);
		window.getFrame().addKeyListener(keyManager);
		window.getFrame().addMouseListener(mouseManager);
		window.getFrame().addMouseMotionListener(mouseManager);
		window.getCanvas().addMouseListener(mouseManager);
		window.getCanvas().addMouseMotionListener(mouseManager);
		
		// handler
		handler = new Handler(this);
		
		// states
		game = new Game(handler);
		menu = new Menu(handler);
		lose = new Lose(handler);
	}
	
	public void tick() {
		keyManager.tick();
		handler.tick();
		
		if(gameState == STATE.Game) {
			game.tick();
		} else if(gameState == STATE.Menu) {
			menu.tick();
		} else if(gameState == STATE.Lose) {
			lose.tick();
		}
	}
	
	public void render() {
		bs = window.getCanvas().getBufferStrategy();
		if(bs == null) {
			window.getCanvas().createBufferStrategy(3);
			return;
		}
		g = bs.getDrawGraphics();
		/////////////////////////
		
		g.setColor(Color.black);
		g.fillRect(0, 0, width, height);
		
		handler.render(g);
		
		if(gameState == STATE.Game) {
			game.render(g);
		} else if(gameState == STATE.Menu) {
			menu.render(g);
		} else if(gameState == STATE.Lose) {
			lose.render(g);
		}
		
		/////////////////////////
		g.dispose();
		bs.show();
	}
	
	public synchronized void start() {
		if(running) return;
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	
	public synchronized void stop() {
		if(!running) return;
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void run() {
		init();
		
		int fps = 60;
		double timePerTick = 1000000000/fps;
		double delta = 0;
		long now;
		long lastTime = System.nanoTime();
		long timer = 0;
		int ticks = 0;
		int renders = 0;
		
		while(running) {
			now = System.nanoTime();
			delta += (now - lastTime)/timePerTick;
			timer += now - lastTime;
			lastTime = now;
			
			if(delta >= 1) {
				tick();
				ticks++;
				delta--;
			}
			render();
			renders++;
			
			if(timer >= 1000000000) {
				System.out.println("TICKS: " + ticks + " | FPS: " + renders);
				ticks = 0;
				renders = 0;
				timer = 0;
			}
		}
		stop();
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
}
