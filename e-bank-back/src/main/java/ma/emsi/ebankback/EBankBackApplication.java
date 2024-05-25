package ma.emsi.ebankback;

import ma.emsi.ebankback.dtos.BankAccountDTO;
import ma.emsi.ebankback.dtos.CurrentBankAccountDTO;
import ma.emsi.ebankback.dtos.CustomerDTO;
import ma.emsi.ebankback.dtos.SavingBankAccountDTO;
import ma.emsi.ebankback.entities.*;
import ma.emsi.ebankback.enums.AccountStatus;
import ma.emsi.ebankback.enums.OperationType;
import ma.emsi.ebankback.exceptions.BalanceNotSufficientException;
import ma.emsi.ebankback.exceptions.BankAccountNotFoundException;
import ma.emsi.ebankback.exceptions.CustomerNotFoundException;
import ma.emsi.ebankback.repositories.AccountOperationRepository;
import ma.emsi.ebankback.repositories.BankAccountRepository;
import ma.emsi.ebankback.repositories.CustomerRepository;
import ma.emsi.ebankback.services.BankAccountService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

@SpringBootApplication
public class EBankBackApplication {

    public static void main(String[] args) {
        SpringApplication.run(EBankBackApplication.class, args);
    }
    @Bean
    CommandLineRunner commandLineRunner(BankAccountService bankAccountService, BankAccountRepository bankAccountRepository){
        return args -> {
            Stream.of("Hassan","Imane","Zineb").forEach(name -> {
                CustomerDTO customer = new CustomerDTO();
                customer.setName(name);

                customer.setEmail(name+"@gmail.com");
                bankAccountService.saveCustomer(customer);
            });
            bankAccountService.listCustomers().forEach(customer -> {
                try {
                    bankAccountService.saveCurrentBankAccount(Math.random()*5000,9000, customer.getId());
                    bankAccountService.saveSavingBankAccount(Math.random()*7000,4.5, customer.getId());

                } catch (CustomerNotFoundException e) {
                    e.printStackTrace();
                }

            });
            List<BankAccountDTO> bankAccounts=bankAccountService.bankAccountList();
            for (BankAccountDTO bankAccount : bankAccounts) {
                for (int i=0;i<10;i++) {
                    String accountId;
                    if (bankAccount instanceof SavingBankAccountDTO){
                        accountId=((SavingBankAccountDTO) bankAccount).getId();
                    }
                    else {
                        accountId=((CurrentBankAccountDTO) bankAccount).getId();
                    }
                    bankAccountService.credit(accountId,10000+Math.random()*9000,"Credit");
                    bankAccountService.debit(accountId,1000+Math.random()*3000,"Debit");
                }
            }
    };}}
//    //@Bean
//    CommandLineRunner start(CustomerRepository customerRepository,
//                            AccountOperationRepository accountOperationRepository,
//                            BankAccountRepository bankAccountRepository) {
//        return args -> {
//            Stream.of("Hassan","Yasser","Zineb").forEach(name -> {
//                Customer customer = new Customer();
//                customer.setName(name);
//                customer.setEmail(name+"@gmail.com");
//                customerRepository.save(customer);
//            });
//            customerRepository.findAll().forEach(customer -> {
//                CurrentAccount currentAccount = new CurrentAccount();
//                currentAccount.setId(UUID.randomUUID().toString());
//                currentAccount.setBalance(Math.random()*100.0);
//                currentAccount.setStatus(AccountStatus.CREATED);
//                currentAccount.setCreatedAt(new Date());
//                currentAccount.setCustomer(customer);
//                currentAccount.setOverDraft(7000);
//                bankAccountRepository.save(currentAccount);
//
//                SavingAccount savingAccount = new SavingAccount();
//                savingAccount.setBalance(Math.random()*100.0);
//                savingAccount.setId(UUID.randomUUID().toString());
//                savingAccount.setStatus(AccountStatus.CREATED);
//                savingAccount.setCreatedAt(new Date());
//                savingAccount.setCustomer(customer);
//                savingAccount.setInterestRate(5.5);
//                bankAccountRepository.save(savingAccount);
//            });
//            bankAccountRepository.findAll().forEach(acc->{
//                for (int i=0;i<10;i++)
//                {
//                    AccountOperation accountOperation=new AccountOperation();
//                    accountOperation.setOperationDate(new Date());
//                    accountOperation.setAmount(Math.random()*10000.0);
//                    accountOperation.setType(Math.random()>0.5? OperationType.DEBIT:OperationType.CREDIT);
//                    accountOperation.setBankAccount(acc);
//                    accountOperationRepository.save(accountOperation);
//                }
//
//        };
//    }
//}
