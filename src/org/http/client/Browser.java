package org.http.client;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.UnknownHostException;

public class Browser extends SConnection {
	private static String CONTENT_TYPE = "Content-Type";
	private static String CONTENT_DISPOSTION = "Content-Disposition";
	private static String CHARSET = "charset=";
/*	
	public Browser(String url) throws Exception {
		int i=0,j;
		if(url.length()>4 && url.substring(0, 4).equals("http"))
			i=url.indexOf("://")+3;
		j=url.indexOf("/",i);
		super(url.substring(i,j),url.substring(j));
		this.domain = url.substring(i,j);
		this.path = url.substring(j);
	}
*/	
	public Browser(String domain,String path) throws UnknownHostException, IOException{
		super(domain,path);
		getHeader();
	}
	public void getFile(String address) throws Exception{
		int t;
		FileOutputStream realFile = new FileOutputStream(address+"/"+getFileName());
		byte[] buffer=new byte[1024];
		while((t=input.read(buffer))!=-1){
			realFile.write(buffer,0,t);
		}
		realFile.close();
		input.close();
	}
	
	public StringBuilder getHtml() throws UnknownHostException, IOException{
		InputStreamReader ir;
		String charset = getHeaderField(CONTENT_TYPE);
		
		if(charset!=null && charset.length()>9){
			charset = charset.substring(charset.indexOf(CHARSET,9)+8);
			ir=new InputStreamReader(input,charset);
		}
		else
			ir=new InputStreamReader(input);
		
		StringBuilder html = new StringBuilder();
		int t;
		while((t=ir.read())!=-1) { 
			//System.out.print((char)t);
			html.append((char)t); 
		}
		return html;
	}
	public String getFileName() {
		String dispostion = super.gets.get(CONTENT_DISPOSTION);
		if(dispostion == null)
			return "";
		try{
			dispostion = URLDecoder.decode(dispostion,"UTF-8");
		}catch(UnsupportedEncodingException ex){}
		//System.out.println("dispostion="+dispostion);
		return dispostion.substring(dispostion.indexOf(' ')+1);
	}
	//必须调用getInputStream()后才能有效
	public String getHeaderField(String key){ return this.gets.get(key); }
	public InputStream getInputStream(){ return super.input; }
}
