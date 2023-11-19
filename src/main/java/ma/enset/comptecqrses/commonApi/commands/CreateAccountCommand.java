package ma.enset.comptecqrses.commonApi.commands;

public class CreateAccountCommand extends BAseCommand<String>{
    private  double initialBalance;
    private String currency;
    public CreateAccountCommand(String id, double initialBalance, String currency) {
        super(id);
        this.initialBalance = initialBalance;
        this.currency = currency;
    }
}
