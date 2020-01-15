package com.jnscas.statscsgo.steam;

import com.jnscas.pinhead.entities.UserPinhead;
import com.jnscas.pinhead.http.HttpClient;
import com.jnscas.statscsgo.model.UserStats;
import com.jnscas.statscsgo.steam.api.responses.UserStatsCsGoResponse;

import static com.jnscas.pinhead.http.Parser.parse;

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

    public String getRecentlyPlayedGames(UserStats userStats) {
        String url = createBaseUrl(protocol, host) + "/GetRecentlyPlayedGames/" + steamVersion + "?key=" + steamKey +
                "&steamid=" + userStats.getSteamId64();
        return get(url, String.class);
    }

    public UserStatsCsGoResponse getUserStatsCsGo(UserPinhead userPinhead) {
        String url = createBaseUrl(protocol, host) + "/ISteamUserStats/GetUserStatsForGame/" + steamVersion + "?key=" + steamKey +
                "&steamid=" + ((UserStats) userPinhead).getSteamId64() + "&appid=" + APP_ID_CS_GO;
        return get(url, UserStatsCsGoResponse.class);
    }

    private String createBaseUrl(String protocol, String host) {
        return String.format("%s://%s", protocol, host);
    }

}
