package com.qa.utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.commons.compress.archivers.dump.InvalidFormatException;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class utilities {
	
	public static FileInputStream fis;
	public static FileOutputStream fos;
	public static Workbook wb;
	public static Sheet sh;
	public static Row row;
	public static Cell cell;
	
	public utilities() throws EncryptedDocumentException, IOException {
		
		fis=new FileInputStream("D:\\TestData\\ApiTestData.xlsx");
		wb=WorkbookFactory.create(fis);
		sh=wb.getSheet("sheet1");
	}
	
	//get row count
	
	public int getRowcount(String sheet) {
		
		int rowno=wb.getSheet(sheet).getLastRowNum();
		return rowno;
		
	}
	
	//get cell count
	
	public int getCellcount(String sheet) {
		int cellno=wb.getSheet(sheet).getRow(0).getLastCellNum();
		return cellno;
	}
	
	//get data
	/*
	 * public String[][] getData(String sheet,int row,int cell) {
	 * 
	 * //String[][]data=
	 * 
	 * 
	 * }
	 */
	
	public static Object[][] getTestData(String sheetName) {
		FileInputStream file = null;
		try {
			file = new FileInputStream("D:\\TestData\\ApiTestData.xlsx");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			wb = WorkbookFactory.create(file);
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		sh = wb.getSheet(sheetName);
		Object[][] data = new Object[sh.getLastRowNum()][sh.getRow(0).getLastCellNum()];
		// System.out.println(sheet.getLastRowNum() + "--------" +
		// sheet.getRow(0).getLastCellNum());
		for (int i = 0; i < sh.getLastRowNum(); i++) {
			for (int k = 0; k < sh.getRow(0).getLastCellNum(); k++) {
				data[i][k] = sh.getRow(i + 1).getCell(k).toString();
				// System.out.println(data[i][k]);
			}
		}
		return data;
	}
	

}
