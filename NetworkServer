import java.util.*;
import java.net.*;
import java.io.*;

public class NetworkServer
{
	public static void main(String[] args) throws Exception
	{
		System.out.println("Opening Socket");
		ServerSocket server = new ServerSocket(17395);
		System.out.println("Opened");
		while(true)
		{
			try{
			Socket client = server.accept();
			System.out.println("Client Accepted");
			DataOutputStream out = new DataOutputStream(new BufferedOutputStream(client.getOutputStream()));
			DataInputStream in = new DataInputStream(new BufferedInputStream(client.getInputStream()));
			String str = in.readUTF();
			System.out.printf("%s said %s",client.getRemoteSocketAddress(),str);
			out.writeUTF("Hello, thank you for trying out networking! the flag is: [redacted]");
			out.flush();
			out.close();
			in.close();
			client.close();
			System.out.println("Client Disconnected");
			}catch(Exception e){e.printStackTrace();}
		}
	}
}
