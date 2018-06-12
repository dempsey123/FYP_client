package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server{
	private static ArrayList<Socket> list=new ArrayList<Socket>();
	public static void main(String[] args) throws IOException{
		ServerSocket server=new ServerSocket(8888);
		while(true)
		{
			Socket socket=server.accept();
			System.out.println("New client: "+socket.getInetAddress());
			list.add(socket);
			Thread t=new Thread(new ServerThread(socket,list));
			t.start();
		}
	}
}
