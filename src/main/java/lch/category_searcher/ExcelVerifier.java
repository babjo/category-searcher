package lch.category_searcher;

import java.io.File;
import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelVerifier implements Verifier {
	
	public static final int PRODUCTNAME_CELL = 8;

	public boolean isVaild(File file) {
		Workbook wb;
		try {
			wb = WorkbookFactory.create(file);
		} catch (InvalidFormatException e) {
			throw new RuntimeException("not excel form");
		} catch (IOException e) {
			throw new RuntimeException("io error");
		}
		Sheet sheet = wb.getSheetAt(0);
		Row row = sheet.getRow(0);
		
		if(row.getCell(PRODUCTNAME_CELL) != null && "상품명".equals(row.getCell(PRODUCTNAME_CELL).toString()))
			return true;
		return false;
	}
}
