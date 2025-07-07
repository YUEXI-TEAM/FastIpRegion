package cc.mcyx.fastipregion;

import cc.mcyx.fastipregion.listener.PlayerListener;
import cc.mcyx.fastipregion.papi.FastIpRegionExpansion;
import me.clip.placeholderapi.metrics.bukkit.Metrics;
import me.clip.placeholderapi.metrics.charts.SimplePie;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class FastIpRegion extends JavaPlugin {
    public static FastIpRegion fastIpRegion;
    public static final Integer PLUGIN_ID = 26412;

    @Override
    public void onLoad() {
        FastIpRegion.fastIpRegion = this;
    }

    @Override
    public void onEnable() {
        Metrics metrics = new Metrics(this, PLUGIN_ID);
        metrics.addCustomChart(new SimplePie("26412", () -> "FastIpRegion"));

        Bukkit.getPluginManager().registerEvents(new PlayerListener(), this);
        FastIpRegionExpansion.getInstance().register();
    }

    @Override
    public void onDisable() {
        FastIpRegionExpansion.getInstance().unregister();
    }
}
