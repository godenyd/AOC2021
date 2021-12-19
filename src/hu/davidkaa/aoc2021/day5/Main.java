package hu.davidkaa.aoc2021.day5;

import java.util.ArrayList;
import java.util.List;

import hu.davidkaa.aoc2021.day5.model.Vector;
import hu.davidkaa.aoc2021.day5.model.Segment;

public class Main {

	public static void main(String[] args) {
		int[] coordinates = Data.getCoordinates();

		List<Segment> segments = new ArrayList<>(coordinates.length / 4);

		for (int i = 0; i < coordinates.length; i += 4) {
			segments.add(new Segment(
					new Vector(coordinates[i], coordinates[i + 1]),
					new Vector(coordinates[i + 2], coordinates[i + 3])));
		}
		
		int[][] map = new int[1000][1000];
		
		segments.forEach(segment -> {
			
			if (segment.isHorizontalOrVertical()) {
				int x1 = segment.getStart().getX();
				int x2 = segment.getEnd().getX();
				int y1 = segment.getStart().getY();
				int y2 = segment.getEnd().getY();
				
				for (int i = Math.min(x1, x2); i <= Math.max(x1, x2); i++) {
					for (int j = Math.min(y1, y2); j <= Math.max(y1, y2); j++) {
						map[i][j]++;
					}
				}
			} else {
				int xdiff = segment.getEnd().getX() - segment.getStart().getX();
				int ydiff = segment.getEnd().getY() - segment.getStart().getY();
				
				Vector dir = new Vector(xdiff, ydiff);
				Vector normalizedDir = dir.normalize();
				
				int length = Math.abs(dir.getX());
				
				Vector currentCoord = segment.getStart();
				
				for (int i = 0; i <= length; i++) {
					map[currentCoord.getX()][currentCoord.getY()]++;
					currentCoord = currentCoord.add(normalizedDir);
				}
			}
		});
		
		System.out.println(calculateDangerCount(map));
	}
	
	public static int calculateDangerCount(int[][] map) {
		
		int highDangerCount = 0;
		
		for (int i = 0; i < 1000; i++) {
			for (int j = 0; j < 1000; j++) {
				
				if (map[i][j] > 1) {
					highDangerCount++;
				}
			}
		}
		
		return highDangerCount;
	}
}
 