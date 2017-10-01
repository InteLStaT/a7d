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
				"�dv az Amerika 7 k�rtyaj�t�kban. Rem�lem ismered a szab�lyokat!",
				"Egy robot ellen fogsz j�tszani, a sz�m�t�g�p d�nti el, hogy ki kezd.",
				"N�h�ny fontos kifejez�s, amit j�t�k k�zben l�tni fogsz:",
				"A \"legfels� k�rtya\" az a k�rtya, ami felfele n�z, �s erre kell tenni",
				"a saj�t k�rty�idat. A \"pakli\"-ban vannak a lefele n�z� k�rty�k, innen",
				"fogsz tudni venni. A k�t vonalk�val kezd�d� sorok az �gymond \"chat\" ",
				"(igaz�b�l nem tudsz chatelni, az mag�t�l t�rt�nik).",
				"Azt, hogy hogyan kell k�rty�t tenni �s venni, majd j�t�k k�zben elmondom.",
				AnsiColor.YELLOW + "Ha k�sz vagy, nyomj entert!" + AnsiColor.RESET
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
