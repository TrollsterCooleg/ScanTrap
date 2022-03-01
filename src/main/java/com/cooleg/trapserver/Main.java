package com.cooleg.trapserver;

import com.cooleg.trapserver.SqlUtils.SQLStorage;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.SQLException;

public final class Main extends JavaPlugin {

    PluginManager pm = Bukkit.getPluginManager();
    static SQLStorage sqlDatabase = null;

    @Override
    public void onEnable() {
        CustomConfig Config = new CustomConfig(this, "sql.yml");
        try {
            Config.load(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            sqlDatabase = new SqlLogger(this, Config);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        sqlDatabase.connectToDatabase();
        pm.registerEvents(new PaperPingListener((SqlLogger) sqlDatabase), this);
        pm.registerEvents(new AsyncJoinListener((SqlLogger) sqlDatabase), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

}
