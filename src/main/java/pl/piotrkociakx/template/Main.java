package pl.piotrkociakx.template;

import org.bukkit.plugin.java.JavaPlugin;
import pl.piotrkociakx.template.conifg.*;
import pl.piotrkociakx.template.listeners.*;
import pl.piotrkociakx.template.commands.*;
import org.bukkit.command.CommandExecutor;
import org.bukkit.event.Listener;


public final class Main extends JavaPlugin {
    private ConfigManager configManager;

    @Override
    public void onEnable() {
        saveDefaultConfig();

        configManager = new ConfigManager(this);
        registerFeature("funkcja1.enabled", "funkcja1", new TestListener(configManager));
        registerCommand("komenda1.enabled", "komenda1",new TestCommand(this, getConfig()));
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
