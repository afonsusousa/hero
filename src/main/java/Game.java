import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;

public class Game {

    private Screen screen;
    private int x = 10;
    private int y = 10;

    private Arena arena = new Arena(50,50);

    Game() throws IOException {

        // Code to Initialize the terminal
        TerminalSize terminalSize = new TerminalSize(50, 50);
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
        arena.draw(screen.newTextGraphics());
        screen.refresh();
    }

    public void run() throws IOException {

        KeyStroke key;

        while(true){
            draw();
            key = screen.readInput();
            processKey(key);

            if (key.getKeyType() == KeyType.Character && key.getCharacter() == ('q')) screen.close();
            if (key.getKeyType() == KeyType.EOF) break;

        }
    }

}
