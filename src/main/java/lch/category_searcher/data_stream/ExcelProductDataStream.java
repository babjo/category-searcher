package lch.category_searcher.data_stream;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;

import lch.category_searcher.ExcelCellNum;
import lch.category_searcher.crawler.parser.Category;
import lch.category_searcher.ui.CategorySearcherUI;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelProductDataStream implements ProductDataStream{

	public static final int COLUMN_DESCRIPTION = 1;
	private Workbook workbook;
	private Sheet sheet;
	private Iterator<Row> it;
	private Row currentRow;

	public void read(File selectedFile) {
		try {
			workbook = WorkbookFactory.create(selectedFile);
		} catch (InvalidFormatException e) {
			throw new RuntimeException("not excel form");
		} catch (IOException e) {
			throw new RuntimeException("io error");
		}
		sheet = workbook.getSheetAt(0);
		it = sheet.iterator();
		pass();
	}

	private void pass() {
		int i=0;
		while(i<COLUMN_DESCRIPTION){
			it.next();
			i++;
		}
	}

	public int getPhysicalNumberOfProducts() {
		return sheet.getPhysicalNumberOfRows()-COLUMN_DESCRIPTION;
	}

	public boolean hasProduct() {
		return it.hasNext();
	}

	public String nextProductName() {
		this.currentRow = it.next();
		return currentRow.getCell(ExcelCellNum.PRODUCTNAME.value()).toString();
	}

	public void setCategoryValue(Category category) {
		currentRow.createCell(ExcelCellNum.CATEGORY.value());
		if(category != null)
			currentRow.getCell(ExcelCellNum.CATEGORY.value()).setCellValue(category.toString());
		else
			currentRow.getCell(ExcelCellNum.CATEGORY.value()).setCellValue("없음");
	}

	public void write(String fileName) {
		FileOutputStream fileOut;
		try {
			String path = CategorySearcherUI.CHOOSER_DEFAULT_PATH + fileName;
			System.out.println(path);
			fileOut = new FileOutputStream(path);
			workbook.write(fileOut);
			fileOut.close();
		} catch (FileNotFoundException e) {
			throw new RuntimeException("file not found");
		} catch (IOException e) {
			throw new RuntimeException("io error");
		}
	}

}
