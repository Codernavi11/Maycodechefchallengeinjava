import java.util.*;
 
class xor{
    static long gcd(long a, long b){
        if(b == 0)
            return a;
        else
            return gcd(b, a % b);
    }
 
    public static void main(String [] args){
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while(t--!=0){
            int n = sc.nextInt();
            long [] a = new long[n];
            for(int i=0;i<n;i++)
                a[i] = sc.nextLong();
 
            long g = a[0];
            for(int i=1;i<n;i++)
                g = gcd(g, a[i]);
            if(g==1)
                System.out.println(0);
            else
                System.out.println(-1);
        }
    }
}