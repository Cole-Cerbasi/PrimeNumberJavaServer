package cop3024_JavaServer;

import java.net.*;
import java.io.*;
import java.nio.charset.*;

public class ServerJava {
	
	static boolean primeCheck(int n) {
		if(n<=1) return false;
		for(int i = 2; i <= Math.sqrt(n); i++) if(n%i==0) return false;
		return true;
	}
	
	public static void main(String[] args) {
		
		System.out.println(primeCheck(6));
		System.out.println(primeCheck(11));
		
		ServerSocket server = null;
		boolean shutdown = false;
		
		try {
			
			server = new ServerSocket(1236);
			System.out.println("Port 1236 bound. Listening...");
			
		} catch(IOException e) {
			
			e.printStackTrace();
			System.exit(-1);
			
		}
		
		while(!shutdown) {
			
			Socket client = null;
			InputStream input = null;
			OutputStream output = null;
			
			try {
				
				client = server.accept();
				input = client.getInputStream();
				output = client.getOutputStream();
				
				int n = input.read();
				byte[] data = new byte[n];
				input.read(data);
				
				String clientInput = new String(data, StandardCharsets.UTF_8);
				clientInput.replace("\n", "");
				System.out.println("Client input: "+clientInput);
				
				clientInput = String.valueOf(primeCheck(Integer.parseInt(clientInput)));
				
				String response = clientInput;
				output.write(response.length());
				output.write(response.getBytes());
				
				client.close();
				
			}catch( IOException e) {
				e.printStackTrace();
				continue;
			}
			
		}
	}
}
