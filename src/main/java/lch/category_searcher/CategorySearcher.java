package lch.category_searcher;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Locale;

import lch.category_searcher.crawler.AuctionCategoryCrawler;
import lch.category_searcher.crawler.CategoryCrawler;
import lch.category_searcher.crawler.parser.Category;
import lch.category_searcher.ui.CategorySearcherWorker;
import lch.category_searcher.verifier.ExcelVerifier;
import lch.category_searcher.verifier.Verifier;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class CategorySearcher {

	private Verifier excelVerifier;
	private CategoryCrawler crawler;
	
	public CategorySearcher(){
		excelVerifier = new ExcelVerifier();
		crawler = new AuctionCategoryCrawler();
	}

	public void setVerifier(Verifier excelVerifier) {
		this.excelVerifier = excelVerifier;
	}

	public void setCrawler(CategoryCrawler crawler) {
		this.crawler = crawler;
	}
	
	public boolean isVaild(File selectedFile) {
		return excelVerifier.isVaild(selectedFile);
	}
	
	public void search(File selectedFile) {
		excelVerifier.isVaild(selectedFile);
		Workbook workbook;
		try {
			workbook = WorkbookFactory.create(selectedFile);
		} catch (InvalidFormatException e) {
			throw new RuntimeException("not excel form");
		} catch (IOException e) {
			throw new RuntimeException("io error");
		}
		
		Sheet sheet = workbook.getSheetAt(0);
		Iterator<Row> it = sheet.iterator();
		System.out.println(sheet.getPhysicalNumberOfRows());
		
		it.next();
		while(it.hasNext()){
			Row row = it.next();
			String productName = row.getCell(ExcelVerifier.PRODUCTNAME_CELL).toString();
			Category category = crawler.crawling(productName);
			System.out.println(category.toString());
			System.out.println(row.getLastCellNum());
			row.createCell(ExcelVerifier.CATEGORY_CELL);
			if(category != null)
				row.getCell(ExcelVerifier.CATEGORY_CELL).setCellValue(category.toString());
			else
				row.getCell(ExcelVerifier.CATEGORY_CELL).setCellValue("없음");
		}
		String fileName = getCurrentTime()+ selectedFile.getName();
		FileOutputStream fileOut;
		try {
			String name = CategorySearcherUI.CHOOSER_DEFAULT_PATH + fileName;
			System.out.println(name);
			fileOut = new FileOutputStream(name);
			workbook.write(fileOut);
			fileOut.close();
		} catch (FileNotFoundException e) {
			throw new RuntimeException("file not found");
		} catch (IOException e) {
			throw new RuntimeException("io error");
		}
	}

	private String getCurrentTime() {
		SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat ( "yyyy년MM월dd일HH시mm분ss초_", Locale.KOREA );
		Date currentTime = new Date ( );
		return mSimpleDateFormat.format ( currentTime );
	}

	public void search(File selectedFile, CategorySearcherWorker categorySearcherWorker) {
		excelVerifier.isVaild(selectedFile);
		Workbook workbook;
		try {
			workbook = WorkbookFactory.create(selectedFile);
		} catch (InvalidFormatException e) {
			throw new RuntimeException("not excel form");
		} catch (IOException e) {
			throw new RuntimeException("io error");
		}
		
		Sheet sheet = workbook.getSheetAt(0);
		Iterator<Row> it = sheet.iterator();
		System.out.println(sheet.getPhysicalNumberOfRows());
		
		it.next();
		int current = 0;
		categorySearcherWorker.setMinAndMax(current, sheet.getPhysicalNumberOfRows()-1);
		while(it.hasNext()){
			Row row = it.next();
			String productName = row.getCell(ExcelVerifier.PRODUCTNAME_CELL).toString();
			Category category = crawler.crawling(productName);
			System.out.println(category.toString());
			System.out.println(row.getLastCellNum());
			row.createCell(41);
			if(category != null)
				row.getCell(41).setCellValue(category.toString());
			else
				row.getCell(41).setCellValue("없음");
			categorySearcherWorker.setCurrent(++current);
		}
		String fileName = getCurrentTime()+ selectedFile.getName();
		FileOutputStream fileOut;
		try {
			String name = "./test_excel/" + fileName;
			System.out.println(name);
			fileOut = new FileOutputStream(name);
			workbook.write(fileOut);
			fileOut.close();
		} catch (FileNotFoundException e) {
			throw new RuntimeException("file not found");
		} catch (IOException e) {
			throw new RuntimeException("io error");
		}
	}

}
