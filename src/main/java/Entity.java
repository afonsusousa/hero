import java.util.List;

public abstract class Entity extends Element{

    public Entity(int x, int y){
        super(x, y);
    }

    public Position moveUp(){
        return new Position(getPosition().getX(), getPosition().getY()-1);
    }
    public Position moveDown(){
        return new Position(getPosition().getX(), getPosition().getY()+1);
    }
    public Position moveLeft(){
        return new Position(getPosition().getX()-1, getPosition().getY());
    }
    public Position moveRight(){
        return new Position(getPosition().getX()+1, getPosition().getY());
    }

}
