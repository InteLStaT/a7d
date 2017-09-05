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

		postMsg(card, game.getTopCard());

		return card;
	}

	public GCard proposeCardWithSuit(List<GCard> handView, Game game, Player player, GCard.Suit suit) {
		GCard card = null;
		
		for (GCard _card : handView) {
			if (GameRules.isValidAskedCard(card, suit)) {
				card = _card;
			}
		}
		
		postMsg(card, game.getTopCard());
		
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
		System.out.print("-- AI Bot: Thinking...");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
		}
	}

	private void postMsg(GCard card, GCard topCard) {
		if (topCard.getRank() == GCard.Rank.ACE && card == null) {
			System.out.println("\r-- AI Bot: You skipped me!");
			System.out.println();
		} else if (card == null) {
			System.out.println("\r-- AI Bot: I drew.     ");
			System.out.println();
		} else {
			System.out
					.println("\r-- AI Bot: I put the card " + SampleConsoleController.color(card) + " into the pile.");
			System.out.println();
		}
	}

	private void postMsgAsked(GCard.Suit suit) {
		System.out.println("\r-- AI Bot: I'm asking for " + SampleConsoleController.colormap.get(suit)
				+ suit.toString() + AnsiColor.RESET);
		System.out.println();
	}

}
