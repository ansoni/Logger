package me.prism3.logger.Events;

import me.prism3.logger.Database.External.ExternalData;
import me.prism3.logger.Database.SQLite.Global.SQLiteData;
import me.prism3.logger.Discord.Discord;
import me.prism3.logger.Main;
import me.prism3.logger.Utils.FileHandler;
import me.prism3.logger.Utils.Messages;
import me.prism3.logger.Utils.Data;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.Objects;

public class OnItemPickup implements Listener {

    private final Main main = Main.getInstance();

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onItemPick(final EntityPickupItemEvent event) {

        if (event.getEntity() instanceof Player && !event.isCancelled()
                && this.main.getConfig().getBoolean("Log-Player.Item-Pickup")) {

            final Player player = (Player) event.getEntity();

            if (player.hasPermission(Data.loggerExempt)) return;

            final String playerName = player.getName();
            final Material item = event.getItem().getItemStack().getType();
            String itemName = Objects.requireNonNull(event.getItem().getItemStack().getItemMeta()).getDisplayName();

            if (itemName != null){

                itemName = itemName.contains("\\") ? itemName : itemName.replace("\\", "\\\\");

            } else itemName =  " ";

            final int amount = event.getItem().getItemStack().getAmount();
            final World world = player.getWorld();
            final String worldName = world.getName();
            final int blockX = event.getItem().getLocation().getBlockX();
            final int blockY = event.getItem().getLocation().getBlockY();
            final int blockZ = event.getItem().getLocation().getBlockZ();

            // Log To Files
            if (Data.isLogToFiles) {

                if (Data.isStaffEnabled && player.hasPermission(Data.loggerStaffLog)) {

                    if (!Objects.requireNonNull(Messages.get().getString("Discord.Item-Pickup-Staff")).isEmpty()) {

                        Discord.staffChat(player, Objects.requireNonNull(Messages.get().getString("Discord.Item-Pickup-Staff")).replaceAll("%time%", Data.dateTimeFormatter.format(ZonedDateTime.now())).replaceAll("%world%", worldName).replaceAll("%item%", String.valueOf(item)).replaceAll("%amount%", String.valueOf(amount)).replaceAll("%x%", String.valueOf(blockX)).replaceAll("%y%", String.valueOf(blockY)).replaceAll("%z%", String.valueOf(blockZ)).replaceAll("%renamed%", String.valueOf(itemName)), false);

                    }

                    try {

                        BufferedWriter out = new BufferedWriter(new FileWriter(FileHandler.getstaffFile(), true));
                        out.write(Objects.requireNonNull(Messages.get().getString("Files.Item-Pickup-Staff")).replaceAll("%time%", Data.dateTimeFormatter.format(ZonedDateTime.now())).replaceAll("%world%", worldName).replaceAll("%player%", playerName).replaceAll("%item%", String.valueOf(item)).replaceAll("%amount%", String.valueOf(amount)).replaceAll("%x%", String.valueOf(blockX)).replaceAll("%y%", String.valueOf(blockY)).replaceAll("%z%", String.valueOf(blockZ)).replaceAll("%renamed%", String.valueOf(itemName)) + "\n");
                        out.close();

                    } catch (IOException e) {

                        this.main.getServer().getLogger().warning("An error occurred while logging into the appropriate file.");
                        e.printStackTrace();

                    }

                    if (Data.isExternal && this.main.getExternal().isConnected()) {

                        ExternalData.itemPickup(Data.serverName, worldName, playerName, item, amount, blockX, blockY, blockZ, itemName, true);

                    }

                    if (Data.isSqlite && this.main.getSqLite().isConnected()) {

                        SQLiteData.insertItemPickup(Data.serverName, player, item, blockX, amount, blockY, blockZ, itemName, true);

                    }

                    return;

                }

                try {

                    BufferedWriter out = new BufferedWriter(new FileWriter(FileHandler.getItemPickupFile(), true));
                    out.write(Objects.requireNonNull(Messages.get().getString("Files.Item-Pickup")).replaceAll("%time%", Data.dateTimeFormatter.format(ZonedDateTime.now())).replaceAll("%world%", worldName).replaceAll("%player%", playerName).replaceAll("%item%", String.valueOf(item)).replaceAll("%amount%", String.valueOf(amount)).replaceAll("%x%", String.valueOf(blockX)).replaceAll("%y%", String.valueOf(blockY)).replaceAll("%z%", String.valueOf(blockZ)).replaceAll("%renamed%", String.valueOf(itemName)) + "\n");
                    out.close();

                } catch (IOException e) {

                    this.main.getServer().getLogger().warning("An error occurred while logging into the appropriate file.");
                    e.printStackTrace();

                }
            }

            // Discord
            if (!player.hasPermission(Data.loggerExemptDiscord)) {

                if (Data.isStaffEnabled && player.hasPermission(Data.loggerStaffLog)) {

                    if (!Objects.requireNonNull(Messages.get().getString("Discord.Item-Pickup-Staff")).isEmpty()) {

                        Discord.staffChat(player, Objects.requireNonNull(Messages.get().getString("Discord.Item-Pickup-Staff")).replaceAll("%time%", Data.dateTimeFormatter.format(ZonedDateTime.now())).replaceAll("%world%", worldName).replaceAll("%item%", String.valueOf(item)).replaceAll("%amount%", String.valueOf(amount)).replaceAll("%x%", String.valueOf(blockX)).replaceAll("%y%", String.valueOf(blockY)).replaceAll("%z%", String.valueOf(blockZ)).replaceAll("%renamed%", String.valueOf(itemName)), false);

                    }
                } else {

                    if (!Objects.requireNonNull(Messages.get().getString("Discord.Item-Pickup")).isEmpty()) {

                        Discord.itemPickup(player, Objects.requireNonNull(Messages.get().getString("Discord.Item-Pickup")).replaceAll("%time%", Data.dateTimeFormatter.format(ZonedDateTime.now())).replaceAll("%world%", worldName).replaceAll("%item%", String.valueOf(item)).replaceAll("%amount%", String.valueOf(amount)).replaceAll("%x%", String.valueOf(blockX)).replaceAll("%y%", String.valueOf(blockY)).replaceAll("%z%", String.valueOf(blockZ)).replaceAll("%renamed%", String.valueOf(itemName)), false);
                    }
                }
            }

            // External
            if (Data.isExternal && this.main.getExternal().isConnected()) {

                try {

                    ExternalData.itemPickup(Data.serverName, worldName, playerName, item, amount, blockX, blockY, blockZ, itemName, player.hasPermission(Data.loggerStaffLog));

                } catch (Exception e) { e.printStackTrace(); }
            }

            // SQLite
            if (Data.isSqlite && this.main.getSqLite().isConnected()) {

                try {

                    SQLiteData.insertItemPickup(Data.serverName, player, item, amount, blockX, blockY, blockZ, itemName, player.hasPermission(Data.loggerStaffLog));

                } catch (Exception exception) { exception.printStackTrace(); }
            }
        }
    }
}