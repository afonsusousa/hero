import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;

public class Game {

    private Screen screen;
    private int x = 50;
    private int y = 25;
    private int hud_height = 3;
    private Arena arena;
    private HUD hud;

    Game() throws IOException {

        // Code to Initialize the terminal
        TerminalSize terminalSize = new TerminalSize(x, y+hud_height);
        DefaultTerminalFactory terminalFactory = new
                DefaultTerminalFactory()
                .setInitialTerminalSize(terminalSize);

        Terminal terminal = terminalFactory.createTerminal();

        screen = new TerminalScreen(terminal);
        screen.setCursorPosition(null); // we don't need a cursor
        screen.startScreen(); // screens must be started
        screen.doResizeIfNecessary();

        arena = new Arena(x,y);
        hud = new HUD(x,y, hud_height);
    }

    private void processKey(KeyStroke key) {
        arena.processKey(key);
    }

    private void draw() throws IOException {
        screen.clear();
        TextGraphics graphics = screen.newTextGraphics();
        arena.draw(graphics);
        hud.draw(graphics, String.valueOf(arena.getScore()));
        screen.refresh();
    }

    public void run() throws IOException {

        KeyStroke key;

        while(true){
            draw();
            key = screen.readInput();
            processKey(key);
            if (key.getKeyType() == KeyType.Character && key.getCharacter() == ('q') || arena.checkCollision()) screen.close();
            if (key.getKeyType() == KeyType.EOF) break;
        }
    }

}
