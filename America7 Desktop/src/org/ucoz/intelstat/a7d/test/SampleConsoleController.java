package org.ucoz.intelstat.a7d.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.ucoz.intelstat.a7.core.Controller;
import org.ucoz.intelstat.a7.core.Game;
import org.ucoz.intelstat.a7.core.Game.Player;
import org.ucoz.intelstat.a7.core.GameListener;
import org.ucoz.intelstat.a7.core.GamePassiveEvent;
import org.ucoz.intelstat.a7.core.GameQuantitativeEvent;
import org.ucoz.intelstat.a7.core.GameState;
import org.ucoz.intelstat.gc.GCard;
import org.ucoz.intelstat.gc.GCard.Rank;
import org.ucoz.intelstat.gc.GCard.Suit;

// And oh boi, this is just a console app.
public class SampleConsoleController implements Controller, GameListener {

	private Player me;
	private int oppCardCount = Game.INITIAL_HAND_SIZE;
	public static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

	@Override
	public GCard proposeCard(List<GCard> handView, Game game, Player player) {

		GCard card = null;

		printProposeInformation(handView, game);
		System.out.println();

		int idx = readIndexFromUser(0, handView.size(),
				"Enter the card's number you want to put in the pile, or 0 to draw: > ");

		if (idx == 0) {
			card = null;
			chatPropose(true, card, game);
		} else {
			card = handView.get(idx - 1);
			chatPropose(false, card, game);
		}
		return card;
	}

	@Override
	public GCard proposeCardWithSuit(List<GCard> handView, Game game, Player player, Suit suit) {

		GCard card = null;

		printProposeInformation(handView, game);
		System.out.println();
		System.out.println("You have to play a card with suit " + colormap.get(suit) + suit.toString()
				+ (AnsiColor.RESET + "."));

		int idx = readIndexFromUser(0, handView.size(),
				"Enter the card's number you want to put in the pile, or 0 to draw: > ");

		if (idx == 0) {
			card = null;
			chatPropose(true, card, game);
		} else {
			card = handView.get(idx - 1);
			chatPropose(false, card, game);
		}

		return card;
	}

	@Override
	public GCard.Suit askForSuit(List<GCard> handView, Game game, Player player) {
		System.out.println("You have to ask for a suit.");
		System.out.print("The suits are: | ");

		for (GCard.Suit suit : GCard.Suit.values()) {
			System.out.print(AnsiColor.RESET + (suit.ordinal() + 1) + ": " + colormap.get(suit) + suit.toString() + (AnsiColor.RESET + " | "));
		}

		System.out.println("\033[0m");
		
		GCard.Suit suit = GCard.Suit.values()[readIndexFromUser(1, 4, "Enter the suit's number you want to ask for: ") - 1];
		
		chatAsked(suit);
		
		return suit;
	}

	
	private int readIndexFromUser(int min, int max, String msg) {
		int idx = -1;
		while (true) {
			System.out.print(msg);
			try {
				idx = Integer.parseInt(reader.readLine());
			} catch (IOException e) {
				System.out.println("Some kind of error happened that I can't handle :(\nI will quit :(");
				System.exit(1);
			} catch (NumberFormatException e) {
				idx = -1;
			}
			if (idx >= min && idx <= max) {
				break;
			} else {
				System.out.println("Invalid answer. Try again.");
			}
		}
		return idx;
	}

	private void printProposeInformation(List<GCard> handView, Game game) {
		System.out.println("Top card: " + AnsiColor.BLUE_BACKGROUND + color(game.getTopCard()) + ". Cards in deck: "
				+ game.getStockSize());
		System.out.println("Opponent cards: " + oppCardCount);
		System.out.print("Your cards are: | ");
		for (int i = 0; i < handView.size(); i++) {
			System.out.print(i + 1 + ": " + color(handView.get(i)) + " | ");
		}
	}

	// Chat
	private void chatPropose(boolean drew, GCard card, Game game) {
		if(game.isAceStreak() && drew) {
			System.out.println();
			System.out.println("-- You: You skipped me, bot. What a move!");
			System.out.println();
		}
		else if (drew) {
			System.out.println();
			System.out.println("-- You: I drew.");
			System.out.println();
		} else {
			System.out.println();
			System.out.println("-- You: I put the card " + color(card) + " into the pile.");
			System.out.println();
		}
	}
	
	private void chatAsked(GCard.Suit suit) {
		System.out.println();
		System.out.println("-- You: I'm asking for " + colormap.get(suit) + suit.toString() + AnsiColor.RESET);
		System.out.println();
	}

	public static Map<GCard.Suit, String> colormap = new HashMap<GCard.Suit, String>();
	static {
		colormap.put(GCard.Suit.ACORNS, AnsiColor.YELLOW);
		colormap.put(GCard.Suit.LEAVES, AnsiColor.GREEN);
		colormap.put(GCard.Suit.HEARTS, AnsiColor.RED);
		colormap.put(GCard.Suit.BELLS, AnsiColor.PURPLE);
	}

	// Quick color hack
	static String color(GCard card) {
		String rank;
		String suit = surround(card.getSuit().toString(), colormap.get(card.getSuit()));

		if (card.getRank() == Rank.SEVEN) {
			rank = surround(card.getRank().toString(), AnsiColor.BRIGHT + AnsiColor.CYAN, AnsiColor.RESET_FOREGROUND);
		} else {
			rank = surround(card.getRank().toString(), AnsiColor.CYAN, AnsiColor.RESET_FOREGROUND);
		}

		return rank + " of " + suit;
	}

	static String surround(String str, String prefix) {
		return surround(str, prefix, AnsiColor.RESET);
	}

	static String surround(String str, String prefix, String suffix) {
		return prefix + str + suffix;
	}

	// EVENTS
	@Override
	public void playerJoined(GamePassiveEvent e) {

	}

	@Override
	public void playerLeft(GamePassiveEvent e) {

	}

	@Override
	public void currentPlayerChanged(GamePassiveEvent e) {

	}

	@Override
	public void gameStateChanged(GamePassiveEvent e) {
		if (e.getOldState() == GameState.PREGAME) {
			System.out.println("The top card is \033[44m" + color(e.getGame().getTopCard()) + "\033[0m.");
			System.out.println(AnsiColor.YELLOW + e.getGame().getStartingPlayer().getName() + " will start!" + AnsiColor.RESET);
			System.out.println();
		}
		if (e.getNewState() == GameState.POSTGAME) {
			System.out.println("Winner: " + e.getGame().getWinner().getName());
			System.out.println("You can safely close this window now (and play a new game perhaps?)");
		}
	}

	@Override
	public void playerCardCountChanged(GameQuantitativeEvent e, Player player) {
		if (player != me) {
			oppCardCount = e.getNewValue();
		}
	}

	@Override
	public void roundChanged(GameQuantitativeEvent e) {

	}

	@Override
	public void stockRefilled(Game game) {

	}

	@Override
	public void stockSizeDecreased(GameQuantitativeEvent e) {

	}

	@Override
	public void pileSizeIncreased(GameQuantitativeEvent e) {

	}

}
