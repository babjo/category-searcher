package lch.category_searcher.crawler.parser;


public abstract class Openmarket {
	
	private String html;

	public void search(String keyword){
		if(isEmpty(keyword)){
			html = "";
		}
		else{
			String url = getSearchResultUrl(keyword);
			html = WebAccessor.bringPage(url, getEncoding());
		}
	}
	
	abstract String getSearchResultUrl(String keyword);

	abstract String getEncoding();
	
	private boolean isEmpty(String value) {
		return value == null || value.equals("") || value.equals(" ");
	}

	public String getHtml() {
		return html;
	}



}
