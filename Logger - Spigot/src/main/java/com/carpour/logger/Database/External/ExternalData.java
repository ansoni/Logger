package com.carpour.logger.Database.External;

import com.carpour.logger.API.AuthMeUtil;
import com.carpour.logger.API.EssentialsUtil;
import com.carpour.logger.API.VaultUtil;
import com.carpour.logger.Main;
import org.bukkit.Material;
import org.bukkit.event.world.PortalCreateEvent;

import java.net.InetSocketAddress;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import static com.carpour.logger.Utils.Data.externalDataDel;

public class ExternalData {

    private static Main plugin;

    public ExternalData(Main plugin){ ExternalData.plugin = plugin; }

    public void createTable() {

        try (Connection connection = plugin.getExternal().getHikari().getConnection();
             PreparedStatement playerChat = connection.prepareStatement("CREATE TABLE IF NOT EXISTS Player_Chat "
                     + "(Server_Name VARCHAR(30),Date DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP(),World VARCHAR(100)," +
                     "Playername VARCHAR(100),Message VARCHAR(200),Is_Staff TINYINT,PRIMARY KEY (Date))")) {

            playerChat.executeUpdate();

        } catch (SQLException e) { e.printStackTrace(); }

        try (Connection connection = plugin.getExternal().getHikari().getConnection();
             PreparedStatement playerCommands = connection.prepareStatement("CREATE TABLE IF NOT EXISTS Player_Commands "
                     + "(Server_Name VARCHAR(30),Date DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP(),World VARCHAR(100)," +
                     "Playername VARCHAR(100),Command VARCHAR(256),Is_Staff TINYINT,PRIMARY KEY (Date))")) {

            playerCommands.executeUpdate();

        } catch (SQLException e) { e.printStackTrace(); }

        try (Connection connection = plugin.getExternal().getHikari().getConnection();
             PreparedStatement playerSignText = connection.prepareStatement("CREATE TABLE IF NOT EXISTS Player_Sign_Text "
                     + "(Server_Name VARCHAR(30),Date DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP(),World VARCHAR(100)," +
                     "X INT,Y INT,Z INT,Playername VARCHAR(100),Line VARCHAR(60),Is_Staff TINYINT,PRIMARY KEY (Date))")) {

            playerSignText.executeUpdate();

        } catch (SQLException e) { e.printStackTrace(); }

        try (Connection connection = plugin.getExternal().getHikari().getConnection();
             PreparedStatement playerDeath = connection.prepareStatement("CREATE TABLE IF NOT EXISTS Player_Death "
                     + "(Server_Name VARCHAR(30),Date DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP(),World VARCHAR(100)," +
                     "Playername VARCHAR(100), Player_Level INT, X INT,Y INT,Z INT,Cause VARCHAR(40),By_Who VARCHAR(30)," +
                     "Is_Staff TINYINT,PRIMARY KEY (Date))")) {

            playerDeath.executeUpdate();

        } catch (SQLException e) { e.printStackTrace(); }

        try (Connection connection = plugin.getExternal().getHikari().getConnection();
             PreparedStatement playerTeleport = connection.prepareStatement("CREATE TABLE IF NOT EXISTS Player_Teleport "
                     + "(Server_Name VARCHAR(30),Date DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP(),World VARCHAR(100)," +
                     "Playername VARCHAR(100),From_X INT,From_Y INT,From_Z INT,To_X INT,To_Y INT,To_Z INT," +
                     "Is_Staff TINYINT,PRIMARY KEY (Date))")) {

            playerTeleport.executeUpdate();

        } catch (SQLException e) { e.printStackTrace(); }

        try (Connection connection = plugin.getExternal().getHikari().getConnection();
             PreparedStatement playerJoin = connection.prepareStatement("CREATE TABLE IF NOT EXISTS Player_Join "
                     + "(Server_Name VARCHAR(30),Date DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP(),World VARCHAR(100)," +
                     "Playername VARCHAR(100),X INT,Y INT,Z INT,IP INT UNSIGNED,Is_Staff TINYINT,PRIMARY KEY (Date))")) {

            playerJoin.executeUpdate();

        } catch (SQLException e) { e.printStackTrace(); }

        try (Connection connection = plugin.getExternal().getHikari().getConnection();
             PreparedStatement playerLeave = connection.prepareStatement("CREATE TABLE IF NOT EXISTS Player_Leave "
                     + "(Server_Name VARCHAR(30),Date DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP(),World VARCHAR(100)," +
                     "Playername VARCHAR(100),X INT,Y INT,Z INT,Is_Staff TINYINT,PRIMARY KEY (Date))")) {

            playerLeave.executeUpdate();

        } catch (SQLException e) { e.printStackTrace(); }

        try (Connection connection = plugin.getExternal().getHikari().getConnection();
             PreparedStatement blockPlace = connection.prepareStatement("CREATE TABLE IF NOT EXISTS Block_Place "
                     + "(Server_Name VARCHAR(30),Date DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP(),World VARCHAR(100)," +
                     "Playername VARCHAR(100),Block VARCHAR(40),X INT,Y INT,Z INT,Is_Staff TINYINT,PRIMARY KEY (Date))")) {

            blockPlace.executeUpdate();

        } catch (SQLException e) { e.printStackTrace(); }

        try (Connection connection = plugin.getExternal().getHikari().getConnection();
             PreparedStatement blockBreak = connection.prepareStatement("CREATE TABLE IF NOT EXISTS Block_Break "
                     + "(Server_Name VARCHAR(30),Date DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP(),World VARCHAR(100)," +
                     "Playername VARCHAR(100),Block VARCHAR(40),X INT,Y INT,Z INT,Is_Staff TINYINT,PRIMARY KEY (Date))")) {

            blockBreak.executeUpdate();

        } catch (SQLException e) { e.printStackTrace(); }

        try (Connection connection = plugin.getExternal().getHikari().getConnection();
             PreparedStatement playerKick = connection.prepareStatement("CREATE TABLE IF NOT EXISTS Player_Kick "
                     + "(Server_Name VARCHAR(30),Date DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP(),World VARCHAR(100)," +
                     "Playername VARCHAR(100),X INT,Y INT,Z INT,Reason VARCHAR(50),Is_Staff TINYINT,PRIMARY KEY (Date))")) {

            playerKick.executeUpdate();

        } catch (SQLException e) { e.printStackTrace(); }

        try (Connection connection = plugin.getExternal().getHikari().getConnection();
             PreparedStatement playerLevel = connection.prepareStatement("CREATE TABLE IF NOT EXISTS Player_Level "
                     + "(Server_Name VARCHAR(30),Date DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP()," +
                     "Playername VARCHAR(100),Is_Staff TINYINT,PRIMARY KEY (Date))")) {

            playerLevel.executeUpdate();

        } catch (SQLException e) { e.printStackTrace(); }

        try (Connection connection = plugin.getExternal().getHikari().getConnection();
             PreparedStatement bucketFill = connection.prepareStatement("CREATE TABLE IF NOT EXISTS Bucket_Fill "
                     + "(Server_Name VARCHAR(30),Date DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP(),World VARCHAR(100)" +
                     ",Playername VARCHAR(100),Bucket VARCHAR(40),X INT,Y INT,Z INT,Is_Staff TINYINT,PRIMARY KEY (Date))")) {

            bucketFill.executeUpdate();

        } catch (SQLException e) { e.printStackTrace(); }

        try (Connection connection = plugin.getExternal().getHikari().getConnection();
             PreparedStatement bucketEmpty = connection.prepareStatement("CREATE TABLE IF NOT EXISTS Bucket_Empty "
                     + "(Server_Name VARCHAR(30),Date DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP(),World VARCHAR(100)," +
                     "Playername VARCHAR(100),Bucket VARCHAR(40),X INT,Y INT,Z INT,Is_Staff TINYINT,PRIMARY KEY (Date))")) {

            bucketEmpty.executeUpdate();

        } catch (SQLException e) { e.printStackTrace(); }

        try (Connection connection = plugin.getExternal().getHikari().getConnection();
             PreparedStatement anvil = connection.prepareStatement("CREATE TABLE IF NOT EXISTS Anvil "
                     + "(Server_Name VARCHAR(30),Date DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP()," +
                     "Playername VARCHAR(100),New_name VARCHAR(100),Is_Staff TINYINT,PRIMARY KEY (Date))")) {

            anvil.executeUpdate();

        } catch (SQLException e) { e.printStackTrace(); }

        try (Connection connection = plugin.getExternal().getHikari().getConnection();
             PreparedStatement itemDrop = connection.prepareStatement("CREATE TABLE IF NOT EXISTS Item_Drop "
                     + "(Server_Name VARCHAR(30),Date DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP(),World VARCHAR(100)" +
                     ",Playername VARCHAR(100),Item VARCHAR(50),Amount INT,X INT,Y INT,Z INT,Enchantment VARCHAR(50)" +
                     ",Changed_Name VARCHAR(50),Is_Staff TINYINT,PRIMARY KEY (Date))")) {

            itemDrop.executeUpdate();

        } catch (SQLException e) { e.printStackTrace(); }

        try (Connection connection = plugin.getExternal().getHikari().getConnection();
             PreparedStatement enchant = connection.prepareStatement("CREATE TABLE IF NOT EXISTS Enchanting "
                     + "(Server_Name VARCHAR(30),Date DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP(),World VARCHAR(100)," +
                     "Playername VARCHAR(100),X INT,Y INT,Z INT,Enchantment VARCHAR(50), Enchantment_Level INT, " +
                     "Item VARCHAR(50),Cost INT(5),Is_Staff TINYINT,PRIMARY KEY (Date))")) {

            enchant.executeUpdate();

        } catch (SQLException e) { e.printStackTrace(); }

        try (Connection connection = plugin.getExternal().getHikari().getConnection();
             PreparedStatement bookEditing = connection.prepareStatement("CREATE TABLE IF NOT EXISTS Book_Editing "
                     + "(Server_Name VARCHAR(30),Date DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP(),World VARCHAR(100)," +
                     "Playername VARCHAR(100),Page_Count INT,Page_Content VARCHAR(250),Signed_by VARCHAR(25)," +
                     "Is_Staff TINYINT,PRIMARY KEY (Date))")) {

            bookEditing.executeUpdate();

        } catch (SQLException e) { e.printStackTrace(); }

        try (Connection connection = plugin.getExternal().getHikari().getConnection();
             PreparedStatement itemPickup = connection.prepareStatement("CREATE TABLE IF NOT EXISTS Item_Pickup "
                     + "(Server_Name VARCHAR(30),Date DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP(),World VARCHAR(100)," +
                     "Playername VARCHAR(100),Item VARCHAR(250),Amount INT,X INT,Y INT,Z INT,Changed_Name VARCHAR(250)," +
                     "Is_Staff TINYINT,PRIMARY KEY (Date))")) {

            itemPickup.executeUpdate();

        } catch (SQLException e) { e.printStackTrace(); }

        try (Connection connection = plugin.getExternal().getHikari().getConnection();
             PreparedStatement furnace = connection.prepareStatement("CREATE TABLE IF NOT EXISTS Furnace "
                     + "(Server_Name VARCHAR(30),Date DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP(),World VARCHAR(100)," +
                     "Playername VARCHAR(100),Item VARCHAR(250),Amount INT,X INT,Y INT,Z INT,Is_Staff TINYINT,PRIMARY KEY (Date))")) {

            furnace.executeUpdate();

        } catch (SQLException e) { e.printStackTrace(); }

        try (Connection connection = plugin.getExternal().getHikari().getConnection();
             PreparedStatement gameMode = connection.prepareStatement("CREATE TABLE IF NOT EXISTS Game_Mode "
                     + "(Server_Name VARCHAR(30),Date DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP(),World VARCHAR(100)," +
                     "Playername VARCHAR(100),Game_Mode VARCHAR(15),Is_Staff TINYINT,PRIMARY KEY (Date))")) {

            gameMode.executeUpdate();

        } catch (SQLException e) { e.printStackTrace(); }

        // Server Side Part
        try (Connection connection = plugin.getExternal().getHikari().getConnection();
             PreparedStatement serverStart = connection.prepareStatement("CREATE TABLE IF NOT EXISTS Server_Start "
                     + "(Server_Name VARCHAR(30),Date DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP(),PRIMARY KEY (Date))")) {

            serverStart.executeUpdate();

        } catch (SQLException e) { e.printStackTrace(); }

        try (Connection connection = plugin.getExternal().getHikari().getConnection();
             PreparedStatement serverStop = connection.prepareStatement("CREATE TABLE IF NOT EXISTS Server_Stop "
                     + "(Server_Name VARCHAR(30),Date DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP(),PRIMARY KEY (Date))")) {

            serverStop.executeUpdate();

        } catch (SQLException e) { e.printStackTrace(); }

        try (Connection connection = plugin.getExternal().getHikari().getConnection();
             PreparedStatement consoleCommands = connection.prepareStatement("CREATE TABLE IF NOT EXISTS Console_Commands "
                     + "(Server_Name VARCHAR(30),Date DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP()," +
                     "Command VARCHAR(256),PRIMARY KEY (Date))")) {

            consoleCommands.executeUpdate();

        } catch (SQLException e) { e.printStackTrace(); }

        try (Connection connection = plugin.getExternal().getHikari().getConnection();
             PreparedStatement RAM = connection.prepareStatement("CREATE TABLE IF NOT EXISTS RAM "
                     + "(Server_Name VARCHAR(30),Date DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP()," +
                     "Total_Memory INT,Used_Memory INT,Free_Memory INT,PRIMARY KEY (Date))")) {

            RAM.executeUpdate();

        } catch (SQLException e) { e.printStackTrace(); }

        try (Connection connection = plugin.getExternal().getHikari().getConnection();
             PreparedStatement TPS = connection.prepareStatement("CREATE TABLE IF NOT EXISTS TPS "
                     + "(Server_Name VARCHAR(30),Date DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP()," +
                     "TPS INT,PRIMARY KEY (Date))")) {

            TPS.executeUpdate();

        } catch (SQLException e) { e.printStackTrace(); }

        try (Connection connection = plugin.getExternal().getHikari().getConnection();
             PreparedStatement portalCreation = connection.prepareStatement("CREATE TABLE IF NOT EXISTS Portal_Creation "
                     + "(Server_Name VARCHAR(30),Date DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP()," +
                     "World VARCHAR(100),Caused_By VARCHAR(50),PRIMARY KEY (Date))")) {

            portalCreation.executeUpdate();

        } catch (SQLException e) { e.printStackTrace(); }

        try (Connection connection = plugin.getExternal().getHikari().getConnection();
             PreparedStatement rcon = connection.prepareStatement("CREATE TABLE IF NOT EXISTS RCON "
                     + "(Server_Name VARCHAR(30),Date DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP()," +
                     "IP INT UNSIGNED,Command VARCHAR(50),PRIMARY KEY (Date))")) {

            rcon.executeUpdate();

        } catch (SQLException e) { e.printStackTrace(); }

        try (Connection connection = plugin.getExternal().getHikari().getConnection();
             PreparedStatement craft = connection.prepareStatement("CREATE TABLE IF NOT EXISTS Crafting "
                     + "(Server_Name VARCHAR(30),Date DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP(),World VARCHAR(100)," +
                     "Playername VARCHAR(100),Item VARCHAR(50),Amount INT,X INT,Y INT,Z INT," +
                     "Is_Staff TINYINT,PRIMARY KEY (Date))")) {

            craft.executeUpdate();

        } catch (SQLException e) { e.printStackTrace(); }

        // Extra Side Part
        if (EssentialsUtil.getEssentialsAPI() != null) {

            try (Connection connection = plugin.getExternal().getHikari().getConnection();
                 PreparedStatement afk = connection.prepareStatement("CREATE TABLE IF NOT EXISTS AFK "
                         + "(Server_Name VARCHAR(30),Date DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP()," +
                         "World VARCHAR(100),Playername VARCHAR(100),X INT,Y INT,Z INT,Is_Staff TINYINT," +
                         "PRIMARY KEY (Date))")) {

                afk.executeUpdate();

            } catch (SQLException e) { e.printStackTrace(); }
        }

        if (AuthMeUtil.getAuthMeAPI() != null) {


            try (Connection connection = plugin.getExternal().getHikari().getConnection();
                 PreparedStatement wrongPassword = connection.prepareStatement("CREATE TABLE IF NOT " +
                         "EXISTS Wrong_Password (Server_Name VARCHAR(30)," +
                         "Date DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP(),World VARCHAR(100)," +
                         "Playername VARCHAR(100),Is_Staff TINYINT,PRIMARY KEY (Date))")) {

                wrongPassword.executeUpdate();

            } catch (SQLException e) { e.printStackTrace(); }
        }

        if (VaultUtil.getVaultAPI() && VaultUtil.getVault().isEnabled()) {

            try (Connection connection = plugin.getExternal().getHikari().getConnection();
                 PreparedStatement vault = connection.prepareStatement("CREATE TABLE IF NOT EXISTS Vault "
                         + "(Server_Name VARCHAR(30),Date DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP()," +
                         "Player_Name VARCHAR(100), Old_Balance DOUBLE, New_Balance DOUBLE, " +
                         "Is_Staff TINYINT,PRIMARY KEY (Date))")) {

                vault.executeUpdate();

            } catch (SQLException e) { e.printStackTrace(); }
        }
    }

