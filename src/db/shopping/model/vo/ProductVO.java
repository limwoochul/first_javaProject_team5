package db.shopping.model.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductVO {

	private int pr_num; 
	private String pr_name; 
	private int pr_price;
	private int pr_inventory; 
	private int pr_cg_num;
	
	@Override
	public String toString() {
		return pr_num + ". " + pr_name + " : 가격 = " + pr_price + "원";
	}

	public ProductVO(int choiceProduct, int inventory) {
		this.pr_num = choiceProduct;
		this.pr_inventory = inventory;
	}

}
