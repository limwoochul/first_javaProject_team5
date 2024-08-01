package db.shopping.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import db.shopping.model.vo.MemberVO;
import db.shopping.model.vo.QuestionVO;

public interface MemberDAO {

	MemberVO selectMember(@Param("id")String id);

	List<QuestionVO> selectQuestionList();

	boolean insertMember(
			@Param("id")String id,
			@Param("pw")String pw,
			@Param("name")String name,
			@Param("phone")String phone,
			@Param("address")String address,
			@Param("answer")String answer,
			@Param("question")int question);

}
