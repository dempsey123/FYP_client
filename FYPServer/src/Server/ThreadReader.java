package Server;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import standard.Standard;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.InputStream;
import tuple.Tuple;

public class ThreadReader extends Thread{
	private Socket socket;
	//private Map<String,byte[]> record=new HashMap<String,byte[]>();
	public static ConcurrentHashMap<String,byte[]> data=new ConcurrentHashMap<String,byte[]>();
	
	public ThreadReader(Socket socket)
	{
		this.socket=socket;
	}
	
	public int Search_in(Standard standard, Socket socket)
	{
		for(String pattern:data.keySet())
		{
			if(pattern.equals(standard.getName()))
			{
				try{
					OutputStream out=socket.getOutputStream();
					out.write(data.get(pattern));
					data.remove(pattern,data.get(pattern));
					return 1;
				}catch(IOException e){
					e.printStackTrace();
				}			
			}
		}
		return 0;
	}
	
	public int Search_read(Standard standard,Socket socket)
	{
		 for(String pattern:data.keySet())
			{
				  if(pattern.equals(standard.getName()))
				  {
					  try{
						  OutputStream out=socket.getOutputStream();
						  out.write(data.get(pattern));
						  return 1;
					  }catch(IOException e)
					  {
						  e.printStackTrace();
					  }
				  }
			}
		 return 0;
	}
	
	@Override
	public void run()
	{
		//Object t=null;
		while(true){
			try{
			  ObjectInputStream ois=new ObjectInputStream(socket.getInputStream());
			  Standard standard=(Standard)ois.readObject();
			  if(standard.getMode().equals("out"))
			  {
				  data.put(standard.getName(),standard.getData());
				  System.out.println("the mode is: "+standard.getMode());
				 // is2.close();
			  }
			  else
			  {
				  if(standard.getMode().equals("read"))
				  {
					  int b;
					 while(true)
					 {
						 b=Search_read(standard,socket);
						 if(b==1)
						 {
							 break;
						 }
					 }
				  }
				  if(standard.getMode().equals("in"))
				  {
					  int a;
					  while(true)
					  {
						  a=Search_in(standard,socket);
						  if(a==1)
						  {
							  break;
						  }
					  }
				  }
			  }	
			}catch(IOException e)
			{
				e.printStackTrace();
				//return;
			}catch(ClassNotFoundException e)
			{
				e.printStackTrace();
				//return;
			}
			//in.close();
		}

		
		/*try{
			while(true)
			{
				InputStream is=socket.getInputStream();
				byte[] b=new byte[1024];
				int len=is.read(b);
				String str=new String(b,0,len);
				System.out.println(str);
			}
		}catch(IOException e)
		{
			e.printStackTrace();
		}*/
	}
}
