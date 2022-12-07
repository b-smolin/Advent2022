import com.sun.source.tree.Tree;

import java.util.*;
import java.io.FileNotFoundException;
import java.io.File;

public class DaySeven {
    public static void main(String[] args) {
        try {
            Scanner lines = new Scanner(new File("Day 7/input.txt"));
            TreeNode head = parseInput(lines);
            System.out.println("Part one: " + partOne(head));
            lines.close();
            System.out.println(partTwo(head));
        }catch(FileNotFoundException e){
            System.out.println(e);
        }
    }

    public static TreeNode parseInput(Scanner lines){
        TreeNode current = new TreeNode(-1, "/");
        TreeNode head = current;
        Stack<TreeNode> stack = new Stack<>();
        lines.nextLine();
        while(lines.hasNextLine()){
            String[] line = lines.nextLine().split(" ");
            if(line[1].equals("ls")){
                line = lines.nextLine().split(" ");
                while(!line[1].equals("cd")){
                    int val = (line[0].equals("dir")) ? -1 : Integer.parseInt(line[0]);
                    current.addChild(new TreeNode(val, line[1]));
                    if(!lines.hasNextLine()){
                        break;
                    }
                    line = lines.nextLine().split(" ");
                }
            }
            if(line[1].equals("cd")){
                if(line[2].equals("..")){
                    current = stack.pop();
                }else{
                    stack.push(current);
                    current = current.children.get(line[2]);
                }
            }
        }
        return head;
    }

    public static int partTwo(TreeNode head){
        ArrayList<Integer> dirSizes = new ArrayList<>();
        traverseAndTrackDirSizes(head, dirSizes);
        //the outermost directory must be the biggest so available space = 70000000 - that
        // needed space = 30000000 - available space;
        Collections.sort(dirSizes);
        int needed_space =  30000000 - (70000000 - dirSizes.get(dirSizes.size()-1));
        for (int cur : dirSizes){
            if (cur > needed_space)
                return cur;
        }
        return -1;
    }

    //this function also preps the tree for part 2. nice :)
    public static int partOne(TreeNode head){
        ArrayList<Integer> track =  new ArrayList<>(Arrays.asList(0));
        traverseAndTrackSmallSizes(head, track);
        return track.get(0);
    }

    public static void traverseAndTrackDirSizes(TreeNode head, ArrayList<Integer> track){
        if(head.children.size() > 0){
            track.add(head.size);
            for(TreeNode child : head.children.values()){
                traverseAndTrackDirSizes(child, track);
            }
        }
}
    public static int traverseAndTrackSmallSizes(TreeNode head, ArrayList<Integer> track){
        if(head.size < 0){
            int sum = 0;
            for(TreeNode child : head.children.values()){
                sum += (child.size < 0) ? traverseAndTrackSmallSizes(child, track) : child.size;
            }
            if(sum <= 100000){
                int cur = track.get(0) + sum;
                track.set(0, cur);
            }
            head.size = sum;
        }
        return head.size;
    }
}
