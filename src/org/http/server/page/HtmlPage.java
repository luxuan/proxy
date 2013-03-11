package org.http.server.page;

import java.io.PrintWriter;

public class HtmlPage implements IPage {
	protected String title = "Lius·þÎñÆ÷";
	protected StringBuilder head;
	protected StringBuilder body;
	protected PrintWriter out;
	public HtmlPage(PrintWriter _out){
		this.out = _out;
		head = new StringBuilder();
		body = new StringBuilder();
	}
	
	protected void addTitle(String s){
		this.title += "-"+s;
	}
	protected void addHead(String s){
		this.head.append(s+"\n");
	}
	protected void addBody(String s){
		this.body.append(s+"\n");
	}

	public void run() {
		out.println("<html>");
		out.println("<head>");
		out.println("<title>"+this.title+"</title>");
		out.print(head);
		out.println("</head>");
		out.println("<body>");
		out.print(body);
		out.println("</body>");
		out.println("</html>");
	}
}
