package db.shopping.model.vo;

public class QuestionVO {

	private int qu_num; 
	private String qu_content;
	
	
	@Override
	public String toString() {
		return "[" + qu_num + ". " + qu_content + "]";
	}

}