    public static void playerChat(String serverName, String worldName, String playerName, String msg, boolean staff){

        try (Connection connection = plugin.getExternal().getHikari().getConnection();
                PreparedStatement playerChat = connection.prepareStatement("INSERT IGNORE INTO Player_Chat" +
                        " (Server_Name,World,Playername,Message,Is_Staff) VALUES(?,?,?,?,?)")) {

                playerChat.setString(1, serverName);
                playerChat.setString(2, worldName);
                playerChat.setString(3, playerName);
                playerChat.setString(4, msg);
                playerChat.setBoolean(5, staff);

                playerChat.executeUpdate();

        } catch (SQLException e){ e.printStackTrace(); }
    }

    public static void playerCommands(String serverName, String worldName, String playerName, String msg, boolean staff){

        try (Connection connection = plugin.getExternal().getHikari().getConnection();
                PreparedStatement playerCommands = connection.prepareStatement("INSERT IGNORE INTO Player_Commands" +
                        " (Server_Name,World,Playername,Command,Is_Staff) VALUES(?,?,?,?,?)")){

                playerCommands.setString(1, serverName);
                playerCommands.setString(2, worldName);
                playerCommands.setString(3, playerName);
                playerCommands.setString(4, msg);
                playerCommands.setBoolean(5, staff);

                playerCommands.executeUpdate();

        } catch (SQLException e){ e.printStackTrace(); }
    }

