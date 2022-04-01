package APITestingFrameWork.TestCase;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

import java.util.Hashtable;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import APITestingFrameWork.SetUp.baseTest;
import APITestingFrameWork.Utilities.DataUtil;
import io.restassured.response.Response;

public class createCustomerTest extends baseTest {

	@Test(dataProvider = "getData1", dataProviderClass = DataUtil.class)
	public void createCustomerWithValidAuth(Hashtable<String, String> data) {

		Response response = custAPI.POSTRequest_CustomerCreateWithValidKey(data);

		System.out.println(response.getStatusCode());
		response.prettyPrint();
		System.out.println("Id for the created customer is : "+response.jsonPath().get("id"));
		custAPI.checkResponseCode(response.getStatusCode(),200);

	}

	@Test(dataProvider = "getData1", dataProviderClass = DataUtil.class)
	public void createCustomerWithInValidAuth(Hashtable<String, String> data) {
		Response response = custAPI.POSTRequest_CustomerCreateWithInValidKey(data);

		System.out.println(response.getStatusCode());
		response.prettyPrint();
		custAPI.checkResponseCode(response.getStatusCode(),200);

	}

}
