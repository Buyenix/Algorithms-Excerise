import java.util.Arrays;
public class FourSum {
    private static int get4SumCount(int[] a, int target) {
        Arrays.sort(a);
        int len = a.length;
        int cnt = 0;
        for (int i = 0; i < len-3; i++) {
            //while (i!=0 && a[i] == a[i-1]) i++;
            for (int j = i+1; j < len-2; j++) {
                //while (j!=i+1 && a[j] == a[j-1]) j++;
                int k = j+1;
                int l = len-1;
                while(k<l) {
                    if (a[i]+a[j]+a[k]+a[l] > target) {
                        l--;
                    } else if (a[i]+a[j]+a[k]+a[l] < target) {
                        k++;
                    } else {
                        cnt++;
                        StdOut.printf("a[%d] + a[%d] + a[%d] + a[%d] = %d + %d + %d + %d = %d\n", i, j, k, l, a[i], a[j], a[k], a[l], target);
                        k++;
                        l--;
                        //while(k<l && a[k]==a[k-1]) k++;
                        //while(k<l && a[l]==a[l+1]) l--;
                    }
                }
            }
        }
        return cnt;
    }
    
    private static int get4SumCountBase(int[] a, int target) {
        int len = a.length;
        int cnt = 0;
        for (int l = 0; l < len - 3; l++) {
            for (int i = l + 1; i < len - 2; i++) {
                for (int j = i + 1, k = len - 1; j < k;) {
                    if (a[l] + a[i] + a[j] + a[k] < target) {
                        j++;
                    } else if (a[l] + a[i] + a[j] + a[k] > target) {
                        k--;
                    } else {
                        cnt++;
                        j++;
                        k--;
                    }
                }
            }
        }
        return cnt;
    }
    
    public static void main(String[] args) {
//        Out out = new Out("FourSum.txt");
//        for (int i = 0; i < 50; i++) {
//            int val = StdRandom.uniform(-1000, 1000);
//            out.println(val + "\n");
//        }
        In in = new In("FourSum.txt");
        int[] a = in.readAllInts();
        int cntTest = get4SumCount(a, 0);
        StdOut.printf("CountTest: %d\n", cntTest);
        int cntBase = get4SumCountBase(a, 0);
        StdOut.printf("CountBase: %d\n", cntBase);
    }
}