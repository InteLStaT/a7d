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
import org.ucoz.intelstat.gc.GCard.Suit;

public class SampleConsoleController implements Controller, GameListener {

	private Player me;
	private int oppCardCount = Game.INITIAL_HAND_SIZE;
	public static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

	@Override
	public GCard proposeCard(List<GCard> handView, Game game, Player player) {

		GCard card = null;

		System.out.println(
				"Top card: " + "\033[44m" + color(game.getTopCard().toString()) + ". Cards in deck: " + game.getDrawDeckSize());
		System.out.println("Opponent cards: " + oppCardCount);
		System.out.print("Your cards are: | ");
		for (int i = 0; i < handView.size(); i++) {
			System.out.print(i + 1 + ": " + color(handView.get(i).toString()) + " | ");
		}
		System.out.println();
		System.out.print("Enter the card's number you want to put in the pile, or 0 to draw: > ");
		int idx = 0;
		try {
			idx = Integer.parseInt(reader.readLine());
		} catch (IOException e) {
			System.out.println("Some kind of error happened that I can't handle :("); // yep that's absolutely true, lazyness is a good reason
			System.exit(1);
		} catch (NumberFormatException e) {
			System.out.println("enter a noomba u stoopid");
			System.out.println("now i haz 2 quit cuz error.");
			System.exit(1);
		}
		if (idx == 0) {
			card = null;
			System.out.println();
			System.out.println("-- You: I drew a card.");
			System.out.println();
		} else {
			card = handView.get(idx - 1);
			System.out.println();
			System.out.println("-- You: I put the card " + idx + " " + color(handView.get(idx - 1).toString())
					+ " into the pile.");
			System.out.println();
		}
		return card;
	}

	@Override
	public GCard proposeCardWithSuit(List<GCard> handView, Game game, Player player, Suit suit) {
		
		GCard card = null;
		
		System.out.println(
				"Top card: " + "\033[44m" + color(game.getTopCard().toString()) + ". Cards in deck: " + game.getDrawDeckSize());
		System.out.println("Opponent cards: " + oppCardCount);
		System.out.print("Your cards are: | ");
		for (int i = 0; i < handView.size(); i++) {
			System.out.print(i + 1 + ": " + color(handView.get(i).toString()) + " | ");
		}
		System.out.println();
		System.out.println("You have to play a card with suit " + colormap.get(suit.toString()) + suit.toString() + "\033[0m.");
		System.out.print("Enter the card's number you want to put in the pile, or 0 to draw: > ");
		
		int idx = 0;
		try {
			idx = Integer.parseInt(reader.readLine());
		} catch (IOException e) {
			System.out.println("Some kind of error happened that I can't handle :("); // yep that's absolutely true, lazyness is a good reason
			System.exit(1);
		} catch (NumberFormatException e) {
			System.out.println("enter a noomba u stoopid");
			System.out.println("now i haz 2 quit cuz error.");
			System.exit(1);
		}
		if (idx == 0) {
			card = null;
			System.out.println();
			System.out.println("-- You: I drew a card.");
			System.out.println();
		} else {
			card = handView.get(idx - 1);
			System.out.println();
			System.out.println("-- You: I put the card " + idx + " " + color(handView.get(idx - 1).toString())
					+ " into the pile.");
			System.out.println();
		}
		return card;
	}
	
	@Override
	public GCard.Suit askForSuit(List<GCard> handView, Game game, Player player) {
		System.out.println("You have to ask for a suit.");
		System.out.print("The suits are: | ");
		
		for(GCard.Suit suit : GCard.Suit.values()) {
			System.out.println(suit.ordinal() + 1 + ": " + colormap.get(suit.toString()) + suit.toString() + " | ");
		}
		
		System.out.print("\033[0mEnter the suit's number you want to ask for: ");
		
		int idx = 0;
		try {
			idx = Integer.parseInt(reader.readLine());
		} catch (IOException e) {
			System.out.println("Some kind of error happened that I can't handle :(");
			System.exit(1);
		} catch (NumberFormatException e) {
			System.out.println("enter a noomba u stoopid");
			System.out.println("now i haz 2 quit cuz error.");
			System.exit(1);
		}
		return GCard.Suit.values()[idx - 1];
	}
	
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
			System.out.println("The top card is \033[44m" + color(e.getGame().getTopCard().toString()) + "\033[0m.");
			if (e.getGame().getStartingPlayer() == me) {
				System.out.println("\033[33mYou start!\033[0m");
			} else {
				System.out.println("\033[33mThe bot starts!\033[0m");
			}
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
	public void deckRefilled(Game game) {


	}

	@Override
	public void deckSizeDecreased(GameQuantitativeEvent e) {


	}

	@Override
	public void pileSizeIncreased(GameQuantitativeEvent e) {


	}

	private static Map<String, String> colormap = new HashMap<>();
	static {
		colormap.put("ACORNS", "\033[33m");
		colormap.put("LEAVES", "\033[32m");
		colormap.put("HEARTS", "\033[31m");
		colormap.put("BELLS", "\033[35m");
	}

	// relies on GCard.toString; not robust
	static String color(String card) {
		String[] tokens = card.split(" ");
		String rank = tokens[0];
		if (rank.equals("SEVEN")) {
			rank = surround(tokens[0], "\033[1m\033[36m", "\033[39m");
		} else {
			rank = surround(tokens[0], "\033[36m", "\033[39m");
		}
		return rank + " " + tokens[1] + " " + surround(tokens[2], colormap.get(tokens[2]));
	}

	static String surround(String str, String prefix) {
		return surround(str, prefix, "\033[0m");
	}
	
	static String surround(String str, String prefix, String suffix) {
		return prefix + str + suffix;
	}

}
