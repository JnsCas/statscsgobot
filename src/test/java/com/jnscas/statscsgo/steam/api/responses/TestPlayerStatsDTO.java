package com.jnscas.statscsgo.steam.api.responses;

import java.util.HashMap;
import java.util.Map;

public class TestPlayerStatsDTO {

    private String steamID;
    private String gameName;
    private Map<String, Map<String, Integer>> stats;
    private Map<String, Map<String, Integer>> achievements;

    public TestPlayerStatsDTO() {
        this.stats = new HashMap<>();
        this.achievements = new HashMap<>();
    }

    public TestPlayerStatsDTO totalKills(int value) {
        stats.put("total_kills", insertValue(value));
        return this;
    }

    public TestPlayerStatsDTO totalDeaths(int value) {
        stats.put("total_deaths", insertValue(value));
        return this;
    }

    public TestPlayerStatsDTO mvps(int value) {
        stats.put("total_mvps", insertValue(value));
        return this;
    }

    public TestPlayerStatsDTO totalKillsHeadshot(int value) {
        stats.put("total_kills_headshot", insertValue(value));
        return this;
    }

    public TestPlayerStatsDTO totalKillsHeGrenade(int value) {
        stats.put("total_kills_hegrenade", insertValue(value));
        return this;
    }

    public TestPlayerStatsDTO totalDefusedBombs(int value) {
        stats.put("total_defused_bombs", insertValue(value));
        return this;
    }

    public TestPlayerStatsDTO totalPlantedBombs(int value) {
        stats.put("total_planted_bombs", insertValue(value));
        return this;
    }

    private Map<String, Integer> insertValue(int value) {
        Map<String, Integer> valueMap = new HashMap<>();
        valueMap.put("value", value);
        return valueMap;
    }

    public PlayerStatsDTO build() {
        PlayerStatsDTO playerStatsDTO = new PlayerStatsDTO();
        playerStatsDTO.setStats(stats);
        return playerStatsDTO;
    }

}