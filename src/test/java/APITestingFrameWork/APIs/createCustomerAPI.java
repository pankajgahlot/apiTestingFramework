package APITestingFrameWork.APIs;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

import java.util.Hashtable;

import APITestingFrameWork.SetUp.baseTest;
import APITestingFrameWork.Utilities.ExtentListeners;
import io.restassured.http.Method;
import io.restassured.response.Response;

public class createCustomerAPI extends baseTest {

	public Response POSTRequest_CustomerCreateWithValidKey(Hashtable<String, String> data) {
		Response response = given().auth().basic(prop.getProperty("validAuthKey"), "")
				.formParams("name", data.get("name")).formParam("email", data.get("email"))
				.formParam("description", data.get("description")).post(prop.getProperty("createCustomerEndPoint"));
		return response;
	}

	public Response POSTRequest_CustomerCreateWithInValidKey(Hashtable<String, String> data) {
		Response response = given().auth().basic(prop.getProperty("invalidAuthKey"), "")
				.formParams("name", data.get("name")).formParam("email", data.get("email"))
				.formParam("description", data.get("description")).post(prop.getProperty("createCustomerEndPoint"));
		return response;
	}

	public void checkResponseCode(int actualResponse, int Expected ) {

		try {
			assertEquals(actualResponse, Expected);
			ExtentListeners.testReport.get().info("Response codes Equal, working fine");
			System.out.println("============================= response OK ==========================");
		} catch (Exception e) {
			ExtentListeners.testReport.get().fail("Response codes not Equal, not working fine");
		}

	}
	
	public void checkResponseString (String actualResponse, String  Expected ) {

		try {
			assertEquals(actualResponse, Expected,"value not present in the json");
			ExtentListeners.testReport.get().info("Response Strings Equal, working fine");
			System.out.println("============================= response OK ==========================");
		} catch (Exception e) {
			ExtentListeners.testReport.get().fail("Response Strings not Equal, not working fine");
		}

	}
	


}
