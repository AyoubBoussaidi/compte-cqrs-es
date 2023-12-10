package ma.enset.comptecqrses.commonApi.exceptions;

public class AccountSusppendedException extends RuntimeException {
    public AccountSusppendedException(String accountSusppended) {
        super(accountSusppended);
    }
}
