package application;

import java.util.Comparator;
import java.lang.String;

/*
 * CPS 350: Assignment 3
 * 10/19/18
 * @author Matthew Hildebrandt
 * @author Antall Nguyen
 * 
 * The purpose of this assignment is to implement sorting algorithms
 * for the autocomplete application
 */
public class Term implements Comparable <Term>{

	private long weight;
	private String query;
	
	/* Initializes a term with the given query string and weight. */
	public Term(String query, long weight) {	
		// Throws exception if the query is null
		if(query.equals(null)) {
			throw new java.lang.NullPointerException(); 
		}
		// Throw exception if weight of the word is less than 0
		if(weight < 0) {
			throw new java.lang.IllegalArgumentException();
		}
		this.query = query;
		this.weight = weight;
	}

	/* Compares the two terms in descending order by weight. */
	public static Comparator<Term> byReverseWeightOrder() {
		return new ReverseWeightOrder();
	}

	
	public static Comparator<Term> byPrefixOrder(int r) {
		if(r < 0) throw new IllegalArgumentException();
		
		return new PrefixOrder(r);
	}
	
	private static class PrefixOrder implements Comparator<Term>{

		private final int r;

		private PrefixOrder(int r) {
			this.r = r;
		}

		@Override
		public int compare(Term a, Term b) {
			int lengthTerm1 = a.query.length();
			int lengthTerm2 = b.query.length();
			String word1, word2;
			
			//if both terms are greater length than r
			if(lengthTerm1 >= r && lengthTerm2 >= r) {
				word1 = a.query.toLowerCase().substring(0, r);
				word2 = b.query.toLowerCase().substring(0, r);
				
				int cmp = word1.compareTo(word2);
				if      (cmp <= -1) return -1;
                else if (cmp >= 1)  return 1;
                else                return 0;
			}
			
			//if one term length is bigger than r
			else if(lengthTerm1 >= r && lengthTerm2 < r) {
				word1 = a.query.toLowerCase().substring(0,r);
				word2 = b.query.toLowerCase();
				
				int cmp = word1.compareTo(word2);
				if      (cmp <= -1) return -1;
                else if (cmp >= 1)  return 1;
                else                return 0;
			}
			
			//if the other term length is bigger than r
			else if(lengthTerm1 < r && lengthTerm2 >= r) {
				word1 = a.query.toLowerCase();
				word2 = b.query.toLowerCase().substring(0, r);
				
				int cmp = word1.compareTo(word2);
				if      (cmp <= -1) return -1;
                else if (cmp >= 1)  return 1;
                else                return 0;
			}
			//if both terms's lengths are less than r
			else {
				word1 = a.query.toLowerCase();
				word2 = b.query.toLowerCase();
				
				int cmp = word1.compareTo(word2);
				if      (cmp <= -1) return -1;
                else if (cmp >= 1)  return 1;
                else                return 0;
			}
		}
	}

	public String toString() {		
		return weight + " \t" + query;
	}

	@Override
	public int compareTo(Term that) {
		return this.query.compareTo(that.query);
	}
	
	private static class ReverseWeightOrder implements Comparator<Term> {
		@Override
		public int compare(Term t1, Term t2) {
			Long w1 = t1.weight;
			Long w2 = t2.weight;
			
			int cmp = w1.compareTo(w2);
			if      (cmp <= -1) return 1;  // Opposite result of forward order
            else if (cmp >= 1)  return -1; // Opposite result of forward order
            else                return 0;
			
		}
	}
	
}
