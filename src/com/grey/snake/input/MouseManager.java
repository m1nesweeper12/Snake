package com.grey.snake.input;

import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseManager implements MouseListener, MouseMotionListener {
	
	public int mouseX, mouseY;
	public boolean leftPressed, rightPressed;
	
	public void mouseDragged(MouseEvent e) {
		
	}

	public void mouseMoved(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();
	}

	public void mouseClicked(MouseEvent e) {
		
	}

	public void mousePressed(MouseEvent e) {
		if(e.getButton() == MouseEvent.BUTTON1) leftPressed = true;
		if(e.getButton() == MouseEvent.BUTTON3) rightPressed = true;
	}

	public void mouseReleased(MouseEvent e) {
		if(e.getButton() == MouseEvent.BUTTON1) leftPressed = false;
		if(e.getButton() == MouseEvent.BUTTON3) rightPressed = false;
	}
	
	public Rectangle getBounds() {
		return new Rectangle(mouseX, mouseY, 1, 1);
	}

	public void mouseEntered(MouseEvent e) {
		
	}

	public void mouseExited(MouseEvent e) {
		
	}

}
