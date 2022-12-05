public class Command {
    int moves;
    int origin;
    int dest;
    public Command(int moves, int origin, int dest){
        this.moves = moves;
        this.origin = origin-1;
        this.dest = dest-1;
    }

    public String toString(){
        return "moves " + moves + " origin " + origin + " dest " + dest;
    }
}
