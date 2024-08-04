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
	
	// 상품 등록
    public void insertProduct() {
        int price = 0, inventory = 0, category = 0;

        System.out.print("상품명: ");
        String name = scan.next();
        
        try {
            System.out.print("가격: ");
            price = scan.nextInt();

            System.out.print("재고 수량: ");
            inventory = scan.nextInt();

            System.out.println("카테고리 번호");
            System.out.println("1 : 과일");
            System.out.println("2 : 채소");
            System.out.println("3 : 고기");
            System.out.println("4 : 생선");
            System.out.print("카테고리 번호 입력: ");
            category = scan.nextInt();

        } catch (InputMismatchException e) {
            System.out.println("숫자를 입력하세요");
            scan.next(); // 버퍼 비우기
            return;
        }

        productService.insertProduct(name, price, inventory, category);
    }

    // 상품 수정
    public void updateProduct() {
        List<ProductVO> list = productService.selectProductList();

        if (list.isEmpty()) {
            System.out.println("수정할 상품이 없습니다.");
            return;
        }

        for (ProductVO product : list) {
            System.out.println(product + ", 재고 : " + product.getPr_inventory());
        }
        int num; 
        System.out.print("수정할 상품 번호: ");
        try{
        	num = scan.nextInt();
        }catch(InputMismatchException e) {
        	System.out.println("잘못된 번호를 입력하셨습니다.");
        	return;
        }

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
            return;
        }

        System.out.print("새로운 상품명: ");
        String name = scan.next();

        System.out.print("새로운 가격: ");
        int price = scan.nextInt();

        System.out.print("새로운 재고 수량: ");
        int inventory = scan.nextInt();

        System.out.println("카테고리 번호");
        System.out.println("1 : 과일");
        System.out.println("2 : 채소");
        System.out.println("3 : 고기");
        System.out.println("4 : 생선");

        System.out.print("새로운 카테고리 번호: ");
        int category = scan.nextInt();

        ProductVO product = new ProductVO();
        product.setPr_num(num);
        product.setPr_name(name);
        product.setPr_price(price);
        product.setPr_inventory(inventory);
        product.setPr_cg_num(category);

        productService.updateProduct(product);
        System.out.println("상품이 성공적으로 수정되었습니다.");
    }

    // 상품 삭제
    public void deleteProduct() {
        List<ProductVO> list = productService.selectProductList();
        if (list.isEmpty()) {
            System.out.println("삭제할 상품이 없습니다.");
            return;
        }

        for (ProductVO product : list) {
            System.out.println(product + ", 재고 : " + product.getPr_inventory());
        }

        System.out.print("삭제할 상품 번호: ");
        int num;
        try{
        	num = scan.nextInt();
        }catch(InputMismatchException e) {
        	System.out.println("잘못된 번호를 입력하셨습니다.");
        	return;
        }
        if (productService.deleteProduct(num)) {
            System.out.println("상품이 성공적으로 삭제되었습니다.");
        } else {
            System.out.println("상품 삭제 실패: 잘못된 번호입니다.");
        }
    }
    
	public void searchProductAll(String me_id) {
		List<CategoryVO> categoryList = productService.getCategoryList();
		for(CategoryVO category : categoryList) {
			System.out.println(category);
		}
		
		PrintController.printBar();
		System.out.print("카테고리 선택 : ");
		int categoryNum = nextInt();
		PrintController.printBar();
		
		List<ProductVO> productList = null;
		try {
			productList = productService.getProductList(categoryNum);
		} catch (Exception e) {
			System.out.println("없는 카테고리입니다.");
			return;
		}
		if(productList.size() == 0) {
			System.out.println("조회된 상품이 없습니다.");
			return;
		}
		for(ProductVO product : productList) {
			System.out.println(product);
		}
		PrintController.printBar();
		
		System.out.println("1. 장바구니 담기");
		System.out.println("2. 뒤로가기");
		System.out.print("메뉴선택 : ");
		int choiceMenu = nextInt();
		PrintController.printBar();
		
		if(choiceMenu < 0 || choiceMenu > 2) {
			PrintController.wrongMenu();
		}
		
		
		if(choiceMenu != 1) return;
		
		System.out.print("장바구니에 담을 상품 번호 : ");
		int choiceProduct = nextInt();
		System.out.print("상품 개수 : ");
		int inventory = nextInt();
		if(inventory < 0) {
			System.out.println("잘못입력했습니다.");
			return;
		}
		
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
	
	public void searchProductName(String me_id) {
		System.out.print("상품명 : ");
		String productName = scan.next();
		List<ProductVO> productList = productService.getProductName(productName);
		if(productList.size() == 0) {
			System.err.println("조회된 상품이 없습니다.");
			return;
		}
		
		for(ProductVO product : productList) {
			System.out.println(product);
		}
		
		PrintController.printBar();
		
		System.out.println("1. 장바구니 담기");
		System.out.println("2. 뒤로가기");
		System.out.print("메뉴선택 : ");
		int choiceMenu = nextInt();
		PrintController.printBar();
		
		if(choiceMenu < 0 || choiceMenu > 2) {
			PrintController.wrongMenu();
		}
		
		
		if(choiceMenu != 1) return;
		
		System.out.print("장바구니에 담을 상품 번호 : ");
		int choiceProduct = nextInt();

		System.out.print("상품 개수 : ");
		int inventory = nextInt();
		if(inventory < 0) {
			System.out.println("잘못입력했습니다.");
			return;
		}
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
			System.out.println("조회된 상품이 없습니다.");
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
			productService.updateProductAmount(cart);
		}
		
		System.out.println("구매가 완료되었습니다.");
		
		productService.deleteAllProduct(me_id);
		
		for(CartVO cart : cartList) {
			productService.insertBuy(me_id, cart);
		}
		
	}
	
	public void deleteCartSome(String me_id) {
		
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
		int num = scan.nextInt();

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
	
	private int nextInt() {
		try {
			return scan.nextInt();
		} catch (InputMismatchException e) {
			System.err.println("숫자만 입력가능합니다.");
			scan.nextLine();
			return Integer.MIN_VALUE;
		}
	}

}
