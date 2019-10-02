package group3.xavalocoffee.entities;

import javax.persistence.*;

@Entity
@Table(name = "tbl_drink")
public class Drink {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "drink_id")
    private int id;

    @Column(name = "drink_name")
    private String name;

    @Column(name = "price")
    private float price;

    @Column(name = "disable")
    private boolean disable;

    @Column(name = "description")
    private String description;

    @Column(name = "category_id")
    private int categoryId;

    @Column(name = "image")
    private String image;

    public Drink() {
    }

    public Drink(String name, float price, boolean disable, String description, int categoryId, String image) {
        this.name = name;
        this.price = price;
        this.disable = disable;
        this.description = description;
        this.categoryId = categoryId;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public boolean isDisable() {
        return disable;
    }

    public void setDisable(boolean disable) {
        this.disable = disable;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
