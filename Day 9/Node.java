public class Node {
    int x;
    int y;

    public Node(int x, int y){
        this.x = x;
        this.y = y;
    }

    public String toString(){
        return Integer.toString(x) + " " + Integer.toString(y);
    }
}
