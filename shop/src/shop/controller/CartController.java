package shop;

import java.util.List;
import java.util.Scanner;

public class CartController {

	private UserService userService = new UserServiceImp();
	private ItemService itemService = new ItemServiceImp();
	private Scanner scan;
	
	public ItemController(Scanner scan){
		this.scan = scan;
	}
	
	public void insertCart();
	
	// 등록되어있는 상품을 가져온다.
	List<ItemVO> itemList = itemService.selectItemList();
	
	if(itemList.size() == 0) {
		System.out.println("등록된 상품이 아닙니다, 관리자에게 문의해주세요.");
		return;
	}
	
	// 구매를 희망하는 상품을 선택하여 입력
	ItemVO item = inputItem();
	
	// 사용자가 구매하려는 물품의 재고를 확인하여 등록의 성공여부를 확인
	if(cartService.insertCart( ??? )) {
		System.out.println("상품이 장바구니에 등록되었습니다.");
	}
	else {
		System.out.println("재고가 부족하여 상품이 장바구니에 등록되지 못하였습니다.");
	}
}
