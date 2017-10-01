package org.ucoz.intelstat.a7d.servertest;

import org.ucoz.intelstat.a7.core.Game;
import org.ucoz.intelstat.a7.core.GameListener;
import org.ucoz.intelstat.a7d.GameWindow;

public class NetworkedGame extends GameWindow {

	private Game.Player remotePlayer;
	private Game.Player localPlayer;
	
	public NetworkedGame(LocalController controller) {
		super(new Game());
		remotePlayer = game.new Player("Remote player", new RemoteController());
		localPlayer = game.new Player("Local player", controller);
	}
	
	public void addListener(GameListener l) {
		game.addGameListener(l);
	}
	
}
