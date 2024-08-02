package db.shopping.main;

import java.util.Scanner;

import db.shopping.controller.MemberController;
import db.shopping.controller.PrintController;
import db.shopping.controller.ProductController;
import db.shopping.model.vo.MemberVO;
import program.Program;

public class ProgramImp implements Program {

	private Scanner scan = new Scanner(System.in);
	private MemberVO member = null;
	private MemberController memberController = new MemberController(scan);
	private ProductController productController = new ProductController(scan);
	
	@Override
	public void printMenu() {
		PrintController.printMainMenu();		
	}

	//메인메뉴실행
	@Override
	public void run() {
		int menu = '0';
		
		do {
			printMenu();
			
			try {
				menu = scan.next().charAt(0);
				
				PrintController.printBar();
				
				runMenu(menu);
				
				PrintController.printBar();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} while(menu != '4');
	}

	@Override
	public void runMenu(int menu) throws Exception {
		char ch = (char)menu;
		switch(ch) {
		case '1':
			signup(); //회원가입
			break;
		case '2':
			login(); //로그인
			break;
		case '3':
			findpw(); //비밀번호 찾기
			break;
		case '4':
			System.out.println("프로그램을 종료합니다.");
			break;
		default:
			PrintController.wrongMenu();
		}
	}

	private void signup() {
		if(memberController.signup()) {
			PrintController.signupSuccess();
		}else {
			PrintController.signupFail();
		}
	}

	private void login() {
		member = memberController.login();
		
		if(member == null) {
			PrintController.loginFail();
			return;
		}
		
		if(member.getMe_authority().equals("ADMIN")) {
			admin();
			return;
		}
		
		user();
		
	}

	//관리자메뉴 실행
	private void admin() {
		PrintController.printBar();
		System.out.println("관리자님 환영합니다.");
		PrintController.printBar();
		char menu = '0';
		do {
			PrintController.printAdminMenu();
			
			menu = scan.next().charAt(0);
			PrintController.printBar();
			
			runAdmin(menu);
		} while(menu != '4');
	}

	private void runAdmin(char menu) {
		switch(menu) {
		case '1':
			//productController.insertProduct(); //상품등록
			break;
		case '2':
			//productController.deleteProduct(); //상품삭제
			break;
		case '3':
			//productController.updateProduct(); //상품수정
			break;
		case '4':
			PrintController.logout();
			break;
		default:
			PrintController.wrongMenu();
		}
	}

	//회원(쇼핑)메뉴 실행
	private void user() {
		PrintController.printBar();
		System.out.println(member.getMe_id() + "님 환영합니다.");
		PrintController.printBar();
		char menu = '0';
		do {
			PrintController.printShoppingMenu();
			
			menu = scan.next().charAt(0);
			PrintController.printBar();
			
			runShopping(menu);
		} while(menu != '4');
	}

	private void runShopping(char menu) {
		switch(menu) {
		case '1':
			productController.searchProductAll(member.getMe_id()); //상품전체검색
			break;
		case '2':
			//productController.searchProductName(member.getMe_id()); //이름으로 상품 검색
			break;
		case '3':
			myPage(member.getMe_id()); //마이페이지
			break;
		case '4':
			PrintController.logout();
			break;
		default:
			PrintController.wrongMenu();
		}		
	}

	//마이페이지 실행
	private void myPage(String me_id) {
		PrintController.printBar();
		char menu = '0';
		do {
			PrintController.printMyPageMenu();
			
			menu = scan.next().charAt(0);
			PrintController.printBar();
			
			runMyPage(menu);
		} while(menu != '3');
	}

	private void runMyPage(char menu) {
		switch(menu) {
		case '1':
			//memberController.updateMember(member.getMe_id()); //회원정보 수정
			break;
		case '2':
			cart(); //장바구니 실행
			break;
		case '3':
			PrintController.prev();
			break;
		default:
			PrintController.wrongMenu();
		}
	}

	//장바구니 실행
	private void cart() {
		PrintController.printBar();
		char menu = '0';
		do {
			PrintController.printCartMenu();
			
			menu = scan.next().charAt(0);
			PrintController.printBar();
			
			runCart(menu);
		} while(menu != '4');
	}

	private void runCart(char menu) {
		switch(menu) {
		case '1':
			//productController.searchCart(member.getMe_id()); //장바구니 전체탐색
			break;
		case '2':
			//productController.deleteCartSome(member.getMe_id()); //장바구니 일부 비우기
			break;
		case '3':
			//productController.deleteCartAll(member.getMe_id()); //장바구니 전체 비우기
			cart();
			break;
		case '4':
			PrintController.prev();
			break;
		default:
			PrintController.wrongMenu();
		}
	}

	//비밀번호 찾기 실행
	private void findpw() {
		member = memberController.findId();
		
		if(member == null) {
			PrintController.wrongId();
			return;
		}
		
		memberController.findPw(member);
	}

}
