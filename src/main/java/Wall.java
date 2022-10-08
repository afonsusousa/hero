import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class Wall {
    private Position position;

    public Wall(int x, int y){
        position = new Position(x, y);
    }

    public void setPosition(Position position){
        this.position = position;
    }
    public Position getPosition(){
        return this.position;
    }


    public int getX() {return position.getX();}
    public void setX(int x) {position.setX(x);}
    public int getY() {return position.getY();}
    public void setY(int y) { position.setY(y);}

    public void draw(TextGraphics graphics){

        graphics.setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(position.getX(),
                position.getY()), "@");
    }

}
