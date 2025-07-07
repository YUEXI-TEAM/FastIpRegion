package cc.mcyx.fastipregion.papi;

import cc.mcyx.fastipregion.FastIpRegion;
import cc.mcyx.fastipregion.entity.IpRegion;
import cc.mcyx.fastipregion.service.IpService;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.lang.reflect.Field;

public class FastIpRegionExpansion extends PlaceholderExpansion {

    private static FastIpRegionExpansion instance;

    public static FastIpRegionExpansion getInstance() {
        return instance == null ? (instance = new FastIpRegionExpansion()) : instance;
    }


    @Override
    public String getIdentifier() {
        return "FastIpRegion";
    }

    @Override
    public String getAuthor() {
        return "Zcc";
    }

    @Override
    public String getVersion() {
        return FastIpRegion.fastIpRegion.getDescription().getVersion();
    }


    @Override
    public String onRequest(OfflinePlayer offlinePlayer, String params) {
        if (offlinePlayer == null || !offlinePlayer.isOnline()) return "";
        Player player = offlinePlayer.getPlayer();
        IpRegion ipRegion = IpService.getPlayer(player);
        try {
            Field declaredField = IpRegion.class.getDeclaredField(params);
            declaredField.setAccessible(true);
            return (String) declaredField.get(ipRegion);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            return "未知类型: " + params;
        }
    }
}
