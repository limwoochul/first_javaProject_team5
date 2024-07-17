package shop;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@AllArgsConstructor
@Getter
@Setter

public class Shop {
	
	// 상품의 번호의 경우 이전 예제를 참고하면, 별도의 변수가 없어도 될 것 같음.
	
	private String cusId;
	private String itemName;
	private int itemPrice;
	
	@Override
	public String toString() {
		return "구매자 아이디: " + cusId + " / 상품명 : " + itemName + " / 가격 : " + itemPrice + "원";
	}

}

