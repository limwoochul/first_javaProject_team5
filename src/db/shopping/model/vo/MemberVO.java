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
	@Override
	public String toString() {
		return "회원 정보 [ID :" + me_id + ", PW :" + me_pw + ", 회원명 : " + me_name + ", 전화번호 :" + me_phone
				+ ", 주소 : " + me_address + ", 권한 : " + me_authority + ", 비밀번호 찾기 답 : " + me_answer + "]";
	}
	
	
}