    public static void consoleCommands(String serverName, String msg){

        try (Connection connection = plugin.getExternal().getHikari().getConnection();
             PreparedStatement consoleCommands = connection.prepareStatement("INSERT IGNORE INTO Console_Commands" +
                     " (Server_Name,Command) VALUES(?,?)")){

            consoleCommands.setString(1, serverName);
            consoleCommands.setString(2, msg);

            consoleCommands.executeUpdate();

        } catch (SQLException e){ e.printStackTrace(); }
    }

    public static void playerSignText(String serverName, String worldName, int x, int y, int z,
                                      String playerName, String Lines, boolean staff){

        try (Connection connection = plugin.getExternal().getHikari().getConnection();
                PreparedStatement playerSignText = connection.prepareStatement("INSERT IGNORE INTO Player_Sign_Text" +
                        " (Server_Name,World,X,Y,Z,Playername,Line,Is_Staff) VALUES(?,?,?,?,?,?,?,?)")){

            playerSignText.setString(1, serverName);
            playerSignText.setString(2, worldName);
            playerSignText.setInt(3, x);
            playerSignText.setInt(4, y);
            playerSignText.setInt(5, z);
            playerSignText.setString(6, playerName);
            playerSignText.setString(7, Lines);
            playerSignText.setBoolean(8, staff);

            playerSignText.executeUpdate();

        } catch (SQLException e){ e.printStackTrace(); }
    }

