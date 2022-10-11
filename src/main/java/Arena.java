import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Arena {

    private final int height;
    private final int width;

    public Hero hero = new Hero(10,10, 5);

    private final List<WallElement> walls;
    private final List<Coin> coins;
    private final List<Monster> monsters;
    private final List<Position> doors;

    public Arena(GameMap map) {

        this.width = map.getDimensions().getX();
        this.height = map.getDimensions().getY();
        this.doors = map.getDoors();
        this.walls = createWalls();
        this.walls.addAll(map.getWalls());

        this.coins = createCoins();
        this.monsters = createMonsters();
    }

    private List<WallElement> createWalls() {
        List<WallElement> walls = new ArrayList<>();
        for (int c = 0; c < width; c++) {
            walls.add(new WallElement(c, 0));
            walls.add(new WallElement(c, height));
        }
        for (int r = 1; r < height; r++) {
            walls.add(new WallElement(0, r));
            walls.add(new WallElement(width - 1, r));
        }
        return walls;
    }

    private List<Coin> createCoins() {

        Random random = new Random();
        ArrayList<Coin> coins = new ArrayList<>();

        int x, y;

        for (int i = 0; i < 5; i++){

            x = random.nextInt(width - 2) + 1;
            y = random.nextInt(height - 2) + 1;

            if (walls.contains(new WallElement(x,y))) i--;
            else coins.add(new Coin(x,y));
        }
        return coins;
    }

    private List<Monster> createMonsters() {
        Random random = new Random();
        ArrayList<Monster> monsters = new ArrayList<>();
        for (int i = 0; i < 2; i++)
            monsters.add(new Monster(random.nextInt(width - 2) + 1,
                    random.nextInt(height - 2) + 1));
        return monsters;
    }

    private void openDoor(){
        for(Position door : doors)
            walls.remove(new WallElement(door));
    }

    private boolean canHeroMove(Position position){
        return !walls.contains(new WallElement(position.getX(), position.getY()));
    }

    private void retrieveCoins(){
        for(Coin coin : coins) {
            if (coin.getPosition().equals(hero.getPosition())) {
                coins.remove(coin);
                hero.addScore(100);
                break;
            }
        }
    }

    private boolean canMonsterMove(Position position){
        return !walls.contains(new WallElement(position.getX(), position.getY())) && !monsters.contains(new Monster(position.getX(), position.getY()));
    }

    private void moveMonsters(){

        for(Monster monster : monsters){

            //Monster has 2/3 chance of making the best move towards the player, otherwise moves randomly

            int randomNum = ThreadLocalRandom.current().nextInt(1,  4);
            Position p = randomNum == 1 ? monster.move() : monster.move(hero.getPosition());

            if(canMonsterMove(p)){
                monster.setPosition(p);
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

            retrieveCoins();
            if(coins.isEmpty()) openDoor();

            // moveMonsters();
            if(checkCollision()) hero.damage(1);
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

    public Position getHeroPosition(){
        return hero.getPosition();
    }
    public int getScore() {
        return hero.getScore();
    }
    public int getHealth(){return hero.getHealth();}
    public int getHeight(){return height;}
    public int getWidth(){return width;}

    public boolean heroGotOut(){
        return !(hero.getPosition().getX() < width
                && hero.getPosition().getX() > 0
                && hero.getPosition().getY() < height
                && hero.getPosition().getY() > 0);
    }

    public void draw(TextGraphics graphics){

        graphics.setBackgroundColor(TextColor.Factory.fromString("#336699"));
        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(width, height), ' ');

        for (WallElement wall : walls)
            wall.draw(graphics);

        for (Coin coin : coins)
            coin.draw(graphics);

        for (Monster monster : monsters)
            monster.draw(graphics);

        hero.draw(graphics);
    }
}
