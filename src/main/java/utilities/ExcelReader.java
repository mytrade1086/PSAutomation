package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.formula.functions.Column;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

import com.google.common.io.Files;

public class ExcelReader {
	// CommonMethod cm = new CommonMethod();
	public Sheet testDataSheet, credentialSheet, configurationSheet;
	public Workbook wb = null;
	public Map<String, String> hm, configMap, credentialMap;

	public LinkedHashMap<String, String> excelDataHashmap; // Added for storing data in hashmap

	public ExcelReader() {
		// File file = new File("./src/testData/Driver.xlsx");
		File file = new File("./src/test/resources/testData/Driver.xlsx");
		try {
			FileInputStream inputStream = new FileInputStream(file);
			wb = new XSSFWorkbook(inputStream);
			// testDataSheet= wb.getSheet("TestData");
			credentialSheet = wb.getSheet("Credentials");
			configurationSheet = wb.getSheet("Configuration");
			String strEnv = getConfigVal("ApplicationEnvironment");
			if (strEnv.equals("QA")) {
				testDataSheet = wb.getSheet("TestData_QA");
			}
			if (strEnv.equals("PROD")) {
				testDataSheet = wb.getSheet("TestData_PROD");
			}
			if (strEnv.equals("DEV")) {
				testDataSheet = wb.getSheet("TestData_DEV");
			}

			if (strEnv.equals("UAT")) {
				testDataSheet = wb.getSheet("TestData_UAT");
			}

		} catch (IOException e) {
			System.out.println("Error while Accesing TestData Sheet "+e.getMessage());
		}
	}
	
	
	
	public void InsideExcel() {
		
		System.out.println("Inside ExcelReasder");
	}

	public int getRowCount(Sheet sheetRef) {
		int rowCount = sheetRef.getLastRowNum() - sheetRef.getFirstRowNum();

		//System.out.println("total rows are(1 added)" + (rowCount + 1));
		return rowCount + 1;
	}

	public int getColCountForRow(Row rows) {
		int colNew = 0;
		int colCount = rows.getLastCellNum();

		for (int i = 0; i < colCount; i++) {
			Cell cellNew = rows.getCell(i);
			// if (getCellValue(cellNew).equals(""))
			if (getCellValue(cellNew) == null || getCellValue(cellNew).equals(""))
				break;
			else
			// added for hashmap approach 31/5/2020
			if (i > 2) {
				excelDataHashmap.put(getCellValue(cellNew), null);
			}
			colNew = colNew + 1;
		}
		// return colCount;
		//System.out.println("colNew returns" + colNew);
		return colNew;
	}

	public String getCellValue(Cell cell) {
		String strCellVal = null;
		if (cell != null) {

			if (cell.getCellTypeEnum() == CellType.STRING)
				// if (cell.getCellType() == Cell.CELL_TYPE_STRING)
				strCellVal = cell.getStringCellValue();

			if (cell.getCellTypeEnum() == CellType.NUMERIC)
				// if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC)
				strCellVal = String.valueOf(cell.getNumericCellValue());

			if (cell.getCellTypeEnum() == CellType.BLANK || cell.getCellTypeEnum() == CellType.ERROR)
				// if (cell.getCellType() == Cell.CELL_TYPE_BLANK || cell.getCellType() ==
				// Cell.CELL_TYPE_ERROR)
				strCellVal = "";

			if (cell.getCellTypeEnum() == CellType.FORMULA) {
				// if (cell.getCellType() == Cell.CELL_TYPE_FORMULA) {

				if (cell.getCachedFormulaResultTypeEnum() == CellType.NUMERIC)

					// if (cell.getCachedFormulaResultType() == Cell.CELL_TYPE_NUMERIC)
					strCellVal = String.valueOf(cell.getNumericCellValue());

				if (cell.getCachedFormulaResultTypeEnum() == CellType.STRING)
					// if (cell.getCachedFormulaResultType() == Cell.CELL_TYPE_STRING)
					strCellVal = cell.getStringCellValue();

			}

		}
		/*
		 * else strCellVal=null;
		 */
		// System.out.println("strCellVal : " + strCellVal);

		System.out.println("strCellVal : " + strCellVal);
		return strCellVal;

	}

