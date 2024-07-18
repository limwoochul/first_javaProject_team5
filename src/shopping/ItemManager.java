package shopping;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import program.Program;

public class ItemManager implements Program {
	
	private Scanner scan = new Scanner(System.in);
	//물품조회 리스트
	private List<Item> list = new ArrayList<Item>();
	
	@Override
	public void printMenu() {
		System.out.println(
				  "-----상품조회-----\n"
				+ "1. 전체 조회 \n"
				+ "2. 상품명으로 조회\n"
				+ "3. 장바구니\n"
				+ "4. 이전으로\n"
				+ "메뉴 선택 : ");
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
	


	private void cart() {
		//장바구니 불러올 메소드
		
	}

	private void defaultPrint() {
		System.err.println("올바른 메뉴를 선택하세요.");
	}
	
	private void prev() {
		System.out.println("메뉴로 돌아갑니다.");
		LoginManager lm = new LoginManager();
		lm.run();
		return;
		
	}
	private void producName() {
		System.out.print("상품명 : ");
		String itemName = scan.next();
		System.out.print("상품번호 : ");
		String itemNum = scan.next();
		
		for(Item tmp : list) {
			
		}
		
	}
	private void producAll() {
		for(Item tmp : list) {
			System.out.println(tmp);
		}
		System.out.println("장바구니와 연동중입니다.");
	}


	@Override
	public void run() {
		int menu = 1;

		do {

			printMenu();

			menu = scan.nextInt();
			
			try {
				runMenu(menu);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		} while(menu != 5);

	}
	

}


