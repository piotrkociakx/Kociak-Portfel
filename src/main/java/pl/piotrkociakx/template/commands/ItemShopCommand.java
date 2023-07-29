package pl.piotrkociakx.template.commands;

// Importy związane z Bukkit API
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import pl.piotrkociakx.template.conifg.ConfigManager;
import pl.piotrkociakx.template.conifg.YamlDataManager;
import pl.piotrkociakx.template.guis.ItemShopGui;
import pl.piotrkociakx.template.helpers.ChatHelper;

public class ItemShopCommand implements CommandExecutor {

    private final JavaPlugin plugin;
    private final FileConfiguration config;
    private final YamlDataManager dataManager;

    public ItemShopCommand(JavaPlugin plugin, FileConfiguration config, YamlDataManager dataManager) {
        this.plugin = plugin;
        this.config = config;
        this.dataManager = dataManager;
        plugin.getCommand("itemshop").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Ta komenda wymaga bycia graczem!");
            return false;
        }

        Player player = (Player) sender;

        ItemShopGui itemShopGui = new ItemShopGui(new ConfigManager(plugin), dataManager, plugin);
        itemShopGui.openInventory(player);

        return true;
    }
}
