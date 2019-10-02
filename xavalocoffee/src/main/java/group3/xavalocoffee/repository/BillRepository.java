package group3.xavalocoffee.repository;

import group3.xavalocoffee.entities.Bill;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BillRepository extends JpaRepository<Bill, Integer> {
    Bill findByTableNumberAndPaided(int tableNumber, boolean paided);

    List<Bill> findByUsernameSatff(String username);
}
