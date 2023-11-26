package ma.enset.comptecqrses.commonApi.events;

import lombok.Getter;
import ma.enset.comptecqrses.commonApi.enums.ACCOUNT_STATUS;

public class AccountActivatedEvent extends BaseEvent<String>{
    @Getter
    private ACCOUNT_STATUS status;
    public AccountActivatedEvent(String id, ACCOUNT_STATUS status ) {
        super(id);
        this.status=status;
    }
}
