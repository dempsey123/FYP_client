package standard;

import java.io.Serializable;

public class Standard implements Serializable {
	private String mode;
	private String name;
	private byte[] dataRecord;
	
	public Standard(String mode,String name, byte[] dataRecord)
	{
		this.mode=mode;
		this.name=name;
		this.dataRecord=dataRecord;
	}
	
	public Standard(String mode,String name)
	{
		this.mode=mode;
		this.name=name;
		this.dataRecord=null;
	}
	
	public String getMode()
	{
		return this.mode;
	}
	
	public String getName()
	{
		return this.name;
	}
	
	public byte[] getData()
	{
		return this.dataRecord;
	}
}
