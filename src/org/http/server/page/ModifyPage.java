package org.http.server.page;

import java.io.PrintWriter;

public class ModifyPage extends HtmlPage {
	private final static String PROXY_PORT_TAG = "proxy_port";
	private final static String HTTP_PORT_TAG = "web_port";
	public ModifyPage(String s , PrintWriter out) {
		super(out);
		super.addTitle("修改");
		super.addBody("<div align='center'>");
		super.addBody("<h3>提交端口</h3>");
		if(s!=null)
			super.addBody("Post方法消息: "+s+"<br/><br/>");
		super.addBody("<form method='post' action='/modify.html' >");
		super.addBody("Proxy Port:");
		super.addBody("<input name='"+PROXY_PORT_TAG+"' type='text'  /><br/><br/>");
		super.addBody("Http  Port:");
		super.addBody("<input name='"+HTTP_PORT_TAG+"' type='text'  /><br/><br/>");
		super.addBody("<input type='submit'  value='Submit' />");
		super.addBody("</form>");
		super.addBody("</div>");

	}
	
	public static String getValue(String key,String line){
		int i,j;
		i = line.indexOf(PROXY_PORT_TAG);
		if(i<0)
			return "";
		j = line.indexOf('&',i);
		if(j<0)
			return line.substring(i);
		else
			return line.substring(i,j);

	}

	
}
