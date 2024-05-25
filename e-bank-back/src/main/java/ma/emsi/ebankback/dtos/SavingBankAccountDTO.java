package ma.emsi.ebankback.dtos;


import lombok.*;
import ma.emsi.ebankback.enums.AccountStatus;
import java.util.Date;


@Data
@Getter
@Setter
public class SavingBankAccountDTO extends BankAccountDTO {
    private String id;
    private double balance;
    private Date createdAt;
    private AccountStatus status;
    private CustomerDTO customerDTO;
    private double interestRate;
}
