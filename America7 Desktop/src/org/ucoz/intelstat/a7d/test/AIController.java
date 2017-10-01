package org.ucoz.intelstat.a7d.test;

import java.util.List;
import java.util.Random;

import org.ucoz.intelstat.a7.core.Controller;
import org.ucoz.intelstat.a7.core.Game;
import org.ucoz.intelstat.a7.core.Game.Player;
import org.ucoz.intelstat.a7.core.GameRules;
import org.ucoz.intelstat.gc.GCard;

public class AIController implements Controller {

	Random rgen = new Random();

	@Override
	public GCard proposeCard(List<GCard> handView, Game game, Player player) {

		preMsg();

		GCard card = null;
		GCard topCard = game.getTopCard();

		if (topCard.getRank() == GCard.Rank.UNDER || topCard.getRank() == GCard.Rank.ACE) {
			for (GCard _card : handView) {
				if (GameRules.isValidMove(topCard, _card, true)) {
					card = _card;
					break;
				}
			}
		} else {
			for (GCard _card : handView) {
				if (GameRules.isValidMove(topCard, _card, false)) {
					card = _card;
					break;
				}
			}
		}

		postMsg(card, game);

		return card;
	}

	public GCard proposeCardWithSuit(List<GCard> handView, Game game, Player player, GCard.Suit suit) {
		preMsg();
		
		GCard card = null;
		
		for (GCard _card : handView) {
			if (GameRules.isValidAskedCard(_card, suit)) {
				card = _card;
			}
		}
		
		postMsg(card, game);
		
		return card;
	}

	// Returns random suit
	@Override
	public GCard.Suit askForSuit(List<GCard> handView, Game game, Player player) {
		GCard.Suit suit = GCard.Suit.values()[rgen.nextInt(GCard.Suit.values().length)];
		postMsgAsked(suit);
		return suit;
	}

	private void preMsg() {
		System.out.print("-- AI Bot: Gondolkozok...");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
		}
	}

	private void postMsg(GCard card, Game game) {
		if (card == null && game.isAceStreak()) {
			System.out.println("\r-- AI Bot: Kimarasztottál!");
			System.out.println();
		} else if (card == null) {
			System.out.println("\r-- AI Bot: Vettem.         ");
			System.out.println();
		} else {
			System.out
					.println("\r-- AI Bot: " + SampleConsoleController.color(card) + "t tettem.");
			System.out.println();
		}
	}

	private void postMsgAsked(GCard.Suit suit) {
		System.out.println("\r-- AI Bot: Ezt a színt kérem " + SampleConsoleController.colormap.get(suit)
				+ suit.toString() + AnsiColor.RESET);
		System.out.println();
	}

}
