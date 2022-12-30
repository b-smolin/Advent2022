import java.util.ArrayList;

public class Triplet {
    int x;
    int y;
    int z;

    public Triplet(int x, int y, int z){
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public boolean equals(Object o){
        if( o == this){
            return true;
        }

        if(!(o instanceof Triplet)){
            return false;
        }

        Triplet compare = (Triplet)o;
        return compare.x == this.x && compare.y == this.y && this.z == compare.z;
    }

    public int hashCode(){
        return x*100 + y*10 + z;
    }

    public String toString(){
        return "x: " + x + " y: " + y + " z: " + z;
    }

    public ArrayList<Triplet> getNeighbors(){
        ArrayList<Triplet> neighbors = new ArrayList<>();
        neighbors.add(new Triplet(x-1, y, z));
        neighbors.add(new Triplet(x+1, y, z));
        neighbors.add(new Triplet(x, y+1, z));
        neighbors.add(new Triplet(x, y-1, z));
        neighbors.add(new Triplet(x, y, z+1));
        neighbors.add(new Triplet(x, y, z-1));
        return neighbors;
    }
}
