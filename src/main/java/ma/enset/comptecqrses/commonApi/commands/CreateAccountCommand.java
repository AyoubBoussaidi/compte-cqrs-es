package ma.enset.comptecqrses.commonApi.commands;

import lombok.Data;
import lombok.Getter;


public class CreateAccountCommand extends BAseCommand<String>{
    @Getter
    private  double initialBalance;
    @Getter
    private String currency;
    public CreateAccountCommand(String id, double initialBalance, String currency) {
        super(id);
        this.initialBalance = initialBalance;
        this.currency = currency;
    }
}
