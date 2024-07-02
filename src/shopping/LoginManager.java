package shopping;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class LoginManager {
	private static Scanner scan = new Scanner(System.in);
	private List<User> userList = new ArrayList<User>();
	private List<String> findPwQuestion = Arrays.asList("1. 가장 존경하는 사람은?",
														"2. 가장 좋아하는 노래는?",
														"3. 가장 좋아하는 음식은?");
	public void printMenu() {
		System.out.print(
				"메인 메뉴\n"
						+ "1. 회원가입\n"
						+ "2. 로그인\n"
						+ "3. 비밀번호 찾기\n"
						+ "4. 관리자 로그인\n"
						+ "5. 프로그램 종료\n"
						+ "메뉴 선택 : ");
	}

	public void run() {
		int menu;
		do {
			printMenu();
			menu = scan.nextInt();
			try {
				runMenu(menu);
			} catch (Exception e) {
				System.out.println("예외 발생");
			}
		}while(menu != 5);
	}

	public void runMenu(int menu) throws Exception {
		switch(menu) {
		case 1:
			register();
			break;
		case 2:
			userlogin();
			break;
		case 3:
			findPassword();
			break;
		case 4:
			adminLogin();
			break;
		case 5:
			exit();
			break;
		default:
			wrongMenu();
		}
	}
	
	public void printBar() {
		System.out.println("--------------");
	}

	private void register() {
		System.out.print("아이디 : ");
		String id = scan.next();
		if(!checkId(id)) {
			printBar();
			return;
		}

		System.out.print("비밀번호 : ");
		String pw = scan.next();
		if(!checkPw(pw)) {
			printBar();
			return;
		}

		System.out.print("이름 : ");
		scan.nextLine();
		String name = scan.nextLine();
		System.out.print("주소 : ");
		String address = scan.nextLine();
		System.out.print("핸드폰번호 : ");
		String phoneNumber = scan.nextLine();
		System.out.println("---비밀번호 분실 시 찾기 질문---");
		System.out.println(findPwQuestion);
		System.out.print("질문 번호 선택 : ");
		int question = scan.nextInt();
		if(question<1 || question>3) {
			System.out.println("번호를 잘못 입력했습니다.");
			return;
		}
		System.out.print("답변 : ");
		scan.nextLine();
		String answer = scan.nextLine();

		User user = new User(id, pw, name, address, phoneNumber, question, answer);
		userList.add(user);
		System.out.println("-----회원가입이 완료되었습니다.-----");
	}

	private boolean checkId(String id) {
		if(id==null || id.length()<4 || id.length()>15) {
			System.out.println("아이디는 4자에서 15자 사이여야 합니다.");
			return false;
		}

		if(id.contains(" ")) {
			System.out.println("공백을 포함할 수 없습니다.");
			return false;
		}

		for(User user : userList) {
			if(user.getId().equals(id)) {
				System.out.println("이미 존재하는 아이디입니다.");
				return false;
			}
		}
		System.out.println("사용 가능한 아이디입니다.");
		return true;
	}

	private boolean checkPw(String pw) {
		if (pw==null || pw.length() < 6 || pw.length() > 15) {
			System.out.println("비밀번호는 6자에서 15자 사이여야 합니다.");
			return false;
		}

		if(pw.contains(" ")) {
			System.out.println("공백을 포함할 수 없습니다.");
			return false;
		}

		for (char c : pw.toCharArray()) {
			if (!Character.isLetterOrDigit(c)) {
				System.out.println("비밀번호는 문자와 숫자로만 이루어져야 합니다.");
				return false;
			}
		}
		System.out.println("사용 가능한 비밀번호입니다.");
		return true;
	}

	private void userlogin() {
		User user = checkUser();
		if(user==null) {
			System.out.println("아이디 또는 비밀번호가 잘못되었습니다.");
			printBar();
			return;
		}

		System.out.println("로그인 성공!");
		printBar();
	}

	private User checkUser() {
		System.out.print("아이디 : ");
		String id = scan.next();
		System.out.print("비밀번호 : ");
		String pw = scan.next();
		
		for(User user : userList) {
			if(user.getId().equals(id) && user.getPw().equals(pw)) {
				return user;
			}
		}
		return null;
	}
	
	private void findPassword() {
		System.out.print("아이디 : ");
		String id = scan.next();
		User user = new User(id);
		
		int index = userList.indexOf(user);
		if(index<0) {
			System.out.println("일치하는 아이디가 없습니다.");
			return;
		}
		
		user = userList.get(index);
		
		System.out.println("---비밀번호 찾기 질문 목록---");
		System.out.println(findPwQuestion);
		System.out.print("질문 번호 선택 : ");
		int question = scan.nextInt();
		if(question<1 || question>3) {
			System.out.println("번호를 잘못 입력했습니다.");
			return;
		}
		System.out.print("답변 : ");
		scan.nextLine();
		String answer = scan.nextLine();
		
		if(user.getFindPwQuestionNum() == question 
				&& user.getFindPwAnswer().equals(answer)) {
			System.out.println("비밀번호는 [" + user.getPw() + "] 입니다.");
			return;
		}
		
		System.out.println("질문 또는 답변이 일치하지 않습니다.");
		
	}

	private void adminLogin() {
		System.out.print("관리자 아이디 : ");
		String id = scan.next();
		System.out.print("관리자 비밀번호 : ");
		String pw = scan.next();
		
		AdminManager am = new AdminManager();
		if(!am.loginAdmin(id, pw)) {
			System.out.println("관리자 아이디 또는 비밀번호가 잘못되었습니다.");
			printBar();
			return;
		}
		
		System.out.println("관리자 로그인 성공!");
		printBar();
	}

	private void exit() {
		System.out.println("프로그램을 종료합니다.");
	}

	private void wrongMenu() {
		System.out.println("올바른 메뉴를 선택하세요.");
	}
}
