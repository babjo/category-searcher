package lch.category_searcher.crawler.parser;

import java.util.ArrayList;

import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.HtmlNode;
import org.htmlcleaner.TagNode;
import org.htmlcleaner.TagNodeVisitor;

public class AuctionCategoryParser implements CategoryParser{
	
	private Category category;
	private ArrayList<Category> categroys;
	
	private HtmlCleaner cleaner = new HtmlCleaner();
	public static final String DELIMITER = ">";
	
	public AuctionCategoryParser(){
	}
	
	public void parse(String html) {
		initCategory();
		if(isEmpty(html))
			return;
		else
			parseHtml(html);
	}
	
	private void initCategory(){
		category = null;
		categroys = null;
	}
	
	private boolean isEmpty(String value) {
		return value == null || value.equals("");
	}
	
	private void parseHtml(String html) {
		TagNode node = cleaner.clean(html);
		node.traverse(new TagNodeVisitor() {
			public boolean visit(TagNode tagNode, HtmlNode htmlNode) {
				if (htmlNode instanceof TagNode) {
					TagNode tag = (TagNode) htmlNode;
					if(isCategoryTag(tag)){
						getCategoryText(tag);
					}
				}
				return true;
			}
		});
	}
	
	private boolean isCategoryTag(TagNode tag) {
		return "cat-list".equals(tag.getAttributeByName("class"));
	}
	
	private void getCategoryText(TagNode tag) {
		TagNode categoryTags = tag.getChildTags()[1];
		
		category = new Category(toCategoryForm(categoryTags.getChildTags()[0].getText().toString()));
		categroys = new ArrayList<Category>();
		for(int i=0; i < categoryTags.getChildTagList().size(); i++){
			categroys.add(new Category(toCategoryForm(categoryTags.getChildTags()[i].getText().toString())));
		}
	}
	
	private String toCategoryForm(String value) {
		int beginIndex = value.lastIndexOf('(')+1;
		value = value.substring(0,beginIndex);
		value = value.replaceAll("\\s+", DELIMITER);
		return value.substring(1, value.length()-1);
	}
	
	public Category getCategory() {
		return category;
	}
	
	public ArrayList<Category> getCategorys() {
		return categroys;
	}
}
