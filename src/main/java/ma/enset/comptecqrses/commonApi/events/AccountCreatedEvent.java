package ma.enset.comptecqrses.commonApi.events;

import lombok.Getter;
import ma.enset.comptecqrses.commonApi.enums.ACCOUNT_STATUS;

public class AccountCreatedEvent extends BaseEvent<String>{
    @Getter private double initialBalance;
    @Getter private String currency;
    @Getter private ACCOUNT_STATUS status;
    public AccountCreatedEvent(String id, double initialBalance, String currency, ACCOUNT_STATUS status) {
        super(id);
        this.initialBalance = initialBalance;
        this.currency = currency;
        this.status = status;
    }
}
