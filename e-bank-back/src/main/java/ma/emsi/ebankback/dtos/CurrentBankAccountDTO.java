package ma.emsi.ebankback.dtos;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import ma.emsi.ebankback.enums.AccountStatus;

import java.util.Date;


@Data
@Getter
@Setter
public class CurrentBankAccountDTO extends BankAccountDTO {
    private String id;
    private double balance;
    private Date createdAt;
    private AccountStatus status;
    private CustomerDTO customerDTO;
    private double overDraft;
}