	/*
	 * public void getTestData(String strTestCase) {
	 * 
	 * // String strTestCase="TC_DIRUpdateDescription"; String tstName =
	 * null,strKey=null,strVal=null; int vFlag = 0,k=0; hm=new
	 * HashMap<String,String>();
	 * 
	 * int rowCount = getRowCount(testDataSheet); // System.out.println(rowCount);
	 * 
	 * for (int i = 0; i < rowCount; i++) { Row row=testDataSheet.getRow(i);
	 * //getting testcase name from sheet Cell cell=row.getCell(1);
	 * tstName=getCellValue(cell);
	 * 
	 * //Comparing with input testcase name if yes fetching values
	 * if(tstName.equals(strTestCase)) { //System.out.println("Row Number="+i); k =
	 * getDataRowCount(i,rowCount);
	 * 
	 * 
	 * Row rowKeyName=testDataSheet.getRow(i); Row
	 * rowKeyVal=testDataSheet.getRow(i+1);
	 * 
	 * //System.out.println("Number of Cells ="+getColCountForRow(rowKeyName)); int
	 * vColNo=getColCountForRow(rowKeyName);
	 * 
	 * 
	 * for (int j = 2; j < vColNo; j++) { Cell cellKeyName=rowKeyName.getCell(j);
	 * Cell cellKeyVal=rowKeyVal.getCell(j);
	 * 
	 * if(cellKeyName.getCellType()==Cell.CELL_TYPE_BLANK
	 * ||cellKeyName.getCellType()==Cell.CELL_TYPE_ERROR) break; else {
	 * strKey=getCellValue(cellKeyName); if (cellKeyName!=null) {
	 * strKey=getCellValue(cellKeyName); strVal=getCellValue(cellKeyVal); }
	 * hm.put(strKey, strVal); } } //breaking the row iteration loop if we found
	 * testcase vFlag=1; break; }
	 * 
	 * else vFlag=0; }
	 * 
	 * if(vFlag==0)
	 * System.out.println("Searched case not present in Testdata sheet");
	 * 
	 * }
	 */

	public void closeWorkBook() throws IOException {
		wb.close();
	}

	/*
	 * public String getTestDataVal(String vParameter) { return hm.get(vParameter);
	 * }
	 */

	public void getCredentialData(String vExpectedEnv) {
		String strKey = null, strVal = null, vEnvironment = null;
		int vFlag = 0;
		credentialMap = new HashMap<String, String>();
		int rowCount = getRowCount(credentialSheet);

		for (int i = 1; i < rowCount; i++) {
			Row row = credentialSheet.getRow(i);
			// getting testcase name from sheet
			Cell cell = row.getCell(0);
			vEnvironment = getCellValue(cell);

			// Comparing with input testcase name if yes fetching values
			if (vEnvironment.equals(vExpectedEnv)) {

				Row rowKeyName = credentialSheet.getRow(0);
				Row rowKeyVal = credentialSheet.getRow(i);

				int vColNo = getColCountForRow(rowKeyName);

				for (int j = 0; j < vColNo; j++) {
					Cell cellKeyName = rowKeyName.getCell(j);
					Cell cellKeyVal = rowKeyVal.getCell(j);

					// Mofified by Sumit 29/5/2020
//					if (cellKeyName.getCellType() == Cell.CELL_TYPE_BLANK
//							|| cellKeyName.getCellType() == Cell.CELL_TYPE_ERROR)
//						break;

					if (cellKeyName.getCellTypeEnum() == CellType.BLANK
							|| cellKeyName.getCellTypeEnum() == CellType.ERROR)
						break;

					else {
						strKey = getCellValue(cellKeyName);
						if (cellKeyName != null) {
							strKey = getCellValue(cellKeyName);
							strVal = getCellValue(cellKeyVal);
						}
						credentialMap.put(strKey, strVal);
					}
				}
				// breaking the row iteration loop if we found testcase
				vFlag = 1;
				break;
			}

			else
				vFlag = 0;
		}

		System.out.println("crenedital map is "+credentialMap);
		if (vFlag == 0)
			System.out.println("Searched Environment not Found");
		
		
		
	}

