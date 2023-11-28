package ma.enset.comptecqrses.commonApi.exceptions;

public class AmountNegativeException extends RuntimeException{
    public AmountNegativeException(String amountShouldNotBeNegative) {
        super(amountShouldNotBeNegative);
    }
}
