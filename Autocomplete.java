package application;

import java.util.Comparator;
import java.util.Arrays;

/*
 * CPS 350: Assignment 3
 * 10/17/18
 * @author Matthew Hildebrandt
 * @author Antall Nguyen
 * 
 * The purpose of this assignment is to implement sorting algorithms
 * for the autocomplete application
 */
public class Autocomplete { // implement sorting algorithm in this class

	private final Term[] termsCopy;
	private int n;
	private Term[] match;

	/* Initializes the data structure from the given array of terms. */
	public Autocomplete(Term[] terms) throws NullPointerException {
		if (terms == null)
			throw new NullPointerException("ERROR: terms should be not be null");
		termsCopy = new Term[terms.length];

		for (int i = 0; i < terms.length; i++) {
			termsCopy[i] = terms[1];
		}
		// Arrays.sort(termsCopy);
		
		/*
		for (int i = 0; i < termsCopy.length; i++) {
			System.out.println("index: " + i + " element: " + termsCopy[i]);
		}
		*/
	}

	/*
	 * Returns all terms that start with the given prefix, in descending order of
	 * weight.
	 */
	public Term[] allMatches(String prefix) throws NullPointerException {
		Comparator comp = Term.byPrefixOrder(prefix.length());
		Term key = new Term(prefix, 0);
		int begin = BinarySearchDeluxe.firstIndexOf(termsCopy, key, comp);
		// if (...)
		int end = BinarySearchDeluxe.lastIndexOf(termsCopy, key, comp);
		this.n = end - begin + 1;
		Term[] allMatches = new Term[n];
		int j = 0;
		for (int i = begin; i <= end; i++) {
			match[j++] = termsCopy[i];
		}
		// sort by reverse weight HERE:...
		//Arrays.sort(allMatches, Term.byReverseWeightOrder() );
		sortReverse(allMatches);
		//
		this.match = allMatches;
		return allMatches;
	}


	@SuppressWarnings("rawtypes")
	public void sort(Comparable[] a) {
		Comparable[] aux = new Comparable[a.length];
		sort(a, aux, 0, a.length - 1); 
	}

	private void sort(Comparable[] a, Comparable[] aux, int lo, int hi) { // recursive method
		if (hi <= lo)
			return;
		int mid = lo + (hi - lo) / 2;
		sort(a, aux, lo, mid); // 1st half is sorted after called
		sort(a, aux, mid + 1, hi);// 2nd half is sorted after called
		merge(a, aux, lo, mid, hi); // merge sorted subarrays
	}

	@SuppressWarnings("unchecked")
	private void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi)
	 {
		 for (int k = lo; k <= hi; k++) {aux[k] = a[k];} // copy to an auxiliary array
		 int i = lo, j = mid+1;
		 for (int k = lo; k <= hi; k++) {
			 if 	 (i > mid) 				break; //a[k] = aux[j++];
			 else if (j > hi)  				a[k] = aux[i++];
			 else if (less(aux[j], aux[i])) a[k] = aux[j++];
			 else 							a[k] = aux[i++];
		 }
	 }
	private static boolean less(Comparable v, Comparable w)
	{ 
		return v.compareTo(w) < 0; 
	} 
	
	public void sortReverse(Comparable[] a) {
		Comparable[] aux = new Comparable[a.length];
		sortReverse(a, aux, 0, a.length - 1); 
	}
	private void sortReverse(Comparable[] a, Comparable[] aux, int lo, int hi) { // recursive method
		if (hi <= lo)
			return;
		int mid = lo + (hi - lo) / 2;
		sortReverse(a, aux, lo, mid); // 1st half is sorted after called
		sortReverse(a, aux, mid + 1, hi);// 2nd half is sorted after called
		mergeReverse(a, aux, lo, mid, hi); // merge sorted subarrays
	}
	
	private void mergeReverse(Comparable[] a, Comparable[] aux, int lo, int mid, int hi)
	 {
		 for (int k = lo; k <= hi; k++) {aux[k] = a[k];} // copy to an auxiliary array
		 int i = lo, j = mid+1;
		 for (int k = lo; k <= hi; k++) {
			 if 	 (i > mid) 				break; //a[k] = aux[j++];
			 else if (j > hi)  				a[k] = aux[i++];
			 else if ( !less(aux[j], aux[i]) ) a[k] = aux[i++]; // switched logic for reverse order
			 else 							a[k] = aux[j++];    // switch logic for reverse order
		 } 
	 }
 }
