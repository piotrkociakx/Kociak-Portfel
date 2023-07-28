package pl.piotrkociakx.template.listeners;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import pl.piotrkociakx.template.conifg.ConfigManager;
import pl.piotrkociakx.template.helpers.*;

public class TestListener implements Listener {
    private String testowystring;
    private Boolean testowyboolen;

    public TestListener(ConfigManager configManager) {
        testowystring = configManager.getConfig().getString("testowystring.z.configu");
        testowyboolen = configManager.getConfig().getBoolean("testowyboolen.z.configu");
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();
    }
}
