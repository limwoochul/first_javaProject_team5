package db.shopping.controller;

import java.util.List;
import java.util.Scanner;

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

}
