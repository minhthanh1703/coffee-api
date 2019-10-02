package group3.xavalocoffee.dto;

import group3.xavalocoffee.entities.BillInfo;

public class BillInfoDTO extends BillInfo {
    private int billInfoId;
    private float subPrice;
    private String image;


    public BillInfoDTO() {
    }

    public BillInfoDTO(int billInfoId, int billId, int drinkId, int count, float subPrice, String image) {
        super(billId, drinkId, count);
        this.subPrice = subPrice;
        this.image = image;
        this.billInfoId = billInfoId;
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
}
