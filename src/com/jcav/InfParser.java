package com.jcav;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.*;
import com.google.gson.*;

public class InfParser {
	private final int page;
	
	public InfParser(int p){
		page = p;
	}
	
	public void parser(){
		System.out.println("Parse page:" + page);
		JsonParser parser = new JsonParser();
		try{
			
			String json = new String(
						Files.readAllBytes(new File("pages/page" + page + ".json").toPath()),
						"utf-8"
					);
			JsonArray array = 
					parser.parse(json)
						.getAsJsonObject().get("result")
						.getAsJsonObject().get("list")
						.getAsJsonArray();
			JsonArray store = new JsonArray();
			Gson gson = new GsonBuilder().setLenient().create();
			
			for(JsonElement e : array){
				JsonObject obj = e.getAsJsonObject();
				Inf i = new Inf();
				i.setName(obj.get("title").getAsString());
				i.setFavourite(obj.get("favorites").getAsLong());
				i.setUrl(obj.get("url").getAsString());
				new InfDownloader(i).download();
				store.add(gson.toJsonTree(i));
				Thread.sleep(300);
			}
			
			File file = new File("infs/inf" + page + ".json");
			if(file.exists()) file.delete();
			Files.write(
					file.toPath(), 
					store.toString().getBytes("utf-8"), 
					StandardOpenOption.CREATE
					);
			
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("    [WARNING] error parse page:" + page);
		}
		
		
	}
	
}
