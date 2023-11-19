package ma.enset.comptecqrses.commonApi.commands;

public class DebiteAccountCommand extends BAseCommand<String>{
    private  double amount;
    private String currency;
    public DebiteAccountCommand(String id, double amount, String currency) {
        super(id);
        this.amount = amount;
        this.currency = currency;
    }
}
