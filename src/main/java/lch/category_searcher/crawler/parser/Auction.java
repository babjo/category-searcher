package lch.category_searcher.crawler.parser;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;


public class Auction extends Openmarket{
	
	private static final String AUCTION_ENCODING = "EUC-KR";
	private static final String AUCTION_SEARCH_URL_PREFIX = "http://search.auction.co.kr/search/search.aspx?keyword=";
	private static final String AUCTION_SEARCH_URL_POSTFIX = "&itemno=&nickname=&arraycategory=&frm=&dom=auction&isSuggestion=No&retry=&Fwk=%BB%C7%B7%CE%B7%CE+%BC%F6%C0%FA%BC%BC%C6%AE&acode=SRP_SU_0100&encKeyword=%25EB%25BD%2580%25EB%25A1%259C%25EB%25A1%259C%2520%25EC%2588%2598%25EC%25A0%2580%25EC%2584%25B8%25ED%258A%25B8&cc=0E64";

	@Override
	String getSearchResultUrl(String keyword){
		try {
			return AUCTION_SEARCH_URL_PREFIX+URLEncoder.encode(keyword, AUCTION_ENCODING)+AUCTION_SEARCH_URL_POSTFIX;
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException("UnsupportedEncoding");
		}
	}
	@Override
	String getEncoding() {
		return AUCTION_ENCODING;
	}
}
