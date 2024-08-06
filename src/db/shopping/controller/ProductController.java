package db.shopping.controller;

import java.util.InputMismatchException;
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
	
	// 숫자 입력 처리 메서드
	private int getIntInput(String prompt) {
		int number = -1;
		while (true) {
			try {
				System.out.print(prompt);
				number = scan.nextInt();
				break; // 올바른 입력이 들어오면 루프 종료
			} catch (InputMismatchException e) {
				System.out.println("숫자를 입력해 주세요.");
				scan.next(); // 잘못된 입력을 소비하여 무한 루프 방지
				PrintController.printBar();
			}
		}
		return number;
	}
	
	// 공백 없는 문자열 입력 처리 메소드
	private String getNonEmptyInput(String prompt) {
		String input = "";
		while(true) {
			System.out.print(prompt);
			input = scan.nextLine();
			if(input.trim().isEmpty() || input.contains(" ")) {
				System.out.println("공백은 입력할 수 없습니다.");
				PrintController.printBar();
			} else {
				break;
			}
		}
		return input;
	}
	
	// 관리자 상품 등록
    public void insertProduct() {

    	scan.nextLine();
        System.out.print("상품명: ");
        String name = scan.nextLine();

        int price = getIntInput("가격: ");

        int inventory = getIntInput("재고 수량: ");

        System.out.println("카테고리 번호");
        
		List<CategoryVO> categoryList = productService.getCategoryList();
		for(CategoryVO category : categoryList) {
			System.out.println(category);
		}
		
        int categoryNum = 0;
        		
        while(true) {
        	categoryNum = getIntInput("카테고리 번호 입력: ");
        	if(categoryNum <= 0 || categoryNum > categoryList.size()) {
        		System.out.println("없는 카테고리 번호입니다.");
        		PrintController.printBar();
        	} else {
        		break;
        	}
        }
        

        productService.insertProduct(name, price, inventory, categoryNum);
    }

    // 관리자 상품 수정
    public void updateProduct() {
        List<ProductVO> list = productService.selectProductList();

        if (list.isEmpty()) {
            System.out.println("수정할 상품이 없습니다.");
			PrintController.printBar();
            return;
        }

        for (ProductVO product : list) {
            System.out.println(product + ", 재고 : " + product.getPr_inventory());
        }
        
        int num = getIntInput("수정할 상품 번호 : ");
        
        // 입력된 번호가 리스트에 존재하는지 확인
        boolean exists = false;
        for (ProductVO product : list) {
            if (product.getPr_num() == num) {
                exists = true;
                break;
            }
        }
        // 입력된 번호가 존재하지 않으면 예외 처리
        if (!exists) {
        	System.out.println("잘못된 번호입니다. 해당 번호의 상품이 존재하지 않습니다.");
			PrintController.printBar();
            return;
        }

        scan.nextLine();
        System.out.print("새로운 상품명: ");
        String name = scan.nextLine();

        int price = getIntInput("새로운 가격 : ");

        int inventory = getIntInput("새로운 재고 수량 : ");

        System.out.println("카테고리 번호");
		List<CategoryVO> categoryList = productService.getCategoryList();
		for(CategoryVO category : categoryList) {
			System.out.println(category);
		}
		
        int categoryNum = 0;
		
        while(true) {
        	categoryNum = getIntInput("새로운 카테고리 번호 입력: ");
        	if(categoryNum <= 0 || categoryNum > categoryList.size()) {
        		System.out.println("없는 카테고리 번호입니다.");
        		PrintController.printBar();
        	} else {
        		break;
        	}
        }


        ProductVO product = new ProductVO();
        product.setPr_num(num);
        product.setPr_name(name);
        product.setPr_price(price);
        product.setPr_inventory(inventory);
        product.setPr_cg_num(categoryNum);

        productService.updateProduct(product);
        System.out.println("상품이 성공적으로 수정되었습니다.");
    }

    // 관리자 상품 삭제
    public void deleteProduct() {
        List<ProductVO> list = productService.selectProductList();
        if (list.isEmpty()) {
            System.out.println("삭제할 상품이 없습니다.");
			PrintController.printBar();
            return;
        }

        for (ProductVO product : list) {
            System.out.println(product + ", 재고 : " + product.getPr_inventory());
        }

        int num = getIntInput("삭제할 상품 번호 : ");

        if (productService.deleteProduct(num)) {
            System.out.println("상품이 성공적으로 삭제되었습니다.");
			PrintController.printBar();
        } else {
            System.out.println("상품 삭제 실패: 잘못된 번호입니다.");
			PrintController.printBar();
        }
    }
    
    // 사용자 상품 전체조회
	public void searchProductAll(String me_id) {
		List<CategoryVO> categoryList = productService.getCategoryList();
		for(CategoryVO category : categoryList) {
			System.out.println(category);
		}
		
		int categoryNum = 0;
		while (true) {
			PrintController.printBar();
			categoryNum  = getIntInput("카테고리 선택 : ");
			if(categoryNum <= 0 || categoryNum > categoryList.size()) {
				System.out.println("없는 카테고리 번호입니다.");
			} else {
				break;
			}
		}
		
		
		List<ProductVO> productList = productService.getProductList(categoryNum);

		if(productList.size() == 0) {
			System.out.println("조회된 상품이 없습니다.");
			PrintController.printBar();
			return;
		}
		for(ProductVO product : productList) {
			System.out.println(product + ", 남은 재고 : " + product.getPr_inventory());
		}
		PrintController.printBar();
		
		System.out.println("1. 장바구니 담기");
		System.out.println("2. 뒤로가기");
		int choiceMenu = 0;
		while(true) {
			PrintController.printBar();
			choiceMenu = getIntInput("메뉴선택 : ");
			if(choiceMenu <= 0 || choiceMenu > 2) {
				PrintController.wrongMenu();
			} else if(choiceMenu == 2) {
				PrintController.prev();
				PrintController.printBar();
				return;
			} else {
				break;
			}
		}
		
		int choiceProduct = 0;
		ProductVO checkProduct = new ProductVO();
		
		while(true) {
			PrintController.printBar();
			choiceProduct = getIntInput("장바구니에 담을 상품 번호 : ");
			
			checkProduct = productService.checkProductNum(choiceProduct);
			
			if(checkProduct == null) {
				System.out.println("존재하지 않는 상품 번호입니다.");
			} else {
				break;
			}
		}
		
		int inventory = 0;
		int cartInventory = 0;
		CartVO cart = productService.getCartProduct(me_id, choiceProduct);
		if(cart != null) {
			cartInventory = cart.getCa_amount();
		}
		
		if(cart.getCa_amount() == checkProduct.getPr_inventory()) {
			System.out.println("이 상품은 더 이상 장바구니에 담을 수 없습니다.");
			PrintController.printBar();
			return;
		}
		
		while(true) {
			PrintController.printBar();
			inventory = getIntInput("상품 개수 : ");
			if(inventory + cartInventory > checkProduct.getPr_inventory()) {
				System.out.println("남아있는 재고가 없습니다.");
			} else {
				break;
			}
			
		}
		
		ProductVO product = new ProductVO(choiceProduct, inventory);

		
		if(productService.updateCart(me_id, product)) {
			System.out.println("장바구니 담기 성공!");
			PrintController.printBar();
			return;
		}
		
		
		if(productService.insertCart(me_id, product)) {
			System.out.println("장바구니 담기 성공!");
			PrintController.printBar();
		} else {
			System.out.println("장바구니 담기 실패!");
			PrintController.printBar();
		}
		
	}
	
	// 사용자 상품 이름검색조회
	public void searchProductName(String me_id) {
		scan.nextLine();
		String productName = getNonEmptyInput("상품명 : ");
		List<ProductVO> productList = productService.getProductName(productName);
		if(productList.size() == 0) {
			System.out.println("조회된 상품이 없습니다.");
			PrintController.printBar();
			return;
		}
		
		for(ProductVO product : productList) {
			System.out.println(product + ", 남은 재고 : " + product.getPr_inventory());
		}
		
		PrintController.printBar();
		
		System.out.println("1. 장바구니 담기");
		System.out.println("2. 뒤로가기");
		int choiceMenu = 0;
		while(true) {
			PrintController.printBar();
			choiceMenu = getIntInput("메뉴선택 : ");
			if(choiceMenu <= 0 || choiceMenu > 2) {
				PrintController.wrongMenu();
			} else if(choiceMenu == 2) {
				PrintController.prev();
				PrintController.printBar();
				return;
			} else {
				break;
			}
		}
		
		int choiceProduct = 0;
		ProductVO checkProduct = new ProductVO();
		
		while(true) {
			PrintController.printBar();
			choiceProduct = getIntInput("장바구니에 담을 상품 번호 : ");
			
			checkProduct = productService.checkProductNum(choiceProduct);
			
			if(checkProduct == null) {
				System.out.println("존재하지 않는 상품 번호입니다.");
			} else {
				break;
			}
		}
		
		int inventory = 0;
		int cartInventory = 0;
		CartVO cart = productService.getCartProduct(me_id, choiceProduct);
		if(cart != null) {
			cartInventory = cart.getCa_amount();
		}
		
		if(cart.getCa_amount() == checkProduct.getPr_inventory()) {
			System.out.println("이 상품은 더 이상 장바구니에 담을 수 없습니다.");
			PrintController.printBar();
			return;
		}
		
		while(true) {
			PrintController.printBar();
			inventory = getIntInput("상품 개수 : ");
			if(inventory + cartInventory > checkProduct.getPr_inventory()) {
				System.out.println("남아있는 재고가 없습니다.");
			} else {
				break;
			}
			
		}
		
		ProductVO product = new ProductVO(choiceProduct, inventory);

		
		if(productService.updateCart(me_id, product)) {
			System.out.println("장바구니 담기 성공!");
			PrintController.printBar();
			return;
		}
		
		
		if(productService.insertCart(me_id, product)) {
			System.out.println("장바구니 담기 성공!");
			PrintController.printBar();
		} else {
			System.out.println("장바구니 담기 실패!");
			PrintController.printBar();
		}
		
	}

	// 사용자 장바구니 전체조회
	public void searchCart(String me_id) {
		List<CartVO> cartList = productService.getCartList(me_id);
		
		if(cartList.size() == 0) {
			System.out.println("조회된 상품이 없습니다.");
			PrintController.printBar();
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
		int choiceMenu = getIntInput("메뉴 선택 : ");
		PrintController.printBar();
		
		if(choiceMenu < 0 || choiceMenu > 2) {
			PrintController.wrongMenu();
			PrintController.printBar();
			return;
		} else if(choiceMenu == 2) {
			PrintController.prev();
			PrintController.printBar();
			return;
		}
		
		for(CartVO cart : cartList) {
			if(cart.checkInventory()) {
				System.out.println("남아있는 재고가 없습니다.");
				PrintController.printBar();
				return;
			}
		}
		
		for(CartVO cart : cartList) {
			productService.updateProductAmount(cart);
		}
		
		System.out.println("구매가 완료되었습니다.");
		PrintController.printBar();
		
		productService.deleteAllProduct(me_id);
		
		for(CartVO cart : cartList) {
			productService.insertBuy(me_id, cart);
		}
		
	}
	
	// 사용자 장바구니 일부삭제
	public void deleteCartSome(String me_id) {
		
		// 로그인한 회원의 장바구니 조회 ( 구매한 상품의 번호와 상품명, 개당 가격, 구매수량 출력 )

		List<CartVO> cartList = productService.getCartList(me_id);

		if(cartList.size() == 0) {
			System.out.println("조회된 상품이 없습니다.");
			PrintController.printBar();
			return;
		}

		for(CartVO cart : cartList) {
			System.out.println(cart);
		}
		
		PrintController.printBar();
		
		System.out.println("삭제할 상품의 번호를 입력하세요.");
		
		PrintController.printBar();

		// 삭제할 상품의 번호 입력
		int num = getIntInput("상품번호 : ");

		PrintController.printBar();
		
		// 서비스에게 상품번호를 주고, 삭제요청. 
		// 성공하면 삭제 성공!
		if(productService.deleteSomeProduct(num, me_id)) {
			System.out.println("물품이 성공적으로 삭제되었습니다.");
			PrintController.printBar();
			
			// 실패하면 삭제 실패!
		} else {
			System.out.println("삭제 실패! 번호를 정확히 입력해주세요.");
			PrintController.printBar();
		}
		
	}

	// 사용자 장바구니 전체삭제
	public void deleteCartAll(String me_id) {
		// 로그인한 회원의 장바구니 조회 ( 구매한 상품의 번호와 상품명, 개당 가격, 구매수량 출력 )

		List<CartVO> cartList = productService.getCartList(me_id);

		if(cartList.size() == 0) {
			System.out.println("조회된 상품이 없습니다.");
			PrintController.printBar();
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
				PrintController.printBar();
			} 
		} 
		// N(n) 선택 시,
		// 메뉴로 복귀.
		else if (choice == 'N' || choice == 'n') {
			PrintController.prev();
			PrintController.printBar();
		}
		else {
			PrintController.wrongMenu();
			PrintController.printBar();
		}

	}
	
}
