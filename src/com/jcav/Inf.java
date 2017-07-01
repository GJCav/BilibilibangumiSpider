package com.jcav;
//import lombok.*;

/*@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
*/
public class Inf {
	private long favourite;
	private String name;
	private String url;
	private double count;
	
	public long getFavourite() {
		return favourite;
	}
	public void setFavourite(long favourite) {
		this.favourite = favourite;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public double getCount() {
		return count;
	}
	public void setCount(double count) {
		this.count = count;
	}
}
