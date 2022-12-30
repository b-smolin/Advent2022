import com.sun.source.tree.TryTree;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class DayEighteen {
    public static void main(String[] args) {
        try{
            Scanner lines = new Scanner(new File("Day 18/input.txt"));
            HashSet<Triplet> spaces = new HashSet<>();
            while(lines.hasNextLine()){
                String[] raw = lines.nextLine().split(",");
                spaces.add(new Triplet(Integer.parseInt(raw[0]), Integer.parseInt(raw[1]), Integer.parseInt(raw[2])));
            }
            System.out.println("Part one: " + partOne(spaces));
            System.out.println("Part two: " + partTwo(spaces));

        }catch (FileNotFoundException e){
            System.out.println(e);
        }
    }

    public static int partOne(HashSet<Triplet> spaces){
        int exposed = 0;

        for(Triplet cur: spaces){
            ArrayList<Triplet> toCheck = cur.getNeighbors();
            for(Triplet check: toCheck){
                if(!spaces.contains(check)){
                    exposed++;
                }
            }
        }

        return exposed;
    }

    //check forward, backward, up, down, left, right
    public static int partTwo(HashSet<Triplet> spaces){
        int xMax = 0, yMax = 0, zMax = 0, trappedCount = 0;

        ArrayList<Triplet> freespaces = new ArrayList<>();
//        ArrayList<Triplet> trapped = new ArrayList<>();
        for(Triplet cur: spaces){
            xMax = Math.max(cur.x, xMax);
            yMax = Math.max(cur.y, yMax);
            zMax = Math.max(cur.z, zMax);
            var toCheck = cur.getNeighbors();
            for(var check: toCheck){
                if(!spaces.contains(check)){
                    freespaces.add(check);
                }
            }
        }
        for (var cur: freespaces){
            if(checkVertical(spaces, cur, zMax)
            && checkFront(spaces, cur, yMax)
            && checkSide(spaces, cur, xMax)){
                trappedCount++;
            }
        }

        return freespaces.size() - trappedCount;
    }

    private static boolean checkVertical(HashSet<Triplet> spaces, Triplet space, int zMax){
        boolean foundTop = false, foundBottom = false;
        for(int i = 1 ; i <= zMax ; i++){
            if(spaces.contains(new Triplet(space.x, space.y, space.z+i))){
                foundTop = true;
            }
            if(spaces.contains(new Triplet(space.x, space.y, space.z-i))){
                foundBottom = true;
            }
        }
        return foundBottom && foundTop;
    }

    private static boolean checkSide(HashSet<Triplet> spaces, Triplet space, int xMax){
        boolean foundLeft = false, foundRight = false;
        for(int i = 1 ; i <= xMax ; i++){
            if(spaces.contains(new Triplet(space.x+i, space.y, space.z))){
                foundLeft = true;
            }
            if(spaces.contains(new Triplet(space.x-i, space.y, space.z))){
                foundRight = true;
            }
        }
        return foundLeft && foundRight;
    }

    private static boolean checkFront(HashSet<Triplet> spaces, Triplet space, int yMax){
        boolean foundFront= false, foundBack = false;
        for(int i = 1 ; i<= yMax ; i++) {
            if (spaces.contains(new Triplet(space.x, space.y + i, space.z))) {
                foundFront = true;
            }
            if (spaces.contains(new Triplet(space.x, space.y - i, space.z))) {
                foundBack = true;
            }
        }
        return foundFront && foundBack;
    }


}
