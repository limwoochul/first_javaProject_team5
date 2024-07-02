package shopping;

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
	private int findPwQuestionNum;
	private String findPwAnswer;
	
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
