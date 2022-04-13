package getIP;

import static io.restassured.RestAssured.given;

import java.util.ArrayList;

import org.testng.Assert;
import org.testng.annotations.Test;

import APITestingFrameWork.pojo.orders;
import APITestingFrameWork.pojo.platform_fees;
import APITestingFrameWork.pojo.purchase_units;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class paypalCheck {

	String access_token = "";
	String orderID = "";

	String cleintID = "ASWk5ZyjSTQZkNN_aXBVaWmah8I4YXKE07RUqttmrCmXPnW0eFoSNDoiMz6mH963k3YUZ5p9DQqHgEg5";
	String secretKey = "EEZ4VFw4CxgGEOrnpMfco0DCwSpGXqg8qBFC3ukd4O6DCT_s467eVrGS-lVB9WKSUWayD8f8GdYxyDFD";

	@Test(priority = 1)
	public void getAccessToken() {
		System.out.println("==================getAccessToken===================================");

		RestAssured.baseURI = "https://api-m.sandbox.paypal.com";

		Response response = given().param("grant_type", "client_credentials").auth().preemptive()
				.basic(cleintID, secretKey).post("/v1/oauth2/token");

		System.out.println(response.statusCode());
		response.prettyPrint();
		access_token = response.jsonPath().get("access_token").toString();
		System.out.println("access token is " + access_token);

	}

	@Test(priority = 2, dependsOnMethods = "getAccessToken")
	public void createOrder() {
		System.out.println("==================createOrder===================================");
		RestAssured.baseURI = "https://api-m.sandbox.paypal.com";

		ArrayList<purchase_units> list = new ArrayList<purchase_units>();
		ArrayList<platform_fees> list1 = new ArrayList<platform_fees>();
		list1.add(new platform_fees("CAD", "1.00", "test@paypal.com"));
		list.add(new purchase_units("REFID-000-1001", "CAD", "10.10", "seller@paypal.com", "INSTANT", "999ZAE", list1));

		orders jsonBody = new orders("CAPTURE", list);

		/*
		 * String jsonBody="{\r\n" + "  \"intent\": \"CAPTURE\",\r\n" +
		 * "  \"purchase_units\": [\r\n" + "    {\r\n" +
		 * "      \"reference_id\": \"REFID-000-1001\",\r\n" + "      \"amount\": {\r\n"
		 * + "        \"currency_code\": \"CAD\",\r\n" +
		 * "        \"value\": \"10.00\"\r\n" + "      },\r\n" +
		 * "      \"payee\": {\r\n" +
		 * "        \"email_address\": \"seller@paypal.com\"\r\n" + "      },\r\n" +
		 * "      \"payment_instruction\": {\r\n" + "        \"platform_fees\": [\r\n" +
		 * "          {\r\n" + "            \"amount\": {\r\n" +
		 * "              \"currency_code\": \"CAD\",\r\n" +
		 * "              \"value\": \"1.00\"\r\n" + "            },\r\n" +
		 * "            \"payee\": {\r\n" +
		 * "              \"email_address\": \"test@paypal.com\"\r\n" +
		 * "            }\r\n" + "          }\r\n" + "        ],\r\n" +
		 * "        \"disbursement_mode\": \"INSTANT\",\r\n" +
		 * "        \"payee_pricing_tier_id\": \"999ZAE\"\r\n" + "      }\r\n" +
		 * "    }\r\n" + "  ]\r\n" + "}";
		 */

		Response response = given().contentType(ContentType.JSON)
				.header("PayPal-Request-Id", "7b92603e-77ed-4896-8e78-5dea2050476a").auth().oauth2(access_token)
				.body(jsonBody).post("/v2/checkout/orders");
		System.out.println(response.statusCode());
		response.prettyPrint();
		String status = response.jsonPath().get("status").toString();
		System.out.println("=====================================================");
		System.out.println(status);
		System.out.println("=====================================================");
		Assert.assertEquals(status, "CREATED");
		orderID = response.jsonPath().get("id").toString();

	}

	@Test(priority = 3, dependsOnMethods = { "getAccessToken", "createOrder" })
	public void getOrderDetails() {
		
		System.out.println("==================getOrderDetails===================================");
		//RestAssured.baseURI = "https://api-m.sandbox.paypal.com";
		Response response = given().contentType(ContentType.JSON)
				.auth().oauth2(access_token)
				.get("/v2/checkout/orders" + "/" + orderID);
		System.out.println(response.statusCode());
		response.prettyPrint();
		Assert.assertEquals(response.statusCode(),200);

	}

}
