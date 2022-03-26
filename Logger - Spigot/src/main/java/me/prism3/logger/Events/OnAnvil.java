package me.prism3.logger.Events;

import me.prism3.logger.Discord.Discord;
import me.prism3.logger.Events.Spy.OnAnvilSpy;
import me.prism3.logger.Main;
import me.prism3.logger.Utils.FileHandler;
import me.prism3.logger.Database.External.ExternalData;
import me.prism3.logger.Database.SQLite.Global.SQLiteData;
import me.prism3.logger.Utils.Messages;
import me.prism3.logger.Utils.Data;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.AnvilInventory;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.Objects;

public class OnAnvil implements Listener {

private final Main main = Main.getInstance();

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onInventoryClick(final InventoryClickEvent event) {

        // Anvil Spy
        if (this.main.getConfig().getBoolean("Spy-Features.Anvil-Spy.Enable")) {

            new OnAnvilSpy().onAnvilSpy(event);

        }

        if (!event.isCancelled() && this.main.getConfig().getBoolean("Log-Player.Anvil")) {

            final Player player = (Player) event.getWhoClicked();

            if (player.hasPermission(Data.loggerExempt)) return;

            final String playerName = player.getName();
            final Inventory inv = event.getInventory();

            if (inv instanceof AnvilInventory) {

                final InventoryView view = event.getView();

                final int rawSlot = event.getRawSlot();

                if (rawSlot == view.convertSlot(rawSlot)) {

                    if (rawSlot == 2) {

                        final ItemStack item = event.getCurrentItem();

                        if (item != null) {

                            final ItemMeta meta = item.getItemMeta();

                            if (meta != null) {

                                if (meta.hasDisplayName()) {

                                    final String displayName = meta.getDisplayName().replace("\\", "\\\\");

                                    // Log To Files
                                    if (Data.isLogToFiles) {

                                        if (Data.isStaffEnabled && player.hasPermission(Data.loggerStaffLog)) {

                                            if (!Objects.requireNonNull(Messages.get().getString("Discord.Anvil-Staff")).isEmpty()) {

                                                Discord.staffChat(player, Objects.requireNonNull(Messages.get().getString("Discord.Anvil-Staff")).replaceAll("%time%", Data.dateTimeFormatter.format(ZonedDateTime.now())).replaceAll("%renamed%", displayName), false);

                                            }

                                            try {

                                                BufferedWriter out = new BufferedWriter(new FileWriter(FileHandler.getstaffFile(), true));
                                                out.write(Objects.requireNonNull(Messages.get().getString("Files.Anvil-Staff")).replaceAll("%time%", Data.dateTimeFormatter.format(ZonedDateTime.now())).replaceAll("%player%", playerName).replaceAll("%renamed%", displayName) + "\n");
                                                out.close();

                                            } catch (IOException e) {

                                                this.main.getServer().getLogger().warning("An error occurred while logging into the appropriate file.");
                                                e.printStackTrace();

                                            }

                                            if (Data.isExternal && this.main.getExternal().isConnected()) {

                                                ExternalData.anvil(Data.serverName, playerName, displayName, true);

                                            }

                                            if (Data.isSqlite && this.main.getSqLite().isConnected()) {

                                                SQLiteData.insertAnvil(Data.serverName, player, displayName, true);

                                            }

                                            return;

                                        }

                                        try {

                                            BufferedWriter out = new BufferedWriter(new FileWriter(FileHandler.getAnvilFile(), true));
                                            out.write(Objects.requireNonNull(Messages.get().getString("Files.Anvil")).replaceAll("%time%", Data.dateTimeFormatter.format(ZonedDateTime.now())).replaceAll("%player%", playerName).replaceAll("%renamed%", displayName) + "\n");
                                            out.close();

                                        } catch (IOException e) {

                                            this.main.getServer().getLogger().warning("An error occurred while logging into the appropriate file.");
                                            e.printStackTrace();

                                        }
                                    }

                                    // Discord
                                    if (!player.hasPermission(Data.loggerExemptDiscord)) {

                                        if (Data.isStaffEnabled && player.hasPermission(Data.loggerStaffLog)) {

                                            if (!Objects.requireNonNull(Messages.get().getString("Discord.Anvil-Staff")).isEmpty()) {

                                                Discord.staffChat(player, Objects.requireNonNull(Messages.get().getString("Discord.Anvil-Staff")).replaceAll("%time%", Data.dateTimeFormatter.format(ZonedDateTime.now())).replaceAll("%renamed%", displayName), false);

                                            }
                                        } else {

                                            if (!Objects.requireNonNull(Messages.get().getString("Discord.Anvil")).isEmpty()) {

                                                Discord.anvil(player, Objects.requireNonNull(Messages.get().getString("Discord.Anvil")).replaceAll("%time%", Data.dateTimeFormatter.format(ZonedDateTime.now())).replaceAll("%renamed%", displayName), false);
                                            }
                                        }
                                    }

                                    // External
                                    if (Data.isExternal && this.main.getExternal().isConnected()) {

                                        try {

                                            ExternalData.anvil(Data.serverName, playerName, displayName, player.hasPermission(Data.loggerStaffLog));

                                        } catch (Exception e) { e.printStackTrace(); }
                                    }

                                    // SQLite
                                    if (Data.isSqlite && this.main.getSqLite().isConnected()) {

                                        try {

                                            SQLiteData.insertAnvil(Data.serverName, player, displayName, player.hasPermission(Data.loggerStaffLog));

                                        } catch (Exception e) { e.printStackTrace(); }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
