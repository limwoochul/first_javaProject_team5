package db.shopping.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import db.shopping.model.vo.ProductVO;

@Mapper
public interface ProductDAO {

    boolean insertProduct(@Param("product") ProductVO product);

    void updateProduct(@Param("product") ProductVO product);

    void deleteProduct(@Param("pr_num") int pr_num);

	List<ProductVO> selectProduct();
}

