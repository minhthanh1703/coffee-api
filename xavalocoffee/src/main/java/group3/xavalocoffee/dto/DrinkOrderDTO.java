package group3.xavalocoffee.dto;

public class DrinkOrderDTO {
    private int drinkId;

    private int count;

    public DrinkOrderDTO() {
    }

    public DrinkOrderDTO(int drinkId, int count) {
        this.drinkId = drinkId;
        this.count = count;
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
