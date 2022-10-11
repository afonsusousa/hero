import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class Hero extends Entity{

    private int health;
    private int score = 0;

    public Hero(int x, int y, int health) {
        super(x, y);
        this.health = health;
    }

    public void draw(TextGraphics graphics){

        graphics.setForegroundColor(TextColor.Factory.fromString("#FFFF33"));
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(getPosition().getX(), getPosition().getY()), "X");
    }

    public int getScore(){return score;}

    public void addScore(int value){score += value;}

    public int getHealth() {
        return health;
    }

    public void damage(int hit){
        this.health -= hit;
    }
}
