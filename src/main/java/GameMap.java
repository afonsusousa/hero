import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;


public class GameMap {

    private final MapFile mapfile;
    private final List<WallElement> mapwalls;
    private final List<Position> mapdoors;

    public GameMap(String filename) throws FileNotFoundException {
        this.mapfile = new MapFile(filename);
        this.mapwalls = createWalls();
        this.mapdoors = createDoors();
    }

    private List<Position> createDoors(){
        List<Position> listdoors = new ArrayList<>();

        int doorsize = 5;
        Position door = mapfile.getDoor();

        if(door.getX() == 0 || door.getX() == mapfile.getDimensions().getX()-1) {
            for(int i = 0; i < doorsize; i++){
                listdoors.add(new Position(door.getX(), door.getY()+i));
            }
        }

        if(door.getY() == 0 || door.getY() == mapfile.getDimensions().getY()-1){
            for(int i = 0; i < doorsize; i++){
                listdoors.add(new Position(door.getX()+i, door.getY()));
            }
        }


        return listdoors;
    }

    private List<WallElement> createWalls() {
        List<WallElement> walls = new ArrayList<>();

        for(Wall mapwall : mapfile.getMapWalls()){
            walls.addAll(mapwall.getWallList());
        }
        return walls;
    }

    public Position getDimensions() {
        return mapfile.getDimensions();
    }

    public List<Position> getDoors(){ return mapdoors;}

    public List<WallElement> getWalls(){ return mapwalls;}

}
