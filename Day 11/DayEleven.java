import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class DayEleven {
    public static void main(String[] args) {
        try {
            Scanner lines = new Scanner(new File("Day 11/input.txt"));
            ArrayList<String> raws = linesToRawString(lines);
            MonkeyBarrel group1 = new MonkeyBarrel(rawsToMonkey(raws));
            MonkeyBarrel group2 = new MonkeyBarrel(rawsToMonkey(raws));
            System.out.println("Part one: " + partOne(group1,20));
            System.out.println("Part two: " + partTwo(group2,10000));
        }catch (FileNotFoundException e){
            System.out.println(e);
        }
    }

    public static ArrayList<String> linesToRawString(Scanner lines){
        StringBuilder description = new StringBuilder();
        ArrayList<String> raws = new ArrayList<>();
        while (lines.hasNextLine()){
            String cur = lines.nextLine();
            if(cur.length()==0){
                raws.add(description.toString());
                description.setLength(0);
            }else{
                description.append(cur + "_");
            }
        }
        raws.add(description.toString());
        return raws;
    }

    public static ArrayList<Monkey> rawsToMonkey(ArrayList<String> raws){
        ArrayList<Monkey> monkeys = new ArrayList<>();
        for (String raw : raws){
            //get the relevant strings from input, use index of return val for monkey name
            String[] split = raw.split("_");
            String[] values = {split[1], split[2], split[3], split[4], split[5]};
            String[] rawItems = values[0].split(",");

            //get the initial items for each monkey
            ArrayList<Long> items = new ArrayList<>();
            for(String rawItem: rawItems){
                rawItem = rawItem.replaceAll("\\D+","");
                items.add(Long.parseLong(rawItem));
            }

            int testDenominator = Integer.parseInt(values[2].replaceAll("\\D+",""));
            int trueDestination = Integer.parseInt(values[3].replaceAll("\\D+",""));
            int falseDestination = Integer.parseInt(values[4].replaceAll("\\D+",""));
            String operator = values[1].contains("+") ? "+" : "*";
            String operationValue = values[1].replaceAll("\\D+","").length() == 0 ? "old" : values[1].replaceAll("\\D+","");
            monkeys.add(new Monkey(items, testDenominator, falseDestination, trueDestination, operationValue, operator));
        }
        return monkeys;
    }

    public static long partOne(MonkeyBarrel group, int times){
        group.process(times);
        return group.monkeyBusiness();
    }

    public static long partTwo(MonkeyBarrel group, int times){
        group.processNew(times);
        return group.monkeyBusiness();
    }
}
