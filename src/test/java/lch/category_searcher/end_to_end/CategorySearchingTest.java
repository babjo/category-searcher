package lch.category_searcher.end_to_end;

import org.junit.Test;

public class CategorySearchingTest {

	@Test
	public void loadExcelFailBecauseIncorrectExcelForm() {
		ApplicationRunner r = new ApplicationRunner();
		r.run();
		r.loadIncorrectFile();
		r.showLoadFailDialog();
		r.clickFailDialogOk();
		r.stop();
	}
	
	@Test
	public void loadExcelSuccessBecauseCorrectExcelForm() {
		ApplicationRunner r = new ApplicationRunner();
		r.run();
		r.loadCorrectFile();
		r.showProgressBar();
		r.stop();
	}
}
