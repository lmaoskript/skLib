package sk.addon.skLib.gui;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class GuiClick implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (!(event.getWhoClicked() instanceof Player player)) return;

        GuiInventory gui = GuiInventory.getGui(player.getUniqueId());
        if (gui == null) return;

        if (event.getClickedInventory() == event.getView().getTopInventory()) {
            gui.handleClick(new ClickEvent(event));
        }

        event.setCancelled(gui.isUnstealable());
    }

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event) {
        if (!(event.getPlayer() instanceof Player player)) return;

        GuiInventory.removeGui(player.getUniqueId());
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        GuiInventory.removeGui(event.getPlayer().getUniqueId());
    }

}
