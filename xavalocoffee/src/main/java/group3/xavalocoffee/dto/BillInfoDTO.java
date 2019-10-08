package group3.xavalocoffee.dto;

import group3.xavalocoffee.entities.BillInfo;

public class BillInfoDTO extends BillInfo {
    private int billInfoId;
    private float subPrice;
    private String image;
    private String name;


    public BillInfoDTO() {
    }

    public BillInfoDTO(int billInfoId, int billId, int drinkId, int count, float subPrice, String image) {
        super(billId, drinkId, count);
        this.subPrice = subPrice;
        this.image = image;
        this.billInfoId = billInfoId;
    }

    public BillInfoDTO(int billInfoId, int billId, int drinkId, int count, float subPrice, String image, String name) {
        super(billId, drinkId, count);
        this.subPrice = subPrice;
        this.image = image;
        this.billInfoId = billInfoId;
        this.name = name;
    }

    public float getSubPrice() {
        return subPrice;
    }

    public void setSubPrice(float subPrice) {
        this.subPrice = subPrice;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getBillInfoId() {
        return billInfoId;
    }

    public void setBillInfoId(int billInfoId) {
        this.billInfoId = billInfoId;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
