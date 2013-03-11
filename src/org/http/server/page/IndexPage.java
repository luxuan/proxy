package org.http.server.page;

import java.io.PrintWriter;

public class IndexPage extends HtmlPage {
	public IndexPage(PrintWriter _out){
		super(_out);
		super.addTitle("主页");
		super.addBody("<div align='center'>");
		super.addBody("<h3>Lius 服务器示例</h3>");
		super.addBody("<img src='/shot_pic' width='80%' />");
		super.addBody("</div>");
	}
}
