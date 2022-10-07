import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.screen.*;
import com.googlecode.lanterna.terminal.*;

import java.io.IOException;

public class Application {
	public static void main(String[] args) throws IOException {
		Game game = new Game();
		game.run();
	}
}
