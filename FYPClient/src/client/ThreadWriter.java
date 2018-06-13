package client;

import java.net.Socket;
import java.util.Scanner;

import standard.Standard;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import tuple.Tuple;

public class ThreadWriter extends Thread {
	private Socket socket;
	
	public static float sum=0;

	public ThreadWriter(Socket socket)
	{
		this.socket=socket;
	}
	
	//for put things into server
	public void out(Standard standard)
	{
		try
		{
			//OutputStream os=socket.getOutputStream();
			ObjectOutputStream oos=new ObjectOutputStream(socket.getOutputStream());
			oos.writeObject(standard);
			oos.flush();
		}catch(IOException e)
		{
			e.printStackTrace();
		}		
	}
	
	//for read things from server without removing from server
	public void read(Standard standard)
	{
		try
		{
			ObjectOutputStream oos=new ObjectOutputStream(socket.getOutputStream());
			oos.writeObject(standard);
			oos.flush();
			
			System.out.println("-----------Waiting------------");  //print waiting message
			InputStream in=socket.getInputStream();
		    byte[] s=new byte[1024];		 
			int len2=in.read(s);
		    ByteArrayInputStream TransferBackToS=new ByteArrayInputStream(s);
		    ObjectInputStream in2=new ObjectInputStream(TransferBackToS);
		    Tuple t=(Tuple)in2.readObject();
		     
		    System.out.println("the get back number is:"+t.getNumber());
		    return;
		}catch(IOException e)
		{
			e.printStackTrace();
		}catch(ClassNotFoundException e)
		{
			e.printStackTrace();
		}
	}
	
	public void in(Standard standard)
	{
		//float sum=0;
		try
		{
			ObjectOutputStream oos=new ObjectOutputStream(socket.getOutputStream());
			oos.writeObject(standard);		
			oos.flush();
			
			System.out.println("-----------Waiting------------");  //print waiting message
			InputStream in=socket.getInputStream();
		    byte[] s=new byte[1024];		 
			int len2=in.read(s);
		    ByteArrayInputStream TransferBackToS=new ByteArrayInputStream(s);
		    ObjectInputStream in2=new ObjectInputStream(TransferBackToS);
		    Tuple t=(Tuple)in2.readObject();
		    sum+=t.getNumber();
		     
		    System.out.println("the get back number is:"+t.getNumber());
		    System.out.println("The total sum is:"+sum);
		    return;
		}catch(IOException e)
		{
			e.printStackTrace();
		}catch(ClassNotFoundException e)
		{
			e.printStackTrace();
		}
	}
		
		/*try{
			 OutputStream out=socket.getOutputStream();
			 out.write(name.getBytes());    //this is for sending the name/pattern to the server, so the server can search it can then return something back
			 out.flush();	
		     InputStream in=socket.getInputStream();
			  byte[] s=new byte[1024];		 
			  int len2=in.read(s);
			  ByteArrayInputStream TransferBackToS=new ByteArrayInputStream(s);
			  ObjectInputStream in2=new ObjectInputStream(TransferBackToS);
			  Tuple t=(Tuple)in2.readObject();
			  System.out.println("number: "+t.getNumber());
			  }catch(ClassNotFoundException e){
				  e.printStackTrace();
			  }catch(IOException e)
			{
				  e.printStackTrace();
			}*/
	
	
	@Override
	public void run()
	{
		while(true){
		try{
			System.out.println("input the mode you are going to use: ");
			Scanner input=new Scanner(System.in);
			String mode=input.nextLine();
			System.out.println("input the name: ");
			String name=input.nextLine();

			if(mode.equals("out"))
			{
				System.out.println("input the number: ");
				float number=input.nextFloat();
				
				//this block should think about how to satisfy user's need to make it more flexible
				Tuple t=new Tuple(number);
				ByteArrayOutputStream ByteForObject=new ByteArrayOutputStream();
				ObjectOutputStream outStream=new ObjectOutputStream(ByteForObject);
				outStream.writeObject(t);
				byte[] data=ByteForObject.toByteArray();
				
				out(new Standard("out",name,data));
			}
			if(mode.equals("read"))
			{
				read(new Standard("read",name));
			}
			if(mode.equals("in"))
			{
				in(new Standard("in",name));
			}
		}catch(IOException e)
		{
			e.printStackTrace();
		}
		}
		/*try{
		Scanner input=new Scanner(System.in);
		while(true)
		{
			String str=input.nextLine();
			OutputStream os=socket.getOutputStream();
			os.write(str.getBytes());
		}
		}catch(IOException e)
		{
			e.printStackTrace();
		}*/
	}
}
