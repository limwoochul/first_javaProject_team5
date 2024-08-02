package db.shopping.controller;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

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
        for (ProductVO product : list) {
            System.out.println(product);
        }

        System.out.print("수정할 상품 번호: ");
        int num = scan.nextInt();

        System.out.print("새로운 상품명: ");
        String name = scan.next();

        System.out.print("새로운 가격: ");
        int price = scan.nextInt();

        System.out.print("새로운 재고 수량: ");
        int inventory = scan.nextInt();

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
        for (ProductVO product : list) {
            System.out.println(product);
        }

        System.out.print("삭제할 상품 번호: ");
        int num = scan.nextInt();
        productService.deleteProduct(num);
        System.out.println("상품이 성공적으로 삭제되었습니다.");
    }
}
