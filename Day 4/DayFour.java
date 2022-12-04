import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class DayFour {
    public static void main(String[] args) throws FileNotFoundException{
        Scanner lines = new Scanner(new File("Day 4/input.txt"));
        System.out.println("Part 1: " + partOne(lines));
        lines.close();
        lines = new Scanner(new File("Day 4/input.txt"));
        System.out.println("Part 2: " + partTwo(lines));
        lines.close();
    }

    public static int partOne(Scanner lines){
        int contained = 0;
        while (lines.hasNextLine())
        {
            String currents[] = lines.nextLine().split(",");
            int a[] = new int[2];
            int b[] = new int[2];
            String a_range[] =  currents[0].split("-");
            String b_range[] =  currents[1].split("-");
            a[0] = Integer.parseInt(a_range[0]);
            a[1] = Integer.parseInt(a_range[1]);
            b[0] = Integer.parseInt(b_range[0]);
            b[1] = Integer.parseInt(b_range[1]);
            if(containedRange(a, b)){
                contained++;
            }
        }
        return contained;
    }

    public static int partTwo(Scanner lines){
        int contained = 0;
        while (lines.hasNextLine())
        {
            String currents[] = lines.nextLine().split(",");
            int a[] = new int[2];
            int b[] = new int[2];
            String a_range[] =  currents[0].split("-");
            String b_range[] =  currents[1].split("-");
            a[0] = Integer.parseInt(a_range[0]);
            a[1] = Integer.parseInt(a_range[1]);
            b[0] = Integer.parseInt(b_range[0]);
            b[1] = Integer.parseInt(b_range[1]);
            if(overlap(a, b)){
                contained++;
            }
        }
        return contained;
    }

    public static boolean containedRange(int[] a, int[] b){
        return ((a[0] >= b[0] && a[1] <= b[1]) || (b[0] >= a[0] && b[1] <= a[1]));
    }

    public static boolean overlap(int[] a, int[] b){
        return ((a[0] >= b[0] && a[0] <= b[1]) ||
                (a[1] >= b[0] && a[1] <= b[0]) ||
                (b[0] >= a[0] && b[0] <= a[1]) ||
                (b[1] >= a[0] && b[1] <= a[1]));
    }

    //3-6
    //5-7
}
