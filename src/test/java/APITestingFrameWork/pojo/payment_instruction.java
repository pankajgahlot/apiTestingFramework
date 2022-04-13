package APITestingFrameWork.pojo;

import java.util.ArrayList;

public class payment_instruction {
	
	private String disbursement_mode;
	private String payee_pricing_tier_id;	
	private ArrayList<platform_fees> platform_fees;
	
	
	public payment_instruction(String disbursement_mode,String payee_pricing_tier_id, ArrayList<platform_fees> platform_fees) {
		this.disbursement_mode = disbursement_mode;
		this.payee_pricing_tier_id = payee_pricing_tier_id;
		this.platform_fees = platform_fees;

	}
	public String getDisbursement_mode() {
		return disbursement_mode;
	}
	public void setDisbursement_mode(String disbursement_mode) {
		this.disbursement_mode = disbursement_mode;
	}
	public String getPayee_pricing_tier_id() {
		return payee_pricing_tier_id;
	}
	public void setPayee_pricing_tier_id(String payee_pricing_tier_id) {
		this.payee_pricing_tier_id = payee_pricing_tier_id;
	}
	public ArrayList<platform_fees> getPlatform_fees() {
		return platform_fees;
	}
	public void setPlatform_fees(ArrayList<platform_fees> platform_fees) {
		this.platform_fees = platform_fees;
	}
	
	
	
	

}
