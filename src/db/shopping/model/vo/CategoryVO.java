package db.shopping.model.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CategoryVO {

	private int cg_num; 
	private String cg_name;
	
	@Override
	public String toString() {
		return cg_num + ". " + cg_name;
	}

}
