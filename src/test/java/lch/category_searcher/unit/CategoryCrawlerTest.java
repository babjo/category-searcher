package lch.category_searcher.unit;

import static org.junit.Assert.*;
import lch.category_searcher.crawler.AuctionCategoryCrawler;
import lch.category_searcher.crawler.CategoryCrawler;
import lch.category_searcher.crawler.parser.Category;

import org.junit.Test;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
public class CategoryCrawlerTest {
	@Test
	public void crawlingSuccess() {
		CategoryCrawler crawler = new AuctionCategoryCrawler();
		Category category = crawler.crawling("힐러슈즈 EZ_NEW힐러-로즈-블랙핑크(HLRS-W05) 여성용워킹화 힐러슈즈 여성용워킹화 운동화 칼로리먹는신발 워킹화 칼로리먹는신발 기능성슈");
		assertThat(category.toString(), is("운동화/스니커즈>패션>운동화"));
	}
}
