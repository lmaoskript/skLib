package sk.addon.skLib;

import org.bukkit.plugin.java.JavaPlugin;
import sk.addon.skLib.gui.GuiClick;

public final class SkLib extends JavaPlugin {

    @Override
    public void onEnable() {
        getCommand("sklib").setExecutor(this);
        this.getServer().getPluginManager().registerEvents(new GuiClick(), this);
    }

    @Override
    public void onDisable() {

    }

}
