package org.http.server.page;

import java.io.PrintWriter;

public class ErrorPage extends HtmlPage {
	public ErrorPage(PrintWriter _out){
		super(_out);
		addTitle("错误");
		super.addBody("<div align='center'>");
		super.addBody("<br/><h3>出错了   ");
		super.addBody("<a href='/'>主页</a>|<a href='/modify.html'>测试Post请求</a>");
		super.addBody("</h3><br/>");
		super.addBody("<img src='/pic/error.jpg' />");
		super.addBody("</div>");
	}
}
