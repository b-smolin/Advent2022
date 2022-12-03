import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class DayThree {
    public static void main(String[] args) throws FileNotFoundException{
        Scanner lines = new Scanner(new File("Day 3/input.txt"));
        System.out.println("Part 1: " + partOne(lines));
        lines.close();
        lines = new Scanner(new File("Day 3/input.txt"));
        System.out.println("Part 2: " + partTwo(lines));
        lines.close();

    }

    public static int partOne(Scanner lines){
        int total = 0;
        while(lines.hasNextLine()){
            String current = lines.nextLine();
            int middle = current.length()/2;
            String l = current.substring(0, middle);
            String r = current.substring(middle);
            total += letterScore(commonChar(l, r));
        }
        return total;
    }

    public static int partTwo(Scanner lines){
        int total = 0;
        while (lines.hasNextLine()){
            String l = lines.nextLine();
            String m = lines.nextLine();
            String r = lines.nextLine();
            total+=(letterScore(commonBadge(l, m, r)));
        }
        return total;
    }

    public static char commonBadge(String l, String m, String r){
        int s = l.length();
        for(int i = 0 ; i < s ; i++){
            if(m.contains(l.substring(i, i+1)) && r.contains(l.substring(i, i+1))){
                return l.charAt(i);
            }
        }
        return '1'; //flag for bad input
    }
    public static char commonChar(String l, String r){
        int s = l.length();
        for(int i = 0 ; i < s ; i++){
            if(r.contains(l.substring(i, i+1))){
                return l.charAt(i);
            }
        }
        return '1'; //flag for bad input
    }

    public static int letterScore(char c){
        if (c >= 'A' && c <= 'Z') {
            return c - 38;
        }else {
            return c - 96;
        }
    }

}
