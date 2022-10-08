import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;

public class Arena {
    private int height;
    private int width;

    private Hero hero = new Hero(10,10);

    public Arena(int width, int height){
        this.width = width;
        this.height = height;
    }

    private boolean canMoveHero(Position position){
        return position.getX() >= 0 && position.getX() < width
            && position.getY() >= 0 && position.getY() < height;
    }

    public void moveHero(Position position){
        if(canMoveHero(position)) hero.setPosition(position);
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

    public void draw(Screen screen){
        hero.draw(screen);
    }
}
