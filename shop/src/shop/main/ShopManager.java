package shop;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import program.program;

public class ShopManager implements program {

	private Scanner scan = new Scanner(System.in);
	
	private CartController cartController = new CartController(scan);

	public static void main(String[] args) {

	}

	@Override
	public void printMenu() {
		System.out.println("나의 장바구니");
		System.out.println("1. 장바구니 확인");
		System.out.println("2. 일부 비우기 ");
		System.out.println("3. 전체 비우기");
		System.out.println("4. 이전으로");
		System.out.print("메뉴 선택 : ");
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
			
		} while(menu != 4);

	}

	@Override
	public void runMenu(int menu) throws Exception {

		switch(menu) {

		case 1:
			cartController.checkCart();
			break;

		case 2:
			emptySome();
			break;

		case 3:
			empty();
			break;

		case 4:
			prev();
			break;
			
		default:

		}

	}
/*
	private void insert() {

		// 아이디, 상품 및 가격 입력
		System.out.print("구매자 : ");
		String cusId = scan.next();
		System.out.print("상품명 : ");
		String itemName = scan.next();
		System.out.print("가격 : ");
		int itemPrice = scan.nextInt();

		// 연락처를 이용한 객체 생성 ( 중복여부 확인과 연락처 추가를 위해 필요하다. )
		Shop shop = new Shop(cusId, itemName, itemPrice);

		// 리스트에 연락처 추가

		list.add(shop);

		// 안내문구 출력

		System.out.println("장바구니에 추가를 완료하였습니다.");
	}

	private void checkBag() {
		// 아이디 별로 등록된 상품의 리스트(장바구니) 및 구매의사 여부를 확인하는 메소드.
		// 아이디 입력 시, 번호·상품명·가격 출력 후, 마지막으로 가격의 총액 및 구매하기 버튼 출력.

		//검색
		// 1. 아이디 입력
		System.out.print("구매자 : ");
		String search = scan.next();
		
		// 2. 입력한 아이디에 저장된 리스트를 가격 (각각의 가격) 과 함께 출력
		int i = 0, count = 0;
		int sum = 0;
		for ( Shop shop : list ) {
			++i;;
			if(shop.getCusId().contains(search)) {
				count++;
				System.out.println(i + ". " + shop);
				sum += shop.getItemPrice();
			}
		}
		//검색 결과가 없으면

		if(count == 0) {
			System.out.println("검색 결과가 없습니다.");
			return;
		}

		// 3. 2번과 함께 총액을 출력 후, 구매의사를 물어보는 안내문구 출력
		
		System.out.print("총액 : " + sum + "원");
		
		System.out.println();
		
		printBar();

		// 4. 구매의사
		System.out.println(" 상품을 구매하시겠습니까? (y/n) : ");
		String str = scan.next();
		
		// 4-1 예. 입력 시 ...
		if(str.equals("y"))
		{ System.out.println("구매가 완료되었습니다.");
			return;
		
			// 구매 리스트의 상품과 재고 리스트의 상품을 비교 ( 반복문을 이용하는게 좋겠다고 하심. )
			
			// 구매 리스트의 상품이 재고 리스트의 상품에 있으면 재고 리스트의 상품의 재고를 1씩 감소
			
			// 구매가 완료되면 해당 아이디의 구매 리스트 삭제
		
			// ' 구매가 완료되었습니다 ' 출력 후, 메뉴로 돌아가기
		
			// 동일한 것이 없으면 ' 재고가 없어서 구매할 수 없습니다 ' 출력 후, 메뉴로 돌아가기.
		
			
		
		// 4-2 아니오. 입력 시, ' 메뉴로 돌아갑니다. ' 출력 후, 메뉴로 ...
		
		} else {
			System.out.println("메뉴로 돌아갑니다.");
			return;
		}

	}

	private void emptySome() {
		// 아이디를 입력하여 확인된 상품의 리스트(장바구니) 중에서 일부를 선택하여 삭제하는 메소드.
		// 아이디 입력 시, 출력되는 내용은 checkBag() 와 동일.
		// 삭제를 희망하는 상품의 번호를 입력하여 해당 상품의 삭제.
		// 마지막으로 변동된 총액 출력.

		// 검색

		// 1. 아이디 입력
		System.out.print("아이디 : ");
		String search = scan.next();

		// 2. 입력한 아이디에 저장된 리스트를 가격 (각각의 가격) 과 함께 출력
		int i = 0, count = 0;
		for ( Shop shop : list ) {
			++i;;
			if(shop.getCusId().contains(search)) {
				count++;
				System.out.println(i + ". " + shop);
			}
		}
		
		System.out.println();
		
		printBar();
		
		//검색 결과가 없으면
		if(count == 0) {
			System.out.println("검색 결과가 없습니다.");
			return;
		}

		// 입력한 아이디에 저장된 리스트를 번호와 함께 출력

		// 번호를 선택
		System.out.println("삭제할 물품의 번호 : ");
		int index = scan.nextInt() - 1;
		// 선택한 번호의 연락처를 삭제

		if(list.remove(index) != null) {
			System.out.println("상품을 삭제했습니다.");
			return;
		}
		System.out.println("상품을 삭제하지 못했습니다.");

	}

	private void empty() {
		// 아이디를 입력하여 상품의 리스트(장바구니) 확인 후, 전체를 삭제하는 메소드.
		// 아이디 입력 시, 출력되는 내용은 checkBag() 와 동일.
		// 안내문구 출력 후, 선택 시 전체 삭제처리.

		// 검색

		// 1. 아이디 입력
		System.out.print("아이디 : ");
		String search = scan.next();

		// 2. 입력한 아이디에 저장된 리스트를 가격 (각각의 가격) 과 함께 출력
		int i = 0, count = 0;
		int sum = 0;
		for ( Shop shop : list ) {
			++i;;
			if(shop.getCusId().contains(search)) {
				count++;
				System.out.println(i + ". " + shop);
				sum += shop.getItemPrice();
			}
		}
		
		System.out.print("총액 : " + sum + "원");
		
		System.out.println();
		
		printBar();
		
		//검색 결과가 없으면
		if(count == 0) {
			System.out.println("검색 결과가 없습니다.");
			return;
		}

		// 3. 안내문구 출력 ( 비우기 여부 )
		System.out.print("장바구니를 비우시겠습니까? (y/n) :");
		String str = scan.next();

		// 3-1 'y' 입력 시, ' 비우기가 완료되었습니다. ' 를 출력하며, 해당 아이디에 포함된 리스트를 전체 삭제.
		if(str.equals("y"))
		{ System.out.println("비우기가 완료되었습니다.");
		list.clear();
		// 3-2 'n' 입력 시, ' 메뉴로 돌아갑니다. ' 출력 후, 메뉴로 ...
		} else {
			System.out.println("메뉴로 돌아갑니다.");
			return;
		}

	}

	private void prev() {
		System.out.println("메뉴로 돌아갑니다.");
		return;
	}

	private void printBar() {
		System.out.println("------------------------------");
	}
*/
}
