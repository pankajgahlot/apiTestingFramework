package APITestingFrameWork.pojo;

public class platform_fees {

	private amount amount;
	private payee payee;

	public platform_fees(String currency_code, String value, String email_address) {
		this.amount = new amount(currency_code, value);
		this.payee = new payee(email_address);
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

}
