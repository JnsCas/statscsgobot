package com.jnscas.statscsgo.steam.api.responses;

public class UserStatsCsGoResponse {

    private PlayerStatsDTO playerstats;

    /**
     * for jackson
     */
    public UserStatsCsGoResponse() {
    }

    public PlayerStatsDTO getPlayerstats() {
        return playerstats;
    }

    //FIXME eliminar set (existe solo por jackson)
    public void setPlayerstats(PlayerStatsDTO playerstats) {
        this.playerstats = playerstats;
    }
}
