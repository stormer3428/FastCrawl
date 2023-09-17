package fr.stormer3428.fastcrawl;

import java.util.ArrayList;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class FC extends JavaPlugin implements Listener{

	@Override
	public void onEnable() {
		getCommand("fastcrawl").setExecutor(this);
		getServer().getPluginManager().registerEvents(this, this);
	}

	private static ArrayList<Player> crawlers = new ArrayList<Player>();

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender instanceof Player) {
			Player p = (Player) sender;
			if(cmd.getName().equalsIgnoreCase("fastcrawl")) {
				if(crawlers.contains(p)) {
					crawlers.remove(p);
				}else crawlers.add(p);
			}
		}
		return false;
	}

	@EventHandler
	public void onMove(PlayerMoveEvent e) {
		if(crawlers.contains(e.getPlayer())) {
			Player p = e.getPlayer();
			if(p.getBoundingBox().getHeight() <= 0.61) p.setWalkSpeed(0.8f);
			else if(p.getBoundingBox().getHeight() <= 1.51) p.setWalkSpeed(0.6f);
			else p.setWalkSpeed(0.2f);

		}
	}
}
