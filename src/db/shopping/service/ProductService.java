package db.shopping.service;

import java.util.List;

import db.shopping.model.vo.CartVO;
import db.shopping.model.vo.CategoryVO;
import db.shopping.model.vo.ProductVO;

public interface ProductService {

    void insertProduct(String pr_name, int pr_price, int pr_inventory, int pr_category);
    
    boolean updateProduct(ProductVO product);
    
    boolean deleteProduct(int pr_num);
    
    List<ProductVO> selectProductList();
    
	List<CategoryVO> getCategoryList();

	List<ProductVO> getProductList(int categoryNum);

	boolean insertCart(String me_id, ProductVO product);

	List<CartVO> getCartList(String me_id);

	boolean updateCart(String me_id, ProductVO product);

	void updateProductAmount(CartVO cart);

	boolean deleteSomeProduct(int num, String me_id);

	boolean deleteAllProduct(String me_id);

	List<ProductVO> getProductName(String productName);

	void insertBuy(String me_id, CartVO cart);

	ProductVO checkProductNum(int choiceProduct);

	CartVO getCartProduct(String me_id, int choiceProduct);


}
