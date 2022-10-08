import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

import java.util.concurrent.ThreadLocalRandom;

import static java.lang.Math.abs;

public class Monster extends Entity{

    public Monster(int x, int y){
        super(x, y);
    }

    private Position moveRandom(){
        int randomNum = ThreadLocalRandom.current().nextInt(1,  4);
        switch (randomNum){
            case 1:
                return moveUp();
            case 2:
                return moveDown();
            case 3:
                return moveLeft();
            case 4:
                return moveRight();
        }
        return null;
    }

    private Position chaseHero(Position hero){
        int x = hero.getX() - getPosition().getX();
        int y = hero.getY() - getPosition().getY();

        if(abs(x) >= abs(y))
            return (x > 0 ? moveRight() : moveLeft());
        else
            return (y > 0 ? moveDown() : moveUp());
    }

    public void draw(TextGraphics graphics) {
        graphics.setForegroundColor(TextColor.Factory.fromString("#EA6559"));
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(getPosition().getX(), getPosition().getY()), "M");
    }

    public Position move(Position hero){

        int randomNum = ThreadLocalRandom.current().nextInt(0,  2);
        if (randomNum == 0) return chaseHero(hero);
        return moveRandom();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Monster p = (Monster) o;
        return this.getPosition().equals(p.getPosition());
    }

}
