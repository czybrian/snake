package com.snake;

// Enum本质上是一种特殊的类
// Direction.values()会返回所有的枚举值。
// 枚举类有一个name()方法，和toString()返回一样的值，所不同的是toString()可以被重载，而name()方法是final的，不能被重载

//枚举类还有一个valueOf()方法，这个方法和toString方法是相对应的，调用valueOf("UP")将返回Direction.UP。因此在重写toString()方法时，一般也要相应重写valueOf()方法。

//ordinal()：返回枚举值在枚举类种的顺序，这个顺序根据枚举值声明的顺序而定，这里Direction.RIGHT.ordinal()返回1。

//枚举可以实现接口，但是不能继承，原因在于任何枚举已经继承自java.lang.Enum，而Java是不支持多继承的。

public enum Direction {
	UP(0),
    RIGHT(1),
    DOWN(2),
    LEFT(3);
	
	private final int directionCode;
	
	public int directionCode() {
		return directionCode;
	}
	
	private Direction(int directionCode) {
		this.directionCode = directionCode;
	}
	
	public boolean compatibleWith(Direction newDirection) {
		if (Math.abs(this.directionCode - newDirection.directionCode()) == 2) {
			return false;
		}
		return true;
	}
}
