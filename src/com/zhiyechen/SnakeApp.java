package com.snake;

import java.awt.*;
import javax.swing.*;

// Swing 是一个为Java提供的GUI（Graphics User Interface，图形化界面）编程工具包，是J2SE类库中的一部分，它包含了诸如文本框和按钮等一系列GUI组件。
// Java Swing编程目前来说也不能说是应用非常广泛的技术（比如相比Java Web开发），如果只是练习Java基础，了解一些基本原理和常用组件的用法即可。
// EDT（Event Dispatching Thread，字面上翻译成“事件分配线程”）是Java GUI应用中的一个线程，这个线程管理着所有SWING GUI事件和整个UI界面。我们对UI相关的修改都应该放到EDT中。我们将SnakeApp也实现Runnable接口，init()方法更名为run()。

public class SnakeApp implements Runnable{
	private Grid grid;
	private GameView gameView;
	private GameController gameController;
	
	public void run () {		
		//创建游戏窗体
        JFrame window = new JFrame("贪吃蛇游戏");
        
        Container contentPane = window.getContentPane();
        
        grid = new Grid(Settings.DEFAULT_GRID_WIDTH, Settings.DEFAULT_GRID_HEIGHT);
        
        // 基于Grid初始化gamaView
        gameView = new GameView(grid);
        gameView.init();
        
        // 设置gameView中JPanel的大小
        gameView.getCanvas().setPreferredSize(new Dimension(Settings.DEFAULT_GRID_WIDTH, Settings.DEFAULT_GRID_HEIGHT));
        
        // 将gameView中JPanel加入到窗口中
        contentPane.add(gameView.getCanvas(), BorderLayout.CENTER);

        // 画出棋盘和贪吃蛇
        window.pack();
        window.setResizable(false);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);
        
        gameController = new GameController(grid, gameView);
        window.addKeyListener(gameController);

        // 启动线程
        new Thread(gameController).start();
	}
	public static void main (String[] args) {
		SwingUtilities.invokeLater(new SnakeApp());
	}
}
