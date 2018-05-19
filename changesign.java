import java.io.BufferedReader;
import java.io.InputStreamReader;
 
class q5 
{
	public static void main (String[] args) throws java.lang.Exception
	{
		BufferedReader input=new BufferedReader(new InputStreamReader(System.in));
		int T=Integer.parseInt(input.readLine());
		for (int k=0;k<T;k++)
		{
			int N=Integer.parseInt(input.readLine());
			int[] a=new int[N];
			String str[]=input.readLine().split(" ");
			for (int i=0;i<N;i++)
			{
				a[i]=Integer.parseInt(str[i]);
			}
			long[][] b=new long[N][2];
			long[][] c=new long[N][2];
			for (int i=0;i<N;i++)
			{
				if (help(b,i-1,1,N)<=help(b,i-1,0,N))
				{
					b[i][0]=help(b,i-1,0,N);
					c[i][0]=0;
				}
				else
				{
					b[i][0]=help(b,i-1,1,N);
					c[i][0]=1;
				}
				if (fun(a,i-1,N)>a[i] && fun(a,i+1,N)>a[i])
				{
					if (fun(a,i-1,N)<=a[i]+fun(a,i-2,N))
					{
						if(help(b,i-2,1,N)<a[i]+help(b,i-2,0,N))
						{
							c[i][1]=0;
							b[i][1]=a[i]+help(b,i-2,0,N);
						}
						else 
						{
							c[i][1]=1;
							b[i][1]=help(b,i-2,1,N);
						}
					}
					else 
					{
						if (help(b,i-2,1,N)<=help(b,i-2,0,N))
						{
							c[i][1]=0;
							b[i][1]=help(b,i-2,0,N)+a[i];
						}
						else 
						{
							c[i][1]=1;
							b[i][1]=help(b,i-2,1,N)+a[i];
						}
					}
				}
				else 
				{
					c[i][1]=0;
					b[i][1]=0;
				}				
			}
			long flag=0;
			int i=N-1;
			if (b[i][1]>b[i][0])
				flag=1;
			while (i>=0)
			{
				if (flag!=0)
				{
					a[i]=a[i]*(-1);
					flag=c[i][1];
					i-=2;
				}
				else
				{
					flag=c[i][0];
					i--;
				}
			}
			for (i=0;i<N;i++)
			{
				System.out.print(a[i]+" ");
			}
			System.out.println();
		}
	}
	public static long help(long[][] b,int x,int y,int N )
	{
		if (0<=x && x<N)
			return b[x][y];
		else
			return 0;
	}
	public static int fun(int a[],int y,int N)
	{
		if (0<=y && y<N)
			return a[y];
		else
			return 1000000002;
	}
}
 