package pl.piotrkociakx.template.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import pl.piotrkociakx.template.helpers.ChatHelper;
import pl.piotrkociakx.template.conifg.YamlDataManager;

public class PortfelCommand implements CommandExecutor {

    private final JavaPlugin plugin;
    private final FileConfiguration config;
    private final YamlDataManager dataManager;


    public PortfelCommand(JavaPlugin plugin, FileConfiguration config, YamlDataManager dataManager) {
        this.plugin = plugin;
        this.config = config;
        this.dataManager = dataManager;
        plugin.getCommand("portfel").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)) {
            sender.sendMessage("Ta komenda wymaga bycia graczem!");
            return false;
        }

        Player player = (Player) sender;


        // Kod
        int balance = dataManager.getPlayerBalance(player.getName());
        String message = config.getString("messages.balance", "Masz teraz {balance} pieniędzy");
        String messageWithBalance = message.replace("{balance}", String.valueOf(balance));
        player.sendMessage(ChatHelper.colored(messageWithBalance));


        return true;
    }
}
