package com.test;

import org.mortbay.jetty.Server;
import org.mortbay.jetty.webapp.WebAppContext;

public class ServerStart {

	public static void main( String[] args ) { 
		Server server = new Server(8080);
		new WebAppContext(server, "WebContent", "/");

		try
		{
			server.start();
			server.join();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			System.exit(100);
		}
		
	}

}
