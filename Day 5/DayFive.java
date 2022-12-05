import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java. util.Stack;


public class DayFive {
    public static void main(String[] args) {
        try{
            Scanner lines = new Scanner(new File("Day 5/input.txt"));
            System.out.println("Part 1: " + partOne(lines));
            lines.close();
            lines = new Scanner(new File( "Day 5/input.txt"));
            System.out.println("Part 2: " + partTwo(lines));
            lines.close();
        }catch (FileNotFoundException e){
            System.out.println(e);
        }
    }

    public static String partOne(Scanner lines){
        ArrayList<Stack<Character>> boxes = parseBoxes(lines);
        ArrayList<Command> orders = parseMoves(lines);
        return executeAndGetTopsOne(boxes, orders);
    }

    public static String partTwo(Scanner lines){
        ArrayList<Stack<Character>> boxes = parseBoxes(lines);
        ArrayList<Command> orders = parseMoves(lines);
        return executeAndGetTopsTwo(boxes, orders);
    }

    public static String executeAndGetTopsTwo(ArrayList<Stack<Character>> boxes, ArrayList<Command> orders){
        for(Command com : orders){
            Stack<Character> transition = new Stack<>();
            for(int i = 0 ; i < com.moves ; i++){
                 transition.push( boxes.get(com.origin).pop());
            }
            while (!transition.isEmpty()){
                boxes.get(com.dest).push(transition.pop());
            }
        }
        String tops = "";
        for(Stack<Character> stack : boxes){
            tops += stack.pop();
        }
        return  tops;
    }

    public static String executeAndGetTopsOne(ArrayList<Stack<Character>> boxes, ArrayList<Command> orders){
        for(Command com : orders){
            for(int i = 0 ; i < com.moves ; i++){
                Character remove = boxes.get(com.origin).pop();
                boxes.get(com.dest).push(remove);
            }
        }
        String tops = "";
        for(Stack<Character> stack : boxes){
            tops += stack.pop();
        }
        return  tops;
    }
    public static ArrayList<Command> parseMoves(Scanner lines){
        ArrayList<Command> orders = new ArrayList<>();
        while(lines.hasNextLine()){
            String cur[] = lines.nextLine().split(" ");
            orders.add(new Command(Integer.parseInt(cur[1]), Integer.parseInt(cur[3]), Integer.parseInt(cur[5])));
        }
        return orders;
    }
    public static ArrayList<Stack<Character>> parseBoxes(Scanner lines){
        String line = "start";
        int row = 0;
        char rawinp[][] = new char[9][9];
        ArrayList<Stack<Character>> boxes = new ArrayList<>(
                Arrays.asList(
                        new Stack<Character>(), new Stack<Character>(), new Stack<Character>(),
                        new Stack<Character>(), new Stack<Character>(), new Stack<Character>(),
                        new Stack<Character>(), new Stack<Character>(), new Stack<Character>())
                );

        while(line.length() > 0){
            line = lines.nextLine();
            for(int i = 1 ; i < line.length() ; i += 4){
                if(line.charAt(i)!= ' '){
                    rawinp[row][(i-1)/4] = line.charAt(i);
                }
            }
            row++;
        }
        for(int i = 7 ; i >= 0  ; i--){
            for(int j = 0 ; j < 9 ; j++){
                if(rawinp[i][j] != '\0'){
                    boxes.get(j).push(rawinp[i][j]);
                }
            }
        }
        return boxes;
    }


}

