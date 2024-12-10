package main;

import game.BallGame;
import platform.android.AndroidAPI;
import platform.android.AndroidBallGame;
import platform.playstation.PlayStationBallGame;
import platform.playstation.Playstation5API;
import platform.windows.WindowsAPI;
import platform.windows.WindowsBallGame;

public class Main {

	public static void main(String[] args) {
		System.out.println("ANDROID:");
		BallGame game = new AndroidBallGame(new AndroidAPI());
		game.play();

		System.out.println("\nPLAYSTATION:");
		game = new PlayStationBallGame(new Playstation5API());
		game.play();

		System.out.println("\nWINDOWS:");
		game = new WindowsBallGame(new WindowsAPI());
		game.play();
	}
}
