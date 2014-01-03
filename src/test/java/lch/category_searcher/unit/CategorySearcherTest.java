package lch.category_searcher.unit;
import java.io.File;

import lch.category_searcher.CategorySearcher;
import lch.category_searcher.crawler.CategoryCrawler;
import lch.category_searcher.crawler.parser.Category;
import lch.category_searcher.data_stream.ProductDataStream;
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

//	@Test -> 보류 더 보충이 필요함.
//	public void search_success(){
//		Verifier excelVerifier = Mockito.mock(Verifier.class);
//		CategoryCrawler crawler = Mockito.mock(CategoryCrawler.class);
//		ProductDataStream dataStream = Mockito.mock(ProductDataStream.class);
//		File correctFile = new File("./test_excel/correct.xls"); 
//		
//		searcher.setCategoryCrawler(crawler);
//		searcher.setVerifier(excelVerifier);
//		searcher.setProductDataStream(dataStream);
//		
//		Mockito.when(excelVerifier.isVaild(correctFile)).thenReturn(false);
//		// 전체 개수를 구한다.
//		// 전체 개수만큼 hasProduct에 true을 반환한다.
//		// nextProductName은 걍 아무 값이나 반환한다.
//		
//		// 전체 개수만큼 hasProduct가 불러졌는가
//		// nextProductName이 불려졌는가
//		// 엑셀파일쓰기 함수가 불려졌는가
//		searcher.search(correctFile);
//	}
}
