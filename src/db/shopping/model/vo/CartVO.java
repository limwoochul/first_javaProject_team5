package db.shopping.model.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor

public class CartVO {

	private int ca_num; // 장바구니 번호
	private int ca_amount; // 장바구니 내 수량
	private String ca_me_id; // 구매자
	private int ca_pr_num; // 장바구니 내 상품 번호
	private ProductVO product;
	
	@Override
	public String toString() {
		return "[" + "상품번호 : " + product + " / " + " 구매수량 : " + ca_amount + "]";
	}
	
}
