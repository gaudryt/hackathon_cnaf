package soprasteria;

//The JSON-RPC 2.0 Base classes that define the 
//JSON-RPC 2.0 protocol messages
import com.googlecode.jsonrpc4j.JsonRpcServer;
import com.googlecode.jsonrpc4j.StreamServer;

import entites.ServiceDistantImpl;


import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.UnknownHostException;
import java.text.*;
import java.util.*;

/**
* Demonstration of the JSON-RPC 2.0 Server framework usage. The request
* handlers are implemented as static nested classes for convenience, but in 
* real life applications may be defined as regular classes within their old 
* source files.
*
* @author Vladimir Dzhuvinov
* @version 2011-03-05
*/ 
public class App {
 
	public static void main(String[] ar) throws IOException{
		// create the jsonRpcServer
		ServiceDistantImpl userService = new ServiceDistantImpl();
		JsonRpcServer jsonRpcServer = new JsonRpcServer(userService, ServiceDistantImpl.class);
		
		// create the stream server
		int maxThreads = 50;
		int port = 1420;
		InetAddress bindAddress = InetAddress.getByName("192.168.1.101");
		ServerSocket serverSocket = new ServerSocket(1420, 1, bindAddress);
		StreamServer streamServer = new StreamServer(jsonRpcServer, 5, serverSocket);
		streamServer.setMaxClientErrors(100);
	
		// start it, this method doesn't block
		streamServer.start();
	}
}


