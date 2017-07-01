package com.jcav.pagedownloader;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.zip.GZIPInputStream;

/**
 * 用于下载指定的索引页数
 * @author JCav
 */
public class IndexPageDownloader {
	int page;
	public IndexPageDownloader(int p){
		page = p;
	}
	public void downPage() throws IOException {
		HttpURLConnection c = (HttpURLConnection) new URL("https://bangumi.bilibili.com/web_api/season/"
				+ "index_global?page=" + page + "&page_size=20&version=0&"
				+ "is_finish=0&start_year=0&tag_id=&index_type" + "=1&index_sort=0&quarter=0").openConnection();

		c.setRequestProperty("Host", "bangumi.bilibili.com");
		c.setRequestProperty("Accept", "application/json, text/javascript, */*; q=0.01");
		c.setRequestProperty("X-Requested-With", "XMLHttpRequest");
		c.setRequestProperty("User-Agent",
				"Mozilla/5.0 (Windows NT 6.3; WOW64) " + "AppleWebKit/537.36 (KHTML, like Gecko) "
						+ "Chrome/49.0.2623.221 " + "Safari/537.36 SE 2.X " + "MetaSr 1.0");
		c.setRequestProperty("Referer", "https://bangumi.bilibili.com/anime/index");
		c.setRequestProperty("Accept-Encoding", "gzip");
		c.setRequestProperty("Accept-Language", "zh-CN,zh;q=0.8");

		InputStream in = new GZIPInputStream(new BufferedInputStream(c.getInputStream()));
		OutputStream out = new FileOutputStream("pages/page" + page + ".json");

		byte[] buf = new byte[1024];
		while (true) {
			int size = -1;
			size = in.read(buf);
			if (size == -1) {
				break;
			}
			out.write(buf, 0, size);
		}

		out.close();
		c.disconnect();
	}
}
