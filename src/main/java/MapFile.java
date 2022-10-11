import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MapFile {

    private final Scanner mapscan;
    private final List<Wall> mapwalls;
    private final Position dimensions;
    private final Position door;

    public MapFile(String filename) throws FileNotFoundException {

        this.mapscan = new Scanner(new File(filename));

        this.dimensions = readMapDimensions();
        this.door = readMapDoor();
        this.mapwalls = readMapWalls();
    }


    private Position readMapDimensions(){
        String[] coordinates = mapscan.nextLine().split("\\s");
        return new Position(Integer.parseInt(coordinates[0]), Integer.parseInt(coordinates[1]));
    }

    private List<Wall> readMapWalls(){

        Position position;

        List<Wall> listmapwalls = new ArrayList<>();

        while(mapscan.hasNextLine()){
            String[] wall = mapscan.nextLine().split("\\s");

            position = new Position(Integer.parseInt(wall[0]), Integer.parseInt(wall[1]));

            listmapwalls.add(new Wall(position, Integer.parseInt(wall[2]), wall[3]));
        }
        return listmapwalls;
    }

    private Position readMapDoor(){
        String[] door = mapscan.nextLine().split("\\s");
        return new Position(Integer.parseInt(door[0]), Integer.parseInt(door[1]));
    }

    public List<Wall> getMapWalls() {
        return mapwalls;
    }

    public Position getDimensions() {
        return dimensions;
    }

    public Position getDoor() {
        return door;
    }
}
