    public class InsertionSortWithInt {
        public static void sort(int[] a) {
            int N = a.length;
            for (int i = 1; i < N; i++) {
                int j = i;
                for (; j > 0 && a[i] < a[j-1]; j--) {
                    a[j] = a[j-1];
                }
                a[j] = a[i];
            }
        }
    }