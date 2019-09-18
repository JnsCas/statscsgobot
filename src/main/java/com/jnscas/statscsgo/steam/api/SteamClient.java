package com.jnscas.statscsgo.steam.api;

import com.jnscas.pinhead.http.HttpClient;
import com.jnscas.statscsgo.model.User;

public class SteamClient extends HttpClient {

    private static final Integer APP_ID_CS_GO = 730;

    private String host;
    private String steamKey;
    private String steamVersion;
    private String protocol;

    public SteamClient(String protocol,
                       String host,
                       String steamKey,
                       String steamVersion) {
        this.protocol = protocol;
        this.host = host;
        this.steamKey = steamKey;
        this.steamVersion = steamVersion;
    }

    public String getRecentlyPlayedGames(User user) {
        String url = createBaseUrl(protocol, host) + "/GetRecentlyPlayedGames/" + steamVersion + "?key=" + steamKey +
                "&steamid=" + user.getSteamId64();
        return get(url);
    }

    public String getUserStatsCsGo(User user) {
        String url = createBaseUrl(protocol, host) + "/ISteamUserStats/GetUserStatsForGame/" + steamVersion + "?key=" + steamKey +
                "&steamid=" + user.getSteamId64() + "&appid=" + APP_ID_CS_GO;
        return get(url);
    }

    private String createBaseUrl(String protocol, String host) {
        return String.format("%s://%s", protocol, host);
    }

}
