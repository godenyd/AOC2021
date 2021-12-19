package hu.davidkaa.aoc2021.day5.model;

import lombok.Data;

@Data
public class Vector {
	private int x;
	private int y;
	
	public Vector(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public Vector normalize() {
		int newX = x == 0 ? 0 : (int)Math.signum(x);
		int newY = y == 0 ? 0 : (int)Math.signum(y);
		
		return new Vector(newX, newY);
	}
	
	public Vector add(Vector o) {
		return new Vector(x + o.getX(), y + o.getY());
	}
	
	@Override
	public String toString() {
		return x + ":" + y;
	}
}
