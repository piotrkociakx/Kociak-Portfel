package pl.piotrkociakx.template;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import pl.piotrkociakx.template.conifg.YamlDataManager;
import org.bukkit.entity.Player;

public class Placeholders extends PlaceholderExpansion {

    private final YamlDataManager dataManager;

    public Placeholders(YamlDataManager dataManager) {
        this.dataManager = dataManager;
    }

    @Override
    public String getIdentifier() {
        return "kociakportfel";
    }

    @Override
    public String getAuthor() {
        return "piotrkociakx";
    }

    @Override
    public String getVersion() {
        return "1.0";
    }

    @Override
    public boolean persist() {
        return true;
    }

    @Override
    public String onPlaceholderRequest(Player player, String identifier) {
        if (player == null) {
            return "";
        }

        if (identifier.equals("balance")) {
            return String.valueOf(dataManager.getPlayerBalance(player.getName()));
        }

        return null;
    }
}
