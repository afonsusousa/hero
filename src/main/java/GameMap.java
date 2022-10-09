import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GameMap {

    private List<MapWall> mapwalls;
    private List<Wall> walls;
    private MapFile mapfile;

    public GameMap(String filename) throws FileNotFoundException {
        this.mapfile = new MapFile(filename);
        this.mapwalls = readMapWalls(mapfile.getMapScan());
        this.walls = createWalls(mapwalls);
    }

    private List<Wall> createWalls(List<MapWall> map_walls){

        List<Wall> listwalls = new ArrayList<>();

        for(MapWall mapwall : map_walls){

            int anchor_x = mapwall.getAnchor().getX();
            int anchor_y = mapwall.getAnchor().getY();
            int size = mapwall.getSize();

            if(mapwall.isVertical()){
                for(int i = anchor_y; i < size + anchor_y; i++)
                    listwalls.add(new Wall(anchor_x, i));
            } else {
                for(int i = anchor_x; i < size + anchor_x; i++)
                    listwalls.add(new Wall(i, anchor_y));
            }
        }

        return listwalls;
    }

    private List<MapWall> readMapWalls(Scanner scanner){

        Position position;

        List<MapWall> listmapwalls = new ArrayList<>();

        while(scanner.hasNextLine()){
            String[] wall = scanner.nextLine().split("\\s");

            position = new Position(Integer.parseInt(wall[0]), Integer.parseInt(wall[1]));

            listmapwalls.add(new MapWall(position, Integer.parseInt(wall[2]), Boolean.parseBoolean(wall[3])));
        }
        return listmapwalls;
    }

    public List<Wall> getWalls() {
        return walls;
    }
}
