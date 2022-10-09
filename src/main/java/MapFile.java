import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MapFile {

    private String mapfile;
    private Scanner mapscan;

    public MapFile(String mapfilename) throws FileNotFoundException {
        System.out.println("Aqui");

        this.mapfile = mapfilename;
        this.mapscan = readMapFile(mapfilename);
    }
    private Scanner readMapFile(String filename) throws FileNotFoundException {
        return new Scanner(new File(filename));
    }

    public Scanner getMapScan() {
        return mapscan;
    }
}
