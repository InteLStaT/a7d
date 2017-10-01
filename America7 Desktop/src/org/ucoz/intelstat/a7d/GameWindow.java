package org.ucoz.intelstat.a7d;

import javax.swing.JPanel;

import org.ucoz.intelstat.a7.core.Game;

public class GameWindow {

	protected Game game;
	
	public GameWindow(Game game) {
		this.game = game;
	}
	
	public final void startGame() {
		game.start();
	}
	
}