    public static void playerDeath(String serverName, String worldName, String playerName, int level,
                                   int x, int y, int z, String cause, String who, boolean staff){

        try (Connection connection = plugin.getExternal().getHikari().getConnection();
             PreparedStatement playerDeath = connection.prepareStatement("INSERT IGNORE INTO Player_Death" +
                     " (Server_Name,World,Playername,Player_Level,X,Y,Z,Cause,By_Who,Is_Staff) VALUES(?,?,?,?,?,?,?,?,?,?)")){

            playerDeath.setString(1, serverName);
            playerDeath.setString(2, worldName);
            playerDeath.setString(3, playerName);
            playerDeath.setInt(4, level);
            playerDeath.setInt(5, x);
            playerDeath.setInt(6, y);
            playerDeath.setInt(7, z);
            playerDeath.setString(8, cause);
            playerDeath.setString(9, who);
            playerDeath.setBoolean(10, staff);

            playerDeath.executeUpdate();

        } catch (SQLException e){ e.printStackTrace(); }
    }

    public static void playerTeleport(String serverName, String worldName, String playerName, int ox, int oy, int oz, int tx, int ty, int tz, boolean staff){
        try {
            String database = "Player_Teleport";
            PreparedStatement playerTeleport = plugin.getExternal().getHikari().getConnection().prepareStatement("INSERT IGNORE INTO " + database + "(Server_Name,World,Playername,From_X,From_Y,From_Z,To_X,To_Y,To_Z,Is_Staff) VALUES(?,?,?,?,?,?,?,?,?,?)");
            playerTeleport.setString(1, serverName);
            playerTeleport.setString(2, worldName);
            playerTeleport.setString(3, playerName);
            playerTeleport.setInt(4, ox);
            playerTeleport.setInt(5, oy);
            playerTeleport.setInt(6, oz);
            playerTeleport.setInt(7, tx);
            playerTeleport.setInt(8, ty);
            playerTeleport.setInt(9, tz);
            playerTeleport.setBoolean(10, staff);
            playerTeleport.executeUpdate();

        } catch (SQLException e){ e.printStackTrace(); }
    }

    public static void playerJoin(String serverName, String worldName, String playerName, int x, int y, int z, InetSocketAddress IP, boolean staff) {
        try {
            String database = "Player_Join";
            PreparedStatement playerJoin = plugin.getExternal().getHikari().getConnection().prepareStatement("INSERT IGNORE INTO " + database + "(Server_Name,World,Playername,X,Y,Z,IP,Is_Staff) VALUES(?,?,?,?,?,?,INET_ATON(?),?)");
            playerJoin.setString(1, serverName);
            playerJoin.setString(2, worldName);
            playerJoin.setString(3, playerName);
            playerJoin.setInt(4, x);
            playerJoin.setInt(5, y);
            playerJoin.setInt(6, z);
            if (plugin.getConfig().getBoolean("Player-Join.Player-IP")) {
                playerJoin.setString(7, IP.getHostString());
            }else{

                playerJoin.setString(7, null);
            }
            playerJoin.setBoolean(8, staff);
            playerJoin.executeUpdate();

        } catch (SQLException e) { e.printStackTrace(); }
    }

    public static void playerLeave(String serverName, String worldName, String playerName, int x, int y, int z, boolean staff){
        try {
            String database = "Player_Leave";
            PreparedStatement playerLeave = plugin.getExternal().getHikari().getConnection().prepareStatement("INSERT IGNORE INTO " + database + "(Server_Name,World,Playername,X,Y,Z,Is_Staff) VALUES(?,?,?,?,?,?,?)");
            playerLeave.setString(1, serverName);
            playerLeave.setString(2, worldName);
            playerLeave.setString(3, playerName);
            playerLeave.setInt(4, x);
            playerLeave.setInt(5, y);
            playerLeave.setInt(6, z);
            playerLeave.setBoolean(7, staff);
            playerLeave.executeUpdate();

        } catch (SQLException e){ e.printStackTrace(); }
    }

