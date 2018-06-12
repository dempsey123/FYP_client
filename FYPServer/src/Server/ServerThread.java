package Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;

public class ServerThread extends Thread{
    public Socket socket=null;
    ArrayList<Socket> list;
    InputStream is;
    OutputStream os;
    public ServerThread(Socket socket, ArrayList<Socket> list)
    {
    	this.socket=socket;
    	this.list=list;
    }
    
    @Override
    public void run()
    {
    	Thread t=new Thread(new ThreadReader(socket));
    	t.start();
    }   
    
}
