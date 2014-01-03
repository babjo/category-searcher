package lch.category_searcher.unit;
import java.io.File;

import lch.category_searcher.CategorySearcher;
import lch.category_searcher.Verifier;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

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

}
