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
}
