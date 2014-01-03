package lch.category_searcher.crawler.parser;


public class Category {

	public static final String DELIMITER = ">";
	private static final String NOCONTENT = "";
	private String large;
	private String middle;
	private String small;
	private boolean hasContent;
	
	public Category(String content) {
		if(isEmpty(content)){
			initEmpty();
			hasContent = false;
		}
		else{
			setCategoryValue(content);
			hasContent = true;
		}
	}

	private void initEmpty() {
		large = "";
		middle = "";
		small = "";
	}
	
	private void setCategoryValue(String content) {
		String[] values = content.split(DELIMITER);
		switch(values.length){
		case 4:
		case 3:
			large = values[0];
			middle = values[1];
			small = values[2];
			break;
		case 2:
			large = values[0];
			middle = values[1];
			small = "";
			break;
		default:
			break;
		}
	}
	private boolean isEmpty(String content) {
		return content == null || content.equals("");
	}
	
	public String toString(){
		if(hasContent)
			return large + DELIMITER + middle + DELIMITER + small;
		return NOCONTENT;
	}

	public String getLarge() {
		return large;
	}

	public String getMiddle() {
		return middle;
	}

	public String getSmall() {
		return small;
	}
}
