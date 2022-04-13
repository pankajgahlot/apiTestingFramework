package APITestingFrameWork.pojo;

import java.util.ArrayList;

public class purchase_units {

	private String reference_id;
	private amount amount;
	private payee payee;
	private payment_instruction payment_instruction;
	
	public purchase_units(String reference_id,String currency_code,String value,String email_address,String disbursement_mode,String payee_pricing_tier_id, ArrayList<platform_fees> platform_fees) {
		
		this.reference_id= reference_id;
		
		this.amount= new amount(currency_code, value);
		this.payee= new payee(email_address);
		this.payment_instruction= new payment_instruction(disbursement_mode, payee_pricing_tier_id, platform_fees);
	}

	public String getReference_id() {
		return reference_id;
	}

	public void setReference_id(String reference_id) {
		this.reference_id = reference_id;
	}

	public amount getAmount() {
		return amount;
	}

	public void setAmount(amount amount) {
		this.amount = amount;
	}

	public payee getPayee() {
		return payee;
	}

	public void setPayee(payee payee) {
		this.payee = payee;
	}

	public payment_instruction getPayment_instruction() {
		return payment_instruction;
	}

	public void setPayment_instruction(payment_instruction payment_instruction) {
		this.payment_instruction = payment_instruction;
	}

}
