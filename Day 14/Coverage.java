import java.util.ArrayList;

public class Coverage {
    int sensorX;
    int sensorY;
    int beaconX;
    int beaconY;
    int radius;

    public Coverage(int sX, int sY, int bX, int bY){
        this.sensorX = sX;
        this.sensorY = sY;
        this.beaconX = bX;
        this.beaconY = bY;
        this.radius = Math.abs(sX-bX) + Math.abs(bY-sY);
    }

    public String toString(){
        return "Sensor X : " + sensorX + " Sensor Y : " + sensorY + " Beacon X : " + beaconX + " Beacon Y : " + beaconY + " Radius : " + radius;
    }

    public ArrayList rowOverlap(int y){
        ArrayList<String> overlaps = new ArrayList<>();
        if( y >= sensorY && y <= sensorY+radius){
            int dif = sensorY+radius-y;
            for(int i = sensorX - dif ; i <= sensorX + dif ; i++){
                    overlaps.add("X : " + i + " Y : " + y);
            }
        }
        if (y >= sensorY-radius && y < sensorY){
            int dif = y-(sensorY - radius);
            for(int i = sensorX - dif ; i <= sensorX + dif ; i++){
                    overlaps.add("X : " + i + " Y : " + y);
            }
        }
        return  overlaps;
    }

    public boolean allowedPosition(int x, int y){
        return !((Math.abs(sensorX - x) + Math.abs(sensorY - y)) <= radius);
    }

    public ArrayList getPerimeter(){
        ArrayList<Integer[]> perim = new ArrayList();
            for(int i = sensorY+(radius+1) ; i >= sensorY-(radius+1) ; i--){
                int dif = sensorY + (radius + 1) - i;
                if(i <= 4000000 && i >= 0 && sensorX - dif >= 0 && sensorX + dif <=4000000) {
                    perim.add(new Integer[]{sensorX + dif, i});
                    perim.add(new Integer[]{sensorX - dif, i});
                }
            }
        return perim;
    }

    public String beaconString(){
        return "X : " + beaconX + " Y : " + beaconY;
    }
}
