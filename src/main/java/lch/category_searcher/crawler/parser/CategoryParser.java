package lch.category_searcher.crawler.parser;

import java.util.List;

public interface CategoryParser {
	public void parse(String html);
	public Category getCategory();
	public List<Category> getCategorys();
}
