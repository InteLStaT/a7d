package org.ucoz.intelstat.a7d.test;

import java.awt.GraphicsEnvironment;
import java.io.Console;
import java.io.IOException;
import java.net.URISyntaxException;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;

// Thanks for this amazing bit from stackoverflow
/**
 * This opens a command line and runs some other class in the jar
 * 
 * @author Brandon Barajas
 */
public class PhantomMain {
	public static void main(String[] args) throws IOException, InterruptedException, URISyntaxException {
		// if(new Random().nextInt(10)==0) System.exit(1);
		Console console = System.console();
		if (console == null && !GraphicsEnvironment.isHeadless()) {
			String filename = PhantomMain.class.getProtectionDomain().getCodeSource().getLocation().toString()
					.substring(6);
			String javaPath = System.getProperty("java.home") + "\\bin\\java";
			/*
			 * String[] command = new String[] { "cmd", "/c", "start", "cmd",
			 * "/k", "powershell " + javaPath + " -jar " + filename + "'\"" };
			 */
			String msg = "<html>*** A program nem tudott elindulni, mert az elérési <br>"
					+ "útvonalban található szóköz vagy ékezetes betü. Tedd a <br>"
					+ "programot egy olyan mappába, aminek az elérési útvonala <br>"
					+ "nem tartalmazza ezeket a karaktereket.<br>";
			boolean bad = filename.contains("%20") || filename.chars().anyMatch((int x) -> x > 255);
			if(bad) {
				JFrame f = new JFrame();
				f.setSize(500, 200);
				f.setLocationRelativeTo(null);
				f.getContentPane().setLayout(new BoxLayout(f.getContentPane(), BoxLayout.Y_AXIS));
				f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				f.add(new JLabel(msg));
				f.add(new JLabel(filename));
				f.show();
			}
			String command = "cmd /c start cmd /k powershell \"& '" + javaPath + " '-jar '" + filename + "'\"\r\n"
					+ ((bad) ? "echo " + msg : "");
			// "cmd /c start cmd /k powershell \"& 'C:\\Program Files
			// (x86)\\Java\\jre1.8.0_131\\bin\\java '-jar
			// 'C:/Users/User/Desktop/a7d-2.4-fix.jar'\"\r\n;"
			Runtime.getRuntime().exec(command);
		} else {
			SampleAIGame.main(new String[0]);
		}
	}
}
