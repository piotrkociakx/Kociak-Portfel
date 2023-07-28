package pl.piotrkociakx.template.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import pl.piotrkociakx.template.helpers.*;

public class TestCommand implements CommandExecutor {

    private final JavaPlugin plugin;
    private final FileConfiguration config;

    public TestCommand(JavaPlugin plugin, FileConfiguration config) {
        this.plugin = plugin;
        this.config = config;
        plugin.getCommand("testkomenda").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)) {
            sender.sendMessage("Ta komenda wymaga bycia graczem!");
            return false;
        }

        Player player = (Player) sender;

        if(!player.hasPermission("testpermisja")) {
            player.sendMessage(ChatHelper.colored("&cNie posiadasz wymaganych permisji!"));
            return false;
        }

        // Kod
        player.sendMessage(ChatHelper.colored("&aWszystko sie zgadza!"));

        return true;
    }
}
