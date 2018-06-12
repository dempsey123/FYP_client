package client;

import java.net.Socket;

import tuple.Tuple;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;

public class ThreadReader extends Thread {
	private Socket socket;
	
	public ThreadReader(Socket socket)
	{
		this.socket=socket;
	}
	
	@Override
	public void run()
	{
		while(true)
		{
			try{
				InputStream in=socket.getInputStream();
				 byte[] s=new byte[1024];		 
				 int len2=in.read(s);
			     ByteArrayInputStream TransferBackToS=new ByteArrayInputStream(s);
			     ObjectInputStream in2=new ObjectInputStream(TransferBackToS);
			     Tuple t=(Tuple)in2.readObject();
			     
			     System.out.println("the get back number is:"+t.getNumber());
			}catch(ClassNotFoundException e)
			{
				e.printStackTrace();
			}catch(IOException e)
			{
				e.printStackTrace();
			}	
			new ThreadWriter(socket).start();
		}			
	}
}
