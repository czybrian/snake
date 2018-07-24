package com.snake;

public class Node {
	private final int x;
	private final int y;
	
	public Node(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public boolean isEqual(Node node) {
		if ((this.x == node.getX()) && (this.y == node.getY())) {
			return true;
		}
		return false;
	}
}
