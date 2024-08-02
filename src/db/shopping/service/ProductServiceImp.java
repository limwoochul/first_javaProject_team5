package db.shopping.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import db.shopping.dao.ProductDAO;
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
    public void insertProduct(String name, int price, int inventory, int category) {
        if (productDao == null || checkString(name) || category > 5 || category < 1) {
            System.out.println("상품 등록 실패: 잘못된 입력입니다.");
            return;
        }
        
        ProductVO product = new ProductVO();
        product.setPr_name(name);
        product.setPr_price(price);
        product.setPr_inventory(inventory);
        product.setPr_cg_num(category);

        productDao.insertProduct(product);
        System.out.println("상품이 성공적으로 등록되었습니다.");
    }

    @Override
    public void updateProduct(ProductVO product) {
        productDao.updateProduct(product);
    }

    @Override
    public void deleteProduct(int pr_num) {
        productDao.deleteProduct(pr_num);
    }

    private boolean checkString(String str) {
        return str == null || str.trim().isEmpty();
    }

    @Override
    public List<ProductVO> selectProductList() {
        return productDao.selectProduct();
    }
}
