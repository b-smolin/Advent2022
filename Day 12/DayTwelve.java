import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;

public class DayTwelve {
    public static void main(String[] args) {
        try {
            Scanner lines = new Scanner(new File("Day 12/input.txt"));
            ArrayList<ArrayList<Integer>> map = getBoard(lines);
            System.out.println("Part 1: " + partOne(map));
            System.out.println("Part 2: " + partTwo(map));
        }catch (FileNotFoundException e)
        {
            System.out.println(e);
        }
    }
    public static ArrayList<ArrayList<Integer>> getBoard(Scanner lines) throws FileNotFoundException{
        ArrayList<ArrayList<Integer>> map = new ArrayList<>();
        while (lines.hasNextLine()){
            String line = lines.nextLine();
            ArrayList<Integer> temp = new ArrayList<>();
            for(char c : line.toCharArray()){
                if(c =='S'){
                    temp.add(0);
                }else if(c == 'E'){
                    temp.add(27);
                }else{
                    int val = c - 'a' + 1;
                    temp.add(val);
                }
            }
            map.add(temp);
        }
        return map;
    }

    public static int partOne(ArrayList<ArrayList<Integer>> map){

        int row = -1, col = -1;
        boolean found = false;
        for(int i = 0 ; i < map.size() ; i++){
            for(int j = 0 ; j < map.get(0).size() ;j++){
                if(map.get(i).get(j) == 0){
                    row = i;
                    col = j;
                    found =true;
                    break;
                }
                if(found)
                    break;
            }
        }
        int width = map.get(0).size(), height = map.size();
        boolean[][] visited = new boolean[height][width];
        visited[row][col] = true;
        Queue<int[]> path = new LinkedList<>();
        path.add(new int[]{row, col, 0});
        //path< [row, col, dist] >
        while (!path.isEmpty()){
            int[] current = path.poll();
            if(map.get(current[0]).get(current[1]) == 27){
                return current[2];
            }
            int up = current[0] - 1, down = current[0] + 1, left = current[1] - 1, right = current[1] + 1;

            //up down left right add to BFS search since unweighted
            //say we visited it so we dont search twice
            //since bfs, first time we hit it we found shortest
            if(up >= 0 && !visited[up][current[1]] && map.get(up).get(current[1]) <= map.get(current[0]).get(current[1])+1){
                path.add(new int[]{up, current[1], current[2]+1});
                visited[up][current[1]] = true;
            }
            if(right < width && !visited[current[0]][right] && map.get(current[0]).get(right) <= map.get(current[0]).get(current[1]) +1){
                path.add(new int[]{current[0], right, current[2]+1});
                visited[current[0]][right] = true;
            }
            if(down < height && !visited[down][current[1]] && map.get(down).get(current[1]) <= map.get(current[0]).get(current[1])+1){
                path.add(new int[] {down, current[1], current[2]+1});
                visited[down][current[1]] = true;
            }
            if(left >= 0 && !visited[current[0]][left] && map.get(current[0]).get(left) <= map.get(current[0]).get(current[1]) +1){
                path.add(new int[] {current[0], left, current[2]+1});
                visited[current[0]][left] = true;
            }
        }
        return -1;
    }

    //same idea just start from S and traverse if you are on a level <= nextspace+1
    public static int partTwo(ArrayList<ArrayList<Integer>> map){
        int row = -1, col = -1;
        boolean found = false;
        for(int i = 0 ; i < map.size() ; i++){
            for(int j = 0 ; j < map.get(0).size() ;j++){
                if(map.get(i).get(j) == 27){
                    row = i;
                    col = j;
                    found =true;
                    break;
                }
                if(found)
                    break;
            }
        }
        int width = map.get(0).size(), height = map.size();
        boolean[][] visited = new boolean[height][width];
        visited[row][col] = true;
        Queue<int[]> path = new LinkedList<>();
        path.add(new int[]{row, col, 0});

        //path< [row, col, dist] >
        while (!path.isEmpty()){
            int[] current = path.poll();
            if(map.get(current[0]).get(current[1]) == 1){
                return current[2];
            }
            int up = current[0] - 1, down = current[0] + 1, left = current[1] - 1, right = current[1] + 1;

            if(up >= 0 && !visited[up][current[1]] && map.get(up).get(current[1])+1 >= map.get(current[0]).get(current[1])){
                path.add(new int[]{up, current[1], current[2]+1});
                visited[up][current[1]] = true;
            }
            if(right < width && !visited[current[0]][right] && map.get(current[0]).get(right)+1 >= map.get(current[0]).get(current[1])){
                path.add(new int[]{current[0], right, current[2]+1});
                visited[current[0]][right] = true;
            }
            if(down < height && !visited[down][current[1]] && map.get(down).get(current[1])+1 >= map.get(current[0]).get(current[1])){
                path.add(new int[] {down, current[1], current[2]+1});
                visited[down][current[1]] = true;
            }
            if(left >= 0 && !visited[current[0]][left] && map.get(current[0]).get(left)+1 >= map.get(current[0]).get(current[1])){
                path.add(new int[] {current[0], left, current[2]+1});
                visited[current[0]][left] = true;
            }
        }
        return -1;


    }


}
