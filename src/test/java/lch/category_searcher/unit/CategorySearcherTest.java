package lch.category_searcher.unit;
import java.io.File;

import lch.category_searcher.CategorySearcher;
import lch.category_searcher.crawler.CategoryCrawler;
import lch.category_searcher.verifier.Verifier;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
@RunWith(MockitoJUnitRunner.class)
public class CategorySearcherTest {
	
	private CategorySearcher searcher;

	@Before
	public void create() {
		searcher = new CategorySearcher();
	}
	
	@Test
	public void isVaild_returnFalse_becauseIncorrectFile(){
		File incorrectFile = Mockito.mock(File.class);
		Verifier excelVerifier = Mockito.mock(Verifier.class);
		
		Mockito.when(excelVerifier.isVaild(incorrectFile)).thenReturn(false);
		
		searcher.setVerifier(excelVerifier);
		searcher.isVaild(incorrectFile);
		
		Mockito.verify(excelVerifier).isVaild(incorrectFile);
	}
	
	@Test(expected = NullPointerException.class)
	public void search_throwNullPointer_becauseNull(){
		searcher.search(null);
	}

	@Test
	public void search_success(){
		File dir = new File("./test_excel");
		int expectFileNum = dir.list().length+1;
		File correctFile = new File("./test_excel/correct.xls"); 
		searcher.search(correctFile);
		assertThat(dir.list().length, is(expectFileNum));
	}
}
