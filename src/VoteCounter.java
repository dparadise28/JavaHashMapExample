import java.io.*;
import java.util.*;

public class VoteCounter {

	private static BufferedReader reader;

	public static void main(String[] args) throws IOException {
		HashMap<Character,Integer> hm = new HashMap<Character, Integer>();
		hm.put('A', 0); hm.put('B', 2); hm.put('C', 4);
		hm.put('D', 0); hm.put('E', 1);
		hm.put('F', 0); hm.put('G', 2);
		hm.put('H', 0); hm.put('I', 1);
		
		int talley[][]=new int[6][4];
		for(Object vote: (Object[]) reader()){
			int l=((String) vote).length();
			talley[hm.get(((String)vote).charAt(l-4))+hm.get(((String)vote).charAt(l-3))]
				  [hm.get(((String)vote).charAt(l-2))+hm.get(((String)vote).charAt(l-1))]+=1;
		}
		
		int Penny[]=results(talley,0,2); print_result(Penny,"Penny Pincher");
		int	Skip[]=results(talley,2,4); print_result(Skip,"Skip Dover");
		int	Sue[]=results(talley,4,6); print_result(Sue,"Sue Perman");
	}
	
	public static Object[] reader() throws IOException{
		reader=new BufferedReader(new FileReader(new File("votes.txt")));
		ArrayList<String> votes=new ArrayList<>(); String vote=null;
		
		while ((vote=reader.readLine())!=null) votes.add(vote);
		return (Object[]) votes.toArray();
	}
	
	public static int[] results(int[][] talley, int start, int end){
		int abc=0, d=0, e=0, f=0, g=0, h=0, i=0;
		for(int j=start; j<end; j++)
			for(int k=0; k<4; k++){
				abc+=talley[j][k];
				if(j%2==0){d+=talley[j][k];} else{e+=talley[j][k];}
				if(k%2==0){h+=talley[j][k];} else{i+=talley[j][k];}
				if( k<=1 ){f+=talley[j][k];} else{g+=talley[j][k];}
			}
		
		return new int[] {abc,d,e,f,g,h,i};
	}
	
	public static void print_result(int[] candidate, String Name){
		System.out.println("\t"+ Name  + " has " +candidate[0]+" votes\n\nPROPOSITION 17:   "+
						   candidate[1]+" for Yes "+candidate[2]+" for no\n  MEASURE 1:      "+
						   candidate[3]+" for yes "+candidate[4]+" for no\n  MEASURE 2:      "+
						   candidate[5]+" for yes "+candidate[6]+" for no\n\n");
	}
	
}