package ma.emsi.ebankback.repositories;

import ma.emsi.ebankback.entities.BankAccount;
import ma.emsi.ebankback.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankAccountRepository extends JpaRepository<BankAccount,String> {
}
