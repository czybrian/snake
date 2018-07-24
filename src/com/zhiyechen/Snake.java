package com.snake;

import java.util.LinkedList;

public class Snake {
	private LinkedList<Node> body = new LinkedList<>();
	
	public Node eat (Node food) {
		if (isNeighbor(food, body.getFirst())) {
			body.addFirst(food);
			return food;
		}
		return null;
	}
	
	public Node move (Direction direction) {
		int x = body.getFirst().getX();
		int y = body.getFirst().getY();
		Node toAdd;
		Node toRemove = body.getLast();
		
		switch (direction) {
		case UP:
			toAdd = new Node(x, y-Settings.DEFAULT_NODE_SIZE);
			break;
		case RIGHT:
			toAdd = new Node(x+Settings.DEFAULT_NODE_SIZE, y);
			break;
		case DOWN:
			toAdd = new Node(x, y+Settings.DEFAULT_NODE_SIZE);
			break;
		case LEFT:
			toAdd = new Node(x-Settings.DEFAULT_NODE_SIZE, y);	
			break;
		default:
			toAdd = null;
			break;
		}
		
		if (toAdd != null) {
			body.addFirst(toAdd);
			body.removeLast();
			return toRemove;
		}
		
		return null;
	}
	
	public Node getHead() {
		return body.getFirst();
	}
	
	public Node addTail (Node area) {
		body.addLast(area);
		return area;
	}
	
	public LinkedList<Node> getBody(){
		return body;
	}
	
	private boolean isNeighbor(Node a, Node b) {
		return Math.abs(a.getX() - b.getX()) + Math.abs(a.getY() - b.getY()) == 1;
	}

}
