import java.util.Arrays;
public class ShowSameElements {
    private static void showSameElements(int[] a1, int[] a2) {
        for(int i=0, j=0; (i<a1.length && j<a2.length);) {
            if      (a1[i] > a2[j]) j++;
            else if (a1[i] < a2[j]) i++;
            else {
                StdOut.printf("a1[%d] = a2[%d] = %d\n", i, j, a1[i]);
                i++;
                j++;
            }
        }
    }
    
    public static void main(String[] args) {
        int[] a1 = { 1, 2, 3, 5, 4, 5, 6, 77, 7, 8, 8, 9, 1, 11, 22, 234, 90, 234, 345 };
        int[] a2 = { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 };
        Arrays.sort(a1);
        Arrays.sort(a2);
        showSameElements(a1, a2);
    }
}