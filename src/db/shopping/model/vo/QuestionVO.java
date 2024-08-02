package db.shopping.model.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class QuestionVO {

	private int qu_num; 
	private String qu_content;
	
	//비밀번호 찾기 질문 리스트 toString으로 출력
	@Override
	public String toString() {
		return "[" + qu_num + ". " + qu_content + "]";
	}

}
