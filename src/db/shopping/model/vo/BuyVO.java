package db.shopping.model.vo;

public class BuyVO {

	private int bu_num; 
	private int bu_amount; 
	private String bu_state; 
	private int bu_pr_num; 
	private String bu_me_id;
	
	public BuyVO(String me_id) {
		
		this.bu_me_id = me_id;
		
	}
	
}
