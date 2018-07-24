package com.snake;

//import java.util.Arrays;

public class Grid {
	public final boolean status[][];
	private final int width;
	private final int height;
	
	private Snake snake;
	private Node food;
	private Direction snakeDirection = Direction.LEFT;
	
	public Grid (int width, int height) {
		this.width = width;
		this.height = height;
		
		status = new boolean[width/Settings.DEFAULT_NODE_SIZE][height/Settings.DEFAULT_NODE_SIZE];
//		for (int i=0; i<width; i++) {
//			Arrays.fill(status[width], false);
//		}
		
		initSnake();
		createFood();
	}
	
	private Snake initSnake() {	
		int x = width/2;
		int y = height/2;
		int length = 0;
		int maxLength = width/3;
		
		snake = new Snake();
		
		while (length < maxLength) {
			Node node = new Node(x+length, y);
			snake.addTail(node);
			status[(x+length)/Settings.DEFAULT_NODE_SIZE][y/Settings.DEFAULT_NODE_SIZE] = true;
			length += Settings.DEFAULT_NODE_SIZE;
		}
		
		return snake;
	}
	
	private Node createFood() {
		int x, y;
		do {
			x = (int) (width*Math.random()/Settings.DEFAULT_NODE_SIZE);
			y = (int) (height*Math.random()/Settings.DEFAULT_NODE_SIZE);
		} while (status[x][y]);
		
		food = new Node(x*Settings.DEFAULT_NODE_SIZE, y*Settings.DEFAULT_NODE_SIZE);
		return food;
	}
	
	public boolean nextRound() {
		Node head, tail;
		
		tail = snake.move(snakeDirection);
		if (null == tail) {
			return false;
		}
		
		head = snake.getHead();
		if (isNewHeadAvailable(head)) {
			if (head.isEqual(food)) {
				snake.addTail(tail);
				createFood();
			}
			else {
				status[tail.getX()/Settings.DEFAULT_NODE_SIZE][tail.getY()/Settings.DEFAULT_NODE_SIZE] = false;
			}
			status[head.getX()/Settings.DEFAULT_NODE_SIZE][head.getY()/Settings.DEFAULT_NODE_SIZE] = true;
			return true;
		}
		else {
			return false;
		}
	}
	
	public void changeDirection(Direction newDirection) {
		if (snakeDirection.compatibleWith(newDirection)) {
	        snakeDirection = newDirection;
	    }
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	public Snake getSnake() {
		return snake;
	}
	
	public Node getFood() {
		return food;
	}
	
	private boolean isNewHeadAvailable(Node head) {
		int x = head.getX();
		int y = head.getY();
		
		if (x>(width-Settings.DEFAULT_NODE_SIZE) || x<0 || y>(height-Settings.DEFAULT_NODE_SIZE) || y<0) {
			return false;
		}
		
		if (status[x/Settings.DEFAULT_NODE_SIZE][y/Settings.DEFAULT_NODE_SIZE]) {
			return false;
		}
		
		return true;
	}

}
