package jjtest.dao;

import jjtest.domain.AtmBalance;

/*
 *  In the real life I would use CrudRepository interface, but for simplicity I am using mock DAO class.
 *
 */
public interface AtmDao {

    AtmBalance getAtmBalance();

    void setAtmBalance(AtmBalance balance);

}
