package day4;

import java.util.ArrayList;

public class Even_checker 
{
	
	boolean test(int num)
	{
		String s;
		String[] s1;
		String[] s2 = {"2","4","6","8","0"};
		s = Integer.toString(num);
		s1 = s.split("");
		System.out.println(s1[s1.length-1]);
		for(int i = 0;i<s1.length;i++)
		{
			
			if(s1[s1.length-1].equals(s2[i]))
			{
				return true;
			}
		}
		
		return false;
	}
	int minm(int n1,int n2)
	{
		int z = n1<n2?1:0;
		if(z==1)
		{
			return n1;
		}
		else 
		{
			return n2;
		}
	}
	
	ArrayList<Integer> a()
	{
		ArrayList j = new ArrayList();
		int p;
		for(int i = 1;i<100;i++)
		{
			j.add(i);
		}
		//test prime
		int k; 
		for(int i = 1;i<100;)
		{
			//if(!test(i))
			//{
				
				p = 0;
				for(k = 2;k<i;k++)
				{
					if(i%k==0)
					{
						p=1;
					}
				}
				if(p==0)
				{
					System.out.println(i);
				}
				i = i+2;
			//}
		}
		return j;
	}
}
