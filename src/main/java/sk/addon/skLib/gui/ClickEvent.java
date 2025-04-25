package sk.addon.skLib.gui;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class ClickEvent {

    private final InventoryClickEvent event;
    private final Player player;
    private final int slot;
    private final ItemStack item;
    private final ClickType clickType;

    public ClickEvent(InventoryClickEvent event) {
        this.event = event;
        this.player = (Player) event.getWhoClicked();
        this.slot = event.getSlot();
        this.item = event.getCurrentItem();
        this.clickType = event.getClick();
    }

    public Player getPlayer() {
        return player;
    }

    public int getSlot() {
        return slot;
    }

    public ItemStack getItem() {
        return item;
    }

    public ClickType getClickType() {
        return clickType;
    }

    public void close() {
        player.closeInventory();
    }

}
