package com.jnscas.statscsgo;

import java.util.Map;

public class StatsResolver {

    private static String MESSAGE_STATS_USER_TEMPLATE =
            "\nK/D Ratio: %s\n" +
            "Headshot %%: %s\n" +
            "MVPs: %s";

    private static String MESSAGE_STATS_DETAIL_USER_TEMPLATE =
                    "\nTotal kills: %s\n" +
                    "Total deaths: %s\n" +
                    "Kills Headshot: %s\n" +
                    "Kills Knife: %s\n" +
                    "Kills Enemy Blinded: %s\n" +
                    "Kills HE Grenade: %s\n" +
                    "Kills Molotov: %s\n" +
                    "Planted bombs: %s\n" +
                    "Defused bombs : %s\n" +
                    "Weapons Donated: %s";

    public String createMyStatsUserMessage(Map<String, Map<String, Integer>> stats) {
        return String.format(MESSAGE_STATS_USER_TEMPLATE,
                getKdRatio(stats),
                getHeadShotsPorc(stats),
                getTotalMvps(stats)
        );
    }

    public String createStatsDetailUserMessage(Map<String, Map<String, Integer>> stats) {
        return String.format(MESSAGE_STATS_DETAIL_USER_TEMPLATE,
                getTotalKills(stats),
                getTotalDeaths(stats),
                getTotalKillsHeadshot(stats),
                getKillsKnife(stats),
                getKillsEnemyBlinded(stats),
                getTotalKillsHeGrenade(stats),
                getTotalKillsMolotov(stats),
                getTotalPlantedBombs(stats),
                getTotalDefusedBombs(stats),
                getWeaponsDonated(stats)
        );
    }

    public String createPistolsMessage(Map<String, Map<String, Integer>> stats) {
        return null; //TODO
    }

    private Integer getTotalKillsGlock(Map<String, Map<String, Integer>> stats) {
        return stats.get("total_kills_glock").get("value");
    }

    private Integer getTotalKillsDeagle(Map<String, Map<String, Integer>> stats) {
        return stats.get("total_kills_deagle").get("value");
    }

    private Integer getTotalKillsDualBerettas(Map<String, Map<String, Integer>> stats) {
        return stats.get("total_kills_elite").get("value");
    }

    private Integer getTotalKillsTec9(Map<String, Map<String, Integer>> stats) {
        return stats.get("total_kills_tec9").get("value");
    }

    private Integer getTotalKillsP250(Map<String, Map<String, Integer>> stats) {
        return stats.get("total_kills_p250").get("value");
    }

    private Integer getTotalKillsP2000(Map<String, Map<String, Integer>> stats) {
        return stats.get("total_kills_hkp2000").get("value");
    }

    private Integer getKillsKnife(Map<String, Map<String, Integer>> stats) {
        return stats.get("total_kills_knife").get("value");
    }

    private Integer getKillsEnemyBlinded(Map<String, Map<String, Integer>> stats) {
        return stats.get("total_kills_enemy_blinded").get("value");
    }

    private Integer getWeaponsDonated(Map<String, Map<String, Integer>> stats) {
        return stats.get("total_weapons_donated").get("value");
    }

    private String getHeadShotsPorc(Map<String, Map<String, Integer>> stats) {
        double totalKills = getTotalKills(stats);
        double headShotsPorc = (stats.get("total_kills_headshot").get("value") / totalKills) * 100;
        return String.format("%.1f", headShotsPorc);
    }

    private Integer getTotalKillsHeadshot(Map<String, Map<String, Integer>> stats) {
        return stats.get("total_kills_headshot").get("value");
    }

    private String getKdRatio(Map<String, Map<String, Integer>> stats) {
        double totalKills = getTotalKills(stats);
        double totalDeaths = stats.get("total_deaths").get("value");
        return String.format("%.2f", totalKills/totalDeaths);
    }

    private Integer getTotalKills(Map<String, Map<String, Integer>> stats) {
        return stats.get("total_kills").get("value");
    }

    private Integer getTotalDeaths(Map<String, Map<String, Integer>> stats) {
        return stats.get("total_deaths").get("value");
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

    private int getTotalKillsMolotov(Map<String, Map<String, Integer>> stats) {
        return stats.get("total_kills_molotov").get("value");
    }

    private int getTotalWins(Map<String, Map<String, Integer>> stats) {
        return stats.get("total_wins").get("value");
    }

    private int getTotalPlantedBombs(Map<String, Map<String, Integer>> stats) {
        return stats.get("total_planted_bombs").get("value");
    }
}
