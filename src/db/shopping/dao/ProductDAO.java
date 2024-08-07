package db.shopping.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import db.shopping.model.vo.CartVO;
import db.shopping.model.vo.CategoryVO;
import db.shopping.model.vo.ProductVO;

public interface ProductDAO {


    boolean insertProduct(@Param("product") ProductVO product);

    boolean updateProduct(@Param("product") ProductVO product);

    boolean deleteProduct(@Param("pr_num") int pr_num);

    List<ProductVO> selectProductAll();

    void updateProductNumbers(@Param("pr_num") int pr_num);
    
	List<CategoryVO> selectCategoryList();

	List<ProductVO> selectProductList(@Param("cg_num")int categoryNum);

	boolean insertCart(@Param("me_id")String me_id, @Param("pr")ProductVO product);

	List<CartVO> selectCartList(@Param("me_id")String me_id);

	CartVO selectCart(@Param("me_id")String me_id, @Param("pr")ProductVO product);

	boolean updateCart(@Param("me_id")String me_id, @Param("pr")ProductVO product);

	void updateProductAmount(@Param("ca")CartVO cart);

	boolean deleteSomeProduct(@Param("pr_num")int num, @Param("me_id")String me_id);

	boolean deleteAllProduct(@Param("me_id")String me_id);

	List<ProductVO> selectProductName(@Param("pr_name")String productName);

	void insertBuy(@Param("me_id")String me_id, @Param("ca")CartVO cart);

	ProductVO checkProduct(@Param("pr_num")int productNum);

	CartVO getCartProduct(@Param("me_id")String me_id, @Param("pr_num")int productNum);



}
