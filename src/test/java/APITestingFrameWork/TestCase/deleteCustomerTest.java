package APITestingFrameWork.TestCase;

import static org.testng.Assert.assertTrue;

import java.util.Hashtable;

import org.json.JSONObject;
import org.testng.annotations.Test;

import APITestingFrameWork.SetUp.baseTest;
import APITestingFrameWork.Utilities.DataUtil;
import APITestingFrameWork.Utilities.TestUtils;
import io.restassured.response.Response;

public class deleteCustomerTest extends baseTest{

	
	
	@Test(dataProvider = "getData1", dataProviderClass = DataUtil.class)
	public void createCustomerWithValidID(Hashtable<String, String> data) {

		Response response = delCustAPI.DELETERequest_CustomerCreateWithValidID(data);

		System.out.println(response.getStatusCode());
		response.prettyPrint();
		
		custAPI.checkResponseCode(response.getStatusCode(), 200);
		String json=response.asString();
		

		assertTrue(	TestUtils.getKeyFromJson(json, "id"), "key is not present in the Json response");
		custAPI.checkResponseString(TestUtils.getValueFromJson(json, "id"), data.get("id"));
		
		JSONObject jsonObject = new JSONObject(json);
		System.out.println(jsonObject.has("id"));
		System.out.println(jsonObject.get("id"));
		
		

	}
	
}
