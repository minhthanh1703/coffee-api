package group3.xavalocoffee.dto;

import java.util.List;

public class DrinkOrderRequestDTO {

    private int tableNumber;

    private List<DrinkOrderDTO> listDrink;

    public DrinkOrderRequestDTO(int tableNumber, List<DrinkOrderDTO> listDrink) {
        this.tableNumber = tableNumber;
        this.listDrink = listDrink;
    }

    public DrinkOrderRequestDTO() {
    }

    public int getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(int tableNumber) {
        this.tableNumber = tableNumber;
    }

    public List<DrinkOrderDTO> getListDrink() {
        return listDrink;
    }

    public void setListDrink(List<DrinkOrderDTO> listDrink) {
        this.listDrink = listDrink;
    }
}
