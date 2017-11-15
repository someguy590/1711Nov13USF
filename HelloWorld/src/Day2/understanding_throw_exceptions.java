package Day2;

import java.io.IOException;

public class understanding_throw_exceptions 
{
	public static void main(String[] args)
	{
		int[] arr = new int[4];
		arr[0]=1;
		try
		{
			arr[6]=6;
		}
		catch(ArithmeticException e)
		{
			arr[arr.length-1]=2;
			e.printStackTrace();
		}
		finally
		{
			System.out.println("passed exception");
			System.out.println("\n"+arr[3]);
		}
	}
	static void exampleException(int num)throws IOException
	{
		System.out.println("we are in example exception method");
		throw new IOException();
	}
}
