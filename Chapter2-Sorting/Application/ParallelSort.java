public class ParallelSort
{
    public Integer[] indirectSort(Comparable[] a) {
        int size = a.length;
        Integer[] Index = new Integer[size];
        for (int i = 0; i < size; i++) {
            Index[i] = i;
        }
        for (int i = 1; i < size; i++) {
            for (int j = i; j > 0 && Utils.less(a[Index[j]], a[Index[j-1]]); j--){
                Utils.exch(Index, j, j-1);
            }
        }
        return Index;
    }
}