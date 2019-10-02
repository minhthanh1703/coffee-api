package group3.xavalocoffee.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tbl_bill")
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bill_id")
    private int id;

    @Column(name = "table_number")
    private int tableNumber;

    @Column(name = "paided")
    private boolean paided;

    @Column(name = "total_price")
    private float totalPrice;

    @Column(name = "discount")
    private float discount;

    @Column(name = "username_staff")
    private String usernameSatff;

    @Column(name = "created_at")
    private Date createAt;


    public Bill(int tableNumber, boolean paided, float totalPrice, float discount, String usernameSatff, Date createAt) {
        this.tableNumber = tableNumber;
        this.paided = paided;
        this.totalPrice = totalPrice;
        this.discount = discount;
        this.usernameSatff = usernameSatff;
        this.createAt = createAt;
    }

    public Bill() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(int tableNumber) {
        this.tableNumber = tableNumber;
    }

    public boolean isPaided() {
        return paided;
    }

    public void setPaided(boolean paided) {
        this.paided = paided;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getUsernameSatff() {
        return usernameSatff;
    }

    public void setUsernameSatff(String usernameSatff) {
        this.usernameSatff = usernameSatff;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public float getDiscount() {
        return discount;
    }

    public void setDiscount(float discount) {
        this.discount = discount;
    }
}
