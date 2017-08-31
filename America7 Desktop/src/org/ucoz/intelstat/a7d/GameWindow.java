package org.ucoz.intelstat.a7d;

import javax.swing.JPanel;

import org.ucoz.intelstat.a7.core.Game;

public class GameWindow extends JPanel {

	protected Game game;
	
	public GameWindow(Game game) {
		this.game = game;
	}
	
	public void startGame() {
		game.start();
	}
	
}
