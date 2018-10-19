package application;

import java.util.Comparator;
import java.util.Arrays;

/*
 * CPS 350: Assignment 3
 * 10/19/18
 * @author Matthew Hildebrandt
 * @author Antall Nguyen
 * 
 * The purpose of this assignment is to implement sorting algorithms
 * for the autocomplete application
 */
public class Autocomplete { // implement sorting algorithm in this class

	public Term[] queries;

	/* Initializes the data structure from the given array of terms. */
	public Autocomplete(Term[] terms)  {
		if (terms == null) {
			throw new NullPointerException("ERROR: terms should be not be null");
		}
		this.queries = terms;
		
		sort(queries);

	}

	/*
	 * Returns all terms that start with the given prefix, in descending order of
	 * weight.
	 */
	public Term[] allMatches(String prefix) throws NullPointerException {

		// throws null pointer if there are no terms
		if (prefix == null) {
			throw new java.lang.NullPointerException();
		}
		Term temp = new Term(prefix, 0);
		
		int begin = BinarySearchDeluxe.firstIndexOf(queries, temp, Term.byPrefixOrder(prefix.length()));
		int end = BinarySearchDeluxe.lastIndexOf(queries, temp, Term.byPrefixOrder(prefix.length()));
		
		// DEBUG: output begin and end values
		System.out.println("BinarySearchDeluxe begin: " + begin);
		System.out.println("BinarySearchDeluxe end: " + end);
		// END DEBUG
		
		
		if (begin == -1 || end == -1) {
			Term[] empty = {temp};
			return empty;
			// throw new java.lang.NullPointerException();
		}
		Term[] matches = new Term[end - begin + 1];
		
		// Copy matching elements from array queries to array matches  
		int j = 0;
        for (int i = begin; i <= end ; i++) {
            matches[j++] = queries[i];
        }

		sort(matches, Term.byReverseWeightOrder());
		return matches;
	}
	
	// Sorts elements in lexicographical order by query
	public static void sort(Comparable[] a) {
		Comparable[] aux = new Comparable[a.length];
		sort(a, aux, 0, a.length - 1); 
	}
	private static void sort(Comparable[] a, Comparable[] aux,
		int lo, int hi) { // recursive method
		if (hi <= lo) return;
		int mid = lo + (hi - lo) / 2;
		sort(a, aux, lo, mid); //1st half is sorted after called
		sort(a, aux, mid+1, hi);//2nd half is sorted after called
		merge(a, aux, lo, mid, hi); // merge sorted subarrays
	} 
	@SuppressWarnings("unchecked")
	private static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) {
		for (int k = lo; k <= hi; k++) {aux[k] = a[k];}
			
		int i = lo, j = mid+1;
		for (int k = lo; k <= hi; k++) {
			if (i > mid) break; //a[k] = aux[j++];
			else if (j > hi) a[k] = aux[i++];
			else if (aux[j].compareTo(aux[i]) < 0) a[k] = aux[j++];
			else a[k] = aux[i++];
		}
	} 
	
	// Sorts elements in reverse weight order by weight
	public void sort(Comparable[] a, Comparator comp) {
		Comparable[] aux = new Comparable[a.length];
		sort(a, aux, 0, a.length - 1, comp); 
	}

	private void sort(Comparable[] a, Comparable[] aux, int lo, int hi, Comparator comp) { // recursive method
		if (hi <= lo)
			return;
		int mid = lo + (hi - lo) / 2;
		sort(a, aux, lo, mid, comp); // 1st half is sorted after called
		sort(a, aux, mid + 1, hi, comp);// 2nd half is sorted after called
		merge(a, aux, lo, mid, hi, comp); // merge sorted subarrays
	}

	@SuppressWarnings("unchecked")
	private void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi, Comparator comp)

	 {
		 for (int k = lo; k <= hi; k++) {aux[k] = a[k];} // copy to an auxiliary array
		 int i = lo, j = mid+1;
		 for (int k = lo; k <= hi; k++) {
			 if 	 (i > mid) 				break; //a[k] = aux[j++];
			 else if (j > hi)  				a[k] = aux[i++];
			 else if (comp.compare(aux[j], aux[i]) < 0) a[k] = aux[j++];
			 else 							a[k] = aux[i++];
		 }

	 }
 }
