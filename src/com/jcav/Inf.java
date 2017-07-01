package com.jcav;
//import lombok.*;

/*@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
*/

/**
 * 用来存数据的
 * @author JCav
 */
public class Inf {
	private long favourite; //追番人数
	private String name; //名字
	private String url; //链接
	private double count; //播放次数
	
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
