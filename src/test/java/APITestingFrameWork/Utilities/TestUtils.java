package APITestingFrameWork.Utilities;

import org.json.JSONObject;

public class TestUtils {
	
	
	public static boolean getKeyFromJson(String json, String key) {
		boolean result= false;
		JSONObject jsonObject= new JSONObject(json);
		ExtentListeners.testReport.get().info("validating :"+ key);
		result =jsonObject.has(key);
		return result;
		
	}
	
	public static String getValueFromJson(String json, String key) {
		String  result= null;
		JSONObject jsonObject= new JSONObject(json);
		ExtentListeners.testReport.get().info("validating :"+ key);
		result =jsonObject.get(key).toString();
		return result;
		
	}

}
