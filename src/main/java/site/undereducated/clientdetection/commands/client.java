package site.undereducated.clientdetection.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import site.undereducated.clientdetection.ClientDetectionPlugin;
import us.myles.ViaVersion.api.ViaAPI;
import us.myles.ViaVersion.api.protocol.ProtocolVersion;

public class client implements CommandExecutor {

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
			if(!player.hasPermission("clientdetection.viewother")){
				player.sendMessage(ChatColor.translateAlternateColorCodes('&', ClientDetectionPlugin.prefix + "&c You do not have permission to view other peoples client version!"));
				return true;
			}
			Player target = Bukkit.getPlayer(args[0]);
			if(target != null) {
				final int playerVersion = viaAPI.getPlayerVersion(target.getUniqueId());
				final String version = String.format("%s &7(&c%s&7)", ProtocolVersion.getProtocol(playerVersion).getName(), parseBrand(target.getClientBrandName()));
				player.sendMessage(ChatColor.translateAlternateColorCodes('&', ClientDetectionPlugin.prefix + "&c"  + target.getName() + "&r&7 is on &c" + version));
				return true;
			}
		} else if(args.length < 1){
			if(!player.hasPermission("clientdetection.use")){
				player.sendMessage(ChatColor.translateAlternateColorCodes('&', ClientDetectionPlugin.prefix + "&c You do not have permission to view your client version!"));
				return true;
			}
			if (sender != null) {
				final int playerVersion = viaAPI.getPlayerVersion(player.getUniqueId());
				final String version = String.format("&c%s &7(&c%s&7)", ProtocolVersion.getProtocol(playerVersion).getName(), parseBrand(player.getClientBrandName()));
				player.sendMessage(ChatColor.translateAlternateColorCodes('&', ClientDetectionPlugin.prefix + "&c" + player.getName() + "&r&7 is on &c" + version));
				return true;
			}
		}
		return false;
	}
}