    public static void blockPlace(String serverName, String worldName, String playerName, String block, int x, int y, int z, boolean staff){
        try {
            String database = "Block_Place";
            PreparedStatement blockPlace = plugin.getExternal().getHikari().getConnection().prepareStatement("INSERT IGNORE INTO " + database + "(Server_Name,World,Playername,Block,X,Y,Z,Is_Staff) VALUES(?,?,?,?,?,?,?,?)");
            blockPlace.setString(1, serverName);
            blockPlace.setString(2, worldName);
            blockPlace.setString(3, playerName);
            blockPlace.setString(4, block);
            blockPlace.setInt(5, x);
            blockPlace.setInt(6, y);
            blockPlace.setInt(7, z);
            blockPlace.setBoolean(8, staff);
            blockPlace.executeUpdate();

        } catch (SQLException e){ e.printStackTrace(); }
    }

    public static void blockBreak(String serverName, String worldName, String playerName, String blockname, int x, int y, int z,boolean staff){
        try {
            String database = "Block_Break";
            PreparedStatement blockBreak = plugin.getExternal().getHikari().getConnection().prepareStatement("INSERT IGNORE INTO " + database + "(Server_Name,World,Playername,Block,X,Y,Z,Is_Staff) VALUES(?,?,?,?,?,?,?,?)");
            blockBreak.setString(1, serverName);
            blockBreak.setString(2, worldName);
            blockBreak.setString(3, playerName);
            blockBreak.setString(4, blockname);
            blockBreak.setInt(5, x);
            blockBreak.setInt(6, y);
            blockBreak.setInt(7, z);
            blockBreak.setBoolean(8, staff);
            blockBreak.executeUpdate();

        } catch (SQLException e){ e.printStackTrace(); }
    }

    public static void TPS(String serverName, double tps){
        try {
            String database = "TPS";
            PreparedStatement TPS = plugin.getExternal().getHikari().getConnection().prepareStatement("INSERT IGNORE INTO " + database + "(Server_Name,TPS) VALUES(?,?)");
            TPS.setString(1, serverName);
            TPS.setDouble(2, tps);
            TPS.executeUpdate();

        } catch (SQLException e){ e.printStackTrace(); }
    }

    public static void RAM(String serverName, long TM, long UM, long FM){
        try {
            String database = "RAM";
            PreparedStatement RAM = plugin.getExternal().getHikari().getConnection().prepareStatement("INSERT IGNORE INTO " + database + "(Server_Name,Total_Memory,Used_Memory,Free_Memory) VALUES(?,?,?,?)");
            RAM.setString(1, serverName);
            RAM.setLong(2, TM);
            RAM.setLong(3, UM);
            RAM.setLong(4, FM);
            RAM.executeUpdate();

        } catch (SQLException e){ e.printStackTrace(); }
    }

    public static void playerKick(String serverName, String worldName, String playerName, int x, int y, int z, String reason, boolean staff){
        try {
            String database = "Player_Kick";
            PreparedStatement playerKick = plugin.getExternal().getHikari().getConnection().prepareStatement("INSERT IGNORE INTO " + database + "(Server_Name,World,Playername,X,Y,Z,Reason,Is_Staff) VALUES(?,?,?,?,?,?,?,?)");
            playerKick.setString(1, serverName);
            playerKick.setString(2, worldName);
            playerKick.setString(3, playerName);
            playerKick.setInt(4, x);
            playerKick.setInt(5, y);
            playerKick.setInt(6, z);
            playerKick.setString(7, reason);
            playerKick.setBoolean(8, staff);
            playerKick.executeUpdate();

        } catch (SQLException e){ e.printStackTrace(); }
    }

    public static void portalCreate(String serverName, String worldName, PortalCreateEvent.CreateReason By){
        try {
            String database = "Portal_Creation";
            PreparedStatement portalCreation = plugin.getExternal().getHikari().getConnection().prepareStatement("INSERT IGNORE INTO " + database + "(Server_Name,World,Caused_By) VALUES(?,?,?)");
            portalCreation.setString(1, serverName);
            portalCreation.setString(2, worldName);
            portalCreation.setString(3, By.toString());
            portalCreation.executeUpdate();

        } catch (SQLException e){ e.printStackTrace(); }
    }

    public static void levelChange(String serverName, String playerName, boolean staff){
        try {
            String database = "Player_Level";
            PreparedStatement playerLevel = plugin.getExternal().getHikari().getConnection().prepareStatement("INSERT IGNORE INTO " + database + "(Server_Name,Playername,Is_Staff) VALUES(?,?,?)");
            playerLevel.setString(1, serverName);
            playerLevel.setString(2, playerName);
            playerLevel.setBoolean(3, staff);
            playerLevel.executeUpdate();

        } catch (SQLException e){ e.printStackTrace(); }
    }

    public static void bucketFill(String serverName, String worldName, String playerName, String bucket, int x, int y, int z, boolean staff){
        try {
            String database = "Bucket_Fill";
            PreparedStatement bucketPlace = plugin.getExternal().getHikari().getConnection().prepareStatement("INSERT IGNORE INTO " + database + "(Server_Name,World,Playername,Bucket,X,Y,Z,Is_Staff) VALUES(?,?,?,?,?,?,?,?)");
            bucketPlace.setString(1, serverName);
            bucketPlace.setString(2, worldName);
            bucketPlace.setString(3, playerName);
            bucketPlace.setString(4, bucket);
            bucketPlace.setInt(5, x);
            bucketPlace.setInt(6, y);
            bucketPlace.setInt(7, z);
            bucketPlace.setBoolean(8, staff);
            bucketPlace.executeUpdate();

        } catch (SQLException e){ e.printStackTrace(); }
    }

    public static void bucketEmpty(String serverName, String worldName, String playerName, String bucket, int x, int y, int z, boolean staff){
        try {
            String database = "Bucket_Empty";
            PreparedStatement bucketPlace = plugin.getExternal().getHikari().getConnection().prepareStatement("INSERT IGNORE INTO " + database + "(Server_Name,World,Playername,Bucket,X,Y,Z,Is_Staff) VALUES(?,?,?,?,?,?,?,?)");
            bucketPlace.setString(1, serverName);
            bucketPlace.setString(2, worldName);
            bucketPlace.setString(3, playerName);
            bucketPlace.setString(4, bucket);
            bucketPlace.setInt(5, x);
            bucketPlace.setInt(6, y);
            bucketPlace.setInt(7, z);
            bucketPlace.setBoolean(8, staff);
            bucketPlace.executeUpdate();

        } catch (SQLException e){ e.printStackTrace(); }
    }

