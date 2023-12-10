package ma.enset.comptecqrses.query.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.enset.comptecqrses.commonApi.enums.ACCOUNT_STATUS;

import java.util.Collection;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
public class Account {
    @Id
    private String id;
    private double balance;
    private String currency;
    @Enumerated(EnumType.STRING)
    private ACCOUNT_STATUS status;
    @OneToMany(mappedBy = "account")
    private Collection<Operation> operations;

}
