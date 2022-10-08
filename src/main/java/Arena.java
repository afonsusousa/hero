import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Arena {
    private int height;
    private int width;

    private Hero hero = new Hero(10,10);

    private List<Wall> walls;
    private List<Coin> coins;

    public Arena(int x, int y){
        this.width = x;
        this.height = y;

        this.walls = createWalls();
        this.coins = createCoins();
    }

    private List<Wall> createWalls() {
        List<Wall> walls = new ArrayList<>();
        for (int c = 0; c < width; c++) {
            walls.add(new Wall(c, 0));
            walls.add(new Wall(c, height - 1));
        }
        for (int r = 1; r < height - 1; r++) {
            walls.add(new Wall(0, r));
            walls.add(new Wall(width - 1, r));
        }
        return walls;
    }

    private List<Coin> createCoins() {
        Random random = new Random();
        ArrayList<Coin> coins = new ArrayList<>();
        for (int i = 0; i < 5; i++)
            coins.add(new Coin(random.nextInt(width - 2) + 1,
                    random.nextInt(height - 2) + 1));
        return coins;
    }

    private boolean canMoveHero(Position position){
        return !walls.contains(new Wall(position.getX(), position.getY()));
    }

    private void moveHero(Position position){
        if(canMoveHero(position)) hero.setPosition(position);
    }

    private Position moveUp(){
        return new Position(hero.getPosition().getX(), hero.getPosition().getY()-1);
    }
    private Position moveDown(){
        return new Position(hero.getPosition().getX(), hero.getPosition().getY()+1);
    }
    private Position moveLeft(){
        return new Position(hero.getPosition().getX()-1, hero.getPosition().getY());
    }
    private Position moveRight(){
        return new Position(hero.getPosition().getX()+1, hero.getPosition().getY());
    }

    public void processKey(KeyStroke key){
        switch(key.getKeyType()){
            case ArrowUp:
                moveHero(moveUp());
                break;
            case ArrowDown:
                moveHero(moveDown());
                break;
            case ArrowLeft:
                moveHero(moveLeft());
                break;
            case ArrowRight:
                moveHero(moveRight());
                break;
        }
    }

    public void draw(TextGraphics graphics){

        graphics.setBackgroundColor(TextColor.Factory.fromString("#336699"));
        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(width, height), ' ');

        for (Wall wall : walls)
            wall.draw(graphics);

        for (Coin coin : coins)
            coin.draw(graphics);

        hero.draw(graphics);
    }
}
