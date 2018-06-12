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
	
	@Override
	public void run()
	{
		//Object t=null;
		try{
			while(true){
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
				  Thread t=new Thread(new ThreadWriter(socket,standard,data));
				  t.start();
			  }	
			}
			//in.close();
		}catch(IOException e)
		{
			e.printStackTrace();
			return;
		}catch(ClassNotFoundException e)
		{
			e.printStackTrace();
			return;
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
