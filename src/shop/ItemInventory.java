package shop;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@AllArgsConstructor
@Getter
@Setter

public class ItemInventory {

	private String itemName_1;
	private int itemInven_1;
	
	@Override
	public String toString() {
		return "상품명 : " + itemName_1 + " / 재고 : " + itemInven_1 + "개";
	}
	
}