	public  String getURL(String vURL) {
		getCredentialData(getConfigVal("ApplicationEnvironment"));
		
		
		return credentialMap.get(vURL);
	}

	public void getConfigurationData() {
		String strKey = null, strVal = null;
		configMap = new HashMap<String, String>();

		int rowCount = getRowCount(configurationSheet);

		for (int i = 0; i < rowCount; i++) {
			Row row = configurationSheet.getRow(i);
			// getting testcase name from sheet
			Cell cellKeyName = row.getCell(0);
			Cell cellKeyVal = row.getCell(1);

			// 29/5/2019 modified by sumit
			// if (cellKeyName.getCellType() == Cell.CELL_TYPE_BLANK ||
			// cellKeyName.getCellType() == Cell.CELL_TYPE_ERROR

			if (cellKeyName.getCellTypeEnum() == CellType.BLANK || cellKeyName.getCellTypeEnum() == CellType.ERROR
					|| cellKeyName == null)
				break;
			else {
				strKey = getCellValue(cellKeyName);
				strVal = getCellValue(cellKeyVal);
				configMap.put(strKey, strVal);
			}

		}

		// getCredentialData(configMap.get("ApplicationEnvironment"));
	}

	public String getConfigVal(String vParameter) {
		getConfigurationData();
		return configMap.get(vParameter);

	}

	/*
	 * public int getDataRowCount(int rowCnt,int tRows) { String tstName=null; int
	 * dataCnt=0; for (int i = rowCnt+1; i < tRows; i++) { Row
	 * row=testDataSheet.getRow(i); //getting testcase name from sheet Cell
	 * cell=row.getCell(1); tstName=getCellValue(cell); if(tstName.equals(""))
	 * dataCnt=dataCnt+1; else break; } return dataCnt;
	 * 
	 * }
	 */

	@SuppressWarnings("unused")
	@DataProvider(name = "userData")
	public Object[][] getTestData(Method name) throws Exception {
		String strTestCase = name.getName(); // mycase

		// Added by Sumit 30/5/2020
		excelDataHashmap = new LinkedHashMap<String, String>();

		System.out.println("test case name is " + strTestCase);
		String tstName = null;
		int vFlag = 0, k = 0;
		hm = new HashMap<String, String>();
		Object[][] data = null;

		int rowCount = getRowCount(testDataSheet);
		System.out.println("rowCount is " + rowCount); // 4

		for (int i = 0; i < rowCount; i++) {
			Row row = testDataSheet.getRow(i);
			// getting testcase name from sheet
			Cell cell = row.getCell(1);
			tstName = getCellValue(cell);

			System.out.println("tstName: " + tstName + i);
			// System.out.println("Test Case Name" + strTestCase);
			// Comparing with input testcase name if yes fetching values

			if (tstName.equals(strTestCase)) {
				k = getDataRowCount(i, rowCount); // 1

				System.out.println("getDataRowCount returns" + k);// 2 data row
				int vColNo = getColCountForRow(row);// 2
				System.out.println("getColCountForRow returns" + vColNo);// 5 cls
				data = testData(i, k, vColNo); // i=1 is row where test case name matched , k=2 is data rows for the
												// cases,vcolNo=5 is number of columns in matched row

//				System.out.println("data returns" + data);

				vFlag = 1;
				break;
			}

			else
				vFlag = 0;
		}

		System.out.println("demo line");

		if (vFlag == 0)
			System.out.println("Searched case not present in Testdata sheet");

		Object[][] data2 = new Object[data.length][1];
	//	System.out.println(data2.length + "  " + data2[0].length);

		for (int row = 0; row < data.length; row++) {
			LinkedHashMap<String, String> excelDataHashmap2=new LinkedHashMap<String, String>(excelDataHashmap);
			
			for (int col = 1; col < data[0].length; col++) {
				for (Map.Entry<String, String> entry : excelDataHashmap2.entrySet()) {
		//			System.out.print(data[row][col] + " ");
					entry.setValue((String) data[row][col++]);
				}
				data2[row][0] = excelDataHashmap2;
				break;
			}
		}
		System.out.println("demo line2");
		return data2;
	}

