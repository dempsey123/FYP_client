package Server;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;

import standard.Standard;

import java.io.OutputStream;

public class ThreadWriter extends Thread {
private Socket socket;
private Standard standard;
private ConcurrentHashMap<String,byte[]> data;

public ThreadWriter(Socket socket)
{
	this.socket=socket;
	standard=null;
}

public ThreadWriter(Socket socket, Standard standard,ConcurrentHashMap<String,byte[]> data)
{
	this.socket=socket;
	this.standard=standard;
	this.data=data;
}
@Override
public void run()
{
	if(standard.getMode().equals("read"))
	{
		try{
			for(String pattern:data.keySet())
			{
				  if(pattern.equals(standard.getName()))
				  {
					  OutputStream out=socket.getOutputStream();
					  out.write(data.get(pattern));
				  }
			}
		}catch(IOException e)
		{
			e.printStackTrace();
		}	
	}
	if(standard.getMode().equals("in"))
	{
		try{
			for(String pattern:data.keySet()){
				if(pattern.equals(standard.getName())){
					OutputStream out=socket.getOutputStream();
					out.write(data.get(pattern));
			        data.remove(pattern);
				}
			}
		}catch(IOException e)
		{
			e.printStackTrace();
		}
	}
}
}
