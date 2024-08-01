package db.shopping.model.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MemberVO {

	private String me_id; 
	private String me_pw; 
	private String me_name;
	private String me_phone;
	private String me_address;
	private String me_authority;
	private String me_answer;
	private int me_qu_num;
}
