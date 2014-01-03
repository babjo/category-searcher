package lch.category_searcher.unit;

import java.io.File;

import lch.category_searcher.end_to_end.ApplicationRunner;
import lch.category_searcher.ui.CategorySearcherUI;
import lch.category_searcher.verifier.ExcelVerifier;
import lch.category_searcher.verifier.Verifier;

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
		File incorrectFile = new File(ApplicationRunner.CHOOSER_TEST_PATH, ApplicationRunner.INCORRECT_EXCEL);
		boolean actual = verifier.isVaild(incorrectFile);
		assertThat(actual, is(false));
	}
	
	@Test
	public void isVaild_returnTrue_becauseCorrectFile(){
		File correctFile = new File(ApplicationRunner.CHOOSER_TEST_PATH, ApplicationRunner.CORRECT_EXCEL);
		boolean actual = verifier.isVaild(correctFile);
		assertThat(actual, is(true));
	}

}
