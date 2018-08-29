package jjtest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import jjtest.dao.AccountDao;
import jjtest.dao.AtmDao;
import jjtest.domain.Account;
import jjtest.domain.AtmBalance;
import jjtest.domain.AtmResponse;
import jjtest.domain.Notes;
import jjtest.service.AccountService;
import jjtest.service.AtmException;
import jjtest.service.AtmService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AtmTest
{
    private static  final Logger logger = Logger.getLogger(AtmTest.class.getName());

    @Autowired
    AccountDao accountDao;

    @Autowired
    AtmDao atmDao;

    @Autowired
    AccountService accountService;

    @Autowired
    AtmService atmService;

    @Test
    public void testAccount()
    {
        Account acc = accountService.getAccount("123456789", "1234");
        assertNotNull( acc );
    }

    @Test
    public void testPin()
    {
        Account acc = accountService.getAccount("123456789", "1234");
        assertEquals( acc.getPin(), "1234" );
    }

    @Test
    public void withdrawCorrectAmount()
    {
        AtmResponse response = atmService.withdrawal("987654321", "4321", 325);
        logger.info("notes " + response.getNotes());
        assertTrue( response.isSuccessfull() );

    }

    @Test
    public void withdrawTooBigAmountForAccount()
    {
        try {
            AtmResponse response = atmService.withdrawal("987654321", "4321", 1450);
            assertTrue(false);
        } catch (AtmException e) {
            assertTrue(e.getMessage().contains("The amount requested exceeds your maximum available amount"));
        }

    }

}
