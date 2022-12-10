import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
//12180 too high
public class DayTen {
    public static void main(String[] args) {
        try{
            Scanner lines = new Scanner(new File("Day 10/input.txt"));
            ArrayList<String> instructions = new ArrayList<>();
            while(lines.hasNextLine()){
                instructions.add(lines.nextLine());
            }
            lines.close();
            System.out.println("Part 1: " + partOne(instructions));
            System.out.println("Part 2: " + "\n" + partTwo(instructions));
        }catch (FileNotFoundException e){
            System.err.println(e);
        }
    }

    public static int partOne(ArrayList<String> instructions){
        int X = 1, sum = 0, clock = 0, programCounter = 0;

        while(clock <= 220){
            if(instructions.get(programCounter).equals("noop")){
                clock++;
                if ((clock - 20) % 40 == 0) {
                    sum += clock * X;
                }
                programCounter++;
            }else{
                int hold = Integer.parseInt(instructions.get(programCounter).substring(5));
                clock++;
                if ((clock - 20) % 40 == 0) {
                    sum += clock * X;
                }
                clock++;
                if ((clock - 20) % 40 == 0) {
                    sum += clock * X;
                }
                programCounter++;
                X += hold;
            }
        }
        return sum;
    }

    public static String partTwo(ArrayList<String> instructions){
        StringBuilder msg = new StringBuilder();
        int X = 1, sum = 0, clock = 0, programCounter = 0;

        while(clock < 240){
            if(instructions.get(programCounter).equals("noop")){
                msg.append(writePixel( X,  clock));
                clock++;
                programCounter++;
            }else{
                int hold = Integer.parseInt(instructions.get(programCounter).substring(5));
                msg.append(writePixel( X,  clock));
                clock++;
                msg.append(writePixel( X,  clock));
                clock++;
                programCounter++;
                X += hold;
            }
        }
        return msg.toString();
    }

    public static String writePixel(int X, int clock){
        StringBuilder pixel = new StringBuilder();
        if(clock > 0 && clock % 40 == 0){
            pixel.append("\n");
        }
        if(clock%40 >= X - 1 && clock%40 <= X + 1){
            pixel.append("#");
        }else{
            pixel.append(".");
        }
        return pixel.toString();
    }
}
