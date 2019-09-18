package com.jnscas;

import com.jnscas.statscsgo.StatsCsGoBotWarmUp;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class Main {

    public static void main( String[] args ) {
        ApiContextInitializer.init();
        TelegramBotsApi botsApi = new TelegramBotsApi();
        try {
            botsApi.registerBot(StatsCsGoBotWarmUp.init());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        } finally {
            //StatsCsGoBotWarmUp.close(); FIXME
        }
    }
}
