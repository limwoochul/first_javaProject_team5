package db.shopping.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

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
	public boolean updateCart(String me_id, ProductVO product) {
		CartVO oldVo = productDao.selectCart(me_id, product);
		if(oldVo == null) return false;
		
		return productDao.updateCart(me_id, product);
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
	public void updateProductAmount(CartVO cart) {
		productDao.updateProductAmount(cart);
	}

	@Override
	public boolean deleteSomeProduct(int num, String me_id) {
		return productDao.deleteSomeProduct(num, me_id); 
	}

	@Override
	public boolean deleteAllProduct(String me_id) {
		return productDao.deleteAllProduct(me_id);
	}

	@Override
	public List<ProductVO> getProductName(String productName) {
		if(productName == null || productName.trim().length() == 0) {
			return null;
		}
		return productDao.selectProductName(productName);
	}

	@Override
	public void insertBuy(String me_id, CartVO cart) {
		productDao.insertBuy(me_id, cart);
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
    public boolean updateProduct(ProductVO product) {
    	
        return productDao.updateProduct(product);
    }

    @Override
    public boolean deleteProduct(int pr_num) {
        if (productDao.deleteProduct(pr_num)) {
            productDao.updateProductNumbers(pr_num);
            return true;
        }
        return false;
    }

    private boolean checkString(String str) {
        return str == null || str.trim().isEmpty();
    }

    @Override
    public List<ProductVO> selectProductList() {
        return productDao.selectProductAll();
    }

	@Override
	public ProductVO checkProductNum(int productNum) {
		return productDao.checkProduct(productNum);
	}

	@Override
	public CartVO getCartProduct(String me_id, int productNum) {
		return productDao.getCartProduct(me_id, productNum);
	}

}
