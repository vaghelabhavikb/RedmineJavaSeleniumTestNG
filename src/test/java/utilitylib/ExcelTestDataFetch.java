package utilitylib;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import static config.EnvVars.*;

public class ExcelTestDataFetch {

	FileInputStream inputStream;
	Workbook workbook;
	Sheet sheet;

	public ExcelTestDataFetch(String workbookName, String sheetName) {

		try {
			inputStream = new FileInputStream(new File(tdFolderPath + workbookName + excelFileExt));
			workbook = new XSSFWorkbook(inputStream);
			sheet = workbook.getSheet(sheetName);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void setSheet(String sheetName) {
		sheet = workbook.getSheet(sheetName);
	}

	public int getNoOfColumns() {
		return sheet.getRow(0).getPhysicalNumberOfCells();
	}

	public int getNoOfRows() {
		return sheet.getPhysicalNumberOfRows();
	}

//	public static void main(String[] args) {
//
//		TestDataFetchExcel poi = new TestDataFetchExcel("updateTaskStatusOfSprint1Stories", "Data");
//		
//		HashMap<Integer, HashMap<String, String>> table = poi.getSheetAs2DHashMap();
//
//		for (Integer row : table.keySet()) {
//			for (String colHeader : table.get(row).keySet()) {
//				System.out.print("|" + table.get(row).get(colHeader));
//			}
//			System.out.println("|");
//		}
//	}

	public Object getCellValue(String cellAdd) {

		CellAddress cellAddress = new CellAddress(cellAdd);
		Cell cell = sheet.getRow(cellAddress.getRow()).getCell(cellAddress.getColumn());

		switch (cell.getCellType()) {
			case BOOLEAN:
				return cell.getBooleanCellValue();
			case NUMERIC:
				return cell.getNumericCellValue();
			case STRING:
				return cell.getRichStringCellValue();
			case BLANK:
				return "";
			case ERROR:
				return "Error occured";
			case FORMULA:
				return "Formula present in this cell";
			case _NONE:
				break;
			default:
				return "default return called";
		}
		return null;
	}

	public String getCellValue(int rowNo, int colNo) {

		Cell cell = sheet.getRow(rowNo).getCell(colNo);

		switch (cell.getCellType()) {
			case BOOLEAN:
				return String.valueOf(cell.getBooleanCellValue());
			case NUMERIC:
				return String.valueOf(cell.getNumericCellValue());
			case STRING:
				return String.valueOf(cell.getRichStringCellValue());
			case BLANK:
				return "";
			case ERROR:
				return "Error occured";
			case FORMULA:
				return "Formula present in this cell";
			case _NONE:
				break;
			default:
				return "default return called";
		}
		return null;
	}

	public HashMap<Integer, HashMap<String, String>> getSheetAs2DHashMap() {
		int noOfRows = getNoOfRows();
		int noOfColumns = getNoOfColumns();

		HashMap<Integer, HashMap<String, String>> table = new HashMap<Integer, HashMap<String, String>>();
		String[] columnHeaders = new String[noOfColumns];

		for (int j = 0; j < noOfColumns; j++) {
			columnHeaders[j] = getCellValue(0, j);
		}

		for (int i = 1; i < noOfRows; i++) {
			HashMap<String, String> cells = new HashMap<String, String>();
			for (int j = 0; j < noOfColumns; j++) {
				cells.put(columnHeaders[j], getCellValue(i, j));
			}
			table.put(i, cells);
		}

		return table;
	}

	public List<String> getColumnValues(String column) {
		List<String> columnValues = new ArrayList<String>();
		HashMap<Integer, HashMap<String, String>> table = getSheetAs2DHashMap();
		for (Integer rowNo : table.keySet()) {
			columnValues.add(table.get(rowNo).get(column));
		}
		return columnValues;
	}
	
	public List<String> getColumnValues(HashMap<Integer, HashMap<String, String>> table, String column) {
		List<String> columnValues = new ArrayList<String>(); 
		for (Integer rowNo : table.keySet()) {
			columnValues.add(table.get(rowNo).get(column));
		}
		return columnValues;
	}

	public HashMap<String, List<String>> getColumnsValues(){
		HashMap<String, List<String>> columnsValues = new HashMap<String, List<String>>();
		HashMap<Integer, HashMap<String, String>> table = getSheetAs2DHashMap();
		for (String header : table.get(1).keySet()) {
			columnsValues.put(header, getColumnValues(table, header));
		}
		return columnsValues;
	}
	
	public HashMap<String, List<String>> getColumnsValues(HashMap<Integer, HashMap<String, String>> table){
		HashMap<String, List<String>> columnsValues = new HashMap<String, List<String>>();
		for (String header : table.get(1).keySet()) {
			columnsValues.put(header, getColumnValues(table, header));
		}
		return columnsValues;
	}
	
}










