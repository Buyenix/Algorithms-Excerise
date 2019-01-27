3.1.1: GPA.java (from https://algs4.cs.princeton.edu/31elementary/GPA.java.html)
3.1.2: ArrayST.java and Queue.java.
3.1.3: OrderedSequentialSearchST.java
3.1.4: pending
3.1.5: SequentialSearchST.java
3.1.6: put() -> W, get() -> 2*(W-D) + D = 2W-D
3.1.7: pending
3.1.8: FrequencyCounter.java. And the most frequent word (>10) is Monseigneur.
3.1.9: pending
3.1.10: 
    E A S Y Q U E S T I O N
    E(1)
    A(1) - E(1)
    S(1) - A(1) - E(1)
    Y(1) - S(1) - A(1) - E(1)
    Q(1) - Y(1) - S(1) - A(1) - E(1)
    U(1) - Q(1) - Y(1) - S(1) - A(1) - E(1)
    E(2) - U(1) - Q(1) - Y(1) - S(1) - A(1)
    S(2) - E(2) - U(1) - Q(1) - Y(1) - A(1)
    T(1) - S(2) - E(2) - U(1) - Q(1) - Y(1) - A(1)
    I(1) - T(1) - S(2) - E(2) - U(1) - Q(1) - Y(1) - A(1)
    O(1) - I(1) - T(1) - S(2) - E(2) - U(1) - Q(1) - Y(1) - A(1)
    N(1) - O(1) - I(1) - T(1) - S(2) - E(2) - U(1) - Q(1) - Y(1) - A(1)
3.1.11: pending
3.1.12: pending
3.1.13: 
    Sequential ST: 10^3 * 10^6 + 10^6*10^6
    Binary Search ST: 10^3 * log2(10^3) + 10^3*10^3 + 10^6 * log2(10^3) --> choose this one
3.1.14: 
    Sequential ST: 10^6 * 10^3 + 10^3*10^3 --> choose this one
    Binary Search ST: 10^6 * log2(10^6) + 10^6*10^6 + 10^3 * log2(10^6)
3.1.15:
    Insertion: N * log2(N) + N^2
    Search: 1000*N*log2(N)
    So the percentage of insertion is (log2(N) + N)/(1001log2(N) + N)
3.1.16 ~ 3.1.18: BinarySearchST.java
3.1.19: use a queue
3.1.20: pending
3.1.21: 
    For sequential ST (linked list):
    each node: 16 (object overhead)+ 8 (reference to Key) + 8 (reference to Value) + 8 (reference t0 next) + 8 (extra overhead)= 48 byte
    total: 32 (linked list overhead size) + 48N
    For binary search ST (array)
    total: 2*(24 (array overhead size) + 8N (reference to Key or Value)) = 48 + 16N.
    Since the array size is 25% ~ 100%, the actual memory is 48 + 16N ~ 4*(48 + 16N)
3.1.22: ArrayST.java (see moveToFrontGet())
3.1.23: pending
3.1.24: pending
3.1.25: SoftwareCachingFrequencyCounter.java. Not confident that this is correct and good enough.
3.1.26: pending
3.1.27: S * log2(N) = N^2 + N * log2(N) --> to get the S value???
3.1.28: BinarySearchST.java
