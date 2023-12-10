package ma.enset.comptecqrses.commands.aggregates;

import ma.enset.comptecqrses.commonApi.commands.CreateAccountCommand;
import ma.enset.comptecqrses.commonApi.commands.CrediteAccountCommand;
import ma.enset.comptecqrses.commonApi.commands.DebiteAccountCommand;
import ma.enset.comptecqrses.commonApi.enums.ACCOUNT_STATUS;
import ma.enset.comptecqrses.commonApi.events.AccountActivatedEvent;
import ma.enset.comptecqrses.commonApi.events.AccountCreatedEvent;
import ma.enset.comptecqrses.commonApi.events.AccountCreditedEvent;
import ma.enset.comptecqrses.commonApi.events.AccountDebitedEvent;
import ma.enset.comptecqrses.commonApi.exceptions.AccountSusppendedException;
import ma.enset.comptecqrses.commonApi.exceptions.AmountNegativeException;
import ma.enset.comptecqrses.commonApi.exceptions.BalanceNotSufficientException;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

@Aggregate
public class AccountAggregate {
    @AggregateIdentifier
    private String accountId;
    private double balance;
    private String currency;
    private ACCOUNT_STATUS status;

    // Required by Axon
    public AccountAggregate() {
    }

    @CommandHandler
    public AccountAggregate(CreateAccountCommand createAccountCommand) {
        if (createAccountCommand.getInitialBalance() < 0) {
            throw new RuntimeException("Initial balance must be non-negative.");
        }
        // Apply the event to the aggregate
        AggregateLifecycle.apply(new AccountCreatedEvent(
                createAccountCommand.getId(),
                createAccountCommand.getInitialBalance(),
                createAccountCommand.getCurrency(),
                ACCOUNT_STATUS.CREATED));
    }

    @EventSourcingHandler
    public void on(AccountCreatedEvent event) {
        this.accountId = event.getId();
        this.balance = event.getInitialBalance();
        this.status = ACCOUNT_STATUS.CREATED;
        this.currency = event.getCurrency();
        AggregateLifecycle.apply(new AccountActivatedEvent(
                event.getId(),
                ACCOUNT_STATUS.ACTIVATED
        ));
    }

    @EventSourcingHandler
    public void on(AccountActivatedEvent event){
        this.status=event.getStatus();
    }


    @CommandHandler
    public void handle(CrediteAccountCommand command){
        if (command.getAmount()<0) {
            throw new AmountNegativeException("Amount should not be negative");
        }else{
            AggregateLifecycle.apply(new AccountCreditedEvent(
                    command.getId(),
                    command.getAmount(),
                    command.getCurrency()
            ));
        }
    }
    @EventSourcingHandler
    public void on(AccountCreditedEvent event){
        this.balance+=event.getAmount();
    }


    @CommandHandler
    public void handle(DebiteAccountCommand command){
        if (command.getAmount()<0) throw new AmountNegativeException("Amount should not be negative");
        if(this.balance<command.getAmount()) throw new BalanceNotSufficientException("Balance not sufficient Exception =>"+this.balance);
        if(this.status.equals(ACCOUNT_STATUS.SUSPENDED)) throw new AccountSusppendedException("Account Susppended");

        AggregateLifecycle.apply(new AccountDebitedEvent(
            command.getId(),
            command.getAmount(),
            command.getCurrency()
        ));
    }
    @EventSourcingHandler
    public void on(AccountDebitedEvent event){
        this.balance-=event.getAmount();
    }
}