    public static void anvil(String serverName, String playerName, String newName, boolean staff){
        try {
            String database = "Anvil";
            PreparedStatement anvil = plugin.getExternal().getHikari().getConnection().prepareStatement("INSERT IGNORE INTO " + database + "(Server_Name,Playername,New_name,Is_Staff) VALUES(?,?,?,?)");
            anvil.setString(1, serverName);
            anvil.setString(2, playerName);
            anvil.setString(3, newName);
            anvil.setBoolean(4, staff);
            anvil.executeUpdate();

        } catch (SQLException e){ e.printStackTrace(); }
    }

    public static void serverStart(String serverName){
        try {
            String database = "Server_Start";
            PreparedStatement serverStart = plugin.getExternal().getHikari().getConnection().prepareStatement("INSERT IGNORE INTO " + database + "(Server_Name) VALUES(?)");
            serverStart.setString(1, serverName);
            serverStart.executeUpdate();

        } catch (SQLException e){ e.printStackTrace(); }
    }

    public static void serverStop(String serverName){
        try {
            String database = "Server_Stop";
            PreparedStatement serverStop = plugin.getExternal().getHikari().getConnection().prepareStatement("INSERT IGNORE INTO " + database + "(Server_Name) VALUES(?)");
            serverStop.setString(1, serverName);
            serverStop.executeUpdate();

        } catch (SQLException e){ e.printStackTrace(); }
    }

    public static void itemDrop(String serverName, String world, String playerName, String item, int amount, int x, int y, int z, List<String> enchantment, String changedName, boolean staff){
        try {
            String database = "Item_Drop";
            PreparedStatement itemDrop = plugin.getExternal().getHikari().getConnection().prepareStatement("INSERT IGNORE INTO " + database + "(Server_Name,World,Playername,Item,Amount,X,Y,Z,Enchantment,Changed_Name,Is_Staff) VALUES(?,?,?,?,?,?,?,?,?,?,?)");
            itemDrop.setString(1, serverName);
            itemDrop.setString(2, world);
            itemDrop.setString(3, playerName);
            itemDrop.setString(4, item);
            itemDrop.setInt(5, amount);
            itemDrop.setInt(6, x);
            itemDrop.setInt(7, y);
            itemDrop.setInt(8, z);
            itemDrop.setString(9, String.valueOf(enchantment));
            itemDrop.setString(10, changedName);
            itemDrop.setBoolean(11, staff);
            itemDrop.executeUpdate();

        } catch (SQLException e){ e.printStackTrace(); }
    }

    public static void enchant(String serverName, String world, String playerName, int x, int y, int z, List<String> enchantment, int enchantmentLevel, String item, int cost ,boolean staff){
        try {
            String database = "Enchanting";
            PreparedStatement enchanting = plugin.getExternal().getHikari().getConnection().prepareStatement("INSERT IGNORE INTO " + database + "(Server_Name,World,Playername,X,Y,Z,Enchantment,Enchantment_Level,Item,Cost,Is_Staff) VALUES(?,?,?,?,?,?,?,?,?,?,?)");
            enchanting.setString(1, serverName);
            enchanting.setString(2, world);
            enchanting.setString(3, playerName);
            enchanting.setInt(4, x);
            enchanting.setInt(5, y);
            enchanting.setInt(6, z);
            enchanting.setString(7, String.valueOf(enchantment));
            enchanting.setInt(8, enchantmentLevel);
            enchanting.setString(9, item);
            enchanting.setInt(10, cost);
            enchanting.setBoolean(11, staff);
            enchanting.executeUpdate();

        } catch (SQLException e){ e.printStackTrace(); }
    }

    public static void bookEditing(String serverName, String world, String playerName, int pages, List<String> content, String signed_by, boolean staff){
        try {
            String database = "Book_Editing";
            PreparedStatement enchanting = plugin.getExternal().getHikari().getConnection().prepareStatement("INSERT IGNORE INTO " + database + "(Server_Name,World,Playername,Page_Count,Page_Content,Signed_by,Is_Staff) VALUES(?,?,?,?,?,?,?)");
            enchanting.setString(1, serverName);
            enchanting.setString(2, world);
            enchanting.setString(3, playerName);
            enchanting.setInt(4, pages);
            enchanting.setString(5, String.valueOf(content));
            enchanting.setString(6, signed_by);
            enchanting.setBoolean(7, staff);
            enchanting.executeUpdate();

        } catch (SQLException e){ e.printStackTrace(); }
    }

    public static void afk(String serverName, String world, String playerName, int x, int y, int z,boolean staff){

        if (EssentialsUtil.getEssentialsAPI() != null) {

            try {
                String database = "AFK";
                PreparedStatement afk = plugin.getExternal().getHikari().getConnection().prepareStatement("INSERT IGNORE INTO " + database + "(Server_Name,World,Playername,X,Y,Z,Is_Staff) VALUES(?,?,?,?,?,?,?)");
                afk.setString(1, serverName);
                afk.setString(2, world);
                afk.setString(3, playerName);
                afk.setInt(4, x);
                afk.setInt(5, y);
                afk.setInt(6, z);
                afk.setBoolean(7, staff);
                afk.executeUpdate();

            } catch (SQLException e) { e.printStackTrace(); }
        }
    }

    public static void wrongPassword(String serverName, String world, String playerName,boolean staff){

        if (AuthMeUtil.getAuthMeAPI() != null) {

            try {
                String database = "Wrong_Password";
                PreparedStatement wrongPassword = plugin.getExternal().getHikari().getConnection().prepareStatement("INSERT IGNORE INTO " + database + "(Server_Name,World,Playername,Is_Staff) VALUES(?,?,?,?)");
                wrongPassword.setString(1, serverName);
                wrongPassword.setString(2, world);
                wrongPassword.setString(3, playerName);
                wrongPassword.setBoolean(4, staff);
                wrongPassword.executeUpdate();

            } catch (SQLException e) { e.printStackTrace(); }
        }
    }

