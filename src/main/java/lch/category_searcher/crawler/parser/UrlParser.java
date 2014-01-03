package lch.category_searcher.crawler.parser;

import java.util.ArrayList;

public interface UrlParser {
	
	public void parse(String html);
	
	public ArrayList<String> getUrls();
	
	public String getUrl();
	
}
