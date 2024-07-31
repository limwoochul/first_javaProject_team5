package db.shopping.service;

import java.util.List;

import db.shopping.model.vo.MemberVO;
import db.shopping.model.vo.QuestionVO;

public interface MemberService {

	MemberVO login(String id, String pw);

	boolean checkid(String id);

	List<QuestionVO> getQuestionList();

	boolean signup(String id, String pw, String name, String phone, String address, String answer, int question);

	MemberVO findId(String id);

}
