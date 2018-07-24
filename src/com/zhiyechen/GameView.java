package com.snake;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

public class GameView {
	private Grid grid;
	private JPanel canvas;
	
	public void init() {
		canvas = new JPanel() {
			public void paintComponent(Graphics graphics) {
                drawGridBackground(graphics);
                drawSnake(graphics, grid.getSnake());
                drawFood(graphics, grid.getFood());
			}
		};
	}
	
	public GameView (Grid grid) {
		this.grid = grid;
	}
	
	public void draw() {
		canvas.repaint();
	}
	
	public JPanel getCanvas() {
		return canvas;
	}
	
	public void drawSnake(Graphics graphics, Snake snake) {
		LinkedList<Node> body = snake.getBody();
		for (Node node : body) {			
			drawSquare(graphics, node, Color.white);		
		}
	}
	
	public void drawFood(Graphics graphics, Node squareArea) {
		drawCircle(graphics, squareArea, Color.white);
	}
	
	public void drawGridBackground(Graphics graphics) {
		graphics.setColor(Color.black);
		graphics.fillRect(0, 0, grid.getWidth(), grid.getHeight());
	}
	
	private void drawSquare(Graphics graphics, Node squareArea, Color color) {
        graphics.setColor(color);
        int size = Settings.DEFAULT_NODE_SIZE;
        graphics.fillRect(squareArea.getX(), squareArea.getY(), size-1, size-1);
    }


    private void drawCircle(Graphics graphics, Node squareArea, Color color) {
        graphics.setColor(color);
        int size = Settings.DEFAULT_NODE_SIZE;
        graphics.fillOval(squareArea.getX(), squareArea.getY(), size, size);
    }

}
