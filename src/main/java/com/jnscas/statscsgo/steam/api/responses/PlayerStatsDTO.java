package com.jnscas.statscsgo.steam.api.responses;

import java.util.Map;

public class PlayerStatsDTO {

    private String steamID;
    private String gameName;
    private Map<String, Map<String, Integer>> stats;
    private Map<String, Map<String, Integer>> achievements;

    //FIXME eliminar sets (existe solo por jackson)

    public PlayerStatsDTO() {
    }

    public String getSteamID() {
        return steamID;
    }

    public void setSteamID(String steamID) {
        this.steamID = steamID;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public Map<String, Map<String, Integer>> getStats() {
        return stats;
    }

    public void setStats(Map<String, Map<String, Integer>> stats) {
        this.stats = stats;
    }

    public Map<String, Map<String, Integer>> getAchievements() {
        return achievements;
    }

    public void setAchievements(Map<String, Map<String, Integer>> achievements) {
        this.achievements = achievements;
    }
}
