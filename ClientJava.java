package cop3024_JavaServer;

import java.net.*;
import java.io.*;
import java.nio.charset.*;
import java.util.Scanner;

public class ClientJava {
	
	static String serverGet (String s) {
		String userString = s;
		try {
			Socket connection = new Socket("127.0.0.1", 1236);
			
			InputStream input = connection.getInputStream();
			OutputStream output = connection.getOutputStream();
			
			output.write(userString.length());
			output.write(userString.getBytes());
			
			int n = input.read();
			byte[] data = new byte[n];
			input.read(data);
			
			String serverResponse = new String(data, StandardCharsets.UTF_8);
			
			if(!connection.isClosed()) {
				connection.close();
			}
			
			return serverResponse;
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "Failed";
	}
	
	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		
		int i = 0;
		
		boolean exit = false;
		System.out.println("Ready.");
		while(!exit) {
			String x = scan.nextLine();
			if(x == "exit") break;
			System.out.println(serverGet(x)+ ", " + x + (serverGet(x).matches("true")?" is a prime number.":" is not a prime number!"));
			i++;
			if(i > 100) {
				System.out.println("safety.");
				break;
			}
		}
		scan.close();
	}
}
