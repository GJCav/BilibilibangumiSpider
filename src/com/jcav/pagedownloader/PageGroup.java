package com.jcav.pagedownloader;

/**
 * 使用线程池时组织下载任务
 * @author JCav
 *
 */
public class PageGroup implements Runnable{
	private final int start, end;
	
	public PageGroup(int s, int e){
		start = s;
		end = e;
	}

	public void run() {
		for(int i = start;i <= end;i++){
			try{
				new IndexPageDownloader(i).downPage();
				Thread.sleep(500);
			}catch(Exception e){
				System.out.println("Error: page " + i);
			}
		}
		System.out.println("Downloaded: " + start + " -> " + end);
	}

}
