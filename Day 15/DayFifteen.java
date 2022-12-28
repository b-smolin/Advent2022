import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class DayFifteen {
    public static void main(String[] args) {
        try {
            Scanner lines = new Scanner(new File("Day 15/input.txt"));
            ArrayList<Coverage> spans = new ArrayList<>();
            while(lines.hasNextLine()){
                String[] locations = lines.nextLine().split(":");
                String[] sensor = locations[0].split(",");
                String[] beacon = locations[1].split(",");
                for(int i = 0 ; i < 2 ; i++){
                    sensor[i] = sensor[i].replaceAll("[^-0-9]", "");
                    beacon[i] = beacon[i].replaceAll("[^-0-9]", "");
                }
                spans.add(new Coverage(Integer.parseInt(sensor[0]), Integer.parseInt(sensor[1]), Integer.parseInt(beacon[0]), Integer.parseInt(beacon[1])));
            }
            lines.close();
            System.out.println("Part one: " + partOne(spans));
            System.out.println("Part two: " + partTwo(spans));
        }catch (FileNotFoundException e){
            System.out.println(e);
        }
    }

    public static int partOne(ArrayList<Coverage> spans){
        HashSet<String> invalids = new HashSet<>();
        for(Coverage cur: spans){
            invalids.addAll(cur.rowOverlap(2000000));
        }
        for (Coverage cur: spans){
            invalids.remove(cur.beaconString());
        }
        return invalids.size();
    }

    //possible points must be one pos outside the diamond formed by each
    //sensor beacon pair. how can we leverage?
    //get list of valid points, check each one
    public static BigInteger partTwo(ArrayList<Coverage> spans){
        ArrayList<Integer[]> toInvestigate = new ArrayList<>();
        for(Coverage cur: spans){
            toInvestigate.addAll(cur.getPerimeter());
        }
        for(Integer[] pair: toInvestigate){
            boolean invalid = false;
            for(Coverage cur: spans){
                if(!cur.allowedPosition(pair[0],pair[1])){
                    invalid = true;
                }
            }
            if(!invalid) {
                return new BigInteger("4000000").multiply(BigInteger.valueOf(pair[0])).add(BigInteger.valueOf(pair[1]));
            }
        }
        return new BigInteger(String.valueOf(Long.valueOf(-1)));
    }

    public static boolean isValid(int x, int y, ArrayList<Coverage> spans){
        for(Coverage cur: spans){
            if(!cur.allowedPosition(x, y)){
                return false;
            }
        }
        return true;
    }
}
