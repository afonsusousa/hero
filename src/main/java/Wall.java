import java.util.ArrayList;
import java.util.List;

public class Wall {
    private int size;
    private String orientation;
    private Position anchor;

    public Wall(Position position, int size, String orientation){
        this.anchor  = position;
        this.orientation = orientation;
        this.size = size;
    }

    public List<WallElement> getWallList(){

        List<WallElement> listwalls = new ArrayList<>();


            int anchor_x = anchor.getX();
            int anchor_y = anchor.getY();

            switch (orientation){
                case "Up":
                    for(int i = anchor_y; i > anchor_y - size; i--)
                        listwalls.add(new WallElement(anchor_x, i));
                    break;
                case "Down":
                    for(int i = anchor_y; i < size + anchor_y; i++)
                        listwalls.add(new WallElement(anchor_x, i));
                    break;
                case "Left":
                    for(int i = anchor_x; i > anchor_x - size; i--)
                        listwalls.add(new WallElement(i, anchor_y));
                    break;
                case "Right":
                    for(int i = anchor_x; i < size + anchor_x; i++)
                        listwalls.add(new WallElement(i, anchor_y));
                    break;
            }

        return listwalls;
    }
}
