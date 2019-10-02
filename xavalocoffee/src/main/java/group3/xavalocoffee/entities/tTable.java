package group3.xavalocoffee.entities;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_table")
public class tTable {

    @Id
    @Column(name = "table_number")
    private int tableNumber;

    @Column(name = "status")
    private boolean status;

    public tTable(int tableNumber, boolean status) {
        this.tableNumber = tableNumber;
        this.status = status;
    }

    public tTable() {
    }

    public int getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(int tableNumber) {
        this.tableNumber = tableNumber;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
