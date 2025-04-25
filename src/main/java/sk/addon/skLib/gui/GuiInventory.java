package sk.addon.skLib.gui;

import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.function.Consumer;

public class GuiInventory {

    private static final Map<UUID, GuiInventory> openInventories = new HashMap<>();

    private final String title;
    private final Inventory inventory;
    private final boolean unstealable;
    private final Map<Integer, Consumer<ClickEvent>> clickHandlers = new HashMap<>();

    MiniMessage mm = MiniMessage.miniMessage();

    public GuiInventory(String title, InventoryTypes type, boolean unstealable) {
        this.title = title;
        this.unstealable = unstealable;
        this.inventory = Bukkit.createInventory(null, InventoryType.valueOf(type.toString()), mm.deserialize(title));
    }

    public GuiInventory(String title, int rows, boolean unstealable) {
        this.title = title;
        this.unstealable = unstealable;
        rows = Math.min(6, Math.max(1, rows));
        this.inventory = Bukkit.createInventory(null, rows * 9, mm.deserialize(title));
    }

    public void setItem(int slot, ItemStack item) {
        inventory.setItem(slot, item);
    }

    public GuiInventory setItem(int slot, ItemStack item, Consumer<ClickEvent> clickHandler) {
        inventory.setItem(slot, item);
        if (clickHandler != null) {
            clickHandlers.put(slot, clickHandler);
        }
        return this;
    }

    public void open(Player player) {
        player.openInventory(inventory);
        openInventories.put(player.getUniqueId(), this);
    }

    public void handleClick(ClickEvent event) {
        Consumer<ClickEvent> handler = clickHandlers.get(event.getSlot());
        if (handler != null) {
            handler.accept(event);
        }
    }

    public void fillInventory(ItemStack item) {
        for (int i = 0; i < inventory.getSize(); i++) {
            inventory.setItem(i, item);
        }
    }

    public static GuiInventory getGui(UUID playerId) {
        return openInventories.get(playerId);
    }

    public static void removeGui(UUID playerId) {
        openInventories.remove(playerId);
    }

    public boolean isUnstealable() {
        return unstealable;
    }
}
