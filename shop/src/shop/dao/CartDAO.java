package shop;

import org.apache.ibatis.annotations.Param;

import db.student.model.vo.ScoreVO;

public interface ScoreDAO {
	
	boolean insertItem(@Param("item")ItemVO item);
	
	boolean checkCart(@Param("cart")CartVO cart);
	
}
