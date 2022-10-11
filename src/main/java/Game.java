import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.graphics.TextImage;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class Game {

    private final Screen screen;

    private Arena arena;
    private HUD hud;
    private int score = 0;
    private int hud_height = 3;
    private List<String> mapfiles = Arrays.asList("Maps/Map.txt", "Maps/Map2.txt", "Maps/Map3.txt");

    Iterator<String> filenames = mapfiles.listIterator();

    Game() throws IOException {

        // Load map and create arena + hud accordingly
        GameMap game_map = new GameMap(filenames.next());
        arena = new Arena(game_map);
        hud = new HUD(arena, hud_height, score);

        // Code to Initialize the terminal
        int x = game_map.getDimensions().getX();
        int y = game_map.getDimensions().getY() + hud_height;

        TerminalSize terminalSize = new TerminalSize(x, y);
        DefaultTerminalFactory terminalFactory = new
                DefaultTerminalFactory()
                .setInitialTerminalSize(terminalSize);

        Terminal terminal = terminalFactory.createTerminal();

        screen = new TerminalScreen(terminal);
        screen.setCursorPosition(null); // we don't need a cursor
        screen.startScreen(); // screens must be started
        screen.doResizeIfNecessary();
    }

    private void processKey(KeyStroke key) {
        arena.processKey(key);
    }

    private void draw() throws IOException {
        screen.clear();
        TextGraphics graphics = screen.newTextGraphics();
        arena.draw(graphics);
        hud.draw(graphics);
        screen.refresh();
    }

    private void nextArena() throws FileNotFoundException {
        score += arena.getScore();
        arena = new Arena(new GameMap(filenames.next()));
        hud = new HUD(arena, hud_height, score);
    }

    public void run() throws IOException {

        KeyStroke key;

        while(true){
            draw();
            key = screen.readInput();
            processKey(key);
            if (key.getKeyType() == KeyType.Character && key.getCharacter() == ('q') || arena.getHealth() <= 0) screen.close();
            if (key.getKeyType() == KeyType.EOF) break;

            if (arena.heroGotOut()){
                if(filenames.hasNext()) nextArena();
                else screen.close();
            }
        }


    }

}
