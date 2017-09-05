package org.ucoz.intelstat.a7d.test;

public class AnsiColor {

	// Reset
	public static final String RESET = "\u001B[0m";
	public static final String RESET_FOREGROUND = "\u001B[39m";
	public static final String RESET_BACKGROUND = "\u001B[49m";
	public static final String COLOR_RESET = "\u001b[39;49m";
	
	// Foreground
	public static final String BLACK = "\u001B[30m";
	public static final String RED = "\u001B[31m";
	public static final String GREEN = "\u001B[32m";
	public static final String YELLOW = "\u001B[33m";
	public static final String BLUE = "\u001B[34m";
	public static final String PURPLE = "\u001B[35m";
	public static final String CYAN = "\u001B[36m";
	public static final String WHITE = "\u001B[37m";
	
	// Background
	public static final String BLACK_BACKGROUND = "\u001B[40m";
	public static final String RED_BACKGROUND = "\u001B[41m";
	public static final String GREEN_BACKGROUND = "\u001B[42m";
	public static final String YELLOW_BACKGROUND = "\u001B[43m";
	public static final String BLUE_BACKGROUND = "\u001B[44m";
	public static final String PURPLE_BACKGROUND = "\u001B[45m";
	public static final String CYAN_BACKGROUND = "\u001B[46m";
	public static final String WHITE_BACKGROUND = "\u001B[47m";
	
	public static final String BRIGHT = "\u001b[1m";
	
	private AnsiColor() {}

}
