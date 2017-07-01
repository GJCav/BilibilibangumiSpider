package com.jcav;

import org.jsoup.Jsoup;
import org.jsoup.nodes.*;

import java.net.*;

/**
 * 用于从指定<code>Inf</code>中的URL获取播放次数并填该<code>Inf</code>
 * @author JCav
 *
 */
public class InfDownloader {
	private final Inf inf;
	public InfDownloader(Inf i){
		inf = i;
	}
	public void download(){
		String id = inf.getUrl().substring(inf.getUrl().lastIndexOf("/") + 1);
		System.out.println("Downloading inf for id:" + id);
		try{
			
			Document doc = Jsoup.parse(new URL(inf.getUrl()), 10000);
			String text = doc.getElementsByClass("info-count-item info-count-item-play").get(0).child(1).text();
			double count = getCount(text);
			if(count == -1){
				System.out.println("    [WARNING] error download inf for id:" + id + " Unknow text:" + text);
				return;
			}
			inf.setCount(count);
			
		}catch(Exception e){
			//e.printStackTrace();
			System.out.println("    [WARNING] error download inf for id:" + id);
		}
	}
	
	public double getCount(String text){
		if(text.equals("-")) return 0;
		double count = Double.valueOf(text.substring(0, text.length() - 1));
		switch(text.charAt(text.length() - 1)){
		case '万':
			count *= 10000;
			break;
		case '亿':
			count *= 100000000;
			break;
		case '0':case '1':case '2':case '3':case '4':case '5':case '6':case '7':case '8':case '9':
			count = Double.valueOf(text);
			break;
		default:
			count = -1;
		}
		
		return count;
	}
}








