package shop;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class ItemServiceImp implements ItemService {

	private ItemDAO itemDao;
	
	public SubjectServiceImp() {
		String resource = "shop/config/mybatis-config.xml";
		InputStream inputStream;
		SqlSession session;
		try {
			inputStream = Resources.getResourceAsStream(resource);
			SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
			session = sessionFactory.openSession(true);
			itemDao = session.getMapper(ItemDAO.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
