package db.shopping.controller;

import java.util.List;
import java.util.Scanner;

import db.shopping.model.vo.CartVO;
import db.shopping.model.vo.CategoryVO;
import db.shopping.model.vo.ProductVO;
import db.shopping.service.ProductService;
import db.shopping.service.ProductServiceImp;

public class ProductController {

	private ProductService productService = new ProductServiceImp();
	private Scanner scan;
	
	public ProductController(Scanner scan) {
		this.scan = scan;
	}

	public void searchProductAll(String me_id) {
		List<CategoryVO> categoryList = productService.getCategoryList();
		for(CategoryVO category : categoryList) {
			System.out.println(category);
		}
		
		PrintController.printBar();
		System.out.print("카테고리 선택 : ");
		int categoryNum = scan.nextInt();
		PrintController.printBar();
		
		List<ProductVO> productList = null;
		try {
			productList = productService.getProductList(categoryNum);
		} catch (Exception e) {
			System.out.println("없는 카테고리입니다.");
			return;
		}
		for(ProductVO product : productList) {
			System.out.println(product);
		}
		PrintController.printBar();
		
		System.out.println("1. 장바구니 담기");
		System.out.println("2. 뒤로가기");
		System.out.print("메뉴선택 : ");
		int choiceMenu = scan.nextInt();
		PrintController.printBar();
		
		if(choiceMenu != 1) return;
		
		System.out.print("장바구니에 담을 상품 번호 : ");
		int choiceProduct =scan.nextInt();
		System.out.print("상품 개수 : ");
		int inventory = scan.nextInt();
		PrintController.printBar();
		
		ProductVO product = new ProductVO(choiceProduct, inventory);
		
		if(productService.insertCart(me_id, product)) {
			System.out.println("장바구니 담기 성공!");
		} else {
			System.out.println("장바구니 담기 실패!");
		}
		
	}

	public void searchCart(String me_id) {
		
		// 로그인한 회원의 장바구니 조회 ( 구매한 상품의 번호와 상품명, 개당 가격, 구매수량 출력 )
		
		List<CartVO> cartList = productService.getCartList(me_id);
		
		if(cartList.size() == 0) {
			System.out.println("조회된 상품이 없습니다.");
			return;
		}
		
		int totalPrice = 0;
		
		for(CartVO cart : cartList) {
			System.out.println(cart);
			totalPrice += cart.getTotalPrice();
		}
		
		// 총액 출력
		PrintController.printBar();
		System.out.println("구매 총액 : " + totalPrice + "원");
		PrintController.printBar();
		
		// 구매 의사를 묻는 안내문구 출력
		System.out.print("상품을 구매하시겠습니까? < Y(y) / N(n) > : ");
		char choice = scan.next().charAt(0);
		
		// Y(y) 선택 시,
		// 리스트에 있는 각 물품의 갯수만큼 각 물품의 재고를 차감,
		// ' 구매를 완료하였습니다 ' 출력
		// 해당 장바구니 삭제 후, 메뉴로 복귀.
		if(choice == 'Y' || choice == 'y') {
			
		} 
		
		// N(n) 선택 시,
		// 메뉴로 복귀.
		else if (choice == 'N' || choice == 'n') {
			
		}
		
	}

	public void deleteCartSome(int num, String me_id) {
		
		// 로그인한 회원의 장바구니 조회 ( 구매한 상품의 번호와 상품명, 개당 가격, 구매수량 출력 )

		List<CartVO> cartList = productService.getCartList(me_id);

		if(cartList.size() == 0) {
			System.out.println("조회된 상품이 없습니다.");
			return;
		}

		for(CartVO cart : cartList) {
			System.out.println(cart);
		}
		
		PrintController.printBar();
		
		System.out.println("삭제할 상품의 번호를 입력하세요.");
		
		PrintController.printBar();

		// 삭제할 상품의 번호 입력
		System.out.print("상품번호 : ");
		num = scan.nextInt();

		PrintController.printBar();
		
		// 서비스에게 상품번호를 주고, 삭제요청. 
		// 성공하면 삭제 성공!
		if(productService.deleteSomeProduct(num, me_id)) {
			System.out.println("물품이 성공적으로 삭제되었습니다.");
			
			// 실패하면 삭제 실패!
		} else {
			System.out.println("삭제 실패! 번호를 정확히 입력해주세요.");
		}
		
	}

	public void deleteCartAll(String me_id) {
		// 로그인한 회원의 장바구니 조회 ( 구매한 상품의 번호와 상품명, 개당 가격, 구매수량 출력 )

		List<CartVO> cartList = productService.getCartList(me_id);

		if(cartList.size() == 0) {
			System.out.println("조회된 상품이 없습니다.");
			return;
		}

		for(CartVO cart : cartList) {
			System.out.println(cart);
		}

		PrintController.printBar();

		// 장바구니 비울지 여부에 대한 안내문구
		System.out.print("장바구니를 비우시겠습니까? < Y(y) / N(n) > : ");
		char choice = scan.next().charAt(0);

		PrintController.printBar();
		
		if(choice == 'Y' || choice == 'y') {

			if(productService.deleteAllProduct(me_id)) {
				System.out.println("장바구니 비우기를 완료하였습니다.");
			} 
			
		} 

		// N(n) 선택 시,
		// 메뉴로 복귀.
		else if (choice == 'N' || choice == 'n') {
			return;
		}

	}

}
