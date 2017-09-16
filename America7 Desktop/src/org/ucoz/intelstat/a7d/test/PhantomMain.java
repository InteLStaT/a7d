package org.ucoz.intelstat.a7d.test;

import java.awt.GraphicsEnvironment;
import java.io.Console;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Random;

// Thanks for this amazing bit from stackoverflow
/**
 * This opens a command line and runs some other class in the jar
 * @author Brandon Barajas
 */
public class PhantomMain {
	public static void main(String[] args) throws IOException, InterruptedException, URISyntaxException {
		//if(new Random().nextInt(10)==0) System.exit(1);
		Console console = System.console();
		if (console == null && !GraphicsEnvironment.isHeadless()) {
			String filename = PhantomMain.class.getProtectionDomain().getCodeSource().getLocation().toString()
					.substring(6);
			Runtime.getRuntime()
					.exec(new String[] { "cmd", "/c", "start", "cmd", "/k", "powershell java -jar \"" + filename + "\"" });

		} else {
			SampleAIGame.main(new String[0]);
		}
	}
}
