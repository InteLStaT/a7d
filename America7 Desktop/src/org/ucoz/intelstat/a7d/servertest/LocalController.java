package org.ucoz.intelstat.a7d.servertest;

import java.util.List;

import org.ucoz.intelstat.a7.core.Controller;
import org.ucoz.intelstat.a7.core.Game;
import org.ucoz.intelstat.a7.core.Game.Player;
import org.ucoz.intelstat.gc.GCard;
import org.ucoz.intelstat.gc.GCard.Suit;

public class LocalController implements Controller {

	
	@Override
	public GCard proposeCard(List<GCard> handView, Game game, Player player) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GCard proposeCardWithSuit(List<GCard> handView, Game game, Player player, Suit suit) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Suit askForSuit(List<GCard> handView, Game game, Player player) {
		// TODO Auto-generated method stub
		return null;
	}

}
