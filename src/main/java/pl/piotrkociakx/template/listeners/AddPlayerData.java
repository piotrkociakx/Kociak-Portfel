package pl.piotrkociakx.template.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;
import pl.piotrkociakx.template.conifg.YamlDataManager;
import java.util.logging.Logger;

public class AddPlayerData implements Listener {
    private final YamlDataManager dataManager;
    private final Logger logger;
    private final JavaPlugin plugin;
    public AddPlayerData(JavaPlugin plugin) {
        this.plugin = plugin;
        this.dataManager = new YamlDataManager();
        dataManager.setupDataFile(plugin.getDataFolder());
        this.logger = plugin.getLogger();
    }
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if (!dataManager.playerExists(player.getName())) {
            dataManager.setPlayerBalance(player.getName(), 0);
            logger.info("Stworzono profil dla gracza " + player.getName() + " w bazie danych");
        }
    }
}
