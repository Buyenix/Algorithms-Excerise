public class StackGB {
    private int[] PMTT = null; //permutation
    private int pushCount = 0;
    private int popCount = 0;
    
    public StackGB(int[] pmtt) {
        PMTT = pmtt;
        pushCount = 0;
        popCount = 0;
    }
    
    public boolean isTrue() {
        if (PMTT.length < 1) return false;
        pushCount = PMTT[0] + 1;
        popCount++;
        while (popCount < PMTT.length) {
            while (popCount < PMTT.length && PMTT[popCount] < PMTT[popCount-1]) {
                if (++popCount > pushCount) return false;
            }
            
            while (popCount < PMTT.length && PMTT[popCount] > PMTT[popCount-1]) {
                pushCount = PMTT[popCount] + 1;
                if (++popCount > pushCount) return false;
            }
        }
        return popCount == pushCount;
    }
    
    public static void main(String[] args) {
        int[] pmtt1 = {4, 3, 2, 1, 0, 5, 6, 7, 8, 9};
        int[] pmtt2 = {0, 4, 6, 5, 3, 8, 1, 7, 2, 9};
        int[] pmtt3 = {1, 4, 7, 9, 8, 6, 5, 3, 0, 2};
        int[] pmtt4 = {2, 1, 4, 3, 6, 5, 8, 7, 9, 0};
        StackGB s1 = new StackGB(pmtt1);
        StackGB s2 = new StackGB(pmtt2);
        StackGB s3 = new StackGB(pmtt3);
        StackGB s4 = new StackGB(pmtt4);

        StdOut.println("PMTT1 is " + s1.isTrue());

        StdOut.println("PMTT2 is " + s2.isTrue());

        StdOut.println("PMTT3 is " + s3.isTrue());

        StdOut.println("PMTT4 is " + s4.isTrue());
    }
}