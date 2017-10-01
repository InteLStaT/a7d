package org.ucoz.intelstat.a7d.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;

import org.ucoz.intelstat.a7.core.Controller;
import org.ucoz.intelstat.a7.core.Game;
import org.ucoz.intelstat.a7.core.Game.Player;
import org.ucoz.intelstat.a7.core.GameListener;
import org.ucoz.intelstat.a7d.GameWindow;

public class SampleAIGame extends GameWindow {

	private Player human;
	private Controller humanCtrl;
	private Player ai;
	private Controller aiCtrl;
	private static boolean debug = false;

	public SampleAIGame() {
		super(new Game());
		if (!debug) {
			System.setErr(new PrintStream(new OutputStream() {

				@Override
				public void write(int b) throws IOException {
				

				}
			}));
			;
		}
		humanCtrl = new SampleConsoleController();
		aiCtrl = new AIController();
		game.addGameListener((GameListener) humanCtrl);
		human = game.new Player("Te", humanCtrl);
		ai = game.new Player("Bot", aiCtrl);
		human.readyUp();
		ai.readyUp();
	}

	public static void main(String[] args) {
		printMessage();
		new SampleAIGame().startGame();
	}

	private static void printMessage() {
		String[] msg = {
				"Üdv az Amerika 7 kártyajátékban. Remélem ismered a szabályokat!",
				"Egy robot ellen fogsz játszani, a számítógép dönti el, hogy ki kezd.",
				"Néhány fontos kifejezés, amit játék közben látni fogsz:",
				"A \"legfelsö kártya\" az a kártya, ami felfele néz, és erre kell tenni",
				"a saját kártyáidat. A \"pakli\"-ban vannak a lefele nézö kártyák, innen",
				"fogsz tudni venni. A két vonalkával kezdödö sorok az úgymond \"chat\" ",
				"(igazából nem tudsz chatelni, az magától történik).",
				"Azt, hogy hogyan kell kártyát tenni és venni, majd játék közben elmondom.",
				AnsiColor.YELLOW + "Ha kész vagy, nyomj entert!" + AnsiColor.RESET
		};
		for(String line : msg) {
			System.out.println(line);
		}
		try {
			if (SampleConsoleController.reader.readLine().equals("debug")) {
				debug = true;
			}
			System.out.println();
		} catch (IOException e) {
		}
	}
}
