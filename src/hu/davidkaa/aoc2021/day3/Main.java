package hu.davidkaa.aoc2021.day3;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

	public static void main(String[] args) {

		String[] data = Data.getData();
		
		int[] gammaArray = new int[12];
		int[] epsilonArray = new int[12];
		
		Arrays.asList(data).forEach(number -> {
			
			for (int i = 0; i < number.length(); i++) 
				if ('1' == number.charAt(i)) {
					gammaArray[i]++;
				} else {
					epsilonArray[i]++;
				}
			
		});
		
		int gamma = getNumber(gammaArray, data);
		int epsilon = getNumber(epsilonArray, data);
		
		System.out.println(gamma * epsilon);
		
		int digit = 0;
		
		List<String> dataList = Arrays.asList(data);
		
		while (data.length > 1 && digit < 12) {
			
			char criteria = gammaArray[digit] * 2 >= data.length ? '1' : '0';
			
			dataList = dataList.stream()
					.filter(number -> number.charAt(digit) == criteria)
					.collect(Collectors.toList());
			
		}
	}
	
	protected static int getNumber(int[] numberArray, String[] data) {
		int value = 0;
		
		for (int i = 0; i < numberArray.length; i++) {
			value += numberArray[11 - i] > data.length / 2 ? Math.pow(2, i) : 0;
		}
		
		return value;
	}
}
