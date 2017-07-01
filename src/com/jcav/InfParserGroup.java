package com.jcav;

/**
 * 在使用线程池时组织任务
 * @author JCav
 */
public class InfParserGroup implements Runnable {
	private final int start, end;
	public InfParserGroup(int s, int e){
		start = s;
		end = e;
	}

	@Override
	public void run() {
		for(int i = start;i <= end;i++){
			new InfParser(i).parser();
			try{
				Thread.sleep(500);
			}catch(Exception e){}
		}
	}

}
