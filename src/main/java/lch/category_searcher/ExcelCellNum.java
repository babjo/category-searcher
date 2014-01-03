package lch.category_searcher;

public enum ExcelCellNum {
	PRODUCTNAME(8),
	CATEGORY(41);
	
	int num;
	ExcelCellNum(int num){
		this.num = num;
	}
	
	public int value(){
		return this.num;
	}
}
