import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class HUD {

    private int width;
    private int height;
    private int size;

    public HUD(int x, int y, int s){
        width = x;
        height = y;
        size = s;
    }

    public void draw(TextGraphics graphics, String score) {

        graphics.setBackgroundColor(TextColor.Factory.fromString("#000000"));
        graphics.fillRectangle(new TerminalPosition(0, height+1), new TerminalSize(width, size), ' ');

        graphics.setForegroundColor(TextColor.Factory.fromString("#FFFFFF   "));
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(width/2, height+1+size/2), score);

    }
}