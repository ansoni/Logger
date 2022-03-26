package me.prism3.logger.Database.SQLite.Registration;

import me.prism3.logger.Main;
import org.bukkit.entity.Player;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class SQLiteDataRegistration {

    private static Main plugin;

    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss:SSSXXX");

    public SQLiteDataRegistration(Main plugin) {
        SQLiteDataRegistration.plugin = plugin;
    }

    public void createTable() {

        try {

            final PreparedStatement registration = plugin.getSqLiteReg().getConnection().prepareStatement("CREATE TABLE" +
                    " IF NOT EXISTS Registration (Date TIMESTAMP DEFAULT CURRENT_TIMESTAMP, Player_Name TEXT(20)," +
                    "Player_UUID INTEGER(70))");

            registration.executeUpdate();
            registration.close();

        } catch (SQLException e) { e.printStackTrace(); }
    }

    public static void insertRegistration(Player player) {

        try {

            final PreparedStatement register = plugin.getSqLiteReg().getConnection().prepareStatement("INSERT INTO" +
                    " Registration (Date, Player_Name, Player_UUID) VALUES (?,?,?)");

            register.setString(1, dateTimeFormatter.format(ZonedDateTime.now()));
            register.setString(2, player.getName());
            register.setString(3, String.valueOf(player.getUniqueId()));

            register.executeUpdate();
            register.close();

        } catch (SQLException e) { e.printStackTrace(); }
    }

    public static boolean playerExists(Player player) {

        try {
            final PreparedStatement statement = plugin.getSqLiteReg().getConnection().prepareStatement("SELECT " +
                    "* FROM Registration WHERE Player_UUID=? LIMIT 1");

            statement.setString(1, String.valueOf(player.getUniqueId()));
            final ResultSet results = statement.executeQuery();

            if (results.next()) return true;

        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }
}