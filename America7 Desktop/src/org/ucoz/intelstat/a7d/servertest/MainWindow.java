package org.ucoz.intelstat.a7d.servertest;

import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import org.ucoz.intelstat.a7.core.Game;
import org.ucoz.intelstat.a7.core.Game.Player;
import org.ucoz.intelstat.a7.core.GameListener;
import org.ucoz.intelstat.a7.core.GamePassiveEvent;
import org.ucoz.intelstat.a7.core.GameQuantitativeEvent;

public class MainWindow extends JFrame implements GameListener {

	private NetworkedGame game;
	private GamePanel gamePanel;
	
	public MainWindow() {
		initWindow();
		initUI();
	}

	private void initWindow() {
		setSize(1280, 720);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
	}
	
	private void initUI() {
		Container contentPane = getContentPane();
		contentPane.setBackground(Color.GREEN);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
		
		JLabel header = new JLabel("Amerika 7");
		JButton connectBtn = new JButton("Csatlakozás a szerverhez");
		JButton newServerBtn = new JButton("Új szerver");
		
		header.setFont(header.getFont().deriveFont(20.0f));
		header.setForeground(Color.DARK_GRAY);
		
		connectBtn.addActionListener(this::connectBtnAction);
		
		newServerBtn.addActionListener(this::newServerBtnAction);
		
		contentPane.add(header);
		contentPane.add(connectBtn);
		contentPane.add(newServerBtn);
	}
	
	void connectBtnAction(ActionEvent e) {
		switchToGamePanel();
	}
	
	void newServerBtnAction(ActionEvent e) {
		game = new NetworkedGame(new LocalController() );
		game.addListener(this);
		switchToGamePanel();
	}
	
	private void switchToGamePanel() {
		gamePanel = new GamePanel();
		setContentPane(gamePanel);
		validate();
		repaint();
	}
	
	public static void main(String[] args) {
		new MainWindow().setVisible(true);
	}

	
	
	
	@Override
	public void playerJoined(GamePassiveEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void playerLeft(GamePassiveEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void currentPlayerChanged(GamePassiveEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void gameStateChanged(GamePassiveEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void playerCardCountChanged(GameQuantitativeEvent e, Player player) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void roundChanged(GameQuantitativeEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void stockRefilled(Game game) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void stockSizeDecreased(GameQuantitativeEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pileSizeIncreased(GameQuantitativeEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
