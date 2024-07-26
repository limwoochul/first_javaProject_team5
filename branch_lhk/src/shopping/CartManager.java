package shopping;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import program.Program;

public class CartManager implements Program {

	private Scanner scan;
	private User user;
	private ItemManager itemManager;

	public CartManager(User user, ItemManager itemManager) {
		this.user = user;
		this.itemManager = itemManager;
		this.scan = new Scanner(System.in);
	}

	@Override
	public void printMenu() {
		System.out.println("----장바구니----");
		System.out.println("1. 장바구니 확인");
		System.out.println("2. 일부 비우기");
		System.out.println("3. 전체 비우기");
		System.out.println("4. 이전으로");
		System.out.print("메뉴 선택 : ");
	}

	@Override
	public void run() {

		int menu;

		do {

			printMenu();

			menu = nextInt();
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
			checkBag();
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
			wrongMenu();
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

	private void checkBag() {
		// 아이디 별로 등록된 상품의 리스트(장바구니) 및 구매의사 여부를 확인하는 메소드.
		// 아이디 입력 시, 번호·상품명·가격 출력 후, 마지막으로 가격의 총액 및 구매하기 버튼 출력.
		printBar();
		List<String> cart = user.getCart();
		int total = 0;
		// 2. 입력한 아이디에 저장된 리스트를 가격 (각각의 가격) 과 함께 출력
		/*
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
		 */
		//간단하게 변경
		if(cart.isEmpty()) {
			System.out.println("장바구니가 비어있습니다.");
		}else {
			System.out.println("장바구니 목록:");
			for (String itemName : cart) {
				Item item = getItemByName(itemName);
				if (item != null) {
					int price = Integer.parseInt(item.getItemPrice().replace(",", ""));
					total += price;
					System.out.println(item.getItemNumber() + ". " + item.getItemName() + " : " + item.getItemPrice() + "원");
				}
			}
			System.out.println("---------------");
			System.out.println("총액 : " + total + "원");
			System.out.println("1. 구매하기");
			System.out.println("2. 뒤로가기");
			System.out.print("입력 : ");
			int choice = nextInt();
			handlePurchase(choice, total);
		}

		// 3. 2번과 함께 총액을 출력 후, 구매의사를 물어보는 안내문구 출력
		/*
		System.out.print("총액 : " + sum + "원");

		System.out.println();

		printBar();

		// 4. 구매의사
		System.out.println(" 상품을 구매하시겠습니까? (y/n) : ");
		String str = scan.next();
		// 4-1 예. 입력 시, ' 구매가 완료되었습니다. ' 출력
		if(str.equals("y"))
		{ System.out.println("구매가 완료되었습니다.");
		return;
		// 4-2 아니오. 입력 시, ' 메뉴로 돌아갑니다. ' 출력 후, 메뉴로 ...
		} else {
			System.out.println("메뉴로 돌아갑니다.");
			return;
		}
		 */

	}

    private Item getItemByName(String itemName) {
        for (Item item : itemManager.getItemList()) {
            if (item.getItemName().equals(itemName)) {
                return item;
            }
        }
        return null;
    }
    
    private void handlePurchase(int choice, int total) {
        if (choice == 1) {
            for (String itemName : user.getCart()) {
                Item item = getItemByName(itemName);
                if (item != null && item.getItemInventory() > 0) {
                    item.reduceInventory(1);
                }
            }
            System.out.println("총 " + total + "원이 결제되었습니다. 감사합니다!");
            user.getCart().clear();
        } else if (choice == 2) {
            System.out.println("메뉴로 돌아갑니다.");
        } else {
            System.out.println("잘못된 입력입니다. 메뉴로 돌아갑니다.");
        }
    }
    
	private void emptySome() {
		// 아이디를 입력하여 확인된 상품의 리스트(장바구니) 중에서 일부를 선택하여 삭제하는 메소드.
		// 삭제를 희망하는 상품의 이름을 입력하여 검색 후 해당 상품의 삭제.
		// 마지막으로 변동된 총액 출력.

		/*
		// 2. 입력한 아이디에 저장된 리스트를 가격 (각각의 가격) 과 함께 출력
		int i = 0, count = 0;
//		int sum = 0;
		for ( Shop shop : list ) {
			++i;;
			if(shop.getCusId().contains(search)) {
				count++;
				System.out.println(i + ". " + shop);
//				sum += shop.getItemPrice();
			}
		}

//		System.out.print("총액 : " + sum + "원");

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
		 */
		printBar();
		System.out.print("삭제할 상품 이름 : ");
		scan.nextLine(); //버퍼삭제
		String item = scan.nextLine();

		if(user.getCart().remove(item)) {
			System.out.println("상품이 장바구니에서 삭제되었습니다.");
		}else {
			System.out.println("장바구니에 해당하는 상품이 없습니다.");
		}
	}

	private void empty() {
		// 아이디를 입력하여 상품의 리스트(장바구니) 확인 후, 전체를 삭제하는 메소드.
		// 안내문구 출력 후, 선택 시 전체 삭제처리.

		// 안내문구 출력 ( 비우기 여부 )
		printBar();
		System.out.print("장바구니를 비우시겠습니까? (y/n) :");
		String str = scan.next();

		// 3-1 'y' 입력 시, ' 비우기가 완료되었습니다. ' 를 출력하며, 해당 아이디에 포함된 리스트를 전체 삭제.
		if(str.equals("y") || str.equals("Y")){
			user.getCart().clear();
			System.out.println("장바구니가 비워졌습니다.");
		}
		// 3-2 'n' 입력 시, ' 메뉴로 돌아갑니다. ' 출력 후, 메뉴로 ...
		else if(str.equals("n") || str.equals("N")){
			System.out.println("메뉴로 돌아갑니다.");
			return;
		}
		else {
			System.out.println("잘못된 입력입니다.");
		}
	}

	private void prev() {
		System.out.println("이전 메뉴로 돌아갑니다.");
		return;
	}

	private void printBar() {
		System.out.println("------------------");
	}

	private void wrongMenu() {
		System.out.println("잘못된 메뉴입니다.");
	}

}