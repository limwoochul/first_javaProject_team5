package db.shopping.controller;

public class PrintController {

	public static void printMainMenu() {
		System.out.print(
				  "-------메인 메뉴-------\n"
				+ "1. 회원가입\n"
				+ "2. 로그인\n"
				+ "3. 비밀번호 찾기\n"
				+ "4. 프로그램 종료\n"
				+ "메뉴 선택 : ");
	}

	public static void printBar() {
		System.out.println("--------------------");
	}

	public static void wrongMenu() {
		System.out.println("잘못된 메뉴를 선택했습니다.");
	}

	public static void loginFail() {
		System.out.println("아이디 또는 비밀번호가 잘못되었습니다.");		
	}

	public static void signupSuccess() {
		System.out.println("회원가입 성공!");	
	}

	public static void signupFail() {
		System.out.println("회원가입 실패!");
	}

	public static void printAdminMenu() {
        System.out.print(
                "------관리자 메뉴------\n" +
                "1. 상품 등록\n" +
                "2. 상품 삭제\n" +
                "3. 상품 업데이트\n" + 
                "4. 로그아웃\n" +
                "메뉴 선택 : ");
	}

	public static void logout() {
		System.out.println("로그아웃 합니다.");
	}

	public static void printShoppingMenu() {
		System.out.print(
				  "-------쇼핑하기-------\n"
				+ "1. 전체 조회 \n"
				+ "2. 상품명으로 조회\n"
				+ "3. 마이페이지\n"
				+ "4. 로그아웃\n"
				+ "메뉴 선택 : ");
	}

	public static void printMyPageMenu() {
		System.out.print(
				  "-------마이페이지-------\n"
				+ "1. 회원정보 수정 \n"
				+ "2. 장바구니\n"
				+ "3. 이전으로\n"
				+ "메뉴 선택 : ");
	}

	public static void prev() {
		System.out.println("이전으로 돌아갑니다.");
	}

	public static void printCartMenu() {
		System.out.print(
				  "-------장바구니-------\n"
				+ "1. 장바구니 조회 \n"
				+ "2. 장바구니 일부 비우기\n"
				+ "3. 장바구니 전체 비우기\n"
				+ "4. 이전으로\n"
				+ "메뉴 선택 : ");	
	}

	public static void wrongId() {
		System.out.println("존재하는 아이디가 없습니다.");
	}

}
