/*
 To combat election fraud, your city is instituting a new voting procedure. 
 The ballot has a letter associated with every selection a voter may make. 
 A sample ballot is shown.

1. VOTE FOR MAYOR
A. Pincher, Penny
B. Dover, Skip
C. Perman, Sue

2. PROPOSITION 17
D. YES
E. NO

3. MEASURE 1
F. YES
G. NO

4. MEASURE 2
H. YES
I. NO
 
After submitting their ballot, every voter receives a receipt that has a unique 
ID number and a record of their voting selections. For example, a voter that submits 
a ballot for Sue Perman, Yes on Proposition 17, No on Measure 1, and Yes on 
Measure 2 might receive a receipt with

ID 4925 : CDGH

The next day the city posts all votes on their web page sorted by ID number. 
This allows a voter to confirm their submission and allows anyone to count 
the vote totals for themselves. A sample list for the sample ballot is shown.

ID VOTES
4925 CDGH
4926 AEGH
4927 CDGI
4928 BEGI
4929 ADFH

Write a program that reads the posted voting list from a CSV file and 
outputs the percent of votes cast for each ballot item. You may assume 
that the file does not have any header lines. The first line will contain 
a voter ID and a string representing votes. Define a class named Voter 
that stores an individual’s voting record. The class should have a 
constructor that takes as input a string of votes (e.g., “CDGH”), a voter 
ID, and accessor function(s) that return the person’s ID and vote for 
a specific question.

Store each Voter instance in an array or vector. Your program should 
iterate over the array to compute and output the percent of votes cast 
for each candidate, proposition, and measure. The output should be like 
this:

Votes for Item 1: 32
Votes for Item 2: 15
Votes for Item 3: 20
Votes for Item 4: 14

*/

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