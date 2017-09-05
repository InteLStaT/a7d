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
		
		if(topCard.getRank() == GCard.Rank.UNDER || topCard.getRank() == GCard.Rank.ACE) {
			for(GCard _card : handView) {
				if(_card.getRank() == topCard.getRank()) {
					card = _card;
					break;
				}
			}
		}
		else {
			for(GCard _card : handView) {
				if(GameRules.isValidMove(topCard, _card, false)) {
					card = _card;
					break;
				}
			}
		}
		
		postMsg(card);
		
		return card;
	}

	public GCard proposeCardWithSuit(List<GCard> handView, Game game, Player player, GCard.Suit suit) {
		for(GCard card : handView) {
			if(card.getSuit() == suit) {
				return card;
			}
		}
		return null;
	}
	
	// Returns random suit
	@Override
	public GCard.Suit askForSuit(List<GCard> handView, Game game, Player player) {
		return GCard.Suit.values()[rgen.nextInt(GCard.Suit.values().length)];
	}
	
	private void preMsg() {
		System.out.print("-- AI Bot: Thinking...");
	}
	
	private void postMsg(GCard card) {
		if(card == null) {
			System.out.println("\r-- AI Bot: I drew a card.");
			System.out.println();
		} else {
			System.out.println("\r-- AI Bot: I put the card " + SampleConsoleController.color(card) + " into the pile.");
			System.out.println();
		}
	}
	

}
