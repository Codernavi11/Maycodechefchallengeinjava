import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
class may3 {
 	public static void main(String[] args) throws NumberFormatException, IOException {		
		BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(rd.readLine());
		for(int i=0;i<T;i++) {
			String[] variables = rd.readLine().split(" ");
			int N = Integer.parseInt(variables[0]);
			int K = Integer.parseInt(variables[1]);
 
			String[] x = rd.readLine().split(" ");
			int[] array = new int[x.length];
			int j=0;
			for(String str:x)
			{
				array[j] = Integer.parseInt(str);
				j++;
			}
 
			PriorityQueue<Integer> pqO = new PriorityQueue<Integer>(); 
			PriorityQueue<Integer> pqE= new PriorityQueue<Integer>(new Comparator<Integer>(){
				public int compare(Integer o1, Integer o2){
					return o2 - o1;
				}
			});
 
 
			for(int t1= 0;t1<array.length;t1 = t1+ 2) 
			{
				pqE.add(array[t1]);
			}
 
			for(int t2= 1;t2<array.length;t2 = t2+2) 
			{
				pqO.add(array[t2]);
			} 
			int count = 0;
			int esum = 0;
			int osum = 0;
			while( count < K  && pqE.peek() > pqO.peek() )
			{
				int pop1 = pqE.poll() ;
				int pop2 = pqO.remove() ;
				pqO.add(pop1) ;
				pqE.add(pop2) ;
				count ++ ;
				
			}
			
			Iterator itr = pqE.iterator();
			while (itr.hasNext())
				esum = esum + (int) itr.next() ;
			Iterator itr2 = pqO.iterator(); 
			for(Integer item : pqO){
				osum  = osum + (int)itr2.next() ;
			}
			if(osum > esum) 
			{
				System.out.println("YES");
			}
			else  
			{
				System.out.println("NO");
 
			}
 
 
		}
 
	}
 
}
 