package hu.davidkaa.aoc2021.day1;

public class Main {

	public static void main(String[] args) {

		int increaseCounter = 0;
		
		int[] depthArray = Data.getDepthArray();
		
		for (int i = 1; i < depthArray.length; i++)
			if (depthArray[i] > depthArray[i - 1])
				increaseCounter++;
		
		System.out.println("counter: " + increaseCounter);

		int windowCounter = 0; 
		
		for (int i = 3; i < depthArray.length; i++) {
			int windowPrev = depthArray[i-3] + depthArray[i-2] + depthArray[i-1];
			int windowCur = depthArray[i-2] + depthArray[i-1] + depthArray[i];
			
			if (windowCur > windowPrev)
				windowCounter++;
		}
		
		System.out.println("window counter: " + windowCounter);
	}
	
}
