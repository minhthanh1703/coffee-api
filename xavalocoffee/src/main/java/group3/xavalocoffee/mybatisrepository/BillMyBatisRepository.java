package group3.xavalocoffee.mybatisrepository;

import group3.xavalocoffee.entities.Bill;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BillMyBatisRepository {

    List<Bill> getBillByUsernameAndPage(String username, int from, int to);
}
