package db.shopping.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import db.shopping.model.vo.CategoryVO;
import db.shopping.model.vo.ProductVO;

public interface ProductDAO {

	List<CategoryVO> selectCategoryList();

	List<ProductVO> selectProductList(@Param("cg_num")int categoryNum);

	boolean insertCart(@Param("me_id")String me_id, @Param("pr")ProductVO product);

}
