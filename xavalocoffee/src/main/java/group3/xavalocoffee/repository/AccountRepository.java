package group3.xavalocoffee.repository;

import group3.xavalocoffee.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, String> {
    List<Account> findAll();

    Account findByUsername (String username);

    Account findAccountByUsernameAndPassword(String username, String password);
}
