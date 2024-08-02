package db.shopping.service;

import java.util.List;

import db.shopping.model.vo.CartVO;
import db.shopping.model.vo.CategoryVO;
import db.shopping.model.vo.ProductVO;

public interface ProductService {

	List<CategoryVO> getCategoryList();

	List<ProductVO> getProductList(int categoryNum);

	boolean insertCart(String me_id, ProductVO product);

	List<CartVO> getCartList(String me_id);

	boolean deleteSomeProduct(int num, String me_id);

	boolean deleteAllProduct(String me_id);

	void updateProductAmount(CartVO cart);

	void insertBuy(String me_id, CartVO cart);

}
