package ma.enset.comptecqrses.query.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.enset.comptecqrses.commonApi.queries.GetAccountByIdQuery;
import ma.enset.comptecqrses.commonApi.queries.GettAllAccountsQuery;
import ma.enset.comptecqrses.query.entities.Account;
import org.apache.coyote.Response;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/query/accounts")
@AllArgsConstructor
@Slf4j
public class AccountQueryController {
    private QueryGateway queryGateway;

    @GetMapping("/allAccounts")
    public List<Account> accountList(){
        List<Account> response =queryGateway.query(new GettAllAccountsQuery(),
                ResponseTypes.multipleInstancesOf(Account.class)).join();
        return  response;
    }

    @GetMapping("/{id}")
    public Account getAccount(@PathVariable String id){
        Account response =queryGateway.query(new GetAccountByIdQuery(id), ResponseTypes.instanceOf(Account.class)).join();
        return  response;
    }
}
