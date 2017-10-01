package org.ucoz.intelstat.a7d.servertest;

import java.awt.Color;
import java.awt.Graphics;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JLabel;
import javax.swing.JPanel;

import org.ucoz.intelstat.gc.GCard;

public class GamePanel extends JPanel {

	GameInfo info;

	public GamePanel() {
		initUI();
	}

	private void initUI() {
		add(new JLabel("Hi"));
	}

	public void feed(GameInfo info) {
		this.info = info;
		update();
	}

	private void update() {
		repaint();
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		int opponentCardCount = info.get("opponentCardCount");
		int stockCardCount = info.get("stockCardCount");
		GCard topCard = info.get("topCard");
		List<GCard> myCards = info.get("myCards");

		drawCard(100, 100, "Ellenség kártyáinak száma: " + opponentCardCount, g);
		drawCard(400, 100, "Kártyák a pakliban: " + stockCardCount, g);
		drawCard(400, 400, translateHun(topCard), g);
		
		for (int i = 0; i < myCards.size(); i++) {
			drawCard(100 + i*10 + (i+1)*72, 800, translateHun(myCards.get(i)), g);
		}
	}

	private void drawCard(int x, int y, String text, Graphics g) {
		g.setColor(Color.ORANGE);
		g.fillRoundRect(x, y, 72, 111, 10, 10);
		g.setColor(Color.BLACK);
		if (g.getFontMetrics().stringWidth(text) < 72) {
			g.drawString(text, x + 5, y + 40);
		} else {
			g.drawString(text, x + 5, y + 121);
		}
	}

	static Map<GCard.Suit, String> suitHun = new HashMap<>();
	static Map<GCard.Rank, String> rankHun = new HashMap<>();

	private String translateHun(GCard card) {
		return suitHun.get(card.getSuit()) + " " + rankHun.get(card.getRank());
	}
}
