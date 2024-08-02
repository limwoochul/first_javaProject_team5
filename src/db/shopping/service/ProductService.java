package db.shopping.service;

import java.util.List;

import db.shopping.model.vo.ProductVO;

public interface ProductService {
    void insertProduct(String pr_name, int pr_price, int pr_inventory, int pr_category);
    boolean updateProduct(ProductVO product);
    boolean deleteProduct(int pr_num);
    List<ProductVO> selectProductList();
}
