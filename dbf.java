import java.util.*;
import java.lang.*;
import java.io.*;
 
/* Name of the class has to be "Main" only if the class is public. */
class Codechef
{
	public static void main (String[] args) throws java.lang.Exception
	{
		// your code goes here
		Scanner s = new Scanner(System.in);
		long t;
		t = s.nextLong();
 
		while(t>0){
		    t=t-1;
			int m,n;
			long[] array1;
			long[] array2;
			m = s.nextInt();
			n = s.nextInt();
			array1 = new long [m];
			array2 = new long [m];
			for(int i=0;i<m;i++){
			array1[i] = s.nextLong();}
			for(int i=0;i<m;i++){
			array2[i]=s.nextLong();}
 
			long i1=0;		
			for(int i=0;i<m;i++)
				i1 = (i1 + array1[i])%1000000007;
		
			long i2=0;		
			for(int i=0;i<m;i++)
				i2 = (i2 + array2[i])%1000000007;
		
			long ans = fun(n, i1, i2);
			ans = (ans*m)%1000000007;
			System.out.println(ans);		
 
	    }
	}
		public static long fun(long n,long i1, long i2) {
		 long temp;
 		 if(n == 1)
			return i1;
  		else if(n == 2)
			return i2;
  		else{
			  for (long i=2;i<n;i++){
				 temp = (i1%1000000007 + i2%1000000007)%1000000007;
		 		i1 = i2;
		 		i2 = temp;
	  		}
	  		return i2;
		}
	}
 
	
	
}