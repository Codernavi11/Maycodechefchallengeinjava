import java.util.*;
 
class gcd{
    public static void main(String [] args){
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while(t--!=0){
            int n = sc.nextInt();
            long result = 0;
            long [] a = new long[n];
            for(int i=0;i<n;i++)
                a[i] = sc.nextLong();
 
            for(int i=0,j=0;i<n&&j<n;i++,j++){
                int index = i * n + j;
                result ^= (a[i] + a[j]);
            }
            System.out.println(result);
        }
    }
}
 