package db.shopping.service;

import java.util.List;

import db.shopping.model.vo.ProductVO;

public interface ProductService {
    void insertProduct(String pr_name, int pr_price, int pr_inventory, int pr_category);
    void updateProduct(ProductVO product);
    void deleteProduct(int pr_num);
    List<ProductVO> selectProductList();
}
