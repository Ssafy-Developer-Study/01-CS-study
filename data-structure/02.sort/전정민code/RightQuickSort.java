public class RightQuickSort {
    
    public static void sort(int[] a) {
        r_pivot_sort(a, 0, a.length - 1);
    }

    private static void r_pivot_sort(int[] a, int lo, int hi) {

        if(lo>=hi) return;

        int pivot = partition(a, lo, hi);

        r_pivot_sort(a, lo, pivot-1);
        r_pivot_sort(a, pivot+1, hi);
    }

    private static int partition(int[] a, int left, int right) {

        int lo = left;
        int hi = right;
        int pivot = a[right];

        while(lo < hi) {

            while(pivot <= a[hi] && lo < hi) {
                hi--;
            }

            while(pivot > a[lo] && lo < hi) {
                lo++;
            }

            swap(a, lo, hi);
        }
        swap(a, right, hi);

        return hi;
    }

    private static void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}
