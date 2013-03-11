package org.http.server;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;


import org.http.server.page.ErrorPage;
import org.http.server.page.IPage;
import org.http.server.page.IndexPage;
import org.http.server.page.ModifyPage;
import org.http.server.pic.FilePic;
import org.http.server.pic.IPic;
import org.http.server.pic.ShotPic;

public class HttpServer extends Http {
	private final static String ROOT_TAG = "/";
	private final static String INDEX_TAG = "/index.html";
	private final static String MODIFY_TAG = "/modify.html";
	private final static String CAMERA_PIC_TAG = "/camera_pic";
	private final static String SHOT_PIC_TAG = "/shot_pic";
	private final static String PIC_TAG = "/pic/";
	public HttpServer(Socket _client){
		super(_client);
	}
	public void run(){
		ArrayList<String> list = request();
		if(list.size()==0)
			return ;
		//for(String s:list)
			//System.out.println(s);
		final String requestLine = list.get(0);
		if(requestLine.length()==0)
			return;
		final int i,j;
		i = requestLine.indexOf(' ');
		j = requestLine.indexOf(' ',i+1);
		try {
			//System.out.println(requestLine+" "+i+" "+j);
			String path = requestLine.substring(i+1,j);
			OutputStream output = client.getOutputStream();
			PrintWriter writer=new PrintWriter(output,true);
			if(ROOT_TAG.equals(path) || INDEX_TAG.equals(path)){
					responseHead(writer,"text/html",new Date().toString());
					IPage page = new IndexPage(writer);
					page.run();
			}
			else if(MODIFY_TAG.equals(path)){
				responseHead(writer,"text/html",new Date().toString());
				String method = requestLine.substring(0 , i);
				String postMassage = null;
				if(Http.POST_TAG.equals(method)){
					try {
						byte[] buffer =new byte[1024];
						int n = client.getInputStream().read(buffer);
						postMassage = new String(buffer,0,n);
					} catch (Exception e) {
						//e.printStackTrace();
					}
				}
				//System.out.println(postMassage);
				IPage page = new ModifyPage(postMassage,writer);
				page.run();
			}
			else if(path.length()>PIC_TAG.length() && 
					PIC_TAG.equals(path.substring(0, PIC_TAG.length()))){
				responseHead(writer,"image",new Date().toString());
				IPic pic = new FilePic("lib"+path);
				pic.output(output);
			}
			else if(CAMERA_PIC_TAG.equals(path)){
				
			}
			else if(SHOT_PIC_TAG.equals(path)){
				responseHead(writer,"image/png",new Date().toString());
				IPic pic = new ShotPic();
				pic.output(output);
			}
			else {
				responseHead(writer,"text/html",new Date().toString());
				IPage page = new ErrorPage(writer);
				page.run();
			}
		} catch (IOException e) {
			//e.printStackTrace();
		}
		try{
			client.close();
		}catch(Exception ex){}
	}
	public void responseHead(PrintWriter writer,String type,
			String modify) throws IOException{
		writer.println("HTTP/1.1 200 OK");
		//writer.println("Content-Length:"+length);
		writer.println("Last-Modified:"+modify);
		writer.println("Accept-Ranges:bytes");
		writer.println("Date:"+new Date().toString());
		writer.println("Server:Lius");
		writer.println("Connection:close");
		writer.println();
	}
	
}
