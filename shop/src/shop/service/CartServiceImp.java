package shop;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class CartServiceImp implements CartService {

	private CartDAO cartDao;
	
	public SubjectServiceImp() {
		String resource = "shop/config/mybatis-config.xml";
		InputStream inputStream;
		SqlSession session;
		try {
			inputStream = Resources.getResourceAsStream(resource);
			SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
			session = sessionFactory.openSession(true);
			cartDao = session.getMapper(CartDAO.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public boolean insertCart ( ??? ) {
		
		// 등록되지 않은 상품이면 false를 반환
		// 입력된 상품의 정보를 DB에서 가져온다.
		
		ItemVO dbItem = itemDao.selectItem(item);
		
		if(dbItem == null) {
			return false;
		}
		
		// 선택한 상품이 이미 장바구니에 등록이 되어있다면 false를 반환
		
		CartVO dbCart = caerDao.selectCart(cart);
		if(dbCart == null) {
			return false;
		}
		
		ItemVO dbItem = itemDao.selectItem(dbItem.get ??? ()); // ???
		if(dbItem != null) {
			return false;
		}
		
		item.set ??? _key(dbItem.get ??? key());
		return itemDao.insertItem(item);
		
	}
	
}
