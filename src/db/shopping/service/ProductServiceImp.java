package db.shopping.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import db.shopping.dao.MemberDAO;
import db.shopping.dao.ProductDAO;
import db.shopping.model.vo.CartVO;
import db.shopping.model.vo.CategoryVO;
import db.shopping.model.vo.ProductVO;

public class ProductServiceImp implements ProductService {

	private ProductDAO productDao;
	
	public ProductServiceImp() {
		String resource = "db/shopping/config/mybatis-config.xml";
		InputStream inputStream;
		SqlSession session;
		
		try {
			inputStream = Resources.getResourceAsStream(resource);
			SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
			session = sessionFactory.openSession(true);
			productDao = session.getMapper(ProductDAO.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<CategoryVO> getCategoryList() {
		return productDao.selectCategoryList();
	}

	@Override
	public List<ProductVO> getProductList(int categoryNum) {
		return productDao.selectProductList(categoryNum);
	}

	@Override
	public boolean insertCart(String me_id, ProductVO product) {
		if(product == null) return false;
		
		try {
			return productDao.insertCart(me_id, product);
		} catch(Exception e) {			
			return false;
		}
	}

	@Override
	public List<CartVO> getCartList(String me_id) {
		return productDao.selectCartList(me_id);
	}

	@Override
	public boolean deleteSomeProduct(int num, String me_id) {
		
		// dao 에 상품번호를 주면서 삭제 요청 후, 삭제 여부 반환.
		return productDao.deleteSomeProduct(num, me_id); 
	}

	@Override
	public boolean deleteAllProduct(String me_id) {
		
		return productDao.deleteAllProduct(me_id); 
	}

	@Override
	public void updateProductAmount(CartVO cart) {
		
		productDao.updateProductAmount(cart);
		
	}

	@Override
	public void insertBuy(String me_id, CartVO cart) {
		productDao.insertBuy(me_id, cart);
	}


}
