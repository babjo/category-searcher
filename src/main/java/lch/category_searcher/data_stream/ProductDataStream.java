package lch.category_searcher.data_stream;

import java.io.File;

import lch.category_searcher.crawler.parser.Category;

public interface ProductDataStream {
	void read(File selectedFile);
	int getPhysicalNumberOfProducts();
	boolean hasProduct();
	String nextProductName();
	void setCategoryValue(Category category);
	void write(String fileName);
}
