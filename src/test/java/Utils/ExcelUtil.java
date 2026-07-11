package Utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtil {

	public static Object[][] getTestData(String sheetName) throws IOException {

		// Read Excel file
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + File.separator + "TestData.xlsx");

		// Open workbook
		Workbook workbook = new XSSFWorkbook(fis);

		// Select sheet
		Sheet sheet = workbook.getSheet(sheetName);

		// Get row & column count
		int rows = sheet.getPhysicalNumberOfRows();
		int cols = sheet.getRow(0).getPhysicalNumberOfCells();

		// Create Object array (excluding header)
		Object[][] data = new Object[rows - 1][cols];

		// Create a DataFormatter to preserve the cell's displayed format in Excel
		DataFormatter formatter = new DataFormatter();
		
		// Read Excel data
		for (int i = 1; i < rows; i++) {

			for (int j = 0; j < cols; j++) {

				// Read each cell value and store it in the Object[][] array
				data[i - 1][j] = formatter.formatCellValue(sheet.getRow(i).getCell(j));
			}
		}

		// Close resources
		workbook.close();
		fis.close();

		// Return data
		return data;

	}

}
