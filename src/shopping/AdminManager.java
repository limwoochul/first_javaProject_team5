package shopping;

public class AdminManager {
	private static final String adminId = "admin";
	private static final String adminPw = "admin1234";
	
	public boolean loginAdmin(String id, String pw) {
		return adminId.equals(id) && adminPw.equals(pw);
	}
}
