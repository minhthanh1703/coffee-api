package group3.xavalocoffee.entities;

import javax.persistence.*;

@Entity
@Table(name = "tbl_bill_info")
public class BillInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bill_info_id")
    private int id;

    @Column(name = "bill_id")
    private int billId;

    @Column(name = "drink_id")
    private int drinkId;

    @Column(name = "count")
    private int count;

    public BillInfo() {
    }

    public BillInfo(int billId, int drinkId, int count) {
        this.billId = billId;
        this.drinkId = drinkId;
        this.count = count;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBillId() {
        return billId;
    }

    public void setBillId(int billId) {
        this.billId = billId;
    }

    public int getDrinkId() {
        return drinkId;
    }

    public void setDrinkId(int drinkId) {
        this.drinkId = drinkId;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
