package org.ucoz.intelstat.a7d.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.EnumMap;
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
				"�rd be a k�rtya sz�m�t, amit tenni akarsz, vagy 0-t a vev�shez: > ");

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
		System.out.println("A k�vetkez� sz�nt k�ri az ellenfeled: " + colormap.get(suit) + suit.toString()
				+ (AnsiColor.RESET + "."));

		int idx = readIndexFromUser(0, handView.size(),
				"�rd be a k�rtya sz�m�t, amit tenni akarsz, vagy 0-t a vev�shez: > ");

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
		System.out.println("Sz�nt kell k�rned.");
		System.out.print("Ezek a sz�nek: | ");

		for (GCard.Suit suit : GCard.Suit.values()) {
			System.out.print(AnsiColor.RESET + (suit.ordinal() + 1) + ": " + colormap.get(suit) + suit.toString() + (AnsiColor.RESET + " | "));
		}

		System.out.println("\033[0m");
		
		GCard.Suit suit = GCard.Suit.values()[readIndexFromUser(1, 4, "�rd be a sz�n sz�m�t, amit k�rni akarsz: ") - 1];
		
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
				System.out.println("Hib�s v�lasz. Pr�b�ld �jra.");
			}
		}
		return idx;
	}

	private void printProposeInformation(List<GCard> handView, Game game) {
		System.out.println("Legfels� k�rtya: " + AnsiColor.BLUE_BACKGROUND + color(game.getTopCard()) + ". K�rty�k a pakliban: "
				+ game.getStockSize());
		System.out.println("Ellens�g k�rty�inak sz�ma: " + oppCardCount);
		System.out.print("A te k�rty�id: | ");
		for (int i = 0; i < handView.size(); i++) {
			System.out.print(i + 1 + ": " + color(handView.get(i)) + " | ");
		}
	}

	// Chat
	private void chatPropose(boolean drew, GCard card, Game game) {
		if(game.isAceStreak() && drew) {
			System.out.println();
			System.out.println("-- Te: Kimarasztott�l! De j� neked.");
			System.out.println();
		}
		else if (drew) {
			System.out.println();
			System.out.println("-- Te: Vettem.");
			System.out.println();
		} else {
			System.out.println();
			System.out.println("-- Te: " + color(card) + "t tettem.");
			System.out.println();
		}
	}
	
	private void chatAsked(GCard.Suit suit) {
		System.out.println();
		System.out.println("-- Te: Ezt a sz�nt k�rem: " + colormap.get(suit) + suit.toString() + AnsiColor.RESET);
		System.out.println();
	}

	public static Map<GCard.Suit, String> colormap = new HashMap<GCard.Suit, String>();
	public static EnumMap<GCard.Suit, String> suitHun = new EnumMap<>(GCard.Suit.class);
	public static EnumMap<GCard.Rank, String> rankHun = new EnumMap<>(GCard.Rank.class);
	static {
		colormap.put(GCard.Suit.ACORNS, AnsiColor.YELLOW);
		colormap.put(GCard.Suit.LEAVES, AnsiColor.GREEN);
		colormap.put(GCard.Suit.HEARTS, AnsiColor.RED);
		colormap.put(GCard.Suit.BELLS, AnsiColor.PURPLE);
		
		suitHun.put(GCard.Suit.ACORNS, "MAKK");
		suitHun.put(GCard.Suit.LEAVES, "Z�LD");
		suitHun.put(GCard.Suit.HEARTS, "PIROS");
		suitHun.put(GCard.Suit.BELLS, "T�K");
		
		rankHun.put(GCard.Rank.ACE, "�SZ");
		rankHun.put(GCard.Rank.UNDER, "KETTES");
		rankHun.put(GCard.Rank.OVER, "H�RMAS");
		rankHun.put(GCard.Rank.KING, "KIR�LY");
		rankHun.put(GCard.Rank.SEVEN, "HETES");
		rankHun.put(GCard.Rank.EIGHT, "NYOLCAS");
		rankHun.put(GCard.Rank.NINE, "KILENCES");
		rankHun.put(GCard.Rank.TEN, "TIZES");
	}
	

	// Quick color hack
	static String color(GCard card) {
		String rank;
		String suit = surround(suitHun.get(card.getSuit()), colormap.get(card.getSuit()));

		if (card.getRank() == Rank.SEVEN) {
			rank = surround(rankHun.get(card.getRank()), AnsiColor.BRIGHT + AnsiColor.CYAN, AnsiColor.RESET);
		} else {
			rank = surround(rankHun.get(card.getRank()), AnsiColor.CYAN, AnsiColor.RESET);
		}

		return suit + " " + rank;
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
			System.out.println("A kezd� legfels� k�rtya: \033[44m" + color(e.getGame().getTopCard()) + "\033[0m.");
			System.out.println(AnsiColor.YELLOW + e.getGame().getStartingPlayer().getName() + " fog kezdeni!" + AnsiColor.RESET);
			System.out.println();
		}
		if (e.getNewState() == GameState.POSTGAME) {
			System.out.println("Nyertes: " + e.getGame().getWinner().getName());
			System.out.println("Most biztons�gosan bez�rhatod ezt az ablakot (�s j�tszhatsz �jra!)");
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
