package me.prism3.loggerbungeecord.events;

import com.google.gson.JsonObject;
import me.prism3.loggerbungeecord.Main;
import me.prism3.loggerbungeecord.database.external.ExternalData;
import me.prism3.loggerbungeecord.database.sqlite.SQLiteData;
import me.prism3.loggerbungeecord.utils.Data;
import me.prism3.loggerbungeecord.utils.FileHandler;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.PostLoginEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.time.ZonedDateTime;
import java.util.Objects;

public class OnLogin implements Listener {

    private final Main main = Main.getInstance();

    @EventHandler
    public void onLogging(final PostLoginEvent event) {

        if (this.main.getConfig().getBoolean("Log-Player.Login")) {

            final ProxiedPlayer player = event.getPlayer();

            if (player.hasPermission(Data.loggerExempt)) return;

            final String playerName = player.getName();
            final String server = player.getServer().getInfo().getName();
            InetSocketAddress playerIP = (InetSocketAddress) event.getPlayer().getSocketAddress();

            if (!Data.isPlayerIP) playerIP = null;

            if (Data.isLogToStdout) {
                JsonObject json = new JsonObject();
                json.addProperty("time", Data.dateTimeFormatter.format(ZonedDateTime.now()));
                json.addProperty("action", "login" );
                json.addProperty("server", server );
                json.addProperty("player", playerName);
                json.addProperty("ip", playerIP.toString());
                System.out.println(json.toString());
            }
            // Log To Files
            if (Data.isLogToFiles) {

                if (Data.isStaffEnabled && player.hasPermission(Data.loggerStaffLog)) {

                    if (!this.main.getMessages().getString("Discord.Player-Login-Staff").isEmpty()) {

                        this.main.getDiscord().staffChat(player, Objects.requireNonNull(this.main.getMessages().getString("Discord.Player-Login-Staff")).replace("%time%", Data.dateTimeFormatter.format(ZonedDateTime.now())).replace("%IP%", String.valueOf(playerIP)), false);

                    }

                    try {

                        final BufferedWriter out = new BufferedWriter(new FileWriter(FileHandler.getStaffLogFile(), true));
                        out.write(this.main.getMessages().getString("Files.Player-Login-Staff").replace("%time%", Data.dateTimeFormatter.format(ZonedDateTime.now())).replace("%player%", playerName).replace("%IP%", String.valueOf(playerIP)) + "\n");
                        out.close();

                    } catch (IOException e) {

                        Main.getInstance().getLogger().severe("An error occurred while logging into the appropriate file.");
                        e.printStackTrace();

                    }

                    if (Data.isExternal && this.main.getExternal().isConnected()) {

                        ExternalData.playerLogin(Data.serverName, playerName, playerIP, true);

                    }

                    if (Data.isSqlite && this.main.getSqLite().isConnected()) {

                        SQLiteData.insertPlayerLogin(Data.serverName, playerName, playerIP, true);

                    }

                    return;

                }

                try {

                    final BufferedWriter out = new BufferedWriter(new FileWriter(FileHandler.getLoginLogFile(), true));
                    out.write(this.main.getMessages().getString("Files.Player-Login").replace("%time%", Data.dateTimeFormatter.format(ZonedDateTime.now())).replace("%player%", playerName).replace("%IP%", String.valueOf(playerIP)) + "\n");
                    out.close();

                } catch (IOException e) {

                    Main.getInstance().getLogger().severe("An error occurred while logging into the appropriate file.");
                    e.printStackTrace();

                }
            }

            // Discord Integration
            if (!player.hasPermission(Data.loggerExemptDiscord)) {

                if (Data.isStaffEnabled && player.hasPermission(Data.loggerStaffLog)) {

                    if (!this.main.getMessages().getString("Discord.Player-Login-Staff").isEmpty()) {

                        this.main.getDiscord().staffChat(player, Objects.requireNonNull(this.main.getMessages().getString("Discord.Player-Login-Staff")).replace("%time%", Data.dateTimeFormatter.format(ZonedDateTime.now())).replace("%IP%", String.valueOf(playerIP)), false);

                    }
                } else {

                    if (!this.main.getMessages().getString("Discord.Player-Login").isEmpty()) {

                        this.main.getDiscord().playerLogin(player, Objects.requireNonNull(this.main.getMessages().getString("Discord.Player-Login")).replace("%time%", Data.dateTimeFormatter.format(ZonedDateTime.now())).replace("%IP%", String.valueOf(playerIP)), false);

                    }
                }
            }

            // External
            if (Data.isExternal && this.main.getExternal().isConnected()) {

                try {

                    ExternalData.playerLogin(Data.serverName, playerName, playerIP, player.hasPermission(Data.loggerStaffLog));

                } catch (Exception e) { e.printStackTrace(); }
            }

            // SQLite
            if (Data.isSqlite && this.main.getSqLite().isConnected()) {

                try {

                    SQLiteData.insertPlayerLogin(Data.serverName, playerName, playerIP, player.hasPermission(Data.loggerStaffLog));

                } catch (Exception e) { e.printStackTrace(); }
            }
        }
    }
}
