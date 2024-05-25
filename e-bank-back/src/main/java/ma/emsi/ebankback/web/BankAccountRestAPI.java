package ma.emsi.ebankback.web;

import ma.emsi.ebankback.dtos.AccountHistoryDTO;
import ma.emsi.ebankback.dtos.AccountOperationDTO;
import ma.emsi.ebankback.dtos.BankAccountDTO;
import ma.emsi.ebankback.entities.BankAccount;
import ma.emsi.ebankback.exceptions.BankAccountNotFoundException;
import ma.emsi.ebankback.services.BankAccountService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BankAccountRestAPI {
    private BankAccountService bankAccountService;

    public BankAccountRestAPI(BankAccountService bankAccountService) {
        this.bankAccountService = bankAccountService;
    }
    @GetMapping("/accounts/{accountId}")
    public BankAccountDTO getBankAccount(@PathVariable String accountId) throws BankAccountNotFoundException {
        return  bankAccountService.getBankAccount(accountId);
    }
    @GetMapping("/accounts")
    public List<BankAccountDTO> listAccounts() throws BankAccountNotFoundException {
        return bankAccountService.bankAccountList();
    }
    @GetMapping("/accounts/{accountId}/operations")
    public List<AccountOperationDTO> getHistory(@PathVariable String accountId)  {
        return  bankAccountService.accountHistory(accountId);
    }
    @GetMapping("/accounts/{accountId}/pageOperations")
    public AccountHistoryDTO getAccountHistory(@PathVariable String accountId,
                                               @RequestParam(name="page",defaultValue = "0") int page,
                                               @RequestParam(name="page",defaultValue = "0") int size) throws BankAccountNotFoundException {
        return  bankAccountService.getAccountHistory(accountId,page,size);
    }

}
