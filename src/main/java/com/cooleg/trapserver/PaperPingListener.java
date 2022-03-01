package com.cooleg.trapserver;

import com.destroystokyo.paper.event.server.PaperServerListPingEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class PaperPingListener implements Listener {
    private SqlLogger sqlLogger;

    public PaperPingListener(SqlLogger sqlLogger) {
        this.sqlLogger = sqlLogger;
    }

    @EventHandler
    public void PaperServerListPing(PaperServerListPingEvent e) {
        String ip = e.getAddress().getHostAddress();
        System.out.println(ip);
        this.sqlLogger.addIP(ip);
    }
}