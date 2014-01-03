package lch.category_searcher.unit;

import java.io.File;

import lch.category_searcher.CategorySearcherUI;
import lch.category_searcher.ExcelVerifier;
import lch.category_searcher.Verifier;
import lch.category_searcher.end_to_end.ApplicationRunner;

import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
public class ExcelVerifierTest {

	private Verifier verifier;

	@Before
	public void create() {
		verifier = new ExcelVerifier();
	}
	
	@Test
	public void isVaild_returnFalse_becauseIncorrectFile(){
		File incorrectFile = new File(CategorySearcherUI.CHOOSER_DEFAULT_PATH, ApplicationRunner.INCORRECT_EXCEL);
		boolean actual = verifier.isVaild(incorrectFile);
		assertThat(actual, is(false));
	}
	
	@Test
	public void isVaild_returnTrue_becauseCorrectFile(){
		File correctFile = new File(CategorySearcherUI.CHOOSER_DEFAULT_PATH, ApplicationRunner.CORRECT_EXCEL);
		boolean actual = verifier.isVaild(correctFile);
		assertThat(actual, is(true));
	}

}
