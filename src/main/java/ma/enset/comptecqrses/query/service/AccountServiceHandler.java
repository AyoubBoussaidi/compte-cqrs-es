package ma.enset.comptecqrses.query.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.enset.comptecqrses.commonApi.enums.OPERATION_TYPE;
import ma.enset.comptecqrses.commonApi.events.AccountActivatedEvent;
import ma.enset.comptecqrses.commonApi.events.AccountCreatedEvent;
import ma.enset.comptecqrses.commonApi.events.AccountCreditedEvent;
import ma.enset.comptecqrses.commonApi.events.AccountDebitedEvent;
import ma.enset.comptecqrses.commonApi.queries.GetAccountByIdQuery;
import ma.enset.comptecqrses.commonApi.queries.GettAllAccountsQuery;
import ma.enset.comptecqrses.query.entities.Account;
import ma.enset.comptecqrses.query.entities.Operation;
import ma.enset.comptecqrses.query.repositories.AccountRepository;
import ma.enset.comptecqrses.query.repositories.OperationRepository;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class AccountServiceHandler{
    AccountRepository accountRepository;
    OperationRepository operationRepository;
    @EventHandler
    public  void on(AccountCreatedEvent event){
        log.info("*****************************************************");
        log.info("AccountCratedEvent received");
        Account account=new Account();
        account.setId(event.getId());
        account.setCurrency(event.getCurrency());
        account.setBalance(event.getInitialBalance());
        account.setStatus(event.getStatus());
        accountRepository.save(account);
    }
    @EventHandler
    public  void on(AccountActivatedEvent event){
        log.info("*****************************************************");
        log.info("AccountActivatedEvent received");
        Account account=accountRepository.findById(event.getId()).get();
        account.setStatus(event.getStatus());
        accountRepository.save(account);
    }

    @EventHandler
    public  void on(AccountCreditedEvent event){
        log.info("*****************************************************");
        log.info("AccountActivatedEvent received");
        Account account=accountRepository.findById(event.getId()).get();
        Operation operation=new Operation();
        operation.setType(OPERATION_TYPE.CREDIT);
        operation.setAmount(event.getAmount());
        operation.setDate(new Date());//a ne pas faire
        operation.setAccount(account);
        operation.setCurrency(operation.getCurrency());
        operationRepository.save(operation);
        account.setBalance(account.getBalance()+event.getAmount());
        accountRepository.save(account);
    }

    @EventHandler
    public  void on(AccountDebitedEvent event){
        log.info("*****************************************************");
        log.info("AccountActivatedEvent received");
        Account account=accountRepository.findById(event.getId()).get();
        Operation operation=new Operation();
        operation.setType(OPERATION_TYPE.DEBIT);
        operation.setAmount(event.getAmount());
        operation.setCurrency(event.getCurrency());
        operation.setDate(new Date());//a ne pas faire
        operation.setAccount(account);
        operationRepository.save(operation);
        account.setBalance(account.getBalance()-event.getAmount());
        accountRepository.save(account);
    }
@QueryHandler
    public List<Account> on(GettAllAccountsQuery query){
        return  accountRepository.findAll();
    }

    @QueryHandler
    public Account on(GetAccountByIdQuery query){
        return  accountRepository.findById(query.getId()).get();
    }







}
