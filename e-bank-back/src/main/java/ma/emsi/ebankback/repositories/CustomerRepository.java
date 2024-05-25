package ma.emsi.ebankback.repositories;

import ma.emsi.ebankback.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,Long> {
}
