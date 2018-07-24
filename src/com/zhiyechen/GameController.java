package com.snake;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JOptionPane;

/* Java已经为我们提供好了键盘监听的接口，其接口定义如下：

public interface KeyListener extends EventListener {
    public void keyPressed(KeyEvent e);
    public void keyReleased(KeyEvent e);
    public void keyTyped(KeyEvent e);
}
Java将键盘输入分成了三个步骤，按下(press)，释放(release)，键入(type)，对应了KeyListener的三个方法：

keyPressed： 按下某个键时会调用该方法
keyReleased： 释放某个键时会调用该方法
keyTyped： 键入某个键时会调用该方法
*/

// 当然，我们需要通过下列语句在SnakeApp进行init()初始化时将GameController注册进window中：
// window.addKeyListener(gameController);
public class GameController implements Runnable, KeyListener{
	
	private final Grid grid;
	private final GameView gameView;
	
	private boolean running;
	private boolean timeout;
	
	public GameController(Grid grid, GameView gameView) {
		 this.grid = grid;
		 this.gameView = gameView;
		 this.running = true;
		 this.timeout = false;
	}
	
	public void run() {
		while (running) {
			try {
				Thread.sleep(Settings.DEFAULT_MOVE_INTERVAL);
			} catch (InterruptedException e) {
				break;
			}
			
			if (timeout) {
				continue;
			}
			// 进入游戏下一步
            // 如果结束，则退出游戏
            // 如果继续，则绘制新的游戏页面
			if (grid.nextRound()) {
				gameView.draw();
			}
			else {
				showGameOverMessage();
				running = false;
			}
		}
		
		running = false;
	}
	
	public void keyPressed (KeyEvent e) {
		int keyCode = e.getKeyCode();

		switch (keyCode) {
		case KeyEvent.VK_UP:
			grid.changeDirection(Direction.UP);
			break;
		case KeyEvent.VK_DOWN:
			grid.changeDirection(Direction.DOWN);
			break;
		case KeyEvent.VK_LEFT:
			grid.changeDirection(Direction.LEFT);
			break;
	    case KeyEvent.VK_RIGHT:
	      	grid.changeDirection(Direction.RIGHT);
	      	break;
	    case KeyEvent.VK_SPACE:
	    	    if (timeout) {
					timeout = false;
			}
	    	    else {
					timeout = true;
				}
	    	    return;
		default:
			break;
		}
        
		if (grid.nextRound()) {
			gameView.draw();
		}
		else {
			showGameOverMessage();
		}
	}
	
	public void keyReleased (KeyEvent e) {		
	}
	
	public void keyTyped (KeyEvent e) {		
	}
	
	public void showGameOverMessage() {
	    JOptionPane.showMessageDialog(null, "游戏结束", "游戏结束", JOptionPane.INFORMATION_MESSAGE);
	}

}
