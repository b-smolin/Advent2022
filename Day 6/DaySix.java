import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;

public class DaySix {
    public static void main(String[] args) {
        try {
            Scanner lines = new Scanner(new File("Day 6/input.txt"));
            System.out.println("Part 1: " + partOne(lines));
            lines.close();
            lines = new Scanner(new File("Day 6/input.txt"));
            System.out.println("Part 2: " + partTwo(lines));
            lines.close();
        }catch (FileNotFoundException e){
            System.out.println(e);
        }
        }

        public static int partOne(Scanner lines){
            String command = lines.nextLine();
            for(int i = 3 ; i < command.length() ; i++){
                HashSet<Character> seen = new HashSet<>();
                for(int j = 0 ; j < 4 ; j++){
                    if(seen.contains(command.charAt(i-j))){
                        break;
                    }else{
                        seen.add(command.charAt(i-j));
                    }
                    if(j == 3)
                        return i+1;
                }
            }
            return -1;
        }

    public static int partTwo(Scanner lines){
        String command = lines.nextLine();
        for(int i = 13 ; i < command.length() ; i++){
            HashSet<Character> seen = new HashSet<>();
            for(int j = 0 ; j < 14 ; j++){
                if(seen.contains(command.charAt(i-j))){
                    break;
                }else{
                    seen.add(command.charAt(i-j));
                }
                if(j == 13)
                    return i+1;
            }
        }
        return -1;
    }
}
