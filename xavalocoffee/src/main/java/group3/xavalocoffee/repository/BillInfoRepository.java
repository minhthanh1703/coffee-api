package group3.xavalocoffee.repository;

import group3.xavalocoffee.entities.BillInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BillInfoRepository extends JpaRepository<BillInfo, Integer> {
    List<BillInfo> findByBillId(int billId);

    List<BillInfo> findByBillIdAndCountGreaterThan(int billId, int count);

}
