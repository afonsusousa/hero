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
    private int score;

    private Hero hero = new Hero(10,10);

    private List<Wall> walls;
    private List<Coin> coins;
    private List<Monster> monsters;

    public Arena(int x, int y){
        this.width = x;
        this.height = y;

        this.walls = createWalls();
        this.coins = createCoins();
        this.monsters = createMonsters();
    }

    public int getScore() {
        return score;
    }

    private List<Wall> createWalls() {
        List<Wall> walls = new ArrayList<>();
        for (int c = 0; c < width; c++) {
            walls.add(new Wall(c, 0));
            walls.add(new Wall(c, height));
        }
        for (int r = 1; r < height; r++) {
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

    private List<Monster> createMonsters() {
        Random random = new Random();
        ArrayList<Monster> monsters = new ArrayList<>();
        for (int i = 0; i < 3; i++)
            monsters.add(new Monster(random.nextInt(width - 2) + 1,
                    random.nextInt(height - 2) + 1));
        return monsters;
    }

    private boolean canHeroMove(Position position){
        return !walls.contains(new Wall(position.getX(), position.getY()));
    }

    private void retrieveCoins(Position position){
        for(Coin coin : coins) {
            if (coin.getPosition().equals(hero.getPosition())) {
                coins.remove(coin);
                score += 10;
                break;
            }
        }
    }

    private boolean canMonsterMove(Position position){
        return !walls.contains(new Wall(position.getX(), position.getY())) && !monsters.contains(new Monster(position.getX(), position.getY()));
    }

    private void moveMonsters(Position hero){

        for(Monster monster : monsters){
            Position move =monster.move(hero);
            if(canMonsterMove(move)){
                monster.setPosition(move);
            }
        }
    }

    public boolean checkCollision(){

        for (Monster monster : monsters){
            if (monster.getPosition().equals(hero.getPosition())) return true;
        }
        return false;
    }

    private void moveHero(Position position){
        if(canHeroMove(position)){
            hero.setPosition(position);
            retrieveCoins(position);
            moveMonsters(hero.getPosition());
            if(checkCollision()) System.out.println("Died");
        }
    }

    public void processKey(KeyStroke key){
        switch(key.getKeyType()){
            case ArrowUp:
                moveHero(hero.moveUp());
                break;
            case ArrowDown:
                moveHero(hero.moveDown());
                break;
            case ArrowLeft:
                moveHero(hero.moveLeft());
                break;
            case ArrowRight:
                moveHero(hero.moveRight());
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

        for (Monster monster : monsters)
            monster.draw(graphics);

        hero.draw(graphics);
    }
}
