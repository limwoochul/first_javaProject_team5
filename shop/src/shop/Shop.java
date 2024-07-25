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

	private String cusId; // 
	private String itemName; // 상품명
	private int itemPrice; // 상품 가격
	
	// 이 경우는 고객 아이디가 없으면 상품과 가격을 등록할 수 없다는 것으로 보일 수 있다. ( 구조가 이상하다. )
	
	// 고객의 아이디와 물품의 리스트가 같이 있도록 클래스를 짜고
	// 상품의 클래스 안에 상품명과 가격이 들어가 있도록 클래스부터 다시 짜야한다.
	
	@Override
	public String toString() {
		return "구매자 아이디: " + cusId + " / 상품명 : " + itemName + " / 가격 : " + itemPrice + "원";
	}

}