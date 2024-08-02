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
		
		if(productService.updateCart(me_id, product)) {
			System.out.println("장바구니 담기 성공!");
			return;
		}
		
		
		if(productService.insertCart(me_id, product)) {
			System.out.println("장바구니 담기 성공!");
		} else {
			System.out.println("장바구니 담기 실패!");
		}
		
	}

	public void searchCart(String me_id) {
		List<CartVO> cartList = productService.getCartList(me_id);
		
		if(cartList.size() == 0) {
			System.out.println("등록된 장바구니가 없습니다.");
			return;
		}
		
		int totalPrice = 0;
		
		for(CartVO cart : cartList) {
			System.out.println(cart);
			totalPrice += cart.getTotalPrice();
		}
		PrintController.printBar();
		System.out.println("총액 : " + totalPrice);
		PrintController.printBar();
		
		System.out.println("구매하시겠습니까?");
		System.out.println("1. 예");
		System.out.println("2. 아니오");
		System.out.print("메뉴 선택 : ");
		int choiceMenu = scan.nextInt();
		PrintController.printBar();
		if(choiceMenu != 1) {
			PrintController.prev();
			return;
		}
		
		for(CartVO cart : cartList) {
			if(cart.checkInventory()) {
				System.out.println("남아있는 재고가 없습니다.");
				return;
			}
		}
		
		for(CartVO cart : cartList) {
			productService.updateProduct(cart);
		}
		
	}

}
