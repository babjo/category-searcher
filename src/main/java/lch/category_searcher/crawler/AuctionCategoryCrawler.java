package lch.category_searcher.crawler;

import java.util.List;

import lch.category_searcher.crawler.parser.Auction;
import lch.category_searcher.crawler.parser.AuctionCategoryParser;
import lch.category_searcher.crawler.parser.Category;
import lch.category_searcher.crawler.parser.CategoryParser;
import lch.category_searcher.crawler.parser.Openmarket;
import lch.category_searcher.crawler.parser.parseShopName;

public class AuctionCategoryCrawler implements CategoryCrawler{
	
	private Openmarket openmarket = new Auction();
	private CategoryParser parser = new AuctionCategoryParser();
	private parseShopName nameParser = new parseShopName();

	public Category crawling(String productName) {
		if(productName == null || productName.equals("")){
			return null;
		}
		productName = trim(productName);
		
		String sampleStr = productName;
		String sampleBefore = null;
		do{
			sampleBefore = sampleStr;				 
			System.out.println(sampleStr);
			
			List<Category> categorys = searchCategoryByWeb(sampleStr);
			if(categorys!=null && categorys.size()!=0)
				return categorys.get(0);
			
			sampleStr = nameParser.parseNext(sampleStr);		 
		}
		while(sampleBefore.length() != sampleStr.length() && sampleStr.length() > 0);
		return null;
	}
	
	private String trim(String productName) {
		productName = productName.replace('(', ' ');
		productName = productName.replace(')', ' ');
		productName = productName.replace('+', ' ');
		return productName;
	}
	
	private List<Category> searchCategoryByWeb(String productName){
		openmarket.search(productName);
		String html = openmarket.getHtml();
		parser.parse(html);
		List<Category> categorys = parser.getCategorys();
		return categorys;
	}

}
