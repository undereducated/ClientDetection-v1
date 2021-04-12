package site.undereducated.clientdetection;

import com.iridium.iridiumcolorapi.IridiumColorAPI;
import org.bukkit.ChatColor;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import site.undereducated.clientdetection.commands.client;
import site.undereducated.undereducatedutil.UndereducatedAPI;
import us.myles.ViaVersion.api.Via;
import us.myles.ViaVersion.api.ViaAPI;

public final class ClientDetectionPlugin extends JavaPlugin implements Listener {
	private static ViaAPI viaAPI;
	public static String prefix = ChatColor.translateAlternateColorCodes('&', IridiumColorAPI.process("<GRADIENT:586ba4>ClientDetection</GRADIENT:324376>") + " &8l&r ");
	@Override
	public void onEnable(){
		this.viaAPI = Via.getAPI();
		this.getCommand("client").setExecutor(new client());
		UndereducatedAPI.loadPlugin("ClientDetection");
	}
	@Override
	public void onDisable() {
		// Plugin shutdown logic
	}
	public static ViaAPI getViaAPI() { return viaAPI; }
}
