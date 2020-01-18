package com.jnscas.statscsgo.pendinginputs;

import com.jnscas.statscsgo.persistence.UserStatsDAO;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

public class StartPendingInputTest {

    StartPendingInput command;

    @Before
    public void init() {
        UserStatsDAO userStatsDAO = mock(UserStatsDAO.class);
        command = new StartPendingInput(userStatsDAO); //FIXME
    }

    @Test
    public void isValidSteamId() {
        boolean result = command.isValidSteamId("12345678901234567");
        assertTrue(result);
    }

    @Test
    public void isNotValidSteamId() {
        boolean result = command.isValidSteamId("12345678q01234567");
        assertTrue(!result);
    }

}
