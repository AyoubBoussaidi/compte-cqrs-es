package ma.enset.comptecqrses.commonApi.commands;

import lombok.Getter;

public class CrediteAccountCommand extends BAseCommand<String>{
    @Getter private  double amount;
    @Getter private String currency;
    public CrediteAccountCommand(String id, double amount, String currency) {
        super(id);
        this.amount = amount;
        this.currency = currency;
    }
}
