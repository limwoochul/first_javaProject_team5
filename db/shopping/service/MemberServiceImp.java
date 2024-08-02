package db.shopping.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import db.shopping.dao.MemberDAO;
import db.shopping.model.vo.MemberVO;
import db.shopping.model.vo.QuestionVO;

public class MemberServiceImp implements MemberService {
	
	private MemberDAO memberDao;
	
	public MemberServiceImp() {
		String resource = "db/shopping/config/mybatis-config.xml";
		InputStream inputStream;
		SqlSession session;
		
		try {
			inputStream = Resources.getResourceAsStream(resource);
			SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
			session = sessionFactory.openSession(true);
			memberDao = session.getMapper(MemberDAO.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public MemberVO login(String id, String pw) {
		MemberVO user = memberDao.selectMember(id);
		
		if(user == null) return null;
		
		if(user.getMe_pw().equals(pw)) {
			return user;
		}
		return null;
	}

	@Override
	public boolean checkid(String id) {
		MemberVO user = memberDao.selectMember(id);
		if(user != null) {
			System.out.println("이미 존재하는 아이디입니다.");
			return false;
		}
		
		String idRegex = "^\\w{6,13}$";
		if(!Pattern.matches(idRegex, id)) {
			System.out.println("아이디는 4~15자 사이의 영문과 숫자로만 이뤄져야 합니다.");
			return false;
		}
		return true;
	}

	@Override
	public List<QuestionVO> getQuestionList() {
		return memberDao.selectQuestionList();
	}

	@Override
	public boolean signup(String id, String pw, String name, String phone,
							String address, String answer,int question) {
		return memberDao.insertMember(id, pw, name, phone, address, answer, question);
	}

	@Override
	public MemberVO findId(String id) {
		MemberVO user = memberDao.selectMember(id);
		
		return user;
	}

}
