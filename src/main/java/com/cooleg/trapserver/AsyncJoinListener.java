package com.cooleg.trapserver;

import com.cooleg.trapserver.SqlUtils.SQLStorage;
import net.kyori.adventure.text.Component;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;

public class AsyncJoinListener implements Listener {
    private SqlLogger sqlLogger;

    public AsyncJoinListener(SqlLogger sqlLogger) {
        this.sqlLogger = sqlLogger;
    }

    @EventHandler
    public void AsyncPlayerPreLogin(AsyncPlayerPreLoginEvent e) {


        String uuid = e.getUniqueId().toString();
        String ip = e.getAddress().getHostAddress();
        System.out.println(uuid);
        e.allow();

        sqlLogger.addUUID(uuid);
        sqlLogger.addIP(ip);

        e.disallow(AsyncPlayerPreLoginEvent.Result.KICK_OTHER, Component.text("Adios!"));

    }

}
