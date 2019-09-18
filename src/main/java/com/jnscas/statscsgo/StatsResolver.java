package com.jnscas.statscsgo;

import java.util.Map;

public class StatsResolver {

    private static String MESSAGE_STATS_USER_TEMPLATE =
            "\nK/D Ratio: %s\n" +
            "Headshot : %s\n" +
            "MVPs: %s";

    private static String MESSAGE_STATS_DETAIL_USER_TEMPLATE =
            "\nKills HE Grenade: %s\n" +
            "Defused bombs : %s\n" +
            "Planted bombs: %s";

    public String createStatsUserMessage(Map<String, Map<String, Integer>> stats) {
        return String.format(MESSAGE_STATS_USER_TEMPLATE,
                getKdRatio(stats),
                getHeadShotsPorc(stats),
                getTotalMvps(stats)
        );
    }

    public String createStatsDetailUserMessage(Map<String, Map<String, Integer>> stats) {
        return String.format(MESSAGE_STATS_DETAIL_USER_TEMPLATE,
                getTotalKillsHeGrenade(stats),
                getTotalDefusedBombs(stats),
                getTotalPlantedBombs(stats)
        );
    }

    private String getHeadShotsPorc(Map<String, Map<String, Integer>> stats) {
        double totalKills = stats.get("total_kills").get("value");
        double headShotsPorc = (stats.get("total_kills_headshot").get("value") / totalKills) * 100;
        return String.format("%.1f", headShotsPorc);
    }

    private String getKdRatio(Map<String, Map<String, Integer>> stats) {
        double totalKills = stats.get("total_kills").get("value");
        double totalDeaths = stats.get("total_deaths").get("value");
        return String.format("%.2f", totalKills/totalDeaths);
    }

    private int getTotalMvps(Map<String, Map<String, Integer>> stats) {
        return stats.get("total_mvps").get("value");
    }

    private int getTotalDefusedBombs(Map<String, Map<String, Integer>> stats) {
        return stats.get("total_defused_bombs").get("value");
    }

    private int getTotalWinsPistolRound(Map<String, Map<String, Integer>> stats) {
        return stats.get("total_wins_pistolround").get("value");
    }

    private int getTotalKillsHeGrenade(Map<String, Map<String, Integer>> stats) {
        return stats.get("total_kills_hegrenade").get("value");
    }

    private int getTotalWins(Map<String, Map<String, Integer>> stats) {
        return stats.get("total_wins").get("value");
    }

    private int getTotalPlantedBombs(Map<String, Map<String, Integer>> stats) {
        return stats.get("total_planted_bombs").get("value");
    }
}
