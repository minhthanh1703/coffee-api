package group3.xavalocoffee.dto;

import group3.xavalocoffee.entities.Bill;
import group3.xavalocoffee.entities.BillInfo;

import java.util.Date;
import java.util.List;

public class BillResponseDTO extends Bill {
    private int billId;
    private List<BillInfoDTO> listBillInfos;

    public BillResponseDTO(int billId, int tableNumber, boolean paided, float totalPrice, float discount, String usernameSatff, Date createAt, List<BillInfoDTO> listBillInfos) {
        super(tableNumber, paided, totalPrice, discount, usernameSatff, createAt);
        this.billId = billId;
        this.listBillInfos = listBillInfos;
    }

    public BillResponseDTO() {
    }

    public int getBillId() {
        return billId;
    }

    public void setBillId(int billId) {
        this.billId = billId;
    }

    public List<BillInfoDTO> getListBillInfos() {
        return listBillInfos;
    }

    public void setListBillInfos(List<BillInfoDTO> listBillInfos) {
        this.listBillInfos = listBillInfos;
    }
}
