package lch.category_searcher.crawler.parser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class WebAccessor {

	public WebAccessor() {
	}

	public static String bringPage(String urls, String encoding){
		URL url;
		try {
			url = new URL(urls);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			setHearders(conn);
			conn.connect();
			return toString(conn, encoding);
		} catch (MalformedURLException e) {
			throw new RuntimeException("malformedURL");
		} catch (IOException e) {
			throw new RuntimeException("io error");
		}
	}

//	public static String bringPageByHttpClient(String url, String encoding)
//			throws ClientProtocolException, IOException {
//		HttpClient client = new DefaultHttpClient();
//		HttpGet get = new HttpGet(url);
//		HttpResponse response = null;
//
//		response = client.execute(get);
//
//		return toStringByHttpClient(response, encoding);
//	}
//
//	private static String toStringByHttpClient(HttpResponse response,
//			String encoding) throws IOException, UnsupportedEncodingException {
//		InputStream is;
//		is = response.getEntity().getContent();
//
//		int len = (int) response.getEntity().getContentLength();
//		if (len > 0) {
//			byte[] data = new byte[len];
//			int offset = 0;
//			int bytesRead = 0;
//			do {
//				try {
//					bytesRead = is.read(data, offset, len);
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				offset += bytesRead;
//				len -= bytesRead;
//			} while (bytesRead > 0);
//
//			is.close();
//
//			is = new ByteArrayInputStream(data);
//		}
//
//		BufferedReader reader = new BufferedReader(new InputStreamReader(is,
//				encoding));
//		StringBuilder sb = new StringBuilder();
//		String line = null;
//		while ((line = reader.readLine()) != null) {
//			sb.append(line);
//			sb.append("\n");
//		}
//		is.close();
//		return sb.toString();
//	}

	private static String toString(HttpURLConnection hurlc, String encoding)
			throws UnsupportedEncodingException, IOException {
		StringBuffer sb = new StringBuffer();
		BufferedReader in = new BufferedReader(new InputStreamReader(
				hurlc.getInputStream(), encoding));

		String line;
		while ((line = in.readLine()) != null) {
			sb.append(line);
		}
		in.close();
		hurlc.disconnect();

		return sb.toString();
	}

	private static void setHearders(HttpURLConnection conn) {
		conn.setRequestProperty(
				"User-Agent",
				"Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.1; Trident/4.0; SLCC2; .NET CLR 2.0.50727; .NET CLR 3.5.30729; .NET CLR 3.0.30729; Media Center PC 6.0; InfoPath.2)");
		conn.setRequestProperty("Accept-Language", "UTF-8");
		conn.setRequestProperty("Method", "GET");
		conn.setRequestProperty(
				"Accept",
				"image/gif, image/xxbitmap, image/jpeg, image/pjpeg,application/xshockwaveflash, application/vnd.msexcel,application/vnd.mspowerpoint, application/msword, */*");
		conn.setRequestProperty("Accept-Charset", "UTF-8");
		conn.setRequestProperty("Referer", "m.naver.com");
		conn.setConnectTimeout(30000);
		conn.setReadTimeout(30000);
	}

}