    public static void itemPickup(String serverName, String world, String playerName, Material item, int amount, int x, int y, int z, String changedName, boolean staff){
        try {
            String database = "Item_Pickup";
            PreparedStatement itemPickup = plugin.getExternal().getHikari().getConnection().prepareStatement("INSERT IGNORE INTO " + database + "(Server_Name,World,Playername,Item,Amount,X,Y,Z,Changed_Name,Is_Staff) VALUES(?,?,?,?,?,?,?,?,?,?)");
            itemPickup.setString(1, serverName);
            itemPickup.setString(2, world);
            itemPickup.setString(3, playerName);
            itemPickup.setString(4, String.valueOf(item));
            itemPickup.setInt(5, amount);
            itemPickup.setInt(6, x);
            itemPickup.setInt(7, y);
            itemPickup.setInt(8, z);
            itemPickup.setString(9, changedName);
            itemPickup.setBoolean(10, staff);
            itemPickup.executeUpdate();

        } catch (SQLException e){ e.printStackTrace(); }
    }

    public static void furnace(String serverName, String world, String playerName, Material item, int amount, int x, int y, int z, boolean staff){
        try {
            String database = "Furnace";
            PreparedStatement furnace = plugin.getExternal().getHikari().getConnection().prepareStatement("INSERT IGNORE INTO " + database + "(Server_Name,World,Playername,Item,Amount,X,Y,Z,Is_Staff) VALUES(?,?,?,?,?,?,?,?,?)");
            furnace.setString(1, serverName);
            furnace.setString(2, world);
            furnace.setString(3, playerName);
            furnace.setString(4, String.valueOf(item));
            furnace.setInt(5, amount);
            furnace.setInt(6, x);
            furnace.setInt(7, y);
            furnace.setInt(8, z);
            furnace.setBoolean(9, staff);
            furnace.executeUpdate();

        } catch (SQLException e){ e.printStackTrace(); }
    }

    public static void rcon(String serverName, String IP, String command) {
        try {
            String database = "RCON";
            PreparedStatement rcon = plugin.getExternal().getHikari().getConnection().prepareStatement("INSERT IGNORE INTO " + database + "(Server_Name,IP,Command) VALUES(?,?,?)");
            rcon.setString(1, serverName);
            rcon.setString(2, IP);
            rcon.setString(3, command);
            rcon.executeUpdate();

        } catch (SQLException e) { e.printStackTrace(); }
    }

    public static void gameMode(String serverName, String world, String playerName, String gameMode, boolean staff) {
        try {
            String database = "Game_Mode";
            PreparedStatement game_Mode = plugin.getExternal().getHikari().getConnection().prepareStatement("INSERT IGNORE INTO " + database + "(Server_Name,World,Playername,Game_Mode,Is_Staff) VALUES(?,?,?,?,?)");
            game_Mode.setString(1, serverName);
            game_Mode.setString(2, world);
            game_Mode.setString(3, playerName);
            game_Mode.setString(4, gameMode);
            game_Mode.setBoolean(5, staff);
            game_Mode.executeUpdate();

        } catch (SQLException e) { e.printStackTrace(); }
    }

    public static void playerCraft(String serverName, String world, String playerName, String item, int amount, int x, int y, int z, boolean staff){
        try {
            String database = "Crafting";
            PreparedStatement craft = plugin.getExternal().getHikari().getConnection().prepareStatement("INSERT IGNORE INTO " + database + "(Server_Name,World,Playername,Item,Amount,X,Y,Z,Is_Staff) VALUES(?,?,?,?,?,?,?,?,?)");
            craft.setString(1, serverName);
            craft.setString(2, world);
            craft.setString(3, playerName);
            craft.setString(4, item);
            craft.setInt(5, amount);
            craft.setInt(6, x);
            craft.setInt(7, y);
            craft.setInt(8, z);
            craft.setBoolean(9, staff);
            craft.executeUpdate();

        } catch (SQLException e){ e.printStackTrace(); }
    }

    public static void vault(String serverName, String playerName, double oldBal, double newBal, boolean staff){
        try {
            String database = "Vault";
            PreparedStatement vault = plugin.getExternal().getHikari().getConnection().prepareStatement("INSERT IGNORE INTO " + database + "(Server_Name,Player_Name,Old_Balance,New_Balance,Is_Staff) VALUES(?,?,?,?,?)");
            vault.setString(1, serverName);
            vault.setString(2, playerName);
            vault.setDouble(3, oldBal);
            vault.setDouble(4, newBal);
            vault.setBoolean(5, staff);
            vault.executeUpdate();

        } catch (SQLException e){ e.printStackTrace(); }
    }

