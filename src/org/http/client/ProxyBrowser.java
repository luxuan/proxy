package org.http.client;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;


public class ProxyBrowser {
	String proxyIp = "computer.hdu.edu.cn";
	int proxyPort = 808;
	public ProxyBrowser() {
		try{
			print();
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}

	public InputStream getInput(String domain,String path) throws UnknownHostException, IOException{
		Socket proxy=new Socket(proxyIp,proxyPort);
		PrintWriter headSender=new PrintWriter(proxy.getOutputStream(),true);
		//只能以一个换行结束，否则后面的信息无效
		headSender.println("GET " + domain+path +" HTTP/1.1");	//debugged:path end with '\r' before
		headSender.println("Host:" + domain);	//请求头必须规范，以免造成不必要的麻烦
		//headSender.println("Connection:Close");	//Keep-Alive，输入流阻塞
		headSender.println();

		return proxy.getInputStream();
	}
	public void print() throws UnknownHostException, IOException{
		InputStream input = getInput("www.baidu.com","/");
		int t;
		while((t=input.read())!=-1) { 
			//System.out.print((char)t);
			System.out.print((char)t); 
		}
	}
}
