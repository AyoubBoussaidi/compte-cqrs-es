package ma.enset.comptecqrses.commonApi.commands;

import lombok.Getter;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

public abstract class BAseCommand<T> {
    @TargetAggregateIdentifier
    @Getter private  T id;

    public BAseCommand(T id) {
        this.id = id;
    }
}
