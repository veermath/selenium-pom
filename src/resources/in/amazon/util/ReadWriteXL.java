package in.amazon.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.testng.annotations.DataProvider;

public class ReadWriteXL 
{
	
	//POI import poi.ss.usermodel
	@DataProvider(name="amazondata")
	public static String[][] getData(Method m) throws EncryptedDocumentException, InvalidFormatException, IOException
	{

		String testCaseName = m.getName();

		FileInputStream fis = new FileInputStream(".\\AmazonTestData\\dataProviderAmazon.xlsx");

		Workbook wb = WorkbookFactory.create(fis);
		Sheet sheet = wb.getSheet("Sheet1");

		int totalTestsToRun = getTestCaseCount(sheet, testCaseName);
		int totalTestData = getTestCaseCellsCount(sheet, testCaseName);
		
		//To store test data in two dim array
		String[][] testData = new String[totalTestsToRun][totalTestData];
		int newTestDataRowIndex=0;
		
		//logic to store data when test case name matches and if it is 'Y'
		int rc = sheet.getLastRowNum();

		for (int i = 0; i <= rc; i++) {
			Row r = sheet.getRow(i);
			int newTestDataColumnIndex=0;
			
			Cell testNameCell = r.getCell(1);
			Cell testStatusCell = r.getCell(2);

			String sheetCellTestName = testNameCell.getStringCellValue();
			String statusValue = testStatusCell.getStringCellValue();

			if (testCaseName.equalsIgnoreCase(sheetCellTestName) && "Y".equalsIgnoreCase(statusValue)) {
				for(int cellDataStart=3;cellDataStart<r.getLastCellNum(); cellDataStart++ )
				{
					Cell c= r.getCell(cellDataStart);
					testData[newTestDataRowIndex][newTestDataColumnIndex++]=c.getStringCellValue();
				}
				newTestDataRowIndex++;
				
			}
		}

		return testData;
		
	}

	public static int getTestCaseCount(Sheet s, String testCaseName) {
		int rc = s.getLastRowNum();

		int testRowsCount = 0;

		for (int i = 0; i <= rc; i++) {
			Row r = s.getRow(i);

			Cell testNameCell = r.getCell(1);
			Cell testStatusCell = r.getCell(2);

			String sheetCellTestName = testNameCell.getStringCellValue();
			String statusValue = testStatusCell.getStringCellValue();

			if (testCaseName.equalsIgnoreCase(sheetCellTestName) && "Y".equalsIgnoreCase(statusValue)) {
				testRowsCount++;
			}
		}

		return testRowsCount;

	}

	public static int getTestCaseCellsCount(Sheet s, String testCaseName) {
		int testCellCount = 0;

		for (int i = 0; i <= s.getLastRowNum(); i++) {
			Row r = s.getRow(i);

			Cell testNameCell = r.getCell(1);
			Cell testStatusCell = r.getCell(2);

			String sheetCellTestName = testNameCell.getStringCellValue();
			String statusValue = testStatusCell.getStringCellValue();

			if (testCaseName.equalsIgnoreCase(sheetCellTestName) && "Y".equalsIgnoreCase(statusValue)) {
				testCellCount = r.getLastCellNum() - 3;
				break;
			}
		}

		return testCellCount;

	}
}
