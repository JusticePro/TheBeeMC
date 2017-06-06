package me.justicepro.beehub.Commands;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.craftbukkit.v1_8_R3.CraftServer;

import me.justicepro.beehub.Commands.ERROR.ErrorCommand;
import me.justicepro.beehub.Commands.Normal.BalanceCommand;
import me.justicepro.beehub.Commands.Normal.MsgCommand;
import me.justicepro.beehub.Commands.Normal.ServerCommand;
import me.justicepro.beehub.Commands.Normal.TestCommand;
import me.justicepro.beehub.Commands.Premium.FireworkCommand;
import me.justicepro.beehub.Commands.Premium.FlightCommand;
import me.justicepro.beehub.Commands.Staff.Economy.CoinCommand;
import me.justicepro.beehub.Commands.Staff.Messaging.BroadcastCommand;
import me.justicepro.beehub.Commands.Staff.Punishing.BanCommand;
import me.justicepro.beehub.Commands.Staff.Punishing.KickCommand;
import me.justicepro.beehub.Commands.Staff.Punishing.PunishCommand;
import me.justicepro.beehub.Commands.Staff.Punishing.UnbanCommand;
import me.justicepro.beehub.Commands.Staff.Punishing.WarnCommand;
import me.justicepro.beehub.Commands.Staff.Ranks.RankCommand;
import me.justicepro.beehub.Commands.Staff.Tools.DisguiseCommand;
import me.justicepro.beehub.Commands.Staff.Tools.GamemodeCommand;

public class CommandManager {
	
	private ArrayList<Command> commands;
	
	public CommandManager() {
		commands = new ArrayList<>();
		
		add(new BalanceCommand(),
				new MsgCommand(),
				new ServerCommand(),
				new FireworkCommand(),
				new FlightCommand(),
				new CoinCommand(),
				new BroadcastCommand(),
				new BanCommand(),
				new KickCommand(),
				new UnbanCommand(),
				new WarnCommand(),
				new RankCommand(),
				new DisguiseCommand(),
				new GamemodeCommand(),
				new PunishCommand(),
				new ErrorCommand(),
				new TestCommand());
		
	}
	
	public void registerAll() {
		((CraftServer)Bukkit.getServer()).getCommandMap().registerAll("hub", commands);
	}
	
	public void add(Command... commands) {
		for (Command command : commands) {
			this.commands.add(command);
		}
	}
	
}