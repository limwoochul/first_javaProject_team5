package shop;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor

public class ItemInventory{

	private String itemName_1;
	private int itemInventory_1;


	@Override
	public String toString() {
		return " 상품명 : " + itemName_1 + " / 재고 : " + itemInventory_1 + "개";
	}

}
