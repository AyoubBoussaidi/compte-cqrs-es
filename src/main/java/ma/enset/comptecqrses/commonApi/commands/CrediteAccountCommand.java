package ma.enset.comptecqrses.commonApi.commands;

public class CrediteAccountCommand extends BAseCommand<String>{
    private  double amount;
    private String currency;
    public CrediteAccountCommand(String id, double amount, String currency) {
        super(id);
        this.amount = amount;
        this.currency = currency;
    }
}