	public Object[][] testData(int sRow, int eRow, int colNew) throws Exception {
		Object[][] excelData = null;
		int k = 0, l = 0;
		Row row;
		int nRow = 0;
		Cell nextCell;

		// to getting actual rows with Yes only
		for (int i = sRow + 1; i < sRow + eRow + 1; i++) {
			row = testDataSheet.getRow(i);
			for (int j = 2; j < colNew; j++) {
				Cell cell = row.getCell(j);
				if (getCellValue(cell).toUpperCase().equals("YES")) {
					nRow = nRow + 1;

				}

			}
		}

		// System.out.println("Row Values =" + nRow);
		excelData = new Object[nRow][colNew - 2];
		// excelData = new Object[eRow][colNew-2]; //OLD
		// System.out.println("Object["+eRow+"]["+colNew+"]");

		/*
		 * for(int i=sRow+1; i<sRow+eRow+1; i++) //OLD {
		 * System.out.println("ROw no="+i); row=testDataSheet.getRow(i); l=0; for(int
		 * j=2; j<colNew; j++) { Cell cell=row.getCell(j); excelData[k][l] =
		 * getCellValue(cell);
		 * System.out.println("excelData["+k+"]["+l+"]="+excelData[k][l]); l=l+1; }
		 * k=k+1; }
		 */

		for (int i = sRow + 1; i < sRow + eRow + 1; i++) {
			// System.out.println("ROw no="+i);
			row = testDataSheet.getRow(i);
			l = 0;
			for (int j = 2; j < colNew; j++) {
				Cell cell = row.getCell(j);
				if (l == 0) {
					if (getCellValue(cell).toUpperCase().equals("YES")) {
						excelData[k][l] = getCellValue(cell);

					} else {
						k = k - 1; // to setting the index properly
						break;
					}
				} else {

					// excelData[k][l] = getCellValue(cell);
					String listOfValues = getCellValue(cell);
					int v = i + 1;
					Row nextRow = testDataSheet.getRow(v);
					nextCell = nextRow.getCell(j);
					while (!getCellValue(nextCell).equals("")) {

						// System.out.println("The Next row value is : " + getCellValue(nextCell));
						listOfValues = listOfValues + "," + getCellValue(nextCell);
						v++;
						nextRow = testDataSheet.getRow(v);

						nextCell = nextRow.getCell(j);
					}
					;
					// System.out.println("listOfValues : " + listOfValues);
					excelData[k][l] = listOfValues;
				}

				System.out.println("excelData[" + k + "][" + l + "]=" + excelData[k][l]);
				l = l + 1;
			}
			k = k + 1;
		}

		return excelData;
	}

	public int getDataRowCount(int rowCnt, int tRows) {
		String tstName = null;
		int dataCnt = 0;
		for (int i = rowCnt + 1; i < tRows; i++) {
			Row row = testDataSheet.getRow(i);
			// getting testcase name from sheet
			Cell cell = row.getCell(1);
			tstName = getCellValue(cell);

			// System.out.println("sumit" +tstName);
			if (tstName.equals(""))
				dataCnt = dataCnt + 1;
			else
				break;
		}
		return dataCnt;

	}
}