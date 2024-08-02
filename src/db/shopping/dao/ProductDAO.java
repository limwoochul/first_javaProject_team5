package db.shopping.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import db.shopping.model.vo.ProductVO;

@Mapper
public interface ProductDAO {

    boolean insertProduct(@Param("product") ProductVO product);

    boolean updateProduct(@Param("product") ProductVO product);

    boolean deleteProduct(@Param("pr_num") int pr_num);

    List<ProductVO> selectProduct();

    void updateProductNumbers(@Param("pr_num") int pr_num);
}
