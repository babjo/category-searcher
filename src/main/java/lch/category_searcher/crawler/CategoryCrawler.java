package lch.category_searcher.crawler;

import lch.category_searcher.crawler.parser.Category;

public interface CategoryCrawler {

	Category crawling(String productName);

}
