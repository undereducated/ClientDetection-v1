package site.undereducated.clientdetection.commands;

import com.iridium.iridiumcolorapi.IridiumColorAPI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import site.undereducated.clientdetection.ClientDetectionPlugin;
import site.undereducated.undereducatedutil.UndereducatedAPI;
import site.undereducated.undereducatedutil.UndereducatedStatistics;
import us.myles.ViaVersion.api.ViaAPI;
import us.myles.ViaVersion.api.protocol.ProtocolVersion;

public class client implements CommandExecutor {

	Plugin plugin;
	public client(ClientDetectionPlugin instance) {
		plugin = instance;
	}

	static Plugin client = Bukkit.getPluginManager().getPlugin("ClientDetection");
	public static String prefix = ChatColor.translateAlternateColorCodes('&', IridiumColorAPI.process(client.getConfig().getString("prefix")));

	// Parse Client brand, and return prettified version
	public String parseBrand(String brand){
		// if lunar client, return "lunar" instead of "lunarclient@yruqgrgwr"
		if(brand.contains("lunarclient")){
			return "lunar";
		}
		// if it contains fabric or forge, return modded mc
		else if(brand.contains("fabric") || brand.contains("forge") || brand.contains("fml") || brand.contains("fml,forge")){
			return "modded";
		}
		// if it gets to here, just return the brand as idk the brand name
		else{
			return brand;
		}
	}

	// ex: parseBrand(target.getClientBrandName());

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

		final ViaAPI viaAPI = ClientDetectionPlugin.getViaAPI();
		Player player = (Player) sender;
		if(args.length >= 1){
			// if the person cannot view another players client, send an error
			if(!player.hasPermission("clientdetection.viewother")){
				player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "&c You do not have permission to view other peoples client version!"));
				return true;
			}
			// if the argument equals reload, reload the client.
			if(args[0].equals("reload")){
				plugin.reloadConfig();
				prefix = ChatColor.translateAlternateColorCodes('&', IridiumColorAPI.process(client.getConfig().getString("prefix")));
				System.out.println(prefix);
				UndereducatedAPI.log("Reloaded configuration", "clientdetection");
				return true;
			}
			// get the target
			Player target = Bukkit.getPlayer(args[0]);
			// if target exists
			if(target != null) {
				final int playerVersion = viaAPI.getPlayerVersion(target.getUniqueId());
				final String version = String.format("%s &7(&c%s&7)", ProtocolVersion.getProtocol(playerVersion).getName(), parseBrand(target.getClientBrandName()));
				player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "&c"  + target.getName() + "&r&7 is on &c" + version));
				return true;
			}
			// else if there's no arguments
		} else if(args.length < 1){
			// if the player does not have permission
			if(!player.hasPermission("clientdetection.use")){
				player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "&c You do not have permission to view your client version!"));
				return true;
			}
			// if sender is not null
			if (sender != null) {
				final int playerVersion = viaAPI.getPlayerVersion(player.getUniqueId());
				final String version = String.format("&c%s &7(&c%s&7)", ProtocolVersion.getProtocol(playerVersion).getName(), parseBrand(player.getClientBrandName()));
				player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "&c" + player.getName() + "&r&7 is on &c" + version));
				return true;
			}
		}
		return false;
	}
}
