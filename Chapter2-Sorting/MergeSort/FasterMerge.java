public class FasterMerge {
  private static Comparable[] aux;
  
  private static boolean less(Comparable v, Comparable w) {
      return v.compareTo(w) < 0;
  }
  
  public static void sort(Comparable[] a) {
      Comparable[] aux = new Comparable[a.length];
      int lo = 0, hi = a.length-1;
      sort(a, aux, lo, hi);
  }
  
  public static void sort(Comparable[] a, Comparable[] aux, int lo, int hi) {
      if (hi <= lo) return;
      int mid = lo + (hi - lo)/2;
      sort(a, aux, lo, mid);
      sort(a, aux, mid+1, hi);
      merge(a, aux, lo, mid, hi);
  }
  
  public static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) {
      for (int i = lo; i <= mid; i++) aux[i] = a[i];
      for (int j = mid + 1; j <= hi; j++) aux[j] = a[hi-j+mid+1];
      
      int i = lo, j = hi;
      for (int k = lo; k <= hi; k++) {
          if (less(a[i], a[j])) a[k] = a[i++];
          else                  a[k] = a[j--];
      }
  }
}