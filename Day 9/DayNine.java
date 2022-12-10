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

//    If the head is ever two steps directly up, down, left, or right from the tail,
//    the tail must also move one step in that direction so it remains close enough:
//    Otherwise, if the head and tail aren't touching and aren't in the same row or column,
//    the tail always moves one step diagonally to keep up:
    public static int partOne(ArrayList<String> moves){
        ArrayList<Node> snake = makeSnake(2);
        Node head = snake.get(0);
        Node tail = snake.get(1);
        HashSet<String> seen = new HashSet<>();
        for(String move : moves){
            int steps = Integer.parseInt(move.substring(2));
            char direction = move.charAt(0);
            for(int i = 0 ; i < steps ; i++) {
                switch (direction) {
                    case 'U' -> head.y++;
                    case 'R' -> head.x++;
                    case 'L' -> head.x--;
                    case 'D' -> head.y--;
                    default -> System.out.println("bad");
                }
                updateSnake(snake);
                seen.add(tail.toString());
            }
        }
        return seen.size();
    }

    public static ArrayList<Node> makeSnake(int size){
        ArrayList<Node> snake = new ArrayList<>();
        for(int i = 0 ; i < size ; i++) {
            snake.add(new Node(0,0));
        }
        return snake;
    }

    public static void updateSnake(ArrayList<Node> snake){
        for(int next = 1; next < snake.size() ; next++){
            if(Math.abs(snake.get(next).x - snake.get(next-1).x) <= 1 && Math.abs(snake.get(next).y - snake.get(next-1).y) <= 1){
                break;
            }
            //current bit follows front bit
            Node current = snake.get(next);
            Node front = snake.get(next-1);
            int xDif = front.x - current.x;
            int yDif = front.y - current.y;

            if(xDif == -2 && yDif == 0){ //it is two to the left
                current.x--;
            }else if(xDif == 2 && yDif == 0){ //it is two to the right
                current.x++;
            }
            else if(xDif == 0 && yDif == 2){ //it is 2 up
                current.y++;
            }
            else if(xDif == 0 && yDif == -2){ //it is 2 down
                current.y--;
            }
            else if(xDif == 2 && yDif == 1 || xDif == 1 && yDif == 2 || xDif == 2 && yDif == 2) {//up right diagonal
                current.x++;
                current.y++;
            }
            else if(xDif == 2 && yDif == -1 || xDif == 1 && yDif == -2 || xDif == 2 && yDif == -2) {//down right diagonal
                current.x++;
                current.y--;
            }
            else if(xDif == -2 && yDif == 1 || xDif == -1 && yDif == 2 || xDif == -2 && yDif == 2) {//up left diagonal
                current.x--;
                current.y++;
            }
            else if(xDif == -2 && yDif == -1 || xDif == -1 && yDif == -2 || xDif == -2 && yDif == -2) {//down left diagonal
                current.x--;
                current.y--;
            }
        }
    }

    public static int partTwo(ArrayList<String> moves){
        //make same move then adjust if needed?
        HashSet<String> seen = new HashSet<>();
        ArrayList<Node> snake = makeSnake(10);
        for(String move: moves) {
            int steps = Integer.parseInt(move.substring(2));
            char direction = move.charAt(0);
            Node head = snake.get(0);
            for (int i = 0; i < steps; i++) {
                switch (direction) {
                    case 'U' -> head.y++;
                    case 'R' -> head.x++;
                    case 'L' -> head.x--;
                    case 'D' -> head.y--;
                    default -> System.out.println("bad");
                }
                updateSnake(snake);
                seen.add(snake.get(9).toString());
            }
        }
        return seen.size();
    }


}
