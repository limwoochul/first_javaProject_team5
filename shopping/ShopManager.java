package shopping;

import java.util.InputMismatchException;
import java.util.Scanner;

import program.Program;

public class ShopManager implements Program {

	private Scanner scan;
	//회원 객체 불러오기
	private User user;
	//아이템 객체 불러오기
	private ItemManager itemManager;

	public ShopManager(User user, ItemManager itemManager) {
		this.scan = new Scanner(System.in);
		this.user = user;
		this.itemManager = itemManager;
	}

	@Override
	public void printMenu() {
		//println -> print로 변경
		System.out.print(
				"----쇼핑하기----\n"
				+ "1. 전체 조회 \n"
				+ "2. 상품명으로 조회\n"
				+ "3. 장바구니\n"
				+ "4. 이전으로\n"
				+ "메뉴 선택 : ");
	}

	private void printBar() {
		System.out.println("--------------");
	}

	@Override
	public void run() {
		int menu = 1;

		do {

			printMenu();

			menu = nextInt();
			try {
				runMenu(menu);
			} catch (Exception e) {
				e.printStackTrace();
			}

		} while(menu != 4); // (이전으로) -> 4번으로 변경
	}

	@Override
	public void runMenu(int menu) throws Exception {
		switch(menu) {
		case 1:
			producAll();
			break;
		case 2:
			producName();
			break;
		case 3:
			cart();
			break;
		case 4:
			prev();
			break;
		default:
			defaultPrint();
		}
	}

	private int nextInt() {
		try {
			return scan.nextInt();
		} catch (InputMismatchException e) {
			scan.nextLine();
			return Integer.MIN_VALUE;
		}
	}

	private void producAll() {
		printBar();
		System.out.println("전체 물건 목록"); //문구 추가

		//향상된 for문으로 아이템리스트 출력
		for(Item item : itemManager.getItemList()) {
			if(item == null) {
				System.out.println("등록된 상품이 없습니다");
				return;
			}
			System.out.println(item.getItemNumber() + ". " + item.getItemName() + " : " + item.getItemPrice() + "원");
		}
		System.out.print("장바구니에 추가할 물건 번호(취소는 -1 입력) : "); //장바구니 추가할지 문구 출력
		int itemNumber = scan.nextInt(); //번호받기
		if(itemNumber == -1) return; //-1이면 이전으로
		addItemToCart(itemNumber); //장바구니 추가
	}

	private void producName() {
		printBar();
		System.out.print("상품명 : ");
		scan.nextLine(); //버퍼 지우기
		String searchName = scan.nextLine(); //nextLine으로 변경
		System.out.println("검색 결과 목록"); //검색결과 문구 추가
		//향상된 for문으로 검색어가 포함된 목록 출력
		for (Item item : itemManager.getItemList()) {
			if (item.getItemName().contains(searchName)) {
				System.out.println(item.getItemNumber() + ". " + item.getItemName() + " : " + item.getItemPrice() + "원");
			}
		}
		System.out.print("장바구니에 추가할 물건 번호(취소는 -1 입력) : "); //장바구니 추가할지 문구 출력
		int itemNumber = scan.nextInt(); //번호받기
		if(itemNumber == -1) return; //-1이면 이전으로
		addItemToCart(itemNumber); //장바구니 추가
	}

	//장바구니에 아이템을 추가하는 메소드
	private void addItemToCart(int itemNumber) {
        Item item = itemManager.getItemNumber(itemNumber);
		if (item != null) {
			user.getCart().add(item.getItemName());
			System.out.println(item.getItemName() + "이 장바구니에 추가되었습니다.");
		} else {
			System.out.println("잘못된 아이템 번호입니다.");
		}
	}

	private void cart() {
		CartManager cm = new CartManager(user, itemManager);
		cm.run();
	}

	private void prev() {
		System.out.println("이전 메뉴로 돌아갑니다.");
	}

	private void defaultPrint() {
		System.err.println("올바른 메뉴를 선택하세요.");
	}

}
