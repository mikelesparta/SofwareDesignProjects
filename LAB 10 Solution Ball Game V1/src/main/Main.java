package main;

import game.BallGame;
import platform.android.AndroidAPI;
import platform.android.AndroidAdapter;
import platform.playstation.PlayStationAdapter;
import platform.playstation.Playstation5API;
import platform.windows.WindowsAPI;
import platform.windows.WindowsAdapter;

public class Main {

	public static void main(String[] args) {
		System.out.println("ANDROID:");
		BallGame game = new BallGame(new AndroidAdapter(new AndroidAPI()));
		game.play();

		System.out.println("\nPLAYSTATION:");
		game.setDevice((new PlayStationAdapter(new Playstation5API())));
		game.play();

		System.out.println("\nWINDOWS:");
		game.setDevice((new WindowsAdapter(new WindowsAPI())));
		game.play();
	}
}
