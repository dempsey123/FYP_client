package tuple;

import java.io.Serializable;

public class Tuple implements Serializable{
	private String pattern;
	private double number;
	
	public Tuple()
	{
		pattern="default";
		number=0;
	}
	
	public Tuple (String pattern, double d)
	{
		this.pattern=pattern;
		this.number=d;
	}
	
	public Tuple(double d)
	{
		this.number=d;
	}
	
	public void SetPattern(String pattern)
	{
		this.pattern=pattern;
	}
	
	public void SetNumber(double number)
	{
		this.number=number;
	}
	
	public String getPattern()
	{
		return pattern;
	}
	
	public double getNumber()
	{
		return number;
	}
	
}

