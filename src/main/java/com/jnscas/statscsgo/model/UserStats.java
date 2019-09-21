package com.jnscas.statscsgo.model;

import com.jnscas.pinhead.entities.UserPinhead;
import org.bson.codecs.pojo.annotations.BsonIgnore;

import java.util.Optional;

public class UserStats extends UserPinhead {

    private String steamId64;

    /**
     * public constructor for mongodb
     */
    public UserStats() {

    }

    public UserStats(Integer telegramId) {
        super(telegramId, Optional.empty());;
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
