package org.ucoz.intelstat.a7d.servertest;

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
public class PhantomServerMain {
	public static void main(String[] args) throws IOException, InterruptedException, URISyntaxException {
		MainWindow.main(new String[0]);
	}
}
