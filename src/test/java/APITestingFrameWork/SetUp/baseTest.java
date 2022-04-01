package APITestingFrameWork.SetUp;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import APITestingFrameWork.APIs.createCustomerAPI;
import APITestingFrameWork.APIs.deleteCustomerAPI;
import APITestingFrameWork.Utilities.ExcelReader;
import io.restassured.RestAssured;

public class baseTest {

	public static createCustomerAPI custAPI = new createCustomerAPI();
	public static deleteCustomerAPI delCustAPI= new deleteCustomerAPI();

	public static Properties prop;
	public FileInputStream fis;
	public static ExcelReader excel = new ExcelReader(".\\src\\test\\resources\\excel\\TestData.xlsx");

	@BeforeSuite
	public void setUp() {

		prop = new Properties();

		try {
			fis = new FileInputStream(".\\src\\test\\resources\\properties\\config.properties");
			prop.load(fis);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		RestAssured.baseURI = prop.getProperty("baseURI");
		RestAssured.basePath = prop.getProperty("basePath");

	}

	@AfterSuite
	public void teardown() {

	}

}
