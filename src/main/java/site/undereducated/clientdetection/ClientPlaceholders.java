package site.undereducated.clientdetection;

import org.bukkit.OfflinePlayer;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.entity.Player;
import site.undereducated.clientdetection.ClientDetectionPlugin;
import site.undereducated.clientdetection.ClientDetectionPlugin;
import us.myles.ViaVersion.api.ViaAPI;
import us.myles.ViaVersion.api.protocol.ProtocolVersion;

/**
 * This class will be registered through the register-method in the
 * plugins onEnable-method.
 */
public class ClientPlaceholders extends PlaceholderExpansion {

	private ClientDetectionPlugin plugin;

	/**
	 * Since we register the expansion inside our own plugin, we
	 * can simply use this method here to get an instance of our
	 * plugin.
	 *
	 * @param plugin
	 *        The instance of our plugin.
	 */
	public ClientPlaceholders(ClientDetectionPlugin plugin){
		this.plugin = plugin;
	}

	/**
	 * Because this is an internal class,
	 * you must override this method to let PlaceholderAPI know to not unregister your expansion class when
	 * PlaceholderAPI is reloaded
	 *
	 * @return true to persist through reloads
	 */
	@Override
	public boolean persist(){
		return true;
	}

	/**
	 * This method parses the client brand
	 * and makes it easier to read
	 */

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

	/**
	 * Because this is a internal class, this check is not needed
	 * and we can simply return {@code true}
	 *
	 * @return Always true since it's an internal class.
	 */
	@Override
	public boolean canRegister(){
		return true;
	}

	/**
	 * The name of the person who created this expansion should go here.
	 * <br>For convienience do we return the author from the plugin.yml
	 *
	 * @return The name of the author as a String.
	 */
	@Override
	public String getAuthor(){
		return plugin.getDescription().getAuthors().toString();
	}

	/**
	 * The placeholder identifier should go here.
	 * <br>This is what tells PlaceholderAPI to call our onRequest
	 * method to obtain a value if a placeholder starts with our
	 * identifier.
	 * <br>The identifier has to be lowercase and can't contain _ or %
	 *
	 * @return The identifier in {@code %<identifier>_<value>%} as String.
	 */
	@Override
	public String getIdentifier(){
		return "client";
	}

	/**
	 * This is the version of the expansion.
	 * <br>You don't have to use numbers, since it is set as a String.
	 *
	 * For convienience do we return the version from the plugin.yml
	 *
	 * @return The version as a String.
	 */
	@Override
	public String getVersion(){
		return plugin.getDescription().getVersion();
	}

	/**
	 * This is the method called when a placeholder with our identifier
	 * is found and needs a value.
	 * <br>We specify the value identifier in this method.
	 * <br>Since version 2.9.1 can you use OfflinePlayers in your requests.
	 *
	 * @param  player
	 *         A {@link org.bukkit.entity.Player Player}.
	 * @param  identifier
	 *         A String containing the identifier/value.
	 *
	 * @return possibly-null String of the requested identifier.
	 */
	@Override
	public String onPlaceholderRequest(Player player, String identifier){

		if(player == null){
			return "";
		}
		final ViaAPI viaAPI = ClientDetectionPlugin.getViaAPI();
		// %example_placeholder1%
		if (identifier.equals("version")) {
			final int playerVersion = viaAPI.getPlayerVersion(player.getUniqueId());
			final String version = ProtocolVersion.getProtocol(playerVersion).getName();
			return version;
		}

		// %example_placeholder2%
		if (identifier.equals("brand")) {
			final String brand =  parseBrand(player.getClientBrandName());
			return brand;
		}
		// We return null if an invalid placeholder (f.e. %example_placeholder3%)
		// was provided
		return null;
	}
}
