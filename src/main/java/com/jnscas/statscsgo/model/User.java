package com.jnscas.statscsgo.model;

import com.jnscas.entities.UserTelegram;
import org.bson.codecs.pojo.annotations.BsonIgnore;

import java.util.Optional;

public class User extends UserTelegram {
    private String steamId64;

    /**
     * public constructor for mongodb
     */
    public User() {

    }

    public User(String userName) {
        super(userName, Optional.empty());;
    }

    public String getSteamId64() {
        return steamId64;
    }

    public void setSteamId64(String steamId64) {
        this.steamId64 = steamId64;
    }

    @BsonIgnore
    public boolean isAlreadyRegistered() {
        return steamId64 != null;
    }
}
