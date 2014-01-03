package lch.category_searcher;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import lch.category_searcher.crawler.AuctionCategoryCrawler;
import lch.category_searcher.crawler.CategoryCrawler;
import lch.category_searcher.crawler.parser.Category;
import lch.category_searcher.data_stream.ExcelProductDataStream;
import lch.category_searcher.data_stream.ProductDataStream;
import lch.category_searcher.ui.CategorySearcherWorker;
import lch.category_searcher.verifier.ExcelVerifier;
import lch.category_searcher.verifier.Verifier;

public class CategorySearcher {

	private Verifier excelVerifier;
	private CategoryCrawler crawler;
	private ProductDataStream productDataStream;
	
	public CategorySearcher(){
		excelVerifier = new ExcelVerifier();
		crawler = new AuctionCategoryCrawler();
		productDataStream = new ExcelProductDataStream();
	}

	public void setVerifier(Verifier excelVerifier) {
		this.excelVerifier = excelVerifier;
	}

	public void setCategoryCrawler(CategoryCrawler crawler) {
		this.crawler = crawler;
	}
	
	public void setProductDataStream(ProductDataStream productDataStream) {
		this.productDataStream = productDataStream;
	}

	public boolean isVaild(File selectedFile) {
		return excelVerifier.isVaild(selectedFile);
	}
	
	public void search(File selectedFile) {
		excelVerifier.isVaild(selectedFile);
		productDataStream.read(selectedFile);
		while(productDataStream.hasProduct()){
			String productName = productDataStream.nextProductName();
			Category category = crawler.crawling(productName);
			productDataStream.setCategoryValue(category);
		}
		String fileName = getCurrentTime()+ selectedFile.getName();
		productDataStream.write(fileName);
	}


	public void search(File selectedFile, CategorySearcherWorker categorySearcherWorker) {
		excelVerifier.isVaild(selectedFile);
		productDataStream.read(selectedFile);
		int current = 0;
		categorySearcherWorker.setMinAndMax(current, productDataStream.getPhysicalNumberOfProducts());
		while(productDataStream.hasProduct()){
			String productName = productDataStream.nextProductName();
			Category category = crawler.crawling(productName);
			productDataStream.setCategoryValue(category);
			categorySearcherWorker.setCurrent(++current);
		}
		String fileName = getCurrentTime()+ selectedFile.getName();
		productDataStream.write(fileName);
	}

	private String getCurrentTime() {
		SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat ( "yyyy년MM월dd일HH시mm분ss초_", Locale.KOREA );
		Date currentTime = new Date ( );
		return mSimpleDateFormat.format ( currentTime );
	}
}
