package db.shopping.service;

import java.util.List;

import db.shopping.model.vo.CategoryVO;
import db.shopping.model.vo.ProductVO;

public interface ProductService {

	List<CategoryVO> getCategoryList();

	List<ProductVO> getProductList(int categoryNum);

	boolean insertCart(String me_id, ProductVO product);

}
