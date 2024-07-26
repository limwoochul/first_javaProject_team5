package shopping;

import java.util.List;
import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Data;



@Data
@AllArgsConstructor
public class User {
	private String id;
	private String pw;
	private String name;
	private String address;
	private String phoneNumber;
	private int findPwQuestionNum; //비밀번호찾기 질문 리스트 두고 번호고르기
	private String findPwAnswer;
	private List<String> Cart; //개인 장바구니를 위해 회원객체에 포함
	
	private static final String adminId = "admin";
	private static final String adminPw = "admin1234";
	
	public boolean loginAdmin(String id, String pw) {
		return adminId.equals(id) && adminPw.equals(pw);
	}
	
	public User() {}
	
	public User(String id) {
		this.id = id;
	}
	
	public User(String id, String pw) {
		this.id = id;
		this.pw = pw;
	} 
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(id, other.id);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
	
	
}
