package application;

import java.util.Comparator;

/*
 * CPS 350: Assignment 3
 * 10/9/18
 * @author Matthew Hildebrandt
 * @author Antall Nguyen
 * 
 * The purpose of this assignment is to implement sorting algorithms
 * for the autocomplete application
 */
public class Term implements Comparable<Term> {
	String query;
	long weight;
	
	/* Initializes a term with the given query string and weight. */
	 public Term(String query, long weight)
	 {
		 if (query.equals(null)) { throw new NullPointerException("ERROR: query equals null... ");}
		 if (weight<0) 			 { throw new IllegalArgumentException("ERROR: weight is negative... ");} 
		 this.query=query; this.weight=weight;
	 }
	 /* Compares the two terms in descending order by weight. */
	 public static Comparator<Term> byReverseWeightOrder()
	 {
		 return new ReverseWeightOrder();
	 }
	 private static class ReverseWeightOrder implements Comparator<Term> {
	  public int compare(Term a, Term b) {
		  return (int) (a.weight - b.weight);
	  }
	 }
	 /* Compares the two terms in lexicographic order but using only the first
	r characters of each query. */
	 public static Comparator<Term> byPrefixOrder(int r)
	 {
		if (r<0) { throw new IllegalArgumentException("ERROR: r value in byPrefixOrder() is negative... ");}
		
	 }
	 /* Compares the two terms in lexicographic order by query. */
	 public int compareTo(Term that) {
		 return Integer.parseInt(this.query)-Integer.parseInt(that.query);
	 }
	
	
	 // Returns a string representation of this term in the following format:
	 // weight (i.e., ??.toString()), followed by a tab, followed by query.
	 public String toString() {
		 
	 }
}
