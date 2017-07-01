package com.jcav;

import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.*;
import java.io.*;
import java.lang.reflect.Type;
import java.net.*;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.zip.*;

import org.jsoup.Jsoup;
import org.jsoup.nodes.*;
import org.jsoup.select.Elements;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.jcav.pagedownloader.PageGroup;

public class Main {

	public static void main(String[] args) throws IOException {
		new Main();
		
	}

	public Main() throws IOException {
		File root = new File("infs");
		File[] infFiles = root.listFiles((File f) -> f.getName().endsWith(".json"));
		if(infFiles != null && infFiles.length != 0){
			
			Gson gson = new GsonBuilder().setLenient().setPrettyPrinting().create();
			JsonParser parser = new JsonParser();
			Type listType = new TypeToken<List<Inf>>(){}.getType();
			
			List<Inf> list = new ArrayList<>();
			
			for(File infFile : infFiles){
				String json = new String(
							Files.readAllBytes(infFile.toPath()),
							"utf-8"
						);
				JsonArray infs = parser.parse(json).getAsJsonArray();
				for(JsonElement e : infs){
					Inf inf = gson.fromJson(e, Inf.class);
					list.add(inf);
				}
			}
			
			List<Inf> listOfFav = list.stream()
					.filter((inf) -> inf.getFavourite() >= 10_0000)
					.sorted((i, ii) -> (int)(ii.getFavourite() - i.getFavourite()))
					.collect(Collectors.toList());
			
			OutputStream favOut = new BufferedOutputStream(new FileOutputStream("追番数排名(追番数).txt"));
			OutputStreamWriter favW = new OutputStreamWriter(favOut, "utf-8");
			PrintWriter favpw = new PrintWriter(favW);
			for(int i = 0;i < listOfFav.size();i++){
				if(i == 10)
					break;
				Inf inf = listOfFav.get(i);
				favpw.println(
						String.format(
								  "Rank:%d\r\n"
								+ "Name:%s\r\n"
								+ "Favourite:%d\r\n"
								+ "Count:%d\r\n"
								+ "URL:%s\r\n", 
								i + 1, inf.getName(), inf.getFavourite(), (int)inf.getCount(), inf.getUrl()
							)
					);
			}
			favpw.close();favW.close();favOut.close();
			
			/*Files.write(
								new File("listSortedByFav.json").toPath(), 
								gson.toJson(listOfFav, listType).getBytes("utf-8"), 
								StandardOpenOption.CREATE
							);*/
			
			List<Inf> listOfCount = list.stream()
					.filter((inf) -> inf.getCount() >= 100_0000)
					.sorted((i, ii) -> (int)(ii.getCount() - i.getCount()))
					.collect(Collectors.toList());
			
			OutputStream couOut = new BufferedOutputStream(new FileOutputStream("追番数排名(播放数).txt"));
			OutputStreamWriter couW = new OutputStreamWriter(couOut, "utf-8");
			PrintWriter coupw = new PrintWriter(couW);
			for(int i = 0;i < listOfCount.size();i++){
				if(i == 10)
					break;
				Inf inf = listOfCount.get(i);
				coupw.println(
						String.format(
								"Rank:%d\r\nName:%s\r\nFavourite:%d\r\nCount:%d\r\nURL:%s\r\n", 
								i + 1, inf.getName(), inf.getFavourite(), (int)inf.getCount(), inf.getUrl()
							)
					);
			}
			coupw.close();couW.close();couOut.close();
			
			/*Files.write(
								new File("listSortedByCount.json").toPath(), 
								gson.toJson(listOfCount, listType).getBytes("utf-8"), 
								StandardOpenOption.CREATE
							);*/
			
		}
	}
	
}









