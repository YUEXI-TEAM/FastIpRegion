package cc.mcyx.fastipregion.listener;

import cc.mcyx.fastipregion.service.IpService;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;

import java.util.UUID;

public class PlayerListener implements Listener {
    @EventHandler
    public void onJoin(AsyncPlayerPreLoginEvent event) {
        IpService.EXECUTOR_SERVICE.execute(() -> {
            UUID playerUUID = event.getUniqueId();
            String address = event.getAddress().getHostAddress();
            IpService.recordIpToAddress(playerUUID, address);
        });
    }
}
