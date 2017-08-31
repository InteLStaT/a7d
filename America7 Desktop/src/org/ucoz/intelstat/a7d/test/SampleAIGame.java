package org.ucoz.intelstat.a7d.test;

import java.io.IOException;
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
		human = game.new Player("You", humanCtrl);
		ai = game.new Player("AI", aiCtrl);
	}

	public static void main(String[] args) {
		printMessage();
		new SampleAIGame().startGame();
	}

	private static void printMessage() {
		System.out.println("Welcome to the America 7 card game.");
		System.out.println("This game uses a card set that contains 32 cards.");
		System.out.println("Each card has a rank and a suit. There are 8 ranks and 4 suits.");
		System.out.println("When you see a card's name, the 1st word is the rank, the 2nd is the suit.");
		System.out.println("At the start of the game, every player is given 5 cards from a shuffled deck.");
		System.out.println("Now, your only opponent is going to be a single robot.");
		System.out.println("You take turns after each other, placing one card down on top of the previous one.");
		System.out.println("The first card that is placed down is randomly taken from the deck.");
		System.out.println("The rule for placing cards is very simple: you can put suit on suit or rank on rank.");
		System.out.println("Additionally, you can put the SEVEN cards on anything.");
		System.out.println(
				"If you can't place a card or just don't feel like it, you can draw a card from the deck at any time.");
		System.out.println("The first one to place all their cards down wins!");
		System.out.print("\033[33mIf you're ready, press enter.\033[0m");
		try {
			if (SampleConsoleController.reader.readLine().equals("debug")) {
				debug = true;
			}
			System.out.println();
		} catch (IOException e) {
		}
	}
}
