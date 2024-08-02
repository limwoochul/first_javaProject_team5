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
		
		// 등록된 물품이 없으면 false를 리턴
		List<CartVO> list = productDao.selectCartList(me_id);
		if(list.size() == 0) {
			System.out.println("삭제할 물품이 없습니다.");
			return false;
		}
		
		// dao 에 상품번호를 주면서 삭제 요청 후, 삭제 여부 반환.
		return productDao.deleteSomeProduct(num); 
	}

	@Override
	public boolean deleteAllProduct(String me_id) {
		
		// 등록된 물품이 없으면 false를 리턴
		List<CartVO> list = productDao.selectCartList(me_id);
		if(list.size() == 0) {
			System.out.println("장바구니에 물건이 없습니다.");
			return false;
		}
		
		return productDao.deleteAllProduct(me_id); 
	}
}
