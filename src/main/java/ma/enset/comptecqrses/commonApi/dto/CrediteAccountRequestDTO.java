package ma.enset.comptecqrses.commonApi.dto;

import lombok.Data;

@Data
public class CrediteAccountRequestDTO {
    private String accountId;
    private String currency;
    private double amount;
}
