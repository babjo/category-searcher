package lch.category_searcher;

import java.io.File;

public class CategorySearcher {

	private Verifier excelVerifier;
	
	public CategorySearcher(){
		excelVerifier = new ExcelVerifier();
	}

	public void setVerifier(Verifier excelVerifier) {
		this.excelVerifier = excelVerifier;
	}
	
	public boolean isVaild(File selectedFile) {
		return excelVerifier.isVaild(selectedFile);
	}


}
