package group3.xavalocoffee.repository;

import group3.xavalocoffee.entities.tTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import java.util.List;

public interface TableRepository extends JpaRepository<tTable, Integer> {
    List<tTable> findAll();
    
    tTable findByTableNumber(int id);


    List<tTable> findByStatus(boolean status);

//    @Query("select table from tbl_table table where table_number like ?1")
//    List<tTable> findByTableNumberContaining(int value);
}
