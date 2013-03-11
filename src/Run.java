import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.util.Scanner;

import org.http.client.Browser;
import org.http.server.HttpProxy;
import org.http.server.HttpServer;

public class Run {
	public static void main(String[] args) {
		final int proxyPort , httpPort;
		Scanner scan =new Scanner(System.in);
		System.out.print("input proxy and http port:");
		proxyPort = scan.nextInt();
		httpPort = scan.nextInt();
		
		Thread proxyt =new Thread(new Runnable(){
			public void run(){ 
				try{
					ServerSocket proxyServer = getServer(proxyPort);
					String info = "PROXY SERVER START: ";
					info += "IP= "+InetAddress.getLocalHost();
					info += "\tPORT= "+proxyServer.getLocalPort();
					System.out.println(info);
					while(true)
						new HttpProxy(proxyServer.accept());
				}catch(Exception ex){ }
			}
		});
		proxyt.start();

		Thread httpt =new Thread(new Runnable(){
			public void run(){
				try{
					ServerSocket httpServer = getServer(httpPort);
					String info = " HTTP SERVER START: ";
					info += "IP= "+InetAddress.getLocalHost();
					info += "\tPORT= "+httpServer.getLocalPort();
					System.out.println(info);
					while(true)
						new HttpServer(httpServer.accept());
				}catch(Exception ex){ }
			}
		});
		httpt.start();
		
		
		//http();
		//new ProxyBrowser();
	}
	public static void http(){
		String domain = "www.baidu.com";
		String path = "/img/i-1.0.0.png";
		try{
			Browser b =new Browser(domain,path);
			StringBuilder html=b.getHtml();
			//System.out.println(html);
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}

	public static ServerSocket getServer(int port){
		ServerSocket server = null;
		int c = 0;
		while(server==null && ++c<100){
			try {
				server = new ServerSocket(port);
				return server;
			} catch (IOException e) {
				port += 3*c + 1;
				//e.printStackTrace();
			}
		}
		return null;
	}
}
