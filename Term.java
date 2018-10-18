package application;

import java.util.Comparator;
import java.lang.String;

/*
 * CPS 350: Assignment 3
 * 10/9/18
 * @author Matthew Hildebrandt
 * @author Antall Nguyen
 * 
 * The purpose of this assignment is to implement sorting algorithms
 * for the autocomplete application
 */
public class Term implements Comparable <Term>{

	private long weight;
	private String query;

	public Term(String query, long weight) {	
		if(query.equals(null)) throw new NullPointerException(); 
		if(weight < 0) throw new IllegalArgumentException();
		
		this.query = query;
		this.weight = weight;
	}

	public static Comparator<Term> byReverseWeightOrder(){
		return new ReverseWeightOrder();
	}

	private static class ReverseWeightOrder implements Comparator<Term>{
		@Override
		public int compare(Term a, Term b) {
			// TODO Auto-generated method stub
			return (int)(a.weight - b.weight); // return + if a > b, - if a < b, 0 if a = b 
		}
	}
	
	public static Comparator<Term> byPrefixOrder(int r){
		if(r < 0) throw new IllegalArgumentException();
		
		return new byPrefixOrder(r);
	}
	
	private static class byPrefixOrder implements Comparator<Term>{

		private int r;

		public byPrefixOrder(int r) {
			// TODO Auto-generated constructor stub
			this.r = r;
		}

		@Override
		public int compare(Term a, Term b) {
			// TODO Auto-generated method stub
			int lengthTerm1 = a.query.length();
			int lengthTerm2 = b.query.length();
			String word1, word2;
			
			//if both terms are greater length than r
			if(lengthTerm1 >= r && lengthTerm2 >= r) {
				word1 = a.query.toLowerCase().substring(0, r);
				word2 = b.query.toLowerCase().substring(0, r);
				
				return word1.compareTo(word2);
			}
			
			//if one term length is bigger than r
			else if(lengthTerm1 >= r&& lengthTerm2 < r) {
				word1 = a.query.toLowerCase().substring(0,r);
				word2 = b.query.toLowerCase();
				
				return word1.compareTo(word2);		
			}
			
			//if the other term length is bigger than r
			else if(lengthTerm1 < r && lengthTerm2 >= r) {
				word1 = a.query.toLowerCase();
				word2 = b.query.toLowerCase().substring(0, r);
				
				return word1.compareTo(word2);
			}
			//if both terms's lengths are less than r
			else {
				word1 = a.query.toLowerCase();
				word2 = b.query.toLowerCase();

				return word1.compareTo(word2);
			}			
		}
	}

		public String toString() {		
			return weight + "/t" + query;
		}

	@Override
	public int compareTo(Term that) {
		// TODO Auto-generated method stub
		return this.query.compareTo(that.query);
	}
}
