package org.ucoz.intelstat.a7d.test;

import java.util.List;

import org.ucoz.intelstat.a7.core.Controller;
import org.ucoz.intelstat.a7.core.Game;
import org.ucoz.intelstat.a7.core.Game.Player;
import org.ucoz.intelstat.a7.core.GameRules;
import org.ucoz.intelstat.gc.GCard;

public class AIController implements Controller {

	@Override
	public GCard proposeCard(GCard topCard, List<GCard> handView, Game game, Player player) {
		
		System.out.print("-- AI Bot: Thinking...");
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
		}
		
		GCard card = null;
		for(int i=0; i<handView.size(); i++) {
			if(GameRules.isValidMove(topCard, handView.get(i), game, player)) {
				card = handView.get(i);
				break;
			}
		}
		if(card == null) {
			System.out.println("\r-- AI Bot: I drew a card.");
			System.out.println();
		} else {
			System.out.println("\r-- AI Bot: I put the card " + SampleConsoleController.color(card.toString()) + " into the pile.");
			System.out.println();
		}
		return card;
	}
	

}