    public void emptyTable(){

        int when = externalDataDel;

        if (when <= 0) return;

        try{

            // Player Side Part
            PreparedStatement player_Chat = plugin.getExternal().getHikari().getConnection().prepareStatement("DELETE FROM Player_Chat WHERE DATE < NOW() - INTERVAL " + when + " DAY");

            PreparedStatement player_Commands = plugin.getExternal().getHikari().getConnection().prepareStatement("DELETE FROM Player_Commands WHERE Date < NOW() - INTERVAL " + when + " DAY");

            PreparedStatement player_Sign_Text = plugin.getExternal().getHikari().getConnection().prepareStatement("DELETE FROM Player_Sign_Text WHERE Date < NOW() - INTERVAL " + when + " DAY");

            PreparedStatement player_Join = plugin.getExternal().getHikari().getConnection().prepareStatement("DELETE FROM Player_Join WHERE DATE < NOW() - INTERVAL " + when + " DAY");

            PreparedStatement player_Leave = plugin.getExternal().getHikari().getConnection().prepareStatement("DELETE FROM Player_Leave WHERE Date < NOW() - INTERVAL " + when + " DAY");

            PreparedStatement player_Kick = plugin.getExternal().getHikari().getConnection().prepareStatement("DELETE FROM Player_Kick WHERE DATE < NOW() - INTERVAL " + when + " DAY");

            PreparedStatement player_Death = plugin.getExternal().getHikari().getConnection().prepareStatement("DELETE FROM Player_Death WHERE Date < NOW() - INTERVAL " + when + " DAY");

            PreparedStatement player_Teleport = plugin.getExternal().getHikari().getConnection().prepareStatement("DELETE FROM Player_Teleport WHERE DATE < NOW() - INTERVAL " + when + " DAY");

            PreparedStatement player_Level = plugin.getExternal().getHikari().getConnection().prepareStatement("DELETE FROM Player_Level WHERE Date < NOW() - INTERVAL " + when + " DAY");

            PreparedStatement block_Place = plugin.getExternal().getHikari().getConnection().prepareStatement("DELETE FROM Block_Place WHERE DATE < NOW() - INTERVAL " + when + " DAY");

            PreparedStatement block_Break = plugin.getExternal().getHikari().getConnection().prepareStatement("DELETE FROM Block_Break WHERE Date < NOW() - INTERVAL " + when + " DAY");

            PreparedStatement bucket_Fill = plugin.getExternal().getHikari().getConnection().prepareStatement("DELETE FROM Bucket_Fill WHERE Date < NOW() - INTERVAL " + when + " DAY");

            PreparedStatement bucket_Empty = plugin.getExternal().getHikari().getConnection().prepareStatement("DELETE FROM Bucket_Empty WHERE Date < NOW() - INTERVAL " + when + " DAY");

            PreparedStatement anvil = plugin.getExternal().getHikari().getConnection().prepareStatement("DELETE FROM Anvil WHERE DATE < NOW() - INTERVAL " + when + " DAY");

            PreparedStatement item_Drop = plugin.getExternal().getHikari().getConnection().prepareStatement("DELETE FROM Item_Drop WHERE Date < NOW() - INTERVAL " + when + " DAY");

            PreparedStatement enchanting = plugin.getExternal().getHikari().getConnection().prepareStatement("DELETE FROM Enchanting WHERE DATE < NOW() - INTERVAL " + when + " DAY");

            PreparedStatement book_Editing = plugin.getExternal().getHikari().getConnection().prepareStatement("DELETE FROM Book_Editing WHERE DATE < NOW() - INTERVAL " + when + " DAY");

            PreparedStatement item_pickup = plugin.getExternal().getHikari().getConnection().prepareStatement("DELETE FROM Item_Pickup WHERE DATE < NOW() - INTERVAL " + when + " DAY");

            PreparedStatement furnace = plugin.getExternal().getHikari().getConnection().prepareStatement("DELETE FROM Furnace WHERE DATE < NOW() - INTERVAL " + when + " DAY");

            PreparedStatement gameMode = plugin.getExternal().getHikari().getConnection().prepareStatement("DELETE FROM Game_Mode WHERE DATE < NOW() - INTERVAL " + when + " DAY");

            PreparedStatement craft = plugin.getExternal().getHikari().getConnection().prepareStatement("DELETE FROM Crafting WHERE DATE < NOW() - INTERVAL " + when + " DAY");

            // Server Side Part
            PreparedStatement server_Start = plugin.getExternal().getHikari().getConnection().prepareStatement("DELETE FROM Server_Start WHERE Date < NOW() - INTERVAL " + when + " DAY");

            PreparedStatement server_Stop = plugin.getExternal().getHikari().getConnection().prepareStatement("DELETE FROM Server_Stop WHERE DATE < NOW() - INTERVAL " + when + " DAY");

            PreparedStatement console_Commands = plugin.getExternal().getHikari().getConnection().prepareStatement("DELETE FROM Console_Commands WHERE DATE < NOW() - INTERVAL " + when + " DAY");

            PreparedStatement RAM = plugin.getExternal().getHikari().getConnection().prepareStatement("DELETE FROM RAM WHERE DATE < NOW() - INTERVAL " + when + " DAY");

            PreparedStatement TPS = plugin.getExternal().getHikari().getConnection().prepareStatement("DELETE FROM TPS WHERE Date < NOW() - INTERVAL " + when + " DAY");

            PreparedStatement portal_Creation = plugin.getExternal().getHikari().getConnection().prepareStatement("DELETE FROM Portal_Creation WHERE DATE < NOW() - INTERVAL " + when + " DAY");

            PreparedStatement rcon = plugin.getExternal().getHikari().getConnection().prepareStatement("DELETE FROM RCON WHERE DATE < NOW() - INTERVAL " + when + " DAY");

            // Extras Side Part
            if (EssentialsUtil.getEssentialsAPI() != null) {

                PreparedStatement afk = plugin.getExternal().getHikari().getConnection().prepareStatement("DELETE FROM AFK WHERE DATE < NOW() - INTERVAL " + when + " DAY");

                afk.executeUpdate();
            }

            if (AuthMeUtil.getAuthMeAPI() != null) {

                PreparedStatement wrong_password = plugin.getExternal().getHikari().getConnection().prepareStatement("DELETE FROM Wrong_Password WHERE DATE < NOW() - INTERVAL " + when + " DAY");

                wrong_password.executeUpdate();
            }

            if (VaultUtil.getVaultAPI() && VaultUtil.getVault().isEnabled()) {

                PreparedStatement vault = plugin.getExternal().getHikari().getConnection().prepareStatement("DELETE FROM Vault WHERE DATE < NOW() - INTERVAL " + when + " DAY");

                vault.executeUpdate();
            }

            player_Chat.executeUpdate();
            player_Commands.executeUpdate();
            player_Sign_Text.executeUpdate();
            player_Join.executeUpdate();
            player_Leave.executeUpdate();
            player_Kick.executeUpdate();
            player_Death.executeUpdate();
            player_Teleport.executeUpdate();
            player_Level.executeUpdate();
            block_Place.executeUpdate();
            block_Break.executeUpdate();
            bucket_Fill.executeUpdate();
            bucket_Empty.executeUpdate();
            anvil.executeUpdate();
            item_Drop.executeUpdate();
            enchanting.executeUpdate();
            book_Editing.executeUpdate();
            item_pickup.executeUpdate();
            furnace.executeUpdate();
            gameMode.executeUpdate();
            craft.executeUpdate();

            server_Start.executeUpdate();
            server_Stop.executeUpdate();
            console_Commands.executeUpdate();
            RAM.executeUpdate();
            TPS.executeUpdate();
            portal_Creation.executeUpdate();
            rcon.executeUpdate();

        } catch (SQLException e){

            plugin.getLogger().severe("An error has occurred while cleaning the tables, if the error persists, contact the Authors!");
            e.printStackTrace();

        }
    }
}