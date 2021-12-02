package hu.davidkaa.aoc2021.day2;

public class Main {

	public static void main(String[] args) {
		
		int forward = 0;
		int depth = 0;

		String[] commands = Data.getCommands();
		
		for (int i = 0; i < commands.length; i++) {
			
			String[] command = commands[i].split(" ");
			
			switch(command[0]) {
			case "forward":
				forward += Integer.parseInt(command[1]);
				break;
			case "up":
				depth -= Integer.parseInt(command[1]);
				break;
			case "down":
				depth += Integer.parseInt(command[1]);
			}
			
		}
		
		System.out.println("value: " + forward * depth);
		
		int aim = 0;
		forward = 0;
		depth = 0;
		
		for (int i = 0; i < commands.length; i++) {
			
			String[] command = commands[i].split(" ");
			
			switch(command[0]) {
			case "forward":
				int x = Integer.parseInt(command[1]);
				forward += x;
				depth += aim * x;
				break;
			case "up":
				aim -= Integer.parseInt(command[1]);
				break;
			case "down":
				aim += Integer.parseInt(command[1]);
			}
			
		}
		
		System.out.println("value: " + forward * depth);
	}
}
