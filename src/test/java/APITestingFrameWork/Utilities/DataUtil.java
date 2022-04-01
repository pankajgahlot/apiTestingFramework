package APITestingFrameWork.Utilities;

import java.lang.reflect.Method;
import java.util.Hashtable;

import org.testng.annotations.DataProvider;

import APITestingFrameWork.SetUp.baseTest;

public class DataUtil extends baseTest {

	@DataProvider
	public Object[][] getData() {

		String SheetName = "TestData";
		int rows = excel.getRowCount(SheetName);
		int cols = excel.getColumnCount(SheetName);

		Object[][] data = new Object[rows - 1][1];

		Hashtable<String, String> table = null;

		for (int row = 2; row <= rows; row++) {

			table = new Hashtable<String, String>();

			for (int col = 0; col < cols; col++) {

				table.put(excel.getCellData(SheetName, col, 1), excel.getCellData(SheetName, col, row));
				data[row - 2][0] = table;
			}
		}

		return data;

	}

	@DataProvider(parallel= true)
	public Object[][] getData1(Method m) {

		// TODO Auto-generated method stub
		String SheetName = prop.getProperty("TestSheetName");
		//ExcelReader excel = new ExcelReader(prop.getProperty("excelPath"));
		int rows = excel.getRowCount(SheetName);
		System.out.println("total rows " + rows);

		String testName = m.getName();
		System.out.println("TESTCASE to run -"+ m.getName());

		// check test cases start row
		int testCaseRowNum = 1;

		for (testCaseRowNum = 1; testCaseRowNum < rows; testCaseRowNum++) {

			String TestCaseName = excel.getCellData(SheetName, 0, testCaseRowNum);

			if (TestCaseName.equalsIgnoreCase(testName))
				break;

		}
		System.out.println("Test case start row is " + testCaseRowNum);

		// checking total number of rows for a tc

		int totalRows = 0;
		int dataStartRowNum = testCaseRowNum + 2;

		while (!excel.getCellData(SheetName, 0, dataStartRowNum + totalRows).equals("")) {
			totalRows++;
		}
		System.out.println("total  number of rows for a tc " + totalRows);

		// checking total number of cols for a tc

		int totalCols = 0;
		int dataStartColNum = testCaseRowNum + 1;

		while (!excel.getCellData(SheetName, totalCols, dataStartColNum).equals("")) {
			totalCols++;
		}
		System.out.println("total  number of Cols for a tc " + totalCols);

		// reading whole data for the testcase
		Object[][] data = new Object[totalRows][1];

		Hashtable<String, String> table = null;
		int i = 0;
		for (int rNum = dataStartRowNum; rNum < (dataStartRowNum + totalRows); rNum++) {
			table = new Hashtable<String, String>();

			for (int cNum = 0; cNum < totalCols; cNum++) {
				// String data =excel.getCellData(SheetName, cNum, rNum);
				table.put(excel.getCellData(SheetName, cNum, dataStartColNum),
						excel.getCellData(SheetName, cNum, rNum));
			}
			data[i][0] = table;
			i++;
		}

		return data;

	}

}
