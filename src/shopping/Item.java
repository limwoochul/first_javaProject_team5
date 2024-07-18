package shopping;

import java.util.Objects;

import lombok.Data;



//상품 관리를 위한 Item class
//상품을 번호, 이름, 가격, 재고로 관리
@Data
class Item {
    private int itemNumber, itemInventory;
    private String itemName, itemPrice;

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Item other = (Item) obj;
        return Objects.equals(itemName, other.itemName) && itemNumber == other.itemNumber
                && Objects.equals(itemPrice, other.itemPrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(itemName, itemNumber, itemPrice);
    }

    public Item(int itemNumber, String itemName, String itemPrice) {
        this.itemNumber = itemNumber;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.itemInventory = 0;
    }
}