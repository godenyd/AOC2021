package hu.davidkaa.aoc2021.day5.model;

import lombok.Data;

@Data
public class Segment {
	
	private Vector start;
	private Vector end;
	
	public Segment(Vector start, Vector end) {
		this.start = start;
		this.end = end;
	}
	
	public boolean isHorizontalOrVertical() {
		return isHorizontal() || isVertical();
	}
	
	public boolean isHorizontal() {
		return start.getX() == end.getX();
	}
	
	public boolean isVertical() {
		return start.getY() == end.getY();
	}
}
