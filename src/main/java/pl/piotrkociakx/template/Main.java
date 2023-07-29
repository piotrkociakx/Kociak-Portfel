package pl.piotrkociakx.template;

import org.bukkit.plugin.java.JavaPlugin;
import pl.piotrkociakx.template.conifg.*;
import pl.piotrkociakx.template.listeners.*;
import pl.piotrkociakx.template.commands.*;
import org.bukkit.command.CommandExecutor;
import org.bukkit.event.Listener;

public final class Main extends JavaPlugin {

    private YamlDataManager dataManager;
    private ConfigManager configManager;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        configManager = new ConfigManager(this);

        dataManager = new YamlDataManager();
        dataManager.setupDataFile(getDataFolder());

        getServer().getPluginManager().registerEvents(new AddPlayerData(this), this);

        registerCommand("commands.enabled.adminportfel", "adminportfel", new AdminPortfelCommand(this, getConfig()));
        registerCommand("commands.enabled.itemshop", "itemshop", new ItemShopCommand(this, getConfig(), dataManager));
        registerCommand("commands.enabled.portfel", "portfel", new PortfelCommand(this, getConfig(), dataManager));
        new Placeholders(dataManager).register();
    }




    private void registerFeature(String configPath, String functionName, Listener listener) {
        if (getConfig().getBoolean(configPath)) {
            getLogger().info("[+] Pomyslnie wczytano: " + functionName);
            getServer().getPluginManager().registerEvents(listener, this);
        } else {
            getLogger().info("[-] Nie mozna zarejstrowac: " + functionName + " (mozliwe ze one sa wylaczone w konfiguracji)");
            return;
        }
    }

    private void registerCommand(String configPath, String commandName, CommandExecutor executor) {
        if (getConfig().getBoolean(configPath)) {
            getLogger().info("[+] Pomyslnie wczytano komende: " + commandName);
            getCommand(commandName).setExecutor(executor);
        } else {
            getLogger().info("[-] Nie mozna zarejstrowac komendy: " + commandName + " (mozliwe ze jest ona wylaczona w konfiguracji)");
            return;
        }
    }
}
