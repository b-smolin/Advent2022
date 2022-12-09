import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;

public class DayNine {
    public static void main(String[] args) {
        try {
            Scanner lines = new Scanner(new File("Day 9/input.txt"));
            ArrayList<String> moves = new ArrayList<>();
            while (lines.hasNextLine()) {
                moves.add(lines.nextLine());
            }
            System.out.println("Part 1: " + partOne(moves));
            System.out.println("Part 2: " + partTwo(moves));
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }
    }

    public static int partOne(ArrayList<String> moves){
        Node head = new Node(0,0);
        Node tail = new Node(0,0);
        HashSet<String> seen = new HashSet<>();
        for(String move : moves){
            int steps = Integer.parseInt(move.substring(2));
            char direction = move.charAt(0);
            for(int i = 0 ; i < steps ; i++) {
                int oldX = head.x;
                int oldY = head.y;
                switch (direction) {
                    case 'U':
                        head.y++;
                        break;
                    case 'R':
                        head.x++;
                        break;
                    case 'L':
                        head.x--;
                        break;
                    case 'D':
                        head.y--;
                        break;
                    default:
                        System.out.println("bad");
                        break;
                }
                if(Math.abs(head.x - tail.x) > 1 || Math.abs(head.y - tail.y) > 1){
                    tail.x = oldX;
                    tail.y = oldY;
                }
                seen.add(tail.toString());
            }
        }
        return seen.size();
    }

    public static int partTwo(ArrayList<String> moves){
        //make same move then adjust if needed?
        HashSet<String> seen = new HashSet<>();
        ArrayList<Node> rope = new ArrayList<>();
        for (int i = 0 ; i < 10 ; i++){
            rope.add(new Node(0,0));
        }
        for(String move: moves) {
            int steps = Integer.parseInt(move.substring(2));
            char direction = move.charAt(0);
            //go through all the rope bits
            for (int i = 0; i < steps; i++) {
                int oldX = head.x;
                int oldY = head.y;
                switch (direction) {
                    case 'U':
                        head.y++;
                        break;
                    case 'R':
                        head.x++;
                        break;
                    case 'L':
                        head.x--;
                        break;
                    case 'D':
                        head.y--;
                        break;
                    default:
                        System.out.println("bad");
                        break;
                }
            }
        }
        return seen.size();
    }


}
