package jjtest;

import jjtest.dao.AccountDao;
import jjtest.dao.AtmDao;
import jjtest.dao.MockAccountDaoImpl;
import jjtest.dao.MockAtmDaoImpl;
import jjtest.domain.AtmBalance;
import jjtest.service.AccountService;
import jjtest.service.AccountServiceImpl;
import jjtest.service.AtmService;
import jjtest.service.AtmServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Service
public class Configuration {

    @Bean
    public AtmService getAtmService() {
        return new AtmServiceImpl();
    }

    @Bean
    public AccountService getAccountService() {
        return new AccountServiceImpl();
    }

    @Bean
    public AccountDao getAccountDao() {
        return new MockAccountDaoImpl();
    }

    @Bean
    public AtmDao getAtmDao() {
        return new MockAtmDaoImpl();
    }

}
