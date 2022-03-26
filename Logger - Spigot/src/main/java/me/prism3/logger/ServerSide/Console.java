package me.prism3.logger.ServerSide;

import me.prism3.logger.Discord.Discord;
import me.prism3.logger.Main;
import me.prism3.logger.Utils.FileHandler;
import me.prism3.logger.Database.External.ExternalData;
import me.prism3.logger.Database.SQLite.Global.SQLiteData;
import me.prism3.logger.Utils.Messages;
import me.prism3.logger.Utils.Data;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerCommandEvent;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.*;

public class Console implements Listener {

    private final Main main = Main.getInstance();

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onConsoleCommand(final ServerCommandEvent event) {

            final String command = event.getCommand().replace("\\", "\\\\");
            final List<String> commandParts = Arrays.asList(event.getCommand().split("\\s+"));

            // Blacklisted Commands
            if (Data.isConsoleCommands) {

                for (String m : Data.consoleCommandsToBlock) {

                    if (commandParts.get(0).equalsIgnoreCase(m)) {
                        event.setCancelled(true);
                        return;
                    }
                }
            }

        if (this.main.getConfig().getBoolean("Log-Server.Console-Commands")) {

            // Log To Files
            if (Data.isLogToFiles) {

                try {

                    BufferedWriter out = new BufferedWriter(new FileWriter(FileHandler.getConsoleLogFile(), true));
                    out.write(Objects.requireNonNull(Messages.get().getString("Files.Server-Side.Console-Commands")).replaceAll("%time%", Data.dateTimeFormatter.format(ZonedDateTime.now())).replaceAll("%command%", command) + "\n");
                    out.close();

                } catch (IOException e) {

                    this.main.getServer().getLogger().warning("An error occurred while logging into the appropriate file.");
                    e.printStackTrace();

                }
            }

            // Discord
            if (!Objects.requireNonNull(Messages.get().getString("Discord.Server-Side.Console-Commands")).isEmpty()) {

                Discord.console(Objects.requireNonNull(Messages.get().getString("Discord.Server-Side.Console-Commands")).replaceAll("%time%", Data.dateTimeFormatter.format(ZonedDateTime.now())).replaceAll("%command%", command), false);
            }

            // External
            if (Data.isExternal && this.main.getExternal().isConnected()) {

                try {

                    ExternalData.consoleCommands(Data.serverName, command);

                } catch (Exception e) { e.printStackTrace(); }
            }

            // SQLite
            if (Data.isSqlite && this.main.getSqLite().isConnected()) {

                try {

                    SQLiteData.insertConsoleCommands(Data.serverName, command);

                } catch (Exception e) { e.printStackTrace(); }
            }
        }
    }
}
