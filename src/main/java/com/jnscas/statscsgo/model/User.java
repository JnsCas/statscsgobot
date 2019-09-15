package com.jnscas.statscsgo.model;

import com.jnscas.entities.UserTelegram;

import java.util.Optional;

public class User extends UserTelegram {

    private Optional<String> steamId64;

    public User(String userName, Optional<String> steamId64) {
        super(userName);
        this.steamId64 = steamId64;
    }

    public Optional<String> getSteamId64() {
        return steamId64;
    }

    public void setSteamId64(Optional<String> steamId64) {
        this.steamId64 = steamId64;
    }


}
