import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class HUD {

    private HUDElement health;
    private HUDElement score;
    private int carried_score;
    private HUDElement position;

    private int y;
    private int width;
    private int height;

    private Arena arena;

    public HUD(Arena arena, int height, int carried_score){

        this.carried_score = carried_score;
        this.y = arena.getHeight() + 1;
        this.width = arena.getWidth();
        this.height = height;

        Position left_pos = new Position(0, y +height/2);
        Position center_pos = new Position(width/2, y + height/2);
        Position right_pos = new Position(width, y + height/2);

        health = new HUDElement(left_pos, "#FF0000");
        score = new HUDElement(center_pos, "#FFFFFF");
        position = new HUDElement(right_pos, "#FFFFFF");

        this.arena = arena;

    }

    public void draw(TextGraphics graphics) {

        graphics.setBackgroundColor(TextColor.Factory.fromString("#000000"));
        graphics.fillRectangle(new TerminalPosition(0, y), new TerminalSize(width, height), ' ');


        score.updateContent(String.valueOf(arena.getScore()+carried_score), graphics);
        position.updateContent(String.format("(%d,%d)", arena.getHeroPosition().getX(), arena.getHeroPosition().getY()), graphics);
        health.updateContent("H".repeat(arena.getHealth()), graphics);

        score.draw(graphics);
        position.draw(graphics);
        health.draw(graphics);

    }
}
