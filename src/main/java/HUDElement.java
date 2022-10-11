import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

import java.util.Map;


public class HUDElement {

    private Position position;

    private String content;
    private String color;

    public HUDElement(Position position, String color){
        this.position = position;
        this.color = color;
    }

    public void updateContent(String content, TextGraphics graphics){

        this.content = content;

        if(position.getX() + content.length() > graphics.getSize().getColumns() || position.getX() + content.length() == graphics.getSize().getColumns() - 1){
            position.setX(graphics.getSize().getColumns() - content.length());
        }

        if(position.getX()-1 < graphics.getSize().getColumns()/2 && position.getX()-1 + content.length() > graphics.getSize().getColumns()/2 ){
            position.setX(graphics.getSize().getColumns()/2 - content.length()/2 - 1);
        }

    }

    public void draw(TextGraphics graphics){

        graphics.setForegroundColor(TextColor.Factory.fromString(color));
        graphics.putString(new TerminalPosition(position.getX(), position.getY()), content);
    }

    public void setColor(String color){
        this.color = color;
    }
}
