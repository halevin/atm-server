package jjtest.controllers;

import jjtest.domain.AtmResponse;
import jjtest.service.AccountService;
import jjtest.service.AtmException;
import jjtest.service.AtmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller for ATM. To test functionality run the server
 * and call in browser
 *
 * http://localhost:8080/actuator/health
 * http://localhost:8080/balance?account=123456789&pin=1234
 * http://localhost:8080/maximum?account=123456789&pin=1234
 *
 * to test witdrawal call
 * curl --data "account=123456789&pin=1234&amount=125" localhost:8080/withdraw
 *
 */

@RestController
@RequestMapping("/")
public class AtmController {

    @Autowired
    public AtmService atmService;

    @Autowired
    public AccountService accountService;

    /**
     * REST endpoint to request balance information
     *
     * @param account
     * @param pin
     * @return
     */

    @RequestMapping(value = "/balance", method = RequestMethod.GET)
    public AtmResponse balance(@RequestParam("account") String account, @RequestParam("pin") String pin) {
        AtmResponse response;
        try {
            double balance = accountService.getBalance(account, pin);
            response = new AtmResponse(true, "Current balance is "+String.format("%.2f", balance));
            response.setValue(balance);
        } catch (AtmException e) {
            response = new AtmResponse(false, e.getMessage());
        }
        return response;
    }

    /**
     * REST endpoint to request maximum available amount
     *
     * @param account
     * @param pin
     * @return
     */
    @RequestMapping(value = "/maximum", method = RequestMethod.GET)
    public AtmResponse maximalWithdrawalAmount(@RequestParam("account") String account, @RequestParam("pin") String pin) {
        AtmResponse response;
        try {
            double amount = accountService.getMaximumWithdrawalAmount(account, pin);
            response = new AtmResponse(true, "Your maximal withdrawal amount is "+String.format("%.2f", amount));
            response.setValue(amount);
        } catch (AtmException e) {
            response = new AtmResponse(false, e.getMessage());
        }
        return response;
    }

    /**
     * REST POST endpoint to withdraw money from ATM
     *
     * @param account
     * @param pin
     * @param amount
     * @return
     */
    @PostMapping("/withdraw")
    public AtmResponse withdrawMoney(@RequestParam("account") String account, @RequestParam("pin") String pin, @RequestParam("amount") Integer amount){
        AtmResponse response;
        try {
            response = atmService.withdrawal(account, pin, amount);
        } catch (AtmException e) {
            response = new AtmResponse(false, e.getMessage());
        }
        return response;
    }

}
