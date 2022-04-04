package APITestingFrameWork.APIs;

import static io.restassured.RestAssured.given;

import java.util.Hashtable;

import APITestingFrameWork.SetUp.baseTest;
import io.restassured.response.Response;

public class deleteCustomerAPI extends baseTest {

	public Response DELETERequest_CustomerCreateWithValidID(Hashtable<String, String> data) {
		
		//delete customer test
		Response response = given().auth().basic(prop.getProperty("validAuthKey"), "")
				.delete(prop.getProperty("createCustomerEndPoint")+"/"+data.get("id"));
		return response;
	}

}
