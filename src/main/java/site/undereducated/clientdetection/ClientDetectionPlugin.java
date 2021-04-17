package site.undereducated.clientdetection;

import com.iridium.iridiumcolorapi.IridiumColorAPI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import site.undereducated.clientdetection.commands.client;
import site.undereducated.undereducatedutil.UndereducatedAPI;
import us.myles.ViaVersion.api.Via;
import us.myles.ViaVersion.api.ViaAPI;

import java.net.MalformedURLException;

public final class ClientDetectionPlugin extends JavaPlugin implements Listener {
	private static ViaAPI viaAPI;
	public static String prefix = "";
	@Override
	public void onEnable(){
		this.viaAPI = Via.getAPI();
		this.getCommand("client").setExecutor(new client(this));
		try {
			UndereducatedAPI.loadPlugin("ClientDetection");
		} catch (Exception e) {
			e.printStackTrace();
		}
		// load config
		getConfig().options().copyDefaults();
		saveDefaultConfig();
		prefix = ChatColor.translateAlternateColorCodes('&', IridiumColorAPI.process(this.getConfig().getString("prefix")));

		// Small check to make sure that PlaceholderAPI is installed
		if(Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null){
			new ClientPlaceholders(this).register();
			UndereducatedAPI.log("Successfully initialized Placeholder API placeholders!", "ClientDetection");
			System.out.println(new ClientPlaceholders(this).isRegistered());
		}
	}

	@Override
	public void onDisable() {
		// Plugin shutdown logic
		UndereducatedAPI.log("Shutting down...", "ClientDetection");
	}

	public static ViaAPI getViaAPI() { return viaAPI; }
}
