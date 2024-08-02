package db.shopping.model.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CartVO {
	
	private int ca_num;
	private int ca_amount; 
	private String ca_me_id; 
	private int ca_pr_num;
	private ProductVO product;
	
	
	@Override
	public String toString() {
		return "[" + "상품번호 : " + product + " / " + " 구매수량 : " + ca_amount + "]";
	}
	
	public int getTotalPrice() {
		return product.getPr_price() * ca_amount;
	}
	
	public boolean checkInventory() {
		if(product.getPr_inventory() < ca_amount) {
			return true;			
		}
		return false;
	}

}
