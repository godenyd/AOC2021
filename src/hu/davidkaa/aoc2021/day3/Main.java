package hu.davidkaa.aoc2021.day3;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
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
		
		Optional<String> oxyRating = reduce(Arrays.asList(data), 0, true);
		Optional<String> co2Rating = reduce(Arrays.asList(data), 0, false);
		
		int oxygenRating = Integer.parseInt(oxyRating.get(),2 );
		int scrubberRating = Integer.parseInt(co2Rating.get(), 2);
		
		System.out.println(oxygenRating * scrubberRating);
	}
	
	protected static int getNumber(int[] numberArray, String[] data) {
		int value = 0;
		
		for (int i = 0; i < numberArray.length; i++) {
			value += numberArray[11 - i] > data.length / 2 ? Math.pow(2, i) : 0;
		}
		
		return value;
	}
	
	private static Optional<String> reduce(List<String> numbers, int index, boolean criteriaMostCommon) {
		
		if (numbers.size() == 1) {
			return Optional.ofNullable(numbers.get(0));
		}
		
		// count ones in current index
		long oneCount = numbers.stream()
				.filter(number -> number.charAt(index) == '1')
				.count();
		
		// determine bit criteria
		char mostCommon = oneCount * 2 >= numbers.size() ? '1' : '0';
		char leastCommon = (numbers.size() - oneCount) * 2 <= numbers.size() ? '0' : '1';
		
		char criteria = criteriaMostCommon ? mostCommon : leastCommon;
		
		// filter number list based on criteria
		numbers = numbers.stream()
				.filter(number -> criteria == number.charAt(index))
				.collect(Collectors.toList());
		
		
		return reduce(numbers, index + 1, criteriaMostCommon);
	}
}
