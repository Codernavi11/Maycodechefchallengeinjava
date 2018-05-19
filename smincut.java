import java.io.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;
import java.util.StringTokenizer;
 
 
/** Class for buffered reading int and double values */
class Reader {
    static BufferedReader reader;
    static StringTokenizer tokenizer;
 
    /** call this method to initialize reader for InputStream */
    static void init(InputStream input) {
        reader = new BufferedReader(
                     new InputStreamReader(input) );
        tokenizer = new StringTokenizer("");
    }
 
    /** get next word */
    static String next() throws IOException {
        while ( ! tokenizer.hasMoreTokens() ) {
            //TODO add check for eof if necessary
            tokenizer = new StringTokenizer(
                   reader.readLine() );
        }
        return tokenizer.nextToken();
    }
 
    static int nextInt() throws IOException {
        return Integer.parseInt( next() );
    }
	
    static double nextDouble() throws IOException {
        return Double.parseDouble( next() );
    }
}
 
class point{
	int value;
	int weight;
	point next;
	point(int a,int b ){
		this.next=null;
		this.value=a;
		this.weight=b;
	}
}
class queue{
	point rear=null;
	point front=null;
	public void insert(int value,int weight){
		if(front==null) {
			front=new point(value,weight);
			rear=front;
		}
		else if(rear==front){
			point temp=new point(value,weight);
			rear.next=temp;
			front=rear.next;
		}
		else {
			front.next=new point(value,weight);
			front=front.next;
		}
	}
	public point delete() {
		if(rear==null) {
			return null;
		}
		else if(rear.value==front.value) {
			 point temp=rear;
			rear=null;
			front=null;
			return temp;
		}
		point temp=rear;
		rear=rear.next;
		return temp;
	}
}
class node{
	int value;
	int v1;
	int v2;
	node(int value,int v1,int v2){
		this.value=value;
		this.v1=v1;
		this.v2=v2;
	}
	
}
class maxheap{
	int pos=-1;
	node[] arr;
	maxheap(int a){
		this.arr=new node[a];
		for(int i=0;i<a;i++) {
			arr[i]=null;
		}
}
	public void upheapify(int index) {
		while(index>0) {
			if(arr[index].value>arr[(index-1)/2].value) {
				node  temp=arr[index];
				arr[index]=arr[(index-1)/2];
				arr[(index-1)/2]=temp;
				index=(index-1)/2;
			}
			else {
				break;
			}
		}
	}
	public void downheapify(int index) {
		int flag=1;
		while((2*index)+2<=pos) {
			int max=Math.max(arr[(2*index)+1].value, arr[(2*index)+2].value);
			if(arr[index].value<max) {
				if(arr[(2*index)+1].value>arr[(2*index)+2].value) {
					node temp=arr[index];
					arr[index]=arr[(2*index)+1];
					arr[(2*index)+1]=temp;
					index=(2*index)+1;
				}
				else {
					node temp=arr[index];
					arr[index]=arr[(index*2)+2];
					arr[(index*2)+2]=temp;
					index=(2*index)+2;
				}
			
			}
			else {
				flag=0;
				break;
			}
		}
		if(flag==1 && (2*index)+1==pos) {
			if(arr[index].value<arr[(2*index)+1].value) {
				node temp=arr[index];
				arr[index]=arr[(2*index)+1];
				arr[(2*index)+1]=temp;
				index=(2*index)+1;
			}
		}
	}
	public node delete() {
		node temp=arr[0];
		arr[0]=arr[pos--];
		downheapify(0);
		return temp;
	}
	public void insert(node a) {
		arr[++pos]=a;
		upheapify(pos);
	}
}
class dsu{
	int[] parent;
	int[] rank;
	dsu(int a){
		parent=new int[a];
		rank=new int[a];
		for(int i=0;i<a;i++) {
			parent[i]=i;
			rank[i]=0;
		}
	}
	public int find(int a) {
		int temp=a+0;
		while(parent[temp]!=temp) {
			temp=parent[temp];
		}
		parent[a]=temp;
		return temp;
	}
	public boolean istrue(int a,int b){
		if(find(a)!=find(b)) {
			return false;
		}
 
	else {
		return true;
	}
	}
	public void union(int a,int b) {
		int x=find(a);
		int y=find(b);
		if(x!=y) {
			if(rank[x]==rank[y]) {
				parent[x]=y;
				rank[y]+=1;
			}
			else if(rank[x]>rank[y]) {
				parent[y]=x;
			}
			else {
				parent[x]=y;
			}
		}
	}
}
public class Main{
	public long bfs(int[][] arr,queue[] qs, long ans,int index) {
		boolean[] visited=new boolean[qs.length];
		visited[index]=true;
		queue q=new queue();
		q.insert(index,Integer.MAX_VALUE);
		
		while(q.rear!=null) {
			point i=q.delete();
			point temp=qs[i.value].rear;
			if(arr[index][i.value]<i.weight &&(index!=i.value)) {
				ans+=(i.weight-arr[index][i.value]);
			}
			while(temp!=null) {
				int j=temp.value;
				if(visited[j]==false) {
					visited[j]=true;
					q.insert(j,Math.min(temp.weight,i.weight));
				}
				temp=temp.next;
			}
			
		}
		return ans;
	}
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
	Reader.init(System.in);
	int x=Reader.nextInt();
	for(int i=0;i<x;i++) {
		int y=Reader.nextInt();
		int[][] arr=new int[y][y];
		long ans=0;
		for(int j=0;j<y;j++) {
			for(int k=0;k<y;k++) {
				arr[j][k]=Reader.nextInt();
			}
		}
		for(int j=0;j<y;j++) {
			for(int k=0;k<y;k++) {
				int temp1=arr[j][k];
				int temp2=arr[k][j];
				int temp3=Math.max(arr[j][k], arr[k][j]);
				if(arr[j][k]!=arr[k][j]) {
					ans+=(temp3-temp1);
					ans+=(temp3-temp2);
					arr[j][k]=temp3;
					arr[k][j]=temp3;
				}
			}
		}
		
		maxheap h=new maxheap(y*y);
		for(int j=0;j<y;j++) {
			for(int k=j+1;k<y;k++) {
				if(arr[j][k]!=0) {
					h.insert( new node(arr[j][k],j,k));
				}
			}
		}
		queue[] qs=new queue[y];
		for(int k=0;k<y;k++) {
			qs[k]=new queue();
		}
		dsu d=new dsu(y);
		while(h.pos>=0) {
			node temp=h.delete();
			if(d.find(temp.v1)!=d.find(temp.v2)) {
				d.union(temp.v1, temp.v2);
				qs[temp.v1].insert(temp.v2, temp.value);
				qs[temp.v2].insert(temp.v1, temp.value);
			}
		}
		Main qq=new Main();
		for(int k=0;k<y;k++) {
		ans=qq.bfs(arr, qs, ans, k);
		}
		System.out.println(ans);
	}
	
} 
}   