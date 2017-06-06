package me.justicepro.beehub.Minigames;

import java.util.HashMap;

public class MinigameStage {
	
	private static HashMap<String, Integer> customStages = new HashMap<>();
	
	public static int LOBBY() {
		return 0;
	}
	
	public static int GAME() {
		return 1;
	}
	
	public static int GAME_OVER() {
		return 2;
	}
	
	public static int CUSTOM(String stage) {
		if (!customStages.containsKey(stage)) {
			return -1;
		}
		return customStages.get(stage);
	}
	
	public static void createCustom(String stage, int id) {
		customStages.put(stage, id);
	}
	
}