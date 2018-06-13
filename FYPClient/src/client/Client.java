package client;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;


public class Client {
	public static void main(String[] args) throws UnknownHostException, IOException
	{
		Socket socket=new Socket("192.168.163.1",8888);
		Thread t=new Thread(new ThreadWriter(socket));
		//Thread s=new Thread(new ThreadReader(socket));
		t.start();
		//s.start();
	}
}
