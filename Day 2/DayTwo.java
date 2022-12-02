import java.io.File;
import java.util.Scanner;

public class DayTwo {
    public static void main(String[] args) throws Exception{
        Scanner lines = new Scanner(new File("Day 2/input.txt"));
        System.out.println("Part 1: " + partOne(lines));
        lines.close();
        lines = new Scanner(new File("Day 2/input.txt"));
        System.out.println("Part 2: " + partTwo(lines));
        lines.close();
    }

    public static int partOne(Scanner lines) throws Exception{
        int fin=  0;
        while(lines.hasNextLine()){
            String move = lines.nextLine();
            char opponent = move.charAt(0);
            char us = move.charAt(2);
            fin += getScoreOne(opponent, us);
        }
        return fin;
    }

    //A: rock
    //B: paper
    //C: scissors
    //X: Rock 1
    //Y: paper 2
    //Z: scissors 3
    public static int getScoreOne(char opponent, char us) throws Exception{
        int total = 0;
        if (us == 'X'){
            total += 1;
            if(opponent == 'A'){total += 3;}
            if(opponent == 'B'){}
            if(opponent == 'C'){total += 6;}
        }
        else if (us == 'Y'){
            total += 2;
            if(opponent == 'A'){total += 6;}
            if(opponent == 'B'){total += 3;}
            if(opponent == 'C'){}
        }
        else if (us == 'Z'){
            total += 3;
            if(opponent == 'A'){}
            if(opponent == 'B'){total += 6;}
            if(opponent == 'C'){total += 3;}
        }
        else{
            throw new Exception();
        }
        return total;
    }

    public static int partTwo(Scanner lines){
        int fin = 0;
        while(lines.hasNextLine()){
            String move = lines.nextLine();
            char opponent = move.charAt(0);
            char us = move.charAt(2);
            fin += getScoreTwo(opponent, us);
        }
        return fin;
    }

    //A: rock 1
    //B: paper 2
    //C: scissors 3
    //X: lose 0
    //Y: tie 3
    //Z: win 6
    public  static  int getScoreTwo(char opponent, char us){
        int total  = 0;
        if(us == 'X'){
            if(opponent == 'A'){total += 3;}
            if(opponent == 'B'){total += 1;}
            if(opponent == 'C'){total += 2;}
        }
        else if (us == 'Y'){
            total += 3;
            if(opponent == 'A'){total += 1;}
            if(opponent == 'B'){total += 2;}
            if(opponent == 'C'){total += 3;}
        }
        else if (us == 'Z'){
            total += 6;
            if(opponent == 'A'){total += 2;}
            if(opponent == 'B'){total += 3;}
            if(opponent == 'C'){total += 1;
            }
        }
        return total;
    }
}
