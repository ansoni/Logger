package me.prism3.logger.utils;

import me.prism3.logger.Main;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;

public class Data {

    private final Main main = Main.getInstance();

    // Date Format
    public static DateTimeFormatter dateTimeFormatter;

    // String
    public static String configVersion;
    public static String pluginVersion;
    public static String serverName;
    public static String gameModeConf;
    public static String dbType;
    public static String dbHost;
    public static String dbUserName;
    public static String dbPassword;
    public static String dbName;
    public static String langPath;
    public static String fileType;
    public static String selectedLang;
    public static String discordSupportServer;
    public static String pluginPrefix;

    // List<String>
    public static List<String> commandsToBlock;
    public static List<String> commandsToLog;
    public static List<String> consoleCommandsToBlock;

    // Integer
    public static int resource_ID;
    public static int ramTpsChecker;
    public static int vaultChecker;
    public static int abovePlayerLevel;
    public static int ramPercent;
    public static int tpsMedium;
    public static int tpsCritical;
    public static int dbPort;
    public static int externalDataDel;
    public static int sqliteDataDel;
    public static int allowedBackups;

    // Long
    public static long fileDeletion;

    // Boolean
    public static boolean isLogToStdout;
    public static boolean isLogToFiles;
    public static boolean isUpdateChecker;
    public static boolean isExternal;
    public static boolean isSqlite;
    public static boolean isStaffEnabled;
    public static boolean isWhitelisted;
    public static boolean isBlacklisted;
    public static boolean isCommandsToBlock;
    public static boolean isCommandsToLog;
    public static boolean isPlayerIP;
    public static boolean isConsoleCommands;
    public static boolean isRegistration;
    public static boolean isPlayerDeathBackup;

    // Permission String
    public static String loggerExempt;
    public static String loggerExemptDiscord;
    public static String loggerStaff;
    public static String loggerStaffLog;
    public static String loggerUpdate;
    public static String loggerSpyBypass;
    public static String loggerSpy;
    public static String loggerReload;

    public void initializeDateFormatter() {

        dateTimeFormatter = DateTimeFormatter.ofPattern(Objects.requireNonNull(this.main.getConfig().getString("Time-Formatter")));

    }

    public void initializeStrings() {

        configVersion = this.main.getConfig().getString("Config");
        serverName = this.main.getConfig().getString("Server-Name");
        gameModeConf = this.main.getConfig().getString("Game-Mode");
        dbType = this.main.getConfig().getString("Database.Type");
        dbHost = this.main.getConfig().getString("Database.Host");
        dbUserName = this.main.getConfig().getString("Database.Username");
        dbPassword = this.main.getConfig().getString("Database.Password");
        dbName = this.main.getConfig().getString("Database.Database");
        langPath = "messages";
        fileType = ".yml";
        selectedLang = this.main.getConfig().getString("Language");
        discordSupportServer = "https://discord.gg/MfR5mcpVfX";
        pluginPrefix = this.main.getConfig().getString("Plugin-Prefix");

    }

    public void initializeListOfStrings() {

        commandsToBlock = this.main.getConfig().getStringList("Player-Commands.Commands-to-Block");
        commandsToLog = this.main.getConfig().getStringList("Player-Commands.Commands-to-Log");
        consoleCommandsToBlock = this.main.getConfig().getStringList("Console-Commands.Commands-to-Block");

    }

    public void initializeIntegers() {

        pluginVersion = this.main.getDescription().getVersion();
        resource_ID = 94236;
        ramTpsChecker = this.main.getConfig().getInt("RAM-TPS-Checker");
        vaultChecker = this.main.getConfig().getInt("Vault-Checker");
        abovePlayerLevel = this.main.getConfig().getInt("Player-Level.Log-Above");
        ramPercent = this.main.getConfig().getInt("RAM.Percent");
        tpsMedium = this.main.getConfig().getInt("TPS.Value-Medium");
        tpsCritical = this.main.getConfig().getInt("TPS.Value-Critical");
        dbPort = this.main.getConfig().getInt("Database.Port");
        externalDataDel = this.main.getConfig().getInt("Database.Data-Deletion");
        sqliteDataDel = this.main.getConfig().getInt("SQLite.Data-Deletion");
        allowedBackups = this.main.getConfig().getInt("Player-Death-Backup.Max-Backup");

    }

    public void initializeLongs() {

        fileDeletion = this.main.getConfig().getLong("File-Deletion");

    }

    public void initializeBooleans() {

        isLogToStdout = this.main.getConfig().getBoolean("Log-to-Stdout");
        isLogToFiles = this.main.getConfig().getBoolean("Log-to-Files");
        isUpdateChecker = this.main.getConfig().getBoolean("Update-Checker");
        isExternal = this.main.getConfig().getBoolean("Database.Enable");
        isSqlite = this.main.getConfig().getBoolean("SQLite.Enable");
        isStaffEnabled = this.main.getConfig().getBoolean("Staff.Enabled");
        isWhitelisted = this.main.getConfig().getBoolean("Player-Commands.Whitelist-Commands");
        isBlacklisted = this.main.getConfig().getBoolean("Player-Commands.Blacklist-Commands");
        isCommandsToBlock = this.main.getConfig().getBoolean("Player-Commands.Blacklist-Commands");
        isCommandsToLog = this.main.getConfig().getBoolean("Player-Commands.Whitelist-Commands");
        isPlayerIP = this.main.getConfig().getBoolean("Player-Join.Player-IP");
        isConsoleCommands = this.main.getConfig().getBoolean("Console-Commands.Blacklist-Commands");
        isRegistration = this.main.getConfig().getBoolean("Log-Player.Registration");
        isPlayerDeathBackup = this.main.getConfig().getBoolean("Player-Death-Backup.Enabled");

    }

    public void initializePermissionStrings() {

        loggerExempt = "logger.exempt";
        loggerExemptDiscord = "logger.exempt.discord";
        loggerStaff = "logger.staff";
        loggerStaffLog = "logger.staff.log";
        loggerSpyBypass = "logger.spy.bypass";
        loggerSpy = "logger.spy";
        loggerReload = "logger.reload";

    }
}
