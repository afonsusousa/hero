public class MapWall {
    private int size;
    private boolean vertical;
    private Position anchor;

    public MapWall(Position position, int size, boolean vertical){
        this.anchor  = position;
        this.vertical = vertical;
        this.size = size;
    }

    public int getSize() {
        return size;
    }

    public boolean isVertical() {
        return vertical;
    }

    public Position getAnchor() {
        return anchor;
    }
}
