package Utils;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class TestDataProvider {

	@DataProvider(name = "loginTestData")
	public Object[][] loginDataProvider() throws IOException {
		return ExcelUtil.getTestData("LoginTestData");
	}

}
