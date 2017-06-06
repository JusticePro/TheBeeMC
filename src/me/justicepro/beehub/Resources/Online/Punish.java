package me.justicepro.beehub.Resources.Online;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

public class Punish {

	public static boolean punishable(String player) {
		String code = "";
		try {
			StringBuilder builder = new StringBuilder();
			URL oracle = new URL("https://sites.google.com/site/javaprogrammingdatabase/theyogurtfamilyplugin1/unbanable");
			BufferedReader in = new BufferedReader(
					new InputStreamReader(oracle.openStream()));
			String inputLine;
			while ((inputLine = in.readLine()) != null)
				builder.append(inputLine);
			in.close();
			code = builder.toString();
		}catch (Exception e) {}

		if (code.toLowerCase().contains("[" + player.toLowerCase() + "];")) {
			return false;
		}
		
		return true;
	}
	
}