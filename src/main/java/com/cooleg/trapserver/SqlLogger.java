package com.cooleg.trapserver;

import com.cooleg.trapserver.SqlUtils.SQLStorage;
import com.cooleg.trapserver.SqlUtils.SQLTableManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.atomic.AtomicInteger;

public class SqlLogger extends SQLStorage {

    public SqlLogger(Plugin main, CustomConfig sqlSettings) throws SQLException {
        super(main, sqlSettings);
    }

    @Override
    public void createTables(Plugin plugin) throws SQLException {
        Connection connection = getConnection();
        addTable(connection, new SQLTableManager("IPs", "PRIMARY KEY (ip)", "ip VARCHAR(15)"));
        addTable(connection, new SQLTableManager("UUIDs", "PRIMARY KEY (uuid)", "uuid VARCHAR(36)"));
        connection.close();
    }

    public void addIP(String ip){
        Bukkit.getScheduler().runTaskAsynchronously(main, ()->{
            Connection connection = null;
            try {
                connection = getConnection();
                PreparedStatement statement = connection.prepareStatement("INSERT INTO IPs (ip) VALUES (?) ON DUPLICATE KEY UPDATE ip=ip");
                statement.setString(1, ip);
                statement.executeUpdate();
            } catch (Exception e) {
                e.printStackTrace();
            }
            finally {
                try { if(connection != null) connection.close(); } catch (SQLException e){ e.printStackTrace(); }
            }
        });
    }

    public void addUUID(String uuid){
        Bukkit.getScheduler().runTaskAsynchronously(main, ()->{
            Connection connection = null;
            try {
                connection = getConnection();
                PreparedStatement statement = connection.prepareStatement("INSERT INTO UUIDs (uuid) VALUES (?) ON DUPLICATE KEY UPDATE uuid=uuid");
                statement.setString(1, uuid);
                statement.executeUpdate();
            } catch (Exception e) {
                e.printStackTrace();
            }
            finally {
                try { if(connection != null) connection.close(); } catch (SQLException e){ e.printStackTrace(); }
            }
        });
    }

}
