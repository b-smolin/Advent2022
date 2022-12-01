import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.TreeSet;

public class DayOne {
	public static void main(String[] args) throws FileNotFoundException{
		Scanner lines = new Scanner(new File("Day 1/input.txt"));
		System.out.println("Part one: " + partOne(lines));
		lines.close();
		lines = new Scanner(new File("Day 1/input.txt"));
		System.out.println("Part two: " + partTwo(lines));
		lines.close();
	}

	//Returns the highest number of calories carried by any single elf
	public static int partOne(Scanner lines){
		int max_calories = 0, current_calories = 0;
		while(lines.hasNextLine()){
			String line = lines.nextLine();
			if(line.length() == 0){
				current_calories = 0;
			}else{
				current_calories += Integer.parseInt(line);
				if(current_calories > max_calories){
					max_calories = current_calories;
				}
			}
		}
		return max_calories;
	}

	//returns the total calories carried by the top three most calorie heavy elves
	public static int partTwo(Scanner lines){
		int top_three = 0, current_calories = 0;
		TreeSet<Integer> totals = new TreeSet<>();
		while(lines.hasNextLine()){
			String line = lines.nextLine();
			if(line.length() == 0){
				totals.add(current_calories);
				current_calories = 0;
			}else{
				current_calories += Integer.parseInt(line);
			}
		}
		for(int i = 0 ; i < 3 ; i++){
			top_three += totals.pollLast();
		}
		return top_three;
	}
}
