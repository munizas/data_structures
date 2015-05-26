public class Sort {
    public static void mergeSort(int[] a, int p, int q) {
        // check if sequence has at least 2 elements
        if (q-p < 2)
            return;
        // calculate midpoint
        int m = (p+q)/2;
        // sort left half
        mergeSort(a, p, m);
        // sort right half
        mergeSort(a, m, q);
        // merge two sorted halves
        merge(a, p, m, q);
    }
    
    public static void merge(int[] a, int p, int m, int q) {
        // check if sequence is already sorted
        if (a[m-1] < a[m])
            return;
        // array to contain sorted elements
        int[] aa = new int[q-p];
        // iteration variables
        int i = p, j = m, k = 0;
        while (i < m && j < q) {
            if (a[i] < a[j])
                aa[k++] = a[i++];
            else
                aa[k++] = a[j++];
        }
        // check if need to shift elements
        if (i < m)
            System.arraycopy(a, i, a, p+k, m-i);
        // copy sorted array into destination array
        System.arraycopy(aa, 0, a, p, k);
    }
}