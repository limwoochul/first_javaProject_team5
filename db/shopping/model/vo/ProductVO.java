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
	private int cg_num;
	private String cg_name; // 카테고리 이름 추가

	@Override
	public String toString() {
		return "상품리스트[상품 번호 : " + pr_num + ", 상품명 : " + pr_name 
				+ " 	카테고리 번호 : " + cg_num + ", 카테고리 : " + cg_name + "]";
	}	
}
