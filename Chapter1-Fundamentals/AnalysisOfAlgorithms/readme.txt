This is for chapter 1.4 ANALYSIS OF ALGORITHMS.
Exercise Mapping:
    1.4.14 --> FourSum.java
    1.4.11 --> StaticsSETofInts.java
    1.4.12 --> ShowSameElements.java
    1.4.15 --> Solution is similar to FourSum.java
    1.4.16 --> Solution: sort the array and then foreach the array to compare the two linked elements' difference to find the smallest one.
    1.4.17 --> Solution: foreach the array to find max/min values to get the max difference.
    1.4.18 --> FindOneLocalMinValueForArray.java. In this exercise, the concept "local min value" is very intersting and important.
    1.4.19 --> FindOneLocalMinValueForMatrix.java && FindOneLocalMinValueForMatrix2.java
    1.4.20 --> Solution: Use binary search to find the maximum (in ~ 1 lg N compares); then use binary search to search in each piece (in ~ 1 lg N compares per piece).
    1.4.21 --> Solution(may not be right): When copy the intput array, we need to sort the array + refactor it by removing the multiple same elements so that contains() can use binary search with time complexity ~lg(R).
    1.4.22  --> FibonacciSearch.java
    1.4.23  --> FractionBinarySearch.java
    1.4.24  --> Strategy 1: use binary search to get F.
                Strategy 2: when N is much larger than F, we can first start from 0 to keep doubling the floor to stop at the floor where the egg is broken. Then use binary search to find the F.
                
   1.4.25  --> Solution to Part 1: To achieve 2 * sqrt(N), drop eggs at floors sqrt(N), 2 * sqrt(N), 3 * sqrt(N), ..., sqrt(N) * sqrt(N). (For simplicity, we assume here that sqrt(N) is an integer.) Let assume that the egg broke at level k * sqrt(N). With the second egg you should then perform a linear search in the interval (k-1) * sqrt(N) to k * sqrt(N). In total you will be able to find the floor F in at most 2 * sqrt(N) trials.
               Hint for Part 2: 1 + 2 + 3 + ... k ~ 1/2 k^2.
