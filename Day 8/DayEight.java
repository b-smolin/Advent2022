import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class DayEight {
    public static void main(String[] args) {
        try {
            Scanner file = new Scanner(new File("Day 8/input.txt"));
            int[][] woods = getWoods(file);
            System.out.println("Part 1: " + partOne(woods));
            System.out.println("Part 2: " + partTwo(woods));
        }catch (FileNotFoundException e){
            System.out.println(e);
        }
    }

    public static int partTwo(int[][] woods){
        int scenic = 0;
        //if on edge viewing distance will be zero and thus score
        //so only check if inside by one
        for (int i = 1 ; i < woods.length-1 ; i++){
            for (int j = 1; j < woods.length-1; j++) {
                int score = getScenic(i, j, woods);
                if (score > scenic){
                    scenic = score;
                }
            }
        }
        return scenic;
    }

    public static int getScenic(int i, int j, int[][] woods){
        int bottomScore = 1, topScore = 1, leftScore = 1, rightScore = 1;
        int size = woods.length;
        int previous = woods[i][j];

        int right = j+1;
        while(right < size-1 && woods[i][right] < previous){
            rightScore++;
            right++;
        }

        int left = j-1;
        previous = woods[i][j];
        while( left > 0 && woods[i][left] < previous){
            leftScore++;
            left--;
        }

        int up = i-1;
        while(up > 0 && woods[up][j] < previous){
            if(up == 0){
                break;
            }
            topScore++;
            up--;
        }

        int down = i+1;
        while (down < size-1 && woods[down][j] < previous) {
            bottomScore++;
            down++;
        }

        return (topScore * bottomScore * leftScore * rightScore);
    }

    public static int partOne(int[][] woods){
        int visible = 0;
        boolean[][] seen = new boolean[woods.length][woods[0].length];

        //process left side
        for(int i = 0 ; i < woods[0].length ; i++){
            int front = -1;
            for(int j = 0 ; j < woods.length ; j++){
                if(woods[i][j] > front){
                    seen[i][j] = true;
                    front = woods[i][j];
                }
            }
        }
        //process top
        for (int i = 0 ; i < woods.length ; i++){
            int front = -1;
            for(int j = 0 ; j < woods.length ; j++){
                if(woods[j][i] > front){
                    seen[j][i] = true;
                    front = woods[j][i];
                }
            }
        }
        //process right side
        for (int i = 0; i < seen.length; i++) {
            int front = -1;
            for (int j = seen.length-1; j >= 0; j--) {
                if(woods[i][j] > front){
                    seen[i][j] = true;
                    front = woods[i][j];
                }
            }
        }

        //process bottom
        for(int i = seen.length-1; i>=0 ; i--){
            int front = -1;
            for (int j = seen.length-1; j>=0 ; j--) {
                if(woods[j][i] > front){
                    seen[j][i] = true;
                    front = woods[j][i];
                }
            }
        }
        //count visible
        for(int i = 0 ; i < seen.length ; i++){
            for (int j = 0; j < seen.length; j++) {
                if(seen[i][j])
                    visible++;
            }
        }
        return visible;
    }


    public static int[][] getWoods(Scanner file){
        ArrayList<String> lines = new ArrayList<>();
        while(file.hasNextLine()){
            lines.add(file.nextLine());
        }
        int width = lines.get(0).length();
        int height = lines.size();
        int woods[][] = new int[height][width];
        for(int i = 0 ; i < height ; i++){
            for(int j = 0 ; j  < width ; j++){
                woods[i][j] = Integer.parseInt(lines.get(i).substring(j, j+1));
            }
        }
        return woods;
    }
}
