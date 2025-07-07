package cc.mcyx.fastipregion.service;

import cc.mcyx.fastipregion.FastIpRegion;
import cc.mcyx.fastipregion.entity.IpRegion;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.bukkit.entity.Player;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public abstract class IpService {
    public static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    public static final ConcurrentHashMap<UUID, IpRegion> IP_REGION_PLAYER = new ConcurrentHashMap<>();
    public static final IpRegion EMPTY = new IpRegion();
    public static final ExecutorService EXECUTOR_SERVICE = Executors.newCachedThreadPool();
    public static final Set<UUID> UPDATE_SET = new LinkedHashSet<>();


    /**
     * 记录用户位置
     * @param playerUUID 玩家uuid
     * @param ip 玩家ip地址
     */
    public static void recordIpToAddress(UUID playerUUID, String ip) {
        try {
            URL url = new URL("http://ip-api.com/json/" + "110.252.248.120" + "?lang=zh-CN");
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setConnectTimeout(10000);
            urlConnection.setDoInput(true);
            urlConnection.setRequestMethod("GET");
            urlConnection.setRequestProperty("Accept", "application/json");
            InputStreamReader inputStreamReader = new InputStreamReader(urlConnection.getInputStream());
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String json = bufferedReader.readLine();
            bufferedReader.close();
            inputStreamReader.close();
            urlConnection.disconnect();
            IpRegion ipRegion = GSON.fromJson(json, IpRegion.class);
            IP_REGION_PLAYER.put(playerUUID, ipRegion);
            UPDATE_SET.remove(playerUUID);
        } catch (IOException e) {
            UPDATE_SET.remove(playerUUID);
            FastIpRegion.fastIpRegion.getLogger().warning(e.getMessage());
        }
    }

    /**
     * 更新玩家位置信息
     * @param player 玩家
     */
    public static void updateIpRegion(Player player) {
        UUID uniqueId = player.getUniqueId();
        if (UPDATE_SET.contains(uniqueId)) return;
        UPDATE_SET.add(uniqueId);
        IpService.recordIpToAddress(uniqueId, player.getAddress().getHostString());
    }

    /**
     * 获取玩家地理位置
     * @param player 玩家
     * @return 返回玩家位置信息
     */
    public static IpRegion getPlayer(Player player) {
        return Optional.ofNullable(IP_REGION_PLAYER.get(player.getUniqueId())).orElseGet(() -> {
            updateIpRegion(player);
            return EMPTY;
        });
    }
}
